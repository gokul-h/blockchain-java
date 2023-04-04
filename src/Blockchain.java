import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Objects;

public class Blockchain {
    public static ArrayList<Block> blockchain = new ArrayList<>();

    static void blockchain_main() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, InvalidKeyException {
        // Transaction 1

        // ALice create and signs a transaction
        Block transaction = new Block("Send 10 Bitcoins to Bob", "0");
        ECDSA Alice = new ECDSA();
        System.out.println("Transaction is created!");
        Alice.sign(transaction);
        System.out.println("Transaction is signed by Alice!");

        // Bob has to verify signature followed by data
        ECDSAVerify Bob = new ECDSAVerify();
        if (Bob.verify(transaction)) {
            System.out.println("Transaction is verified by Bob!");
            if (Objects.equals(transaction.hash, SHA.sha256(transaction.previousHash + transaction.timeStamp + transaction.data))) {
                System.out.println("Hash of message is verified!");
                blockchain.add(transaction);
                System.out.println("Transaction added to blockchain!");
            } else {
                System.out.println("Message is tampered with!");
            }
        } else {
            System.out.println("Transaction verification by Bob failed!");
        }

        System.out.println("\n");
        // Transaction 2
        Block transaction2 = new Block("Send 1 Bitcoins to Scrooge", transaction);
        System.out.println("transaction2 is created!");
        Alice.sign(transaction2);
        System.out.println("transaction2 is signed by Alice!");

        // Bob has to verify signature followed by data
        if (Bob.verify(transaction2)) {
            System.out.println("Transaction2 is verified!");
            if (Objects.equals(transaction2.hash, SHA.sha256(transaction2.previousHash + transaction2.timeStamp + transaction2.data))) {
                System.out.println("Hash of message is verified!");
                blockchain.add(transaction2);
                System.out.println("Transaction2 added to blockchain!");
            } else {
                System.out.println("Message is tampered with!");
            }
        } else {
            System.out.println("Transaction2 verification failed!");
        }
        System.out.println("\n");
        // Transaction 3
        Block transaction3 = new Block("Send 5 Bitcoins to myself", transaction);
        System.out.println("transaction3 is created!");
        Alice.sign(transaction3);
        System.out.println("transaction3 is signed by Alice!");

        // Bob has to verify signature followed by data
        if (Bob.verify(transaction3)) {
            System.out.println("Transaction3 is verified!");
            if (Objects.equals(transaction3.hash, SHA.sha256(transaction3.previousHash + transaction3.timeStamp + transaction3.data))) {
                System.out.println("Hash of message is verified!");
                blockchain.add(transaction3);
                System.out.println("Transaction3 added to blockchain!");
            } else {
                System.out.println("Message is tampered with!");
            }
        } else {
            System.out.println("Transaction3 verification failed!");
        }

        for(Block b : blockchain){
            System.out.println(b.toString());
        }

        System.out.println("\nProgram end!");
    }


}
