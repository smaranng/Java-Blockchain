# Java Blockchain Samples

This repository is a small collection of Java programs that demonstrate the core cryptographic building blocks commonly used in blockchain systems. The focus is on hashing, digital signatures, key generation, and a simple proof-of-work style block structure.

The repository also contains a few hello-world style files, but this README documents only the blockchain and cryptography implementations.

## What This Repo Demonstrates

- SHA-256 hashing for block and message fingerprints
- RSA encryption and RSA digital signatures
- ECDSA digital signatures using elliptic-curve keys
- A simple block model with previous-hash linking and mining via nonce search

## Project Structure

| File | Purpose |
| --- | --- |
| `SimpleBlockchain.java` | Defines the `Block` model and a `SimpleBlockchain` container for a chain of blocks. It includes SHA-256 hashing and a basic mining loop that searches for hashes with a leading-zero difficulty target. |
| `BlockchainWallet.java` | Demonstrates wallet-style balances with ECDSA signing and signature verification on a transaction string. |
| `BlockChainWalletECDSA.java` | Same ECDSA wallet demonstration as above, with a separate class/file name. |
| `BlockChainWalletRSA.java` | Demonstrates wallet-style balances with RSA signing and signature verification on a transaction string. |
| `digital_signature.java` | Minimal RSA digital signature example that signs a transaction message and verifies it with the public key. |
| `sha256.java` | Standalone SHA-256 helper that hashes a sample string. |
| `SimpleRSA.java` | Demonstrates RSA key generation, encryption with the public key, and decryption with the private key. |

## Core Implementation Notes

### 1. Block and Chain Model

`SimpleBlockchain.java` defines a `Block` class with these fields:

- `hash` - the current block hash
- `previoushash` - hash of the previous block in the chain
- `data` - payload stored in the block
- `timestamp` - creation time
- `nonce` - counter used for mining

The block hash is calculated from:

- previous block hash
- timestamp
- nonce
- block data

The `mineblock(int diff)` method repeatedly increments the nonce until the hash starts with a target number of leading zeroes. This is a simple proof-of-work style mechanism.

Important note: `SimpleBlockchain` is currently a scaffold. It defines the chain container and block logic, but the `main` method does not yet add sample blocks or print a full chain.

### 2. RSA Encryption

`SimpleRSA.java` shows classic RSA public-key cryptography:

- generate a 2048-bit RSA key pair
- encrypt plaintext with the public key
- decrypt ciphertext with the private key

This is a direct encryption/decryption demo, separate from signatures.

### 3. RSA Digital Signatures

`digital_signature.java` and `BlockChainWalletRSA.java` demonstrate signing with `SHA256withRSA`:

- generate an RSA key pair
- sign a transaction string with the private key
- verify the signature with the public key

If verification passes, the sample updates balances to simulate a successful transfer.

### 4. ECDSA Wallet Demo

`BlockchainWallet.java` and `BlockChainWalletECDSA.java` demonstrate the same flow using elliptic-curve cryptography:

- generate an EC key pair on curve `secp256r1`
- sign a transaction string with `SHA256withECDSA`
- verify the signature with the public key

This shows a blockchain-friendly signature scheme that is commonly used in modern wallets.

### 5. SHA-256 Utility

`sha256.java` provides a reusable SHA-256 hashing helper and prints the hash of a sample string.

## Cryptography Used

- SHA-256 for hashing
- RSA 2048-bit keys for encryption and signatures
- ECDSA with curve `secp256r1` for wallet signing

## How To Compile

All Java files declare the package `Blockchain`, so compile from the `Java-Blockchain` folder and direct the output to a separate build directory:

```bash
javac -d out *.java
```

This places the compiled classes under `out/Blockchain`.

## How To Run

Run the class you want using its fully qualified package name:

```bash
java -cp out Blockchain.SimpleRSA
java -cp out Blockchain.sha256
java -cp out Blockchain.digital_signature
java -cp out Blockchain.BlockChainWalletRSA
java -cp out Blockchain.BlockchainWallet
java -cp out Blockchain.BlockchainWalletECDSA
```

For the blockchain scaffold:

```bash
java -cp out Blockchain.SimpleBlockchain
```

## Expected Output

- RSA and ECDSA wallet demos print balances before and after a verified transaction.
- `SimpleRSA` prints the original message, encrypted text, and decrypted text.
- `digital_signature` prints the transaction, signature, and verification result.
- `sha256` prints the original string and the SHA-256 hash.
- `SimpleBlockchain` currently defines the data model and mining logic, but does not yet execute a sample chain in `main`.

## Limitations And Current Gaps

- `SimpleBlockchain` does not yet contain end-to-end chain creation, validation, or persistence logic.
- The wallet examples use fixed sample balances and a single transaction flow for demonstration purposes.
- These programs are educational examples, not production-ready wallet or consensus implementations.

## Suggested Next Steps

- Add a `createGenesisBlock()` flow and append blocks into `SimpleBlockchain`.
- Add chain validation that checks `previoushash` links and recalculates block hashes.
- Add transaction objects instead of raw strings for wallet demos.
- Move the demos into a cleaner package structure if the project grows.

## License

No license file is currently present in the repository. Add one if you plan to share or reuse this code publicly.