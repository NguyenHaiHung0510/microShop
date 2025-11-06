package com.microshop.controller;

import com.microshop.dao.DonHangDAO;
import com.microshop.dao.TaiKhoanLienQuanDAO; // Ví dụ: Cần DAO để kiểm tra tồn kho
import com.microshop.model.NguoiDung;
import com.microshop.model.TaiKhoan; // Giả định đây là lớp cha cho các loại tài khoản
import com.microshop.model.DonHang;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "PaymentExecuteServlet", urlPatterns = {"/payment/execute"})
public class PaymentExecuteServlet extends HttpServlet {
    
    // Khởi tạo các DAO cần thiết để kiểm tra sản phẩm
    private final TaiKhoanLienQuanDAO lienQuanDAO = new TaiKhoanLienQuanDAO();
    private final DonHangDAO donhangDAO = new DonHangDAO();
    // private final TaiKhoanFreeFireDAO freeFireDAO = new TaiKhoanFreeFireDAO(); 
    // private final TaiKhoanSteamDAO steamDAO = new TaiKhoanSteamDAO(); 
    
    // Phương thức này xử lý yêu cầu khởi tạo thanh toán
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. KIỂM TRA XÁC THỰC (AUTHENTICATION)
        HttpSession session = request.getSession(false);
        NguoiDung user = (session != null) ? (NguoiDung) session.getAttribute("user") : null;

        if (user == null) {
            request.setAttribute("errorMessage", "Vui lòng đăng nhập để tiến hành thanh toán.");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        
        // 2. THU THẬP THAM SỐ SẢN PHẨM
        String idParam = request.getParameter("id");
        String type = request.getParameter("type"); // Ví dụ: lienquan, steam, game
        
        if (idParam == null || type == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu thông tin sản phẩm (ID hoặc Type).");
            return;
        }
        
        try {
            int maSanPham = Integer.parseInt(idParam);
            TaiKhoan sanPham = null; // Đối tượng chung cho sản phẩm
            // 3. KIỂM TRA TỒN KHO & LẤY GIÁ BÁN CUỐI CÙNG
            switch (type.toLowerCase()) {
                case "lienquan":
                    sanPham = lienQuanDAO.getById(maSanPham);
                    break;
                // case "freefire": sanPham = freeFireDAO.getById(maSanPham); break;
                // case "steam": sanPham = steamDAO.getById(maSanPham); break;
                
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Loại sản phẩm không hợp lệ.");
                    return;
            }

            // 4. XỬ LÝ TRẠNG THÁI SẢN PHẨM
            if (sanPham == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Sản phẩm không tồn tại.");
                return;
            }
            
            if (!sanPham.getTrangThai().equals("DANG_BAN")) {
                // Chuyển hướng về trang chi tiết với thông báo đã hết hàng
                response.sendRedirect(request.getContextPath() + "/shop/game/detail?id=" + maSanPham + "&category=" + type + "&status=sold");
                return;
            }
            // Tạo 1 đơn hàng với trạng thái CHO_THANH_TOAN
            // (MaNguoiDung, MaTaiKhoan, GiaMua, ThoiGianMua, TrangThai, ThoiGianTao)
            DonHang donhang = new DonHang(null,
                user.getMaNguoiDung(), sanPham.getMaTaiKhoan(), 
                sanPham.getGiaBan(),
                null,
                "CHO_THANH_TOAN",
                null
            );
            
            // Lấy ra mã đơn hàng sau khi insert
            int maDonHang = donhangDAO.insert(donhang);
            
            // Lấy ra list mã đơn hàng trong session
            
            List<Integer> listMaDonHang = (List<Integer>) session.getAttribute("maDonHangDangXuLy");
            if (listMaDonHang == null) {
                listMaDonHang = new ArrayList<>();
            }
            listMaDonHang.add(maDonHang);
            System.out.println("HELLOW");
            for(int x : listMaDonHang){
                System.out.println(x);
            }
            // 5. CHUYỂN TIẾP ĐẾN TRANG THANH TOÁN (CHECKOUT)

            // Lưu dữ liệu sản phẩm và người dùng vào Session hoặc Request để trang Checkout sử dụng
            request.setAttribute("sanPhamThanhToan", sanPham);
            // LƯU MÃ ĐƠN HÀNG VÀO SESSION
            request.getSession().setAttribute("maDonHangDangXuLy", listMaDonHang);
            // Chuyển tiếp đến trang hiển thị form xác nhận và chọn cổng thanh toán
            request.getRequestDispatcher("/checkout.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID sản phẩm không hợp lệ.");
        } catch (SQLException ex) {
            Logger.getLogger(PaymentExecuteServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi cơ sở dữ liệu khi kiểm tra sản phẩm.");
        }
    }
    
    // Hạn chế sử dụng doPost() cho quá trình khởi tạo thanh toán.
}