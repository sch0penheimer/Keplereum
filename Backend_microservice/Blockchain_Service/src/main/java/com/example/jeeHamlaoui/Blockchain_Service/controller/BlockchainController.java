package com.example.jeeHamlaoui.Blockchain_Service.controller;

import com.example.jeeHamlaoui.Blockchain_Service.service.BlockchainService;
import com.example.jeeHamlaoui.Blockchain_Service.service.SmartContractService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api/v1/blockchain")
public class BlockchainController {
    private final BlockchainService blockchainService;
    private final SmartContractService smartContractService;

    public BlockchainController(BlockchainService blockchainService, SmartContractService smartContractService) {
        this.blockchainService = blockchainService;
        this.smartContractService = smartContractService;
    }

    // Blockchain CRUD Endpoints
    @GetMapping("/mempool")
    public ResponseEntity<List<?>> getPendingTransactions() {
        try {
            return ResponseEntity.ok(blockchainService.getPendingTransactions());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(List.of(e.getMessage()));
        }
    }

    @GetMapping("/block/{number}")
    public ResponseEntity<?> getBlockByNumber(@PathVariable BigInteger number) {
        try {
            return ResponseEntity.ok(blockchainService.getBlockByNumber(number));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/validators/queue")
    public ResponseEntity<List<String>> getValidatorQueue() {
        try {
            List<String> validatorQueue = blockchainService.getValidatorQueue();
            return ResponseEntity.ok(validatorQueue);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(List.of(e.getMessage()));
        }
    }

    // POST: Submit a transaction
    @PostMapping("/transaction")
    public ResponseEntity<String> submitTransaction(@RequestParam String privateKey, @RequestParam String toAddress,
                                                     @RequestParam BigInteger value, @RequestParam BigInteger gasPrice,
                                                     @RequestParam BigInteger gasLimit) {
        try {
            String transactionHash = blockchainService.publishTransaction(privateKey, toAddress, value, gasPrice, gasLimit);
            return ResponseEntity.ok(transactionHash);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    // GET: Get a transaction by hash (with event details if applicable)
    @GetMapping("/transaction")
    public ResponseEntity<?> getTransactionByHash(@RequestParam String hash) {
        try {
            var transactionDetails = blockchainService.getTransactionByHash(hash);
            return ResponseEntity.ok(transactionDetails);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    //**************************************** Smart Contracts ***********************************************//
    //********************************************************************************************************//

    // Smart Contract CRUD Endpoints
    @PostMapping("/contract/alert")
    public ResponseEntity<String> submitAlert(@RequestParam String privateKey, @RequestParam String alertType,
                                               @RequestParam BigInteger latitude, @RequestParam BigInteger longitude) {
        try {
            return ResponseEntity.ok(smartContractService.submitAlert(privateKey, alertType, latitude, longitude));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        } 
    }

    @PostMapping("/contract/alert/confirm")
    public ResponseEntity<String> confirmAlert(@RequestParam String privateKey, @RequestParam byte[] alertId) {
        try {
            smartContractService.confirmAlert(privateKey, alertId);
            return ResponseEntity.ok("Alert confirmed successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/contract/action")
    public ResponseEntity<String> triggerAction(@RequestParam String privateKey, @RequestParam String satellite,
                                                 @RequestParam BigInteger action, @RequestParam byte[] alertId) {
        try {
            smartContractService.triggerAction(privateKey, satellite, action, alertId);
            return ResponseEntity.ok("Action triggered successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    // GET: All currently submitted alerts with confirmations
    @GetMapping("/alerts")
    public ResponseEntity<?> getAllAlertsWithConfirmations() {
        try {
            var alerts = smartContractService.getAllAlertsWithConfirmations();
            return ResponseEntity.ok(alerts);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    // GET: Alert fetch by ID, with confirmations
    @GetMapping("/alert/{id}")
    public ResponseEntity<?> getAlertById(@PathVariable String id) {
        try {
            var alertDetails = smartContractService.getAlertById(id);
            return ResponseEntity.ok(alertDetails);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}