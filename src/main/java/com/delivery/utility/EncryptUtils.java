package com.delivery.utility;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {
    public static String sha256(String password) {
        StringBuilder hashed = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes(StandardCharsets.UTF_8));
            for (byte b : md.digest()) {
                hashed.append(b);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashed.toString();
    }
}
