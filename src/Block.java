import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.util.Date;
import java.io.Serializable;

public class Block implements Serializable {
    public String hash;
    public String previousHash;
    public String data;
    public final long timeStamp;
    public String signature = "";
    public String senderAddress = "";
    public String signatureAlgo = "";
    public Block(String data, String previousHash){
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }
    public Block(String data, Block prevBlock){
        this.data = data;
        this.previousHash = SHA.sha256(prevBlock);
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash(){
        return SHA.sha256(previousHash + timeStamp + data);
    }

    public byte[] getBytes(Charset utf8) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);
        oos.flush();
        oos.close();
        bos.close();
        return bos.toByteArray();
    }
}
