package com.microshop.dao;

import com.microshop.context.DBContext;
import com.microshop.model.TaiKhoan;
import com.microshop.model.TaiKhoanRiot;

import java.sql.*;
import java.time.LocalDateTime;

public class TaiKhoanRiotDAO {

    /**
     * Lấy tài khoản Riot theo mã tài khoản
     * @param maTaiKhoan mã tài khoản cần tìm
     * @return TaiKhoanRiot nếu có, ngược lại null
     */
    public TaiKhoanRiot getByMaTaiKhoan(Integer maTaiKhoan) {
        String sql = "SELECT tk.*, r.* FROM TAIKHOAN tk " +
                "JOIN TAIKHOAN_RIOT r ON tk.maTaiKhoan = r.maTaiKhoan " +
                "WHERE tk.maTaiKhoan = ?";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maTaiKhoan);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                TaiKhoanRiot acc = new TaiKhoanRiot();

                // ---- Bảng TaiKhoan ----
                acc.setMaTaiKhoan(rs.getInt("maTaiKhoan"));
                acc.setMaDanhMuc(rs.getInt("maDanhMuc"));
                acc.setGiaGoc(rs.getBigDecimal("giaGoc"));
                acc.setGiaBan(rs.getBigDecimal("giaBan"));
                acc.setTrangThai(rs.getString("trangThai"));
                acc.setDiemNoiBat(rs.getString("diemNoiBat"));
                acc.setLuotXem(rs.getInt("luotXem"));
                acc.setThoiGianDang(rs.getTimestamp("thoiGianDang").toLocalDateTime());

                // ---- Bảng TaiKhoan_Riot ----
                acc.setTenDangNhap(rs.getString("tenDangNhap"));
                acc.setMatKhau(rs.getString("matKhau"));
                acc.setCapDoRiot(rs.getInt("capDoRiot"));
                acc.setSoTuongLMHT(rs.getInt("soTuongLMHT"));
                acc.setSoTrangPhucLMHT(rs.getInt("soTrangPhucLMHT"));
                acc.setSoDaSacLMHT(rs.getInt("soDaSacLMHT"));
                acc.setSoBieuCamLMHT(rs.getInt("soBieuCamLMHT"));
                acc.setSoBieuTuongLMHT(rs.getInt("soBieuTuongLMHT"));
                acc.setHangRankLMHT(rs.getString("hangRankLMHT"));
                acc.setKhungRankLMHT(rs.getString("khungRankLMHT"));
                acc.setSoThuCungTFT(rs.getInt("soThuCungTFT"));
                acc.setSoSanDauTFT(rs.getInt("soSanDauTFT"));
                acc.setSoChuongLucTFT(rs.getInt("soChuongLucTFT"));

                return acc;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Thêm mới một tài khoản Riot
     * - Insert vào bảng TaiKhoan trước để lấy maTaiKhoan
     * - Sau đó insert chi tiết vào bảng TaiKhoan_Riot
     */
    public void insert(TaiKhoanRiot new_riot_acc) {
        String insertTaiKhoanSQL = "INSERT INTO TAIKHOAN (maDanhMuc, giaGoc, giaBan, trangThai, diemNoiBat, luotXem, thoiGianDang) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        String insertRiotSQL = "INSERT INTO TAIKHOAN_RIOT (maTaiKhoan, tenDangNhap, matKhau, capDoRiot, soTuongLMHT, soTrangPhucLMHT, " +
                "soDaSacLMHT, soBieuCamLMHT, soBieuTuongLMHT, hangRankLMHT, khungRankLMHT, soThuCungTFT, soSanDauTFT, soChuongLucTFT) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement psTaiKhoan = null;
        PreparedStatement psRiot = null;
        ResultSet generatedKeys = null;

        try {
            conn = DBContext.getConnection();
            conn.setAutoCommit(false); // bắt đầu transaction

            // ---- Insert vào bảng TaiKhoan ----
            psTaiKhoan = conn.prepareStatement(insertTaiKhoanSQL, Statement.RETURN_GENERATED_KEYS);
            psTaiKhoan.setInt(1, new_riot_acc.getMaDanhMuc());
            psTaiKhoan.setBigDecimal(2, new_riot_acc.getGiaGoc());
            psTaiKhoan.setBigDecimal(3, new_riot_acc.getGiaBan());
            psTaiKhoan.setString(4, new_riot_acc.getTrangThai());
            psTaiKhoan.setString(5, new_riot_acc.getDiemNoiBat());
            psTaiKhoan.setInt(6, new_riot_acc.getLuotXem());
            psTaiKhoan.setTimestamp(7, Timestamp.valueOf(
                    new_riot_acc.getThoiGianDang() != null ? new_riot_acc.getThoiGianDang() : LocalDateTime.now())
            );
            psTaiKhoan.executeUpdate();

            generatedKeys = psTaiKhoan.getGeneratedKeys();
            if (generatedKeys.next()) {
                int maTaiKhoan = generatedKeys.getInt(1);
                new_riot_acc.setMaTaiKhoan(maTaiKhoan);

                // ---- Insert vào bảng TaiKhoan_Riot ----
                psRiot = conn.prepareStatement(insertRiotSQL);
                psRiot.setInt(1, maTaiKhoan);
                psRiot.setString(2, new_riot_acc.getTenDangNhap());
                psRiot.setString(3, new_riot_acc.getMatKhau());
                psRiot.setInt(4, new_riot_acc.getCapDoRiot());
                psRiot.setInt(5, new_riot_acc.getSoTuongLMHT());
                psRiot.setInt(6, new_riot_acc.getSoTrangPhucLMHT());
                psRiot.setInt(7, new_riot_acc.getSoDaSacLMHT());
                psRiot.setInt(8, new_riot_acc.getSoBieuCamLMHT());
                psRiot.setInt(9, new_riot_acc.getSoBieuTuongLMHT());
                psRiot.setString(10, new_riot_acc.getHangRankLMHT());
                psRiot.setString(11, new_riot_acc.getKhungRankLMHT());
                psRiot.setInt(12, new_riot_acc.getSoThuCungTFT());
                psRiot.setInt(13, new_riot_acc.getSoSanDauTFT());
                psRiot.setInt(14, new_riot_acc.getSoChuongLucTFT());
                psRiot.executeUpdate();
            }

            conn.commit(); // commit transaction

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback(); // rollback nếu lỗi
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (generatedKeys != null) generatedKeys.close();
                if (psRiot != null) psRiot.close();
                if (psTaiKhoan != null) psTaiKhoan.close();
                if (conn != null) conn.setAutoCommit(true);
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
