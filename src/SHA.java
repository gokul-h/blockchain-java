import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;

class SHA {
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static byte[] getSHA(Block b) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(b.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash) {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 64) {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }

    public static String sha256(String input) {
        try {
            return toHexString(getSHA(input));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String sha256(Block b){
        try{
            return toHexString(getSHA(b));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

