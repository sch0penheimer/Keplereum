package com.example.jeeHamlaoui.Blockchain_Service.service;

import com.example.jeeHamlaoui.Blockchain_Service.web3j.Web3JSingleton;
import com.example.jeeHamlaoui.Blockchain_Service.contracts.SatelliteSystem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SmartContractService {
    private final Web3JSingleton web3JSingleton;
    private final String contractAddress;

    public SmartContractService(Web3JSingleton web3JSingleton, @Value("${blockchain.contract-address}") String contractAddress) {
        this.web3JSingleton = web3JSingleton;
        this.contractAddress = contractAddress;
    }

    // Submit an alert
    public String submitAlert(String privateKey, String alertType, BigInteger latitude, BigInteger longitude) throws Exception {
        Credentials credentials = Credentials.create(privateKey);

        // Fetch the chain ID from the Ethereum network
        BigInteger chainId = web3JSingleton.getWeb3jInstance().ethChainId().send().getChainId();

        // Create a transaction manager with the chainId to enforce EIP-155 replay protection
        TransactionManager transactionManager = new RawTransactionManager(web3JSingleton.getWeb3jInstance(), credentials, chainId.longValue());

        // Now, load the contract with the new transaction manager that includes the chainId
        SatelliteSystem contractWithChainId = SatelliteSystem.load(contractAddress, web3JSingleton.getWeb3jInstance(), transactionManager, new DefaultGasProvider());

        // Send the transaction
        TransactionReceipt transactionReceipt = contractWithChainId.submitAlert(alertType, latitude, longitude).send();

        // Return the transaction hash
        return transactionReceipt.getTransactionHash();
    }

    // Confirm an alert
    public void confirmAlert(String privateKey, byte[] alertId) throws Exception {
        Credentials credentials = Credentials.create(privateKey);
        SatelliteSystem contract = SatelliteSystem.load(contractAddress, web3JSingleton.getWeb3jInstance(), credentials, new DefaultGasProvider());
        contract.confirmAlert(alertId).send();
    }

    // Trigger an action
    public void triggerAction(String privateKey, String satellite, BigInteger action, byte[] alertId) throws Exception {
        Credentials credentials = Credentials.create(privateKey);
        SatelliteSystem contract = SatelliteSystem.load(contractAddress, web3JSingleton.getWeb3jInstance(), credentials, new DefaultGasProvider());
        contract.triggerAction(satellite, action, alertId).send();
    }

    // Get all alerts with confirmations
    public List<Map<String, Object>> getAllAlertsWithConfirmations() throws Exception {
        SatelliteSystem contract = SatelliteSystem.load(contractAddress, web3JSingleton.getWeb3jInstance(), (Credentials) null, new DefaultGasProvider());

        BigInteger alertCount = contract.getAlertCount().send();
        List<Map<String, Object>> alerts = new ArrayList<>();

        for (BigInteger i = BigInteger.ZERO; i.compareTo(alertCount) < 0; i = i.add(BigInteger.ONE)) {
            byte[] alertId = contract.alertIds(i).send();
            List<String> confirmers = contract.getAlertConfirmers(alertId).send();

            alerts.add(Map.of(
                    "alertId", alertId,
                    "confirmers", confirmers
            ));
        }

        return alerts;
    }

    // Get a specific alert by ID
    public Map<String, Object> getAlertById(String alertId) throws Exception {
        SatelliteSystem contract = SatelliteSystem.load(contractAddress, web3JSingleton.getWeb3jInstance(), (Credentials) null, new DefaultGasProvider());

        List<String> confirmers = contract.getAlertConfirmers(alertId.getBytes()).send();

        return Map.of(
                "alertId", alertId,
                "confirmers", confirmers
        );
    }
}