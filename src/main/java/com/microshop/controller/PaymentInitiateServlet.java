package com.microshop.controller;

import com.microshop.dao.DonHangDAO;
import com.microshop.dao.TaiKhoanDAO;
import com.microshop.model.DonHang;
import com.microshop.model.NguoiDung;
import com.microshop.model.TaiKhoan;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// Servlet này xử lý yêu cầu AJAX POST
@WebServlet(name = "PaymentInitiateServlet", urlPatterns = {"/payment/initiate"})
public class PaymentInitiateServlet extends HttpServlet {

    private final DonHangDAO donhangDAO = new DonHangDAO();
    private final TaiKhoanDAO taikhoanDAO = new TaiKhoanDAO();
    private final int NGUONG_HUY_PHUT = 3; // Cài đặt thời gian chờ (ví dụ: 3 phút)

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("application/json"); // Thiết lập kiểu trả về là JSON
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession(false);
        NguoiDung user = (session != null) ? (NguoiDung) session.getAttribute("user") : null;

        if (user == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            out.print("{\"error\": \"Vui lòng đăng nhập lại.\"}");
            return;
        }

        try {
            // Lấy thông tin từ AJAX POST
            int maSanPham = Integer.parseInt(request.getParameter("maSanPham"));
//            int giaBan = Integer.parseInt(request.getParameter("giaBan"));
            String giaBan = request.getParameter("giaBan");
            // String paymentMethod = request.getParameter("paymentMethod"); // Bạn có thể lưu nếu cần
            DonHang dh = donhangDAO.getByMaTaiKhoanChoThanhToan(maSanPham);
            TaiKhoan tk = taikhoanDAO.getById(maSanPham);
            // Nếu tài khoản đã bán
            if (tk != null && tk.getTrangThai().equals("DA_BAN")) {
                response.setStatus(HttpServletResponse.SC_CONFLICT); // 409
                out.print("{\"error\": \"Sản phẩm này đã được bán. Vui lòng tải lại trang.\"}");
                return;
            }
            
            // KIỂM TRA LẠI (QUAN TRỌNG): Đảm bảo sản phẩm vẫn còn
            if(dh != null){
                if(!dh.getMaNguoiDung().equals(user.getMaNguoiDung())){
                    response.setStatus(HttpServletResponse.SC_CONFLICT); // 409
                    out.print("{\"error\": \"Sản phẩm này đang được người khác giao dịch.\"}");
                }
                else{
                    int maDonHang = dh.getMaDonHang();
    
                    // Tính thời gian còn lại
                    LocalDateTime thoiGianTao = dh.getThoiGianTao(); // Chuyển Timestamp sang LocalDateTime
                    LocalDateTime thoiGianHetHan = thoiGianTao.plusMinutes(NGUONG_HUY_PHUT);

                    Duration remaining = Duration.between(LocalDateTime.now(), thoiGianHetHan);
                    Long thoiGianConLaiGiay = remaining.getSeconds();

                    // Đảm bảo không bị âm nếu đã quá hạn (mặc dù Task Cleanup sẽ xử lý)
                    if (thoiGianConLaiGiay < 0) {
                        thoiGianConLaiGiay = 0L;
                    }
                    response.setStatus(HttpServletResponse.SC_OK); // 200
                    // Trả về JSON thành công
                    out.print("{\"success\": true, \"maDonHang\": " + maDonHang + ", \"thoiGianConLai\": " + thoiGianConLaiGiay + "}");
                }
                    return;
            }

            // TẠO ĐƠN HÀNG
            DonHang donhang = new DonHang(null,
                user.getMaNguoiDung(), maSanPham, new BigDecimal(giaBan),
                null, "CHO_THANH_TOAN", null
            );
            
            // INSERT VÀO DB
            int maDonHang = donhangDAO.insert(donhang);

            // Lưu mã đơn hàng vào session để quản lý
            List<Integer> listMaDonHang = (List<Integer>) session.getAttribute("maDonHangDangXuLy");
            if (listMaDonHang == null) {
                listMaDonHang = new ArrayList<>();
            }
            listMaDonHang.add(maDonHang);
            session.setAttribute("maDonHangDangXuLy", listMaDonHang);

            // Trả về thời gian đếm ngược
            long thoiGianConLaiGiay = NGUONG_HUY_PHUT * 60;
            response.setStatus(HttpServletResponse.SC_OK); // 200
            // Trả về JSON thành công
            out.print("{\"success\": true, \"maDonHang\": " + maDonHang + ", \"thoiGianConLai\": " + thoiGianConLaiGiay + "}");

        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
            out.print("{\"error\": \"Dữ liệu sản phẩm không hợp lệ.\"}");
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500
            out.print("{\"error\": \"Lỗi cơ sở dữ liệu khi tạo đơn hàng.\"}");
            Logger.getLogger(PaymentInitiateServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}