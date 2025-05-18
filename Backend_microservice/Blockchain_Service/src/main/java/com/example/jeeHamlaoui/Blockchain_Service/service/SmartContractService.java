package com.example.jeeHamlaoui.Blockchain_Service.service;

import com.example.jeeHamlaoui.Blockchain_Service.utils.UtilityClass;
import com.example.jeeHamlaoui.Blockchain_Service.web3j.Web3JSingleton;
import com.example.jeeHamlaoui.Blockchain_Service.contracts.SatelliteSystem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.abi.EventEncoder;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.EthLog;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tuples.generated.Tuple7;

import java.math.BigInteger;
import java.util.*;

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
    public Map<String, String> confirmAlert(String privateKey, byte[] alertId) throws Exception {
        // Ensure the alertId is padded to 32 bytes
        if (alertId.length != 32) {
            throw new IllegalArgumentException("alertId must be exactly 32 bytes long");
        }

        Credentials credentials = Credentials.create(privateKey);

        // Fetch the chain ID from the Ethereum network
        BigInteger chainId = web3JSingleton.getWeb3jInstance().ethChainId().send().getChainId();

        // Create a transaction manager with the chain ID
        TransactionManager transactionManager = new RawTransactionManager(
                web3JSingleton.getWeb3jInstance(),
                credentials,
                chainId.longValue()
        );

        // Load the contract with the transaction manager
        SatelliteSystem contract = SatelliteSystem.load(contractAddress, web3JSingleton.getWeb3jInstance(), transactionManager, new DefaultGasProvider());

        // Send the transaction
        TransactionReceipt receipt = contract.confirmAlert(alertId).send();

        // Return the transaction hash and a success message
        return Map.of(
                "transactionHash", receipt.getTransactionHash(),
                "message", "Alert confirmed successfully"
        );
    }

    // Trigger an action
    public Map<String, String> triggerAction(String privateKey, String satellite, BigInteger action, byte[] alertId) throws Exception {
        Credentials credentials = Credentials.create(privateKey);

        // Fetch the chain ID from the Ethereum network
        BigInteger chainId = web3JSingleton.getWeb3jInstance().ethChainId().send().getChainId();

        // Create a transaction manager with the chain ID
        TransactionManager transactionManager = new RawTransactionManager(
                web3JSingleton.getWeb3jInstance(),
                credentials,
                chainId.longValue()
        );

        // Load the contract with the transaction manager
        SatelliteSystem contract = SatelliteSystem.load(contractAddress, web3JSingleton.getWeb3jInstance(), transactionManager, new DefaultGasProvider());

        // Send the transaction
        TransactionReceipt receipt = contract.triggerAction(satellite, action, alertId).send();

        // Return the transaction hash and a success message
        return Map.of(
                "transactionHash", receipt.getTransactionHash(),
                "message", "Action triggered successfully"
        );
    }

    // Get all alerts
    public List<Map<String, Object>> getAllGlobalAlerts() throws Exception {
        String defaultPrivateKey = "0xef43f9f547e1a70532ba05824bda6e90d58f1d8c44aeb75ec1354839ad4b7738";
        Credentials credentials = Credentials.create(defaultPrivateKey);
        SatelliteSystem contract = SatelliteSystem.load(contractAddress, web3JSingleton.getWeb3jInstance(), credentials, new DefaultGasProvider());

        // Fetch the latest block number
        BigInteger latestBlock = web3JSingleton.getWeb3jInstance()
                .ethBlockNumber()
                .send()
                .getBlockNumber();

        // Set up a filter to fetch all AlertSubmitted events
        EthFilter submittedFilter = new EthFilter(
                DefaultBlockParameter.valueOf(BigInteger.ZERO),
                DefaultBlockParameter.valueOf(latestBlock),
                contractAddress
        );
        submittedFilter.addSingleTopic(EventEncoder.encode(SatelliteSystem.ALERTSUBMITTED_EVENT));

        // Fetch logs for AlertSubmitted events
        List<EthLog.LogResult> submittedLogResults = web3JSingleton.getWeb3jInstance()
                .ethGetLogs(submittedFilter)
                .send()
                .getLogs();

        // Set up a filter to fetch all AlertConfirmed events
        EthFilter confirmedFilter = new EthFilter(
                DefaultBlockParameter.valueOf(BigInteger.ZERO),
                DefaultBlockParameter.valueOf(latestBlock),
                contractAddress
        );
        confirmedFilter.addSingleTopic(EventEncoder.encode(SatelliteSystem.ALERTCONFIRMED_EVENT));

        // Fetch logs for AlertConfirmed events
        List<EthLog.LogResult> confirmedLogResults = web3JSingleton.getWeb3jInstance()
                .ethGetLogs(confirmedFilter)
                .send()
                .getLogs();

        // Map to store confirmations by alertId (using hexadecimal format)
        Map<String, List<Map<String, Object>>> confirmationsMap = new HashMap<>();

        // Process AlertConfirmed logs to build the confirmations map
        for (EthLog.LogResult logResult : confirmedLogResults) {
            EthLog.LogObject log = (EthLog.LogObject) logResult.get();
            List<String> topics = log.getTopics();

            // Extract alertId and confirmer from the log
            String alertId = topics.get(1).substring(2); // Remove "0x" prefix
            String confirmer = "0x" + topics.get(2).substring(26); // Extract the last 20 bytes for the address

            // Add the confirmation to the map
            confirmationsMap.computeIfAbsent(alertId, k -> new ArrayList<>()).add(Map.of(
                    "publicAddress", confirmer,
                    "confirmationHash", log.getTransactionHash()
            ));
        }

        // Process AlertSubmitted logs to build the global alerts list
        List<Map<String, Object>> globalAlerts = new ArrayList<>();
        for (EthLog.LogResult logResult : submittedLogResults) {
            EthLog.LogObject log = (EthLog.LogObject) logResult.get();
            SatelliteSystem.AlertSubmittedEventResponse event = contract.getAlertSubmittedEventFromLog(log);

            // Normalize alertId to hexadecimal format
            String normalizedAlertId = UtilityClass.bytesToHex(event.alertId);

            // Add the alert to the global list
            globalAlerts.add(Map.of(
                    "alertId", normalizedAlertId,
                    "alertType", event.alertType,
                    "latitude", event.latitude,
                    "longitude", event.longitude,
                    "transactionHash", log.getTransactionHash(),
                    "confirmations", confirmationsMap.getOrDefault(normalizedAlertId, new ArrayList<>())
            ));
        }

        return globalAlerts;
    }

    // Get a specific alert by ID
    public Map<String, Object> getAlertById(String alertIdHex) throws Exception {
        byte[] alertIdBytes = UtilityClass.hexToBytes(alertIdHex);
        String defaultPrivateKey = "0xef43f9f547e1a70532ba05824bda6e90d58f1d8c44aeb75ec1354839ad4b7738";
        Credentials credentials = Credentials.create(defaultPrivateKey);

        // Load the contract
        SatelliteSystem contract = SatelliteSystem.load(contractAddress, web3JSingleton.getWeb3jInstance(), credentials, new DefaultGasProvider());

        // Fetch the confirmers for the alert
        List<String> confirmers = contract.getAlertConfirmers(alertIdBytes).send();

        // Return the alert details and confirmers
        return Map.of(
                "alertId", alertIdHex,
                "confirmers", confirmers
        );
    }

}