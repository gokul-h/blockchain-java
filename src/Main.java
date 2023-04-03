/**
 * What are we trying to achieve here?
 * Algorithm:
 * 1. Public and private keys are generated for a user.
 * 2. User creates a transaction and  signs it using their private key.
 * 3. The signature along with the transaction is sent to the network.
 * 4. Network verifies the signature using sender's public key to decrypt it.
 * 5. If signature is valid it's added to blockchain
 * (here a confirmation message is printed)
 */
public class Main {
    public static void main(String[] args) {
        Blockchain.blockchain_main();
    }
}
