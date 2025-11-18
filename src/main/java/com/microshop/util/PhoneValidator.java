package com.microshop.util;

public class PhoneValidator {

    public static String validatePhone(String sdt) {

        // Nếu bỏ trống → cho phép bỏ qua
        if (sdt == null || sdt.trim().isEmpty()) {
            return null; 
        }

        sdt = sdt.trim();

        // Không cho chứa chữ cái
        if (!sdt.matches("[0-9+]+")) {
            return "Số điện thoại không hợp lệ";
        }

        // Kiểm tra đầu +84 hoặc 0
        if (sdt.startsWith("+84")) {
            sdt = "0" + sdt.substring(3); // +84xxx -> 0xxx
        } else if (!sdt.startsWith("0")) {
            return "Số điện thoại không hợp lệ";
        }

        // Sau khi chuẩn hóa phải 10 số
        if (!sdt.matches("0\\d{9}")) {
            return "Số điện thoại không hợp lệ";
        }

        return null; // Hợp lệ
    }
}
