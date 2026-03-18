package com.microshop.demo;
import java.sql.SQLException;

public class LoggingFailures_A09 {
    public void processPayment() {
        try {
            throw new SQLException("Database connection failed during payment!");
        } catch (SQLException e) {
            // không ghi log hệ thống
        }
    }
}