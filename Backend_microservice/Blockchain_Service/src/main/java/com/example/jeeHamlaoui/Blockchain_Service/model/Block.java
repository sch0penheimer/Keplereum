package com.example.jeeHamlaoui.Blockchain_Service.model;

import jakarta.persistence.*;


import java.time.Instant;
import java.util.ArrayList;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "blocks")
public class Block {
    @Id
    @Column(name = "height", nullable = false, unique = true)
    private Long height;

    @Column(name = "previous_block_hash", nullable = false)
    private String previousBlockHash;

    @Column(name = "current_block_hash", nullable = false, unique = true)
    private String currentBlockHash;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "block_weight", nullable = false)
    private Double blockWeight;

    @Column(name = "transaction_root", nullable = false)
    private String transactionRoot;

    @Column(name = "sha3_uncles", nullable = false)
    private String sha3Uncles;

    @Column(name = "block_size", nullable = false)
    private String blockSize;

    // One Block has many Transactions (1:N)
    @OneToMany(mappedBy = "block", cascade = CascadeType.ALL)
    private List<BlockTransaction> transactions = new ArrayList<>();


    // Getters and setters

}