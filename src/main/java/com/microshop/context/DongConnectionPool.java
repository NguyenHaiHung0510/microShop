package com.microshop.context;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

// Nghe khi Web bị ngắt và thực hiện thu hồi connection pool an toàn
@WebListener
public class DongConnectionPool implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Web da duoc khoi chay thanh cong! - DongConnectionPool dang nghe");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Web da ngung hoat dong, thuc hien thu hoi Connection Pool");
        DBContext.shutdown();
        System.out.println("Connection Pool da thu hoi thanh cong");
    }
}
