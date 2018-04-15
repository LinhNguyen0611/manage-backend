package vn.uit.mobilestore.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class CryptoUtils {

    public static String blowfishEncrypt(String source, String secret) {
        try {
            SecretKeySpec blowfish = new SecretKeySpec(secret.getBytes(),"Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, blowfish);
            byte[] encrypted = cipher.doFinal(source.getBytes(StandardCharsets.UTF_8));

            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String blowfishDecrypt(String encrypted, String secret) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(encrypted);
            SecretKeySpec blowfish = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8),"Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, blowfish);
            byte[] decrypted = cipher.doFinal(decodedBytes);

            return new String(decrypted);
        } catch (Exception e) { }

        return null;
    }
}
