import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;

public class ECDSA {
    private static final String SPEC = "secp256k1";
    private static final String ALGO = "SHA256withECDSA";
    private final PublicKey publicKey;
    private final PrivateKey privateKey;
    ECDSA(PublicKey publicKey) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        ECGenParameterSpec ecSpec = new ECGenParameterSpec(SPEC);
        KeyPairGenerator g = KeyPairGenerator.getInstance("EC");
        g.initialize(ecSpec, new SecureRandom());
        KeyPair keypair = g.generateKeyPair();
        this.publicKey = keypair.getPublic();
        this.privateKey = keypair.getPrivate();

    }

    public void sign(Block b) throws SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        Signature ecdsaSign = Signature.getInstance(ALGO);
        ecdsaSign.initSign(privateKey);
        ecdsaSign.update(b.hash.getBytes(StandardCharsets.UTF_8));
        byte[] signature = ecdsaSign.sign();
        b.senderAddress = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        b.signature = Base64.getEncoder().encodeToString(signature);
        b.signatureAlgo = ALGO;
    }
}
