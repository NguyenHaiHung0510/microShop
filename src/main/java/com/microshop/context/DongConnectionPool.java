package com.microshop.context;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.util.Timer;
import com.microshop.listener.OrderCleanupTask;
import com.microshop.dao.DonHangDAO; 

// Nghe khi Web bị ngắt và thực hiện thu hồi connection pool an toàn
@WebListener
public class DongConnectionPool implements ServletContextListener {
    
    // Khai báo Timer để quản lý tác vụ nền
    private Timer cleanupTimer;
    
    // Khởi tạo DAO (chú ý: phải thread-safe nếu DAO có trạng thái)
    private final DonHangDAO donHangDAO = new DonHangDAO(); 

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Web da duoc khoi chay thanh cong! - DongConnectionPool dang nghe");
        
        // ----------------------------------------------------
        // TÍCH HỢP TÁC VỤ DỌN DẸP ĐƠN HÀNG (OrderCleanupTask)
        // ----------------------------------------------------
        
        cleanupTimer = new Timer(true); // Khởi tạo Timer
        
        // Cấu hình dọn dẹp (ví dụ: Đơn hàng quá 1 phút sẽ bị hủy)
        int cleanupThresholdMinutes = 30; // 30 giây
        long initialDelay = 10 * 1000;  // Chạy lần đầu sau 10 giây
        long repeatPeriod = 15 * 1000;   // Lặp lại mỗi 15 giây
        
        System.out.printf("Tác vụ dọn dẹp đơn hàng đã được khởi động. Kiểm tra mỗi %.1f giây cho đơn hàng quá %d phút.\n", 
                repeatPeriod / 1000.0, cleanupThresholdMinutes);
        
        // Lên lịch cho tác vụ chạy định kỳ
        cleanupTimer.scheduleAtFixedRate(
            new OrderCleanupTask(donHangDAO, cleanupThresholdMinutes), 
            initialDelay, 
            repeatPeriod
        );
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
//        // 1. Dừng Timer khi ứng dụng tắt
        if (cleanupTimer != null) {
            cleanupTimer.cancel();
            System.out.println("Tác vụ dọn dẹp đơn hàng đã được hủy.");

            // THÊM KHOẢNG THỜI GIAN CHỜ (JOIN)
            try {
                // Chờ 1 giây để Timer Thread hoàn thành nốt tác vụ cuối cùng (nếu có)
                // trước khi đóng Connection Pool mà nó đang sử dụng.
                Thread.sleep(1000); 
                System.out.println("Đã chờ Timer dừng hẳn.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Đặt lại cờ ngắt
            }
        }
        
        // 2. Thu hồi Connection Pool
        System.out.println("Web da ngung hoat dong, thuc hien thu hoi Connection Pool");
        // Giả định: DBContext.shutdown() tồn tại
        DBContext.shutdown(); 
        System.out.println("Connection Pool da duoc thu hoi thanh cong");
    }
}