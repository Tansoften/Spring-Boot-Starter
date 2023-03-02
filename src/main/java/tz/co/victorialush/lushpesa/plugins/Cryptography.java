package tz.co.victorialush.lushpesa.plugins;

import org.apache.commons.net.util.Base64;

import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Cryptography {
    private static final String algorithm = "SHA256";
    public static String hashValue(String value) throws NoSuchAlgorithmException {
        MessageDigest digestInstance = MessageDigest.getInstance(algorithm);
        byte[] md = digestInstance.digest(value.getBytes(StandardCharsets.UTF_8));
        return Arrays.toString(Base64.encodeBase64(md));
    }
}
