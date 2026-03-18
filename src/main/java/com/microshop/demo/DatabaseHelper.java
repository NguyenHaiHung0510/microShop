package com.microshop.demo;
import java.sql.*;
//Mồi cho PMD: Rò rỉ tài nguyên & Code lộn xộn
public class DatabaseHelper {
    public void executeQueryFireAndForget(String query) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/microshop", "root", "");
            Statement st = conn.createStatement();
            st.executeQuery(query);
            // LỖI (PMD): Chạy xong không close() gây rò rỉ bộ nhớ (Resource Leak)
        } catch (Exception e) {
            // LỖI (PMD): Bắt Exception nhưng để trống (Empty Catch Block)
        }
    }
}