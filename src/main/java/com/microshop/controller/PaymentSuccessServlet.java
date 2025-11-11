package com.microshop.controller;

import com.microshop.dao.DonHangDAO; // Cần DAO để lưu đơn hàng
import com.microshop.dao.NguoiDungDAO;
import com.microshop.dao.TaiKhoanDAO;
import com.microshop.model.DonHang;
import com.microshop.model.NguoiDung;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servlet xử lý việc hoàn tất giao dịch và chuyển hướng đến trang thông báo thành công.
 */
@WebServlet(name = "PaymentSuccessServlet", urlPatterns = {"/payment/success"})
public class PaymentSuccessServlet extends HttpServlet {
    
    private final DonHangDAO donHangDAO = new DonHangDAO(); // Giả định
    private final TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
    private final NguoiDungDAO nguoidungDAO = new NguoiDungDAO();
    
    // Phương thức này là điểm cuối của form thanh toán (POST từ checkout.jsp)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        NguoiDung user = (session != null) ? (NguoiDung) session.getAttribute("user") : null;

        // 1. KIỂM TRA ĐĂNG NHẬP
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        // 2. THU THẬP DỮ LIỆU THANH TOÁN (từ các trường hidden trong form)
        String maTaiKhoanParam = request.getParameter("maTaiKhoan");
        String giaParam = request.getParameter("gia");
        String paymentMethod = request.getParameter("paymentMethod");

        if (maTaiKhoanParam == null || paymentMethod == null) {
             // Lỗi thiếu dữ liệu, chuyển hướng về trang chủ
             response.sendRedirect(request.getContextPath() + "/home");
             return;
        }

        try {
            int maTaiKhoan = Integer.parseInt(maTaiKhoanParam);
            // double gia = Double.parseDouble(giaParam); // Nếu cần dùng giá trị đã format
            
            // 3. XỬ LÝ LOGIC QUAN TRỌNG (Giao dịch thật)
            
            // TODO: Triển khai logic giao dịch thật (Ghi vào DONHANG, cập nhật trạng thái TAIKHOAN)
            DonHang donhang = donHangDAO.getByMaTaiKhoanChoThanhToan(maTaiKhoan);
            String current_status = donhang.getTrangThai();
            if(current_status.equals("DA_HOAN_THANH")){
                request.getRequestDispatcher("/payment_fail.jsp").forward(request, response);
                return;
            }
            donHangDAO.updateTrangThai(donhang.getMaDonHang(), "DA_HOAN_THANH", LocalDateTime.now());
            taiKhoanDAO.updateTrangThai(maTaiKhoan, "DA_BAN");
            
            // update tổng tiền và hạng thành viên
            nguoidungDAO.updateTongTienDaChi(user.getMaNguoiDung(),new BigDecimal(giaParam));
            nguoidungDAO.updateHangThanhVien(user.getMaNguoiDung());
            NguoiDung userMoi = nguoidungDAO.getById(user.getMaNguoiDung());
    
            // Lưu đối tượng MỚI vào Session
            session.setAttribute("user", userMoi);
            
            // Giả định: Xử lý thành công và lưu thông tin cần thiết vào Request Scope
            request.setAttribute("paymentSuccessStatus", true);
            request.setAttribute("transactionMethod", paymentMethod);
            request.setAttribute("transactionId", "TXN-" + System.currentTimeMillis()); // ID giao dịch giả lập
            
            
            // 4. CHUYỂN TIẾP ĐẾN TRANG THÀNH CÔNG (VIEW)
            request.getRequestDispatcher("/payment_success.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            // Lỗi định dạng ID, chuyển hướng về trang chi tiết
            response.sendRedirect(request.getContextPath() + "/home");
        } catch (Exception ex) {
            Logger.getLogger(PaymentSuccessServlet.class.getName()).log(Level.SEVERE, null, ex);
            // Xử lý lỗi trong quá trình lưu DB
            request.setAttribute("paymentSuccessStatus", false);
            request.setAttribute("errorMessage", "Đã xảy ra lỗi trong quá trình hoàn tất giao dịch.");
            request.getRequestDispatcher("/payment_success.jsp").forward(request, response);
        }
    }

    // Phương thức GET có thể dùng để hiển thị lại thông báo thành công nếu người dùng refresh
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Forward thẳng đến trang View (Giả định rằng dữ liệu cần thiết đã được lưu trong Request/Session nếu cần)
        request.getRequestDispatcher("/payment_success.jsp").forward(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Servlet xử lý xác nhận và chuyển hướng đến trang thông báo thanh toán thành công.";
    }
}