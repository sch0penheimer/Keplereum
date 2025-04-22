package main

import (
	"context"
	"crypto/ecdsa"
	"fmt"
	"log"
	"math/big"
	"os"

	"github.com/ethereum/go-ethereum/accounts/abi/bind"
	"github.com/ethereum/go-ethereum/accounts/keystore"
	"github.com/ethereum/go-ethereum/crypto"
	"github.com/ethereum/go-ethereum/ethclient"

	"github.com/mdn753/development-platform-api-tologists/build/satellite" // Correct import path
)

func main() {
	client, err := ethclient.Dial("http://localhost:8545")
	if err != nil {
		log.Fatal(err)
	}

	// Load the keystore file
	keyJson, err := os.ReadFile("C:\\Users\\Legion\\development-platform-api-tologists\\Blockchain\\geth-nodes\\node1\\keystore\\UTC--2025-03-20T00-27-07.449348769Z--e6427a00f96f7ca5688f1de97ba66c21daf77afe") // replace with actual filename
	if err != nil {
		log.Fatal("Failed to read keystore file:", err)
	}

	// Decrypt the keystore to get private key
	password := "123456789"
	key, err := keystore.DecryptKey(keyJson, password)
	if err != nil {
		log.Fatal("Failed to decrypt key:", err)
	}
	privateKey := key.PrivateKey

	publicKey := privateKey.Public()
	publicKeyECDSA, ok := publicKey.(*ecdsa.PublicKey)
	if !ok {
		log.Fatal("cannot assert type: publicKey is not of type *ecdsa.PublicKey")
	}

	fromAddress := crypto.PubkeyToAddress(*publicKeyECDSA)

	nonce, err := client.PendingNonceAt(context.Background(), fromAddress)
	if err != nil {
		log.Fatal(err)
	}

	gasPrice, err := client.SuggestGasPrice(context.Background())
	if err != nil {
		log.Fatal(err)
	}

	chainID := big.NewInt(12345)
	auth, err := bind.NewKeyedTransactorWithChainID(privateKey, chainID)
	if err != nil {
		log.Fatal(err)
	}

	auth.Nonce = big.NewInt(int64(nonce))
	auth.Value = big.NewInt(0)      // in wei
	auth.GasLimit = uint64(5000000) // in units
	auth.GasPrice = gasPrice

	// Correct function call here: DeploySatellite
	address, tx, instance, err := satellite.DeploySatellite(auth, client)
	if err != nil {
		log.Fatal(err)
	}

	fmt.Printf("✅ Satellite contract deployed at: %s\n", address.Hex())
	fmt.Printf("📦 Transaction Hash: %s\n", tx.Hash().Hex())

	_ = instance // Use instance to interact with your contract
}
