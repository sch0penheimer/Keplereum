package com.example.jeeHamlaoui.Blockchain_Service.service;

import com.example.jeeHamlaoui.Blockchain_Service.web3j.Web3JSingleton;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.tx.RawTransactionManager;
import org.web3j.crypto.Credentials;
import org.web3j.tx.TransactionManager;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

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
        request.setMethod("clique_getSigners");
        request.setParams(Collections.emptyList());

        EthAccounts response = request.send();
        return response.getAccounts();
    }
}