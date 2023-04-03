import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;


public class ECDSAVerify {

    private boolean verify(Block b) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {

        Signature ecdsaVerify = Signature.getInstance(b.signatureAlgo);
        KeyFactory kf = KeyFactory.getInstance("EC");

        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(b.senderAddress));

        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

        ecdsaVerify.initVerify(publicKey);
        ecdsaVerify.update(b.hash.getBytes(StandardCharsets.UTF_8));

        return ecdsaVerify.verify(Base64.getDecoder().decode(b.signature));
    }

}