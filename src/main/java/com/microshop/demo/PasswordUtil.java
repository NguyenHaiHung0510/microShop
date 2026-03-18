package com.microshop.demo;
import java.security.MessageDigest;
//Mồi cho SpotBugs (FindSecBugs): Cryptographic Failures
public class PasswordUtil {
    public String generateTemporaryPasswordHash(String plainText) {
        try {
            // LỖI (SpotBugs): Dùng MD5 là thuật toán đã bị bẻ khóa
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(plainText.getBytes());
            return new String(hashBytes);
        } catch (Exception e) {
            return null;
        }
    }
}