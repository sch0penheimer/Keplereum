package com.example.jeeHamlaoui.Blockchain_Service.service;

import com.example.jeeHamlaoui.Blockchain_Service.web3j.Web3JSingleton;
import com.example.jeeHamlaoui.Blockchain_Service.contracts.SatelliteSystem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;

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
        SatelliteSystem contract = SatelliteSystem.load(contractAddress, web3JSingleton.getWeb3jInstance(), credentials, new DefaultGasProvider());
        return contract.submitAlert(alertType, latitude, longitude).send().getTransactionHash();
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

    // Get alert count
    public BigInteger getAlertCount() throws Exception {
        SatelliteSystem contract = SatelliteSystem.load(contractAddress, web3JSingleton.getWeb3jInstance(), (Credentials) null, new DefaultGasProvider());
        return contract.getAlertCount().send();
    }
}