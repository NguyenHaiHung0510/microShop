package com.microshop.controller;

import com.microshop.dao.DonHangDAO;
import com.microshop.dao.TaiKhoanDAO; // Giả định bạn có DAO này
import com.microshop.model.DonHang;
import com.microshop.model.NguoiDung;
import com.microshop.model.TaiKhoan; // Giả định đây là lớp Model chung
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

/**
 * Xử lý yêu cầu xem danh sách các tài khoản đã mua thành công.
 */
@WebServlet(name = "ViewPurchasedAccountServlet", urlPatterns = {"/profile/view-account"})
public class ViewPurchasedAccountServlet extends HttpServlet {

    // Giả định bạn có một TaiKhoanDAO (hoặc DAO cụ thể)
    private final TaiKhoanDAO taikhoanDAO = new TaiKhoanDAO(); 
    private final DonHangDAO donhangDAO = new DonHangDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. Kiểm tra xác thực (Authentication)
        HttpSession session = request.getSession(false);
        NguoiDung user = (session != null) ? (NguoiDung) session.getAttribute("user") : null;

        if (user == null) {
            // Nếu chưa đăng nhập, chuyển hướng về trang login
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            // 2. Lấy dữ liệu từ DAO
            List<DonHang> purchasedOrders = donhangDAO.getByMaNguoiDungDaHoanThanh(user.getMaNguoiDung());
            List<Integer> maTaiKhoan = new ArrayList<>();
            for(DonHang x : purchasedOrders){
                maTaiKhoan.add(x.getMaTaiKhoan());
            }
            List<TaiKhoan> purchasedAccounts = taikhoanDAO.getAllByList(maTaiKhoan);
            
            // 3. Đặt dữ liệu vào Request Scope
            request.setAttribute("purchasedAccounts", purchasedAccounts);

            // 4. Chuyển tiếp (Forward) sang View (JSP)
            request.getRequestDispatcher("/view_account_detail.jsp").forward(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(ViewPurchasedAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi khi tải danh sách tài khoản đã mua.");
        }
    }
}