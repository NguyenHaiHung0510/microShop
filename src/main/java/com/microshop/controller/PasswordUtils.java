package com.microshop.controller;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {

    // Băm mật khẩu
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12)); // 12 rounds
    }

    // Kiểm tra mật khẩu
    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        if (hashedPassword == null || hashedPassword.isEmpty()) return false;
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
