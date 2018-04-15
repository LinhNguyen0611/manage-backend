package vn.uit.mobilestore.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class HashUtils {

    private static Logger LOGGER = LoggerFactory.getLogger(HashUtils.class);

    public static String hashSha256(String source) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            //Never this case
            LOGGER.info("Hash error: {}", e);
        }
        byte[] hash = digest.digest(source.getBytes(StandardCharsets.UTF_8));

        return DatatypeConverter.printHexBinary(hash);
    }

    public static String hashMd5(String source) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            //Never this case
            LOGGER.info("Hash error: {}", e);
        }
        byte[] hash = digest.digest(source.getBytes(StandardCharsets.UTF_8));

        return DatatypeConverter.printHexBinary(hash);
    }
}
