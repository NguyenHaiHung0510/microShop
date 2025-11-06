package com.microshop.listener;

import com.microshop.dao.DonHangDAO; // Cần thiết để thao tác với đơn hàng
import com.microshop.model.DonHang;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Lắng nghe sự kiện hủy phiên (Session) để dọn dẹp các đơn hàng bị bỏ dở.
 */
@WebListener
public class SessionDestroyListener implements HttpSessionListener {
    
    // Khởi tạo DAO (Nên dùng Singleton Pattern nếu có, nhưng ở đây dùng tạm)
    private final DonHangDAO donHangDAO = new DonHangDAO(); 

    /**
     * Phương thức này được gọi khi một phiên làm việc (Session) hết hạn hoặc bị hủy (invalidate).
     * @param se HttpSessionEvent chứa Session bị hủy.
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        
        HttpSession session = se.getSession();
        // 1. Lấy MaDonHang đang được xử lý (được lưu trong PaymentExecuteServlet)
        List<Integer> listMaDonHang = (List<Integer>) session.getAttribute("maDonHangDangXuLy"); 

        if (listMaDonHang != null) {
            try {
                // 2. Gọi DAO để hủy đơn hàng và giải phóng sản phẩm.
                // Hàm này cần:
                // a) Cập nhật trạng thái đơn hàng thành 'DA_HUY'.
                // b) Cập nhật trạng thái Tài Khoản/Sản phẩm trở lại 'DANG_BAN'.
                for(int maDonHang : listMaDonHang){
                    // xoá những đơn hàng đang chờ thanh toán ra khỏi csdl
                    DonHang donHang = donHangDAO.getById(maDonHang);
                    if(donHang.getTrangThai().equals("CHO_THANH_TOAN")){
                        donHang.setTrangThai("DA_HUY");
                        boolean success = donHangDAO.update(donHang);
                        if (success) {
                            Logger.getLogger(SessionDestroyListener.class.getName())
                                    .log(Level.INFO, "Đơn hàng #{0} đã bị hủy và sản phẩm được giải phóng do Session timeout.", maDonHang);
                        } else {
                             Logger.getLogger(SessionDestroyListener.class.getName())
                                    .log(Level.WARNING, "Không thể hủy đơn hàng #{0} trong DB.", maDonHang);
                        }
                    }
                }
     
            } catch (SQLException ex) {
                // Xử lý lỗi DB trong tác vụ nền
                Logger.getLogger(SessionDestroyListener.class.getName())
                        .log(Level.SEVERE, "Lỗi DB khi hủy đơn hàng #" + listMaDonHang, ex);
            }
        }
        
        // Dọn dẹp thuộc tính session sau khi xử lý
        session.removeAttribute("maDonHangDangXuLy");
    }

    /**
     * Phương thức này được gọi khi một phiên làm việc được tạo.Thường không cần thiết cho logic hủy đơn hàng.
     * @param se
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
    }
}