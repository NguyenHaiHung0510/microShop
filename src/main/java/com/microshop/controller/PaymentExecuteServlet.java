package com.microshop.controller;

import com.microshop.dao.DonHangDAO;
import com.microshop.dao.TaiKhoanFreeFireDAO;
import com.microshop.dao.TaiKhoanLienQuanDAO; // Ví dụ: Cần DAO để kiểm tra tồn kho
import com.microshop.dao.TaiKhoanRiotDAO;

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
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "PaymentExecuteServlet", urlPatterns = {"/payment/execute"})
public class PaymentExecuteServlet extends HttpServlet {

    private final TaiKhoanLienQuanDAO lienQuanDAO = new TaiKhoanLienQuanDAO();
    private final TaiKhoanFreeFireDAO freeFireDAO = new TaiKhoanFreeFireDAO();
    private final TaiKhoanRiotDAO riotDAO = new TaiKhoanRiotDAO();
    private final DonHangDAO donhangDAO = new DonHangDAO(); // Vẫn cần để kiểm tra

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        NguoiDung user = (session != null) ? (NguoiDung) session.getAttribute("user") : null;

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String idParam = request.getParameter("id");
        String type = request.getParameter("type");

        if (idParam == null || type == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu thông tin sản phẩm.");
            return;
        }

        try {
            int maSanPham = Integer.parseInt(idParam);
            TaiKhoan sanPham = null;

            switch (type.toLowerCase()) {
                case "lienquan":
                    sanPham = lienQuanDAO.getById(maSanPham);
                    break;
                case "freefire":
                    sanPham = freeFireDAO.getById(maSanPham);
                    break;
                case "riot":
                    sanPham = riotDAO.getById(maSanPham);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Loại sản phẩm không hợp lệ.");
                    return;
            }

            if (sanPham == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Sản phẩm không tồn tại.");
                return;
            }

            // KIỂM TRA TỒN KHO VÀ TRẠNG THÁI (Không thay đổi)
            if (sanPham.getTrangThai().equals("DA_BAN")) {
                response.sendRedirect(request.getContextPath() + "/shop/game/detail?id=" + maSanPham + "&category=" + type + "&status=sold");
                return;
            }
            DonHang dh = donhangDAO.getByMaTaiKhoanChoThanhToan(maSanPham);
            if (dh != null && !dh.getMaNguoiDung().equals(user.getMaNguoiDung())) {
                response.sendRedirect(request.getContextPath() + "/shop/game/detail?id=" + maSanPham + "&category=" + type + "&status=in_transaction");
                return;
            }

            // Chỉ đặt sản phẩm vào request và chuyển tiếp
            // KHÔNG TẠO ĐƠN HÀNG Ở ĐÂY
            request.setAttribute("sanPhamThanhToan", sanPham);

            // Đặt thời gian mặc định (sẽ được cập nhật bởi AJAX)
            // Bạn có thể đặt thời gian này trong file cấu hình
            final int NGUONG_HUY_PHUT = 3;
            request.setAttribute("thoiGianConLai", NGUONG_HUY_PHUT * 60);

            request.getRequestDispatcher("/checkout.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID sản phẩm không hợp lệ.");
        } catch (SQLException ex) {
            Logger.getLogger(PaymentExecuteServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi cơ sở dữ liệu.");
        }
    }
}
