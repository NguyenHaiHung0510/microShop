package com.microshop.demo;
import java.sql.*;
//Mồi cho Codacy: SQL Injection (Data Flow)
public class ReportExporter {
    public void exportDataByTable(String tableName) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/microshop", "root", "");
            Statement st = conn.createStatement();
            // LỖI (Codacy): Nối chuỗi SQL tạo điều kiện cho Injection
            String sql = "SELECT * FROM " + tableName + " ORDER BY created_at DESC";
            st.executeQuery(sql);
        } catch (Exception e) {}
    }
}