package com.microshop.dao;

import com.microshop.context.DBContext;
import com.microshop.model.TaiKhoan;
import com.microshop.model.TaiKhoanFreeFire;

import java.sql.*;
import java.time.LocalDateTime;

public class TaiKhoanFreeFireDAO {

    /**
     * Lấy tài khoản Free Fire theo mã tài khoản
     * @param maTaiKhoan mã tài khoản cần tìm
     * @return TaiKhoanFreeFire nếu có, ngược lại null
     */
    public TaiKhoanFreeFire getByMaTaiKhoan(Integer maTaiKhoan) {
        String sql = "SELECT tk.*, ff.* FROM TAIKHOAN tk " +
                "JOIN TAIKHOAN_FREEFIRE ff ON tk.maTaiKhoan = ff.maTaiKhoan " +
                "WHERE tk.maTaiKhoan = ?";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maTaiKhoan);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                TaiKhoanFreeFire acc = new TaiKhoanFreeFire();

                // ---- Bảng TaiKhoan ----
                acc.setMaTaiKhoan(rs.getInt("maTaiKhoan"));
                acc.setMaDanhMuc(rs.getInt("maDanhMuc"));
                acc.setGiaGoc(rs.getBigDecimal("giaGoc"));
                acc.setGiaBan(rs.getBigDecimal("giaBan"));
                acc.setTrangThai(rs.getString("trangThai"));
                acc.setDiemNoiBat(rs.getString("diemNoiBat"));
                acc.setLuotXem(rs.getInt("luotXem"));
                acc.setThoiGianDang(rs.getTimestamp("thoiGianDang").toLocalDateTime());

                // ---- Bảng TaiKhoan_FreeFire ----
                acc.setTenDangNhap(rs.getString("tenDangNhap"));
                acc.setMatKhau(rs.getString("matKhau"));
                acc.setCoTheVoCuc(rs.getBoolean("coTheVoCuc"));
                acc.setSoSkinSung(rs.getInt("soSkinSung"));
                acc.setHangRank(rs.getString("hangRank"));
                acc.setLoaiDangKy(rs.getString("loaiDangKy"));

                return acc;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Thêm mới một tài khoản Free Fire
     * - Insert vào bảng TaiKhoan trước để lấy maTaiKhoan
     * - Sau đó insert chi tiết vào bảng TaiKhoan_FreeFire
     */
    public void insert(TaiKhoanFreeFire new_ff_acc) {
        String insertTaiKhoanSQL = "INSERT INTO TAIKHOAN (maDanhMuc, giaGoc, giaBan, trangThai, diemNoiBat, luotXem, thoiGianDang) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        String insertFreeFireSQL = "INSERT INTO TAIKHOAN_FREEFIRE (maTaiKhoan, tenDangNhap, matKhau, coTheVoCuc, soSkinSung, hangRank, loaiDangKy) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement psTaiKhoan = null;
        PreparedStatement psFreeFire = null;
        ResultSet generatedKeys = null;

        try {
            conn = DBContext.getConnection();
            conn.setAutoCommit(false); // bắt đầu transaction

            // ---- Insert vào bảng TaiKhoan ----
            psTaiKhoan = conn.prepareStatement(insertTaiKhoanSQL, Statement.RETURN_GENERATED_KEYS);
            psTaiKhoan.setInt(1, new_ff_acc.getMaDanhMuc());
            psTaiKhoan.setBigDecimal(2, new_ff_acc.getGiaGoc());
            psTaiKhoan.setBigDecimal(3, new_ff_acc.getGiaBan());
            psTaiKhoan.setString(4, new_ff_acc.getTrangThai());
            psTaiKhoan.setString(5, new_ff_acc.getDiemNoiBat());
            psTaiKhoan.setInt(6, new_ff_acc.getLuotXem());
            psTaiKhoan.setTimestamp(7, Timestamp.valueOf(
                    new_ff_acc.getThoiGianDang() != null ? new_ff_acc.getThoiGianDang() : LocalDateTime.now())
            );
            psTaiKhoan.executeUpdate();

            generatedKeys = psTaiKhoan.getGeneratedKeys();
            if (generatedKeys.next()) {
                int maTaiKhoan = generatedKeys.getInt(1);
                new_ff_acc.setMaTaiKhoan(maTaiKhoan);

                // ---- Insert vào bảng TaiKhoan_FreeFire ----
                psFreeFire = conn.prepareStatement(insertFreeFireSQL);
                psFreeFire.setInt(1, maTaiKhoan);
                psFreeFire.setString(2, new_ff_acc.getTenDangNhap());
                psFreeFire.setString(3, new_ff_acc.getMatKhau());
                psFreeFire.setBoolean(4, new_ff_acc.getCoTheVoCuc());
                psFreeFire.setInt(5, new_ff_acc.getSoSkinSung());
                psFreeFire.setString(6, new_ff_acc.getHangRank());
                psFreeFire.setString(7, new_ff_acc.getLoaiDangKy());
                psFreeFire.executeUpdate();
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
                if (psFreeFire != null) psFreeFire.close();
                if (psTaiKhoan != null) psTaiKhoan.close();
                if (conn != null) conn.setAutoCommit(true);
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
