import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

public class Blockchain {
    public static ArrayList<Block> blockchain = new ArrayList<>();

    static void blockchain_main() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, InvalidKeyException {
        // ALice create and signs a transaction
        Block transaction = new Block("First block", "0");
        ECDSA Alice = new ECDSA();
        System.out.println("Transaction is created!");
        Alice.sign(transaction);
        System.out.println("Transaction is signed by Alice!");

        // Bob has to verify
        ECDSAVerify Bob = new ECDSAVerify();
        if (Bob.verify(transaction)) {
            System.out.println("Transaction is verified by Bob!");
            blockchain.add(transaction);
            System.out.println("Transaction added to blockchain!");
        }else {
            System.out.println("Transaction verification by Bob failed!");
        }

        // Check if chain is valid
        if(isChainValid()){
            System.out.println("Blockchain is valid!");
        }
        else{
            System.out.println("Blockchain is Invalid!");
        }
    }

    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);

            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Block hash is not matching!");
                return false;
            }

            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("Previous Hashes are not matching!");
                return false;
            }


        }
        return true;
    }
}
