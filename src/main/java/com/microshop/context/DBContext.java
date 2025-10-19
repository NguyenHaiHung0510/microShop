package com.microshop.context;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBContext {
    
    /*
    jdbc:mysql - định dạng kết nối JDBC cho MySQL
    */
    private static final String DB_URL = "jdbc:mysql://localhost:3306/microshop_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "hung";

    private static HikariDataSource dataSource;

    static {
        try {
            HikariConfig config = new HikariConfig();

            config.setJdbcUrl(DB_URL + "?characterEncoding=UTF-8");
            config.setUsername(DB_USER);
            config.setPassword(DB_PASSWORD);

            config.setDriverClassName("com.mysql.cj.jdbc.Driver");

            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

            config.setMinimumIdle(5);
            config.setMaximumPoolSize(20);
            config.setConnectionTimeout(30000);

            dataSource = new HikariDataSource(config);

            System.out.println("Connection Pool (HikariCP) đã được khởi tạo thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Có lỗi khi khởi tạo Connection Pool: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private DBContext() {}
}
