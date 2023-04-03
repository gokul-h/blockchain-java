import java.util.Date;
public class Block {
    public String hash;
    public String previousHash;
    public String data;
    private final long timeStamp;
    public String signature = "";
    public String senderAddress = "";
    public String signatureAlgo = "";
    public Block(String data, String previousHash){
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash(){
        return SHA.sha256(previousHash + Long.toString(timeStamp) + data);
    }
}
