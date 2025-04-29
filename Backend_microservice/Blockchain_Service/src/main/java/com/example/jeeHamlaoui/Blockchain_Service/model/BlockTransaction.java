package com.example.jeeHamlaoui.Blockchain_Service.model;

import com.example.jeeHamlaoui.Blockchain_Service.model.enumerate.TransactionStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "block_transactions")
public class BlockTransaction {
    @Id
    @Column(name = "hash", nullable = false, unique = true)
    private String hash;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "fee")
    private Double fee;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TransactionStatus status;

    @Column(name = "gas_price")
    private Double gasPrice;

    @Column(name = "gas_limit")
    private Double gasLimit;

    @Column(name = "gas_used")
    private Double gasUsed;

    @Column(name = "block_number")
    private Long blockNumber;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    // Many-to-One: Transaction belongs to a Block
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "block_height", referencedColumnName = "height")
    private Block block;

    // Many-to-One: Sender (NetworkNode)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_public_key", referencedColumnName = "public_key")
    private NetworkNode sender;

    // Many-to-One: Receiver (NetworkNode)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_public_key", referencedColumnName = "public_key")
    private NetworkNode receiver;

    // One-to-One: AbstractTransactionType (Alert, Confirmation, ValidatorAction)
    @OneToOne(mappedBy = "blockTransaction", cascade = CascadeType.ALL)
    private AbstractTransactionType transactionType;

    // Getters and setters
    public void setTransactionType(AbstractTransactionType transactionType) {
        if (this.transactionType != null) {
            this.transactionType.setBlockTransaction(null);  // Unlink old reference
        }
        this.transactionType = transactionType;
        if (transactionType != null && transactionType.getBlockTransaction() != this) {
            transactionType.setBlockTransaction(this);  // Link new reference
        }
    }


    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public AbstractTransactionType getTransactionType() {
        return transactionType;
    }

    public NetworkNode getReceiver() {
        return receiver;
    }

    public void setReceiver(NetworkNode receiver) {
        this.receiver = receiver;
    }

    public NetworkNode getSender() {
        return sender;
    }

    public void setSender(NetworkNode sender) {
        this.sender = sender;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Long getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(Long blockNumber) {
        this.blockNumber = blockNumber;
    }

    public Double getGasUsed() {
        return gasUsed;
    }

    public void setGasUsed(Double gasUsed) {
        this.gasUsed = gasUsed;
    }

    public Double getGasLimit() {
        return gasLimit;
    }

    public void setGasLimit(Double gasLimit) {
        this.gasLimit = gasLimit;
    }

    public Double getGasPrice() {
        return gasPrice;
    }

    public void setGasPrice(Double gasPrice) {
        this.gasPrice = gasPrice;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}