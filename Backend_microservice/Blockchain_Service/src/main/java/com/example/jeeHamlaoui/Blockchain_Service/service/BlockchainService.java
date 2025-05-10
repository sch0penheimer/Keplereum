package com.example.jeeHamlaoui.Blockchain_Service.service;

import com.example.jeeHamlaoui.Blockchain_Service.web3j.Web3JSingleton;
import com.example.jeeHamlaoui.Blockchain_Service.contracts.SatelliteSystem;
import org.springframework.stereotype.Service;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.tx.RawTransactionManager;
import org.web3j.crypto.Credentials;
import org.web3j.tx.TransactionManager;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class BlockchainService {
    private final Web3JSingleton web3JSingleton;

    public BlockchainService(Web3JSingleton web3JSingleton) {
        this.web3JSingleton = web3JSingleton;
    }

    // Get all mempool transactions
    public List<EthBlock.TransactionResult> getPendingTransactions() throws Exception {
        EthBlock pendingBlock = web3JSingleton.getWeb3jInstance()
                .ethGetBlockByNumber(DefaultBlockParameterName.PENDING, true)
                .send();

        if (pendingBlock.getBlock() != null) {
            return pendingBlock.getBlock().getTransactions();
        } else {
            throw new RuntimeException("No pending transactions found.");
        }
    }

    // Get a block by number
    public EthBlock getBlockByNumber(BigInteger blockNumber) throws Exception {
        return web3JSingleton.getWeb3jInstance()
                .ethGetBlockByNumber(DefaultBlockParameter.valueOf(blockNumber), true)
                .send();
    }

    // Get Validator Queue
    public List<String> getValidatorQueue() throws Exception {
        Request<?, EthAccounts> request = web3JSingleton.getWeb3jInstance().ethAccounts();
        ((Request<?, ?>) request).setMethod("clique_getSigners");
        request.setParams(Collections.emptyList());

        EthAccounts response = request.send();
        return response.getAccounts();
    }

    // Publish a transaction
    public String publishTransaction(String privateKey, String toAddress, BigInteger value,
                                     BigInteger gasPrice, BigInteger gasLimit) throws Exception {

        // Get the chain ID dynamically (or hardcode it if you know the network)
        long chainId = 12345;

        // 3. Create credentials from the private key
        Credentials credentials = Credentials.create(privateKey);

        // 3. Build RawTransaction (like web3.js)
        BigInteger nonce = web3JSingleton.getWeb3jInstance()
                .ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.PENDING)
                .send()
                .getTransactionCount();

        RawTransaction rawTx = RawTransaction.createEtherTransaction(
                nonce,
                gasPrice,
                gasLimit,
                toAddress,
                value
        );

        // 4. Sign Offline (like web3.eth.accounts.signTransaction)
        byte[] signedMessage = TransactionEncoder.signMessage(rawTx, chainId, credentials);
        String hexValue = Numeric.toHexString(signedMessage);

        // 5. Send Signed Payload (like web3.eth.sendSignedTransaction)
        EthSendTransaction response = web3JSingleton.getWeb3jInstance()
                .ethSendRawTransaction(hexValue)
                .send();

        // 6. Error Handling (same as before)
        if (response.hasError()) {
            throw new RuntimeException("Transaction failed: " + response.getError().getMessage());
        }

        return response.getTransactionHash();
    }

    // Get a transaction by hash (with event details if applicable)
    public Object getTransactionByHash(String transactionHash) throws Exception {
        // Fetch the transaction details
        EthTransaction transaction = web3JSingleton.getWeb3jInstance()
                .ethGetTransactionByHash(transactionHash)
                .send();

        if (!transaction.getTransaction().isPresent()) {
            throw new RuntimeException("Transaction not found.");
        }

        // Fetch the transaction receipt to check for events
        EthGetTransactionReceipt receiptResponse = web3JSingleton.getWeb3jInstance()
                .ethGetTransactionReceipt(transactionHash)
                .send();

        if (!receiptResponse.getTransactionReceipt().isPresent()) {
            return transaction.getTransaction().get(); // Return transaction details if no receipt
        }

        var receipt = receiptResponse.getTransactionReceipt().get();

        // Check for specific events (AlertConfirmed, ActionTriggered)
        List<SatelliteSystem.AlertConfirmedEventResponse> alertEvents = SatelliteSystem.getAlertConfirmedEvents(receipt);
        List<SatelliteSystem.ActionTriggeredEventResponse> actionEvents = SatelliteSystem.getActionTriggeredEvents(receipt);

        // Return transaction details along with events
        return Map.of(
                "transaction", transaction.getTransaction().get(),
                "alertEvents", alertEvents,
                "actionEvents", actionEvents
        );
    }
}