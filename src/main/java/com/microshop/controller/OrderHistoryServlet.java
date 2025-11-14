package com.microshop.controller;

import com.microshop.dao.DonHangDAO;
import com.microshop.dao.DonHangSlotSteamDAO;
import com.microshop.model.DonHang;
import com.microshop.model.DonHangSlotSteam;
import com.microshop.model.NguoiDung;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servlet này xử lý việc hiển thị lịch sử đơn hàng cho người dùng đã đăng nhập.
 */
@WebServlet(name = "OrderHistoryServlet", urlPatterns = {"/profile/orders"})
public class OrderHistoryServlet extends HttpServlet {

    // Khởi tạo DAO (Giả định DonHangDAO đã có phương thức getByMaNguoiDung)
    private final DonHangDAO donHangDAO = new DonHangDAO();
    private final DonHangSlotSteamDAO donHangSlotSteamDAO = new DonHangSlotSteamDAO();
    /**
     * Handles the HTTP <code>GET</code> method.
     * Xử lý yêu cầu xem lịch sử đơn hàng.
     */
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
            // 2. Lấy MaNguoiDung
            int maNguoiDung = user.getMaNguoiDung();

            // 3. Gọi DAO để lấy danh sách đơn hàng
            List<DonHang> orderList = donHangDAO.getByMaNguoiDung(maNguoiDung);
            List<DonHangSlotSteam> steamOrderList = donHangSlotSteamDAO.getByMaNguoiDung(maNguoiDung);
            // 4. Đặt danh sách đơn hàng vào Request Scope để JSP có thể truy cập
            request.setAttribute("orderList", orderList);
            request.setAttribute("steamOrderList", steamOrderList);
            // 5. Chuyển tiếp (Forward) sang View (JSP)
            // (Bạn cần tạo file /order_history.jsp để hiển thị)
            request.getRequestDispatcher("/orders.jsp").forward(request, response);

        } catch (SQLException ex) {
            // Xử lý lỗi nếu có vấn đề về cơ sở dữ liệu
            Logger.getLogger(OrderHistoryServlet.class.getName()).log(Level.SEVERE, "Lỗi SQL khi lấy lịch sử đơn hàng", ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Không thể tải lịch sử đơn hàng do lỗi máy chủ.");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * (Không dùng trong trường hợp này, nhưng nên chuyển hướng sang doGet)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet để tải và hiển thị lịch sử đơn hàng của người dùng.";
    }
}