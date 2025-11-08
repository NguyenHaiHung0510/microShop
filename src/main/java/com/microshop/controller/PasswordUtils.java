package com.microshop.controller;

import org.mindrot.jbcrypt.BCrypt;
/**
 * Tiện ích dùng để băm và xác minh mật khẩu người dùng.
 * Sử dụng thuật toán BCrypt (được khuyến nghị trong các hệ thống hiện nay).
 */
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
/*
    Lợi ích:
    Không lưu mật khẩu plain text trong DB.
    Mỗi mật khẩu băm sẽ có salt ngẫu nhiên, bảo vệ chống rainbow table.
    Có thể áp dụng cả cho đăng ký, đổi mật khẩu, đăng nhập.
 */