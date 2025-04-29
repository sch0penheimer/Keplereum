package com.example.jeeHamlaoui.Blockchain_Service.model;


import jakarta.persistence.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "network_nodes")
public class NetworkNode {
    @Id
    @Column(name = "public_key", nullable = false, unique = true)
    private String publicKey;

    @Column(name = "authority_status", nullable = false)
    private boolean authorityStatus;

    @Column(name = "blocks_validated")
    private Integer blocksValidated;

    @Column(name = "last_active", nullable = false)
    private Instant lastActive;

    // Owning side of the Satellite relationship (1:1)
    @OneToOne(mappedBy = "networkNode")
    private Satellite satellite;

    // Transactions sent by this node (One-to-Many)
    @OneToMany(mappedBy = "sender")
    private List<BlockTransaction> sentTransactions = new ArrayList<>();

    // Transactions received by this node (One-to-Many)
    @OneToMany(mappedBy = "receiver")
    private List<BlockTransaction> receivedTransactions = new ArrayList<>();

    // Getters and setters


    public NetworkNode() {
    }

    public NetworkNode(String publicKey, List<BlockTransaction> receivedTransactions, List<BlockTransaction> sentTransactions, Satellite satellite, Instant lastActive, Integer blocksValidated, boolean authorityStatus) {
        this.publicKey = publicKey;
        this.receivedTransactions = receivedTransactions;
        this.sentTransactions = sentTransactions;
        this.satellite = satellite;
        this.lastActive = lastActive;
        this.blocksValidated = blocksValidated;
        this.authorityStatus = authorityStatus;
    }

    public List<BlockTransaction> getReceivedTransactions() {
        return receivedTransactions;
    }

    public void setReceivedTransactions(List<BlockTransaction> receivedTransactions) {
        this.receivedTransactions = receivedTransactions;
    }

    public List<BlockTransaction> getSentTransactions() {
        return sentTransactions;
    }

    public void setSentTransactions(List<BlockTransaction> sentTransactions) {
        this.sentTransactions = sentTransactions;
    }

    public Satellite getSatellite() {
        return satellite;
    }

    public void setSatellite(Satellite satellite) {
        this.satellite = satellite;
    }

    public Instant getLastActive() {
        return lastActive;
    }

    public void setLastActive(Instant lastActive) {
        this.lastActive = lastActive;
    }

    public Integer getBlocksValidated() {
        return blocksValidated;
    }

    public void setBlocksValidated(Integer blocksValidated) {
        this.blocksValidated = blocksValidated;
    }

    public boolean isAuthorityStatus() {
        return authorityStatus;
    }

    public void setAuthorityStatus(boolean authorityStatus) {
        this.authorityStatus = authorityStatus;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
