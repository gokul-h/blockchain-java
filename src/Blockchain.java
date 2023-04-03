import java.util.ArrayList;

public class Blockchain {
    public static ArrayList<Block> blockchain = new ArrayList<Block>();

     static void blockchain_main() {
        blockchain.add(new Block("First block", "0"));
        blockchain.add(new Block("Second block", blockchain.get(blockchain.size() - 1).hash));
        blockchain.add(new Block("Third block", blockchain.get(blockchain.size() - 1).hash));
        blockchain.add(new Block("Fourth block", blockchain.get(blockchain.size() - 1).hash));
        blockchain.add(new Block("Fifth block", blockchain.get(blockchain.size() - 1).hash));
        System.out.print("Is chain valid?");
        System.out.print(isChainValid());
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
