package com.microshop.controller;

import com.microshop.dao.DonHangDAO;
import com.microshop.dao.DonHangSlotSteamDAO;
import com.microshop.dao.GameSteamDAO;
import com.microshop.dao.GameTaiKhoanSteamDAO;
import com.microshop.dao.HangThanhVienDAO;
import com.microshop.dao.TaiKhoanFreeFireDAO;
import com.microshop.dao.TaiKhoanLienQuanDAO; // Ví dụ: Cần DAO để kiểm tra tồn kho
import com.microshop.dao.TaiKhoanRiotDAO;
import com.microshop.dao.TaiKhoanSteamDAO;

import com.microshop.model.NguoiDung;
import com.microshop.model.TaiKhoan; // Giả định đây là lớp cha cho các loại tài khoản
import com.microshop.model.DonHang;
import com.microshop.model.DonHangSlotSteam;
import com.microshop.model.HangThanhVien;
import com.microshop.model.TaiKhoanSteam;
import java.io.IOException;
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
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "PaymentExecuteServlet", urlPatterns = {"/payment/execute"})
public class PaymentExecuteServlet extends HttpServlet {

    private final TaiKhoanLienQuanDAO lienQuanDAO = new TaiKhoanLienQuanDAO();
    private final TaiKhoanFreeFireDAO freeFireDAO = new TaiKhoanFreeFireDAO();
    private final TaiKhoanRiotDAO riotDAO = new TaiKhoanRiotDAO();
    private final DonHangDAO donhangDAO = new DonHangDAO(); // Vẫn cần để kiểm tra
    private final DonHangSlotSteamDAO donhangsteamDAO = new DonHangSlotSteamDAO(); // Vẫn cần để kiểm tra
    private final TaiKhoanSteamDAO tksteamDAO = new TaiKhoanSteamDAO();
    private final GameTaiKhoanSteamDAO gametksteamDAO = new GameTaiKhoanSteamDAO();
    private final GameSteamDAO gamesteamDAO = new GameSteamDAO();
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
            TaiKhoanSteam sanPhamSteam = null;
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
//                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Loại sản phẩm không hợp lệ.");
//                    return;
                    break;
            }

            if(!type.toLowerCase().equals("steam")){
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
                
                HangThanhVienDAO hangDao = new HangThanhVienDAO(); // Khởi tạo DAO
    
                // Lấy hạng thành viên của người dùng
                HangThanhVien htv = hangDao.getById(user.getMaHangThanhVien());
                // TRUYỀN DỮ LIỆU CHIẾT KHẤU SANG JSP

                BigDecimal giaBan = sanPham.getGiaBan(); // Giá gốc
                BigDecimal chietKhauPhanTram = htv.getChietKhau(); // Ví dụ: 0.02 (2%)
                BigDecimal tienChietKhau = giaBan.multiply(chietKhauPhanTram);
                // Tính giá cuối cùng
                BigDecimal giaCuoiCung = giaBan.subtract(tienChietKhau);
//                if(dh != null){
//                    donhangDAO.updateGiaMua(dh.getMaDonHang(), giaCuoiCung);
//                }
                request.setAttribute("type", 1);
                request.setAttribute("maGame", -1);
                request.setAttribute("tenHang", htv.getTenHang());
                request.setAttribute("giaBanDau", giaBan);
                request.setAttribute("tienChietKhau", tienChietKhau);
                request.setAttribute("giaCuoiCung", giaCuoiCung);
            }
            else{
                // Lấy list tài khoản chứa steam game theo số slot giảm dần
                List<TaiKhoanSteam> tk_chua_game = gametksteamDAO.getAllTaiKhoanByMaGameSteamSorted(maSanPham);
                // Kiểm tra xem tài khoản nhiểu slot nhất còn slot không
                TaiKhoanSteam tk_nhieu_slot_nhat = tk_chua_game.get(0);
                Integer tong_so_slot = tk_nhieu_slot_nhat.getTongSoSlot();
                Integer so_slot_da_ban = tk_nhieu_slot_nhat.getSoSlotDaBan();
                if(tong_so_slot - so_slot_da_ban <= 0){
                    response.sendRedirect(request.getContextPath() + "/shop/game/detail?id=" + maSanPham + "&category=" + type + "&status=sold");
                    return;
                }
                List<DonHangSlotSteam> list_dh_game = donhangsteamDAO.getByMaGameSteamChoThanhToan(maSanPham);
                boolean dh_dang_duoc_user_thanh_toan = false;
                for(DonHangSlotSteam dh : list_dh_game){
                    if(dh.getMaNguoiDung().equals(user.getMaNguoiDung())){
                        dh_dang_duoc_user_thanh_toan = true;
                        break;
                    }
                } 
                
                // Tìm xem tài khoản nào có còn slot
                for(TaiKhoanSteam tk : tk_chua_game){
                    // Xem tài khoản này có đang được giao dịch hay không?
                    List<DonHangSlotSteam> listdh = donhangsteamDAO.getByMaTaiKhoanChoThanhToan(tk.getMaTaiKhoanSteam());   
                    Integer sl = tk.getTongSoSlot();
                    Integer sold_sl = tk.getSoSlotDaBan();
                    // Nếu còn slot chưa được giao dịch
                    if(listdh.size() != (sl - sold_sl)){
                        sanPhamSteam = tk;
                        break;
                    }
                }

                // Chỉ đặt sản phẩm vào request và chuyển tiếp
                // KHÔNG TẠO ĐƠN HÀNG Ở ĐÂY
                request.setAttribute("sanPhamThanhToan", sanPhamSteam);
                
                HangThanhVienDAO hangDao = new HangThanhVienDAO(); // Khởi tạo DAO
    
                // Lấy hạng thành viên của người dùng
                HangThanhVien htv = hangDao.getById(user.getMaHangThanhVien());
                // TRUYỀN DỮ LIỆU CHIẾT KHẤU SANG JSP

                BigDecimal giaBan = gamesteamDAO.getById(maSanPham).getGiaBan(); // Giá gốc
                BigDecimal chietKhauPhanTram = htv.getChietKhau(); // Ví dụ: 0.02 (2%)
                BigDecimal tienChietKhau = giaBan.multiply(chietKhauPhanTram);
                // Tính giá cuối cùng
                BigDecimal giaCuoiCung = giaBan.subtract(tienChietKhau);
                request.setAttribute("type", 2);
                request.setAttribute("maGame", maSanPham);
                request.setAttribute("tenHang", htv.getTenHang());
                request.setAttribute("giaBanDau", giaBan);
                request.setAttribute("tienChietKhau", tienChietKhau);
                request.setAttribute("giaCuoiCung", giaCuoiCung);
            }
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
