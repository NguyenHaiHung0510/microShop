package com.microshop.dao;

import com.microshop.context.DBContext;
import com.microshop.model.TaiKhoanLienQuan;

import java.sql.*;
import java.time.LocalDateTime;

public class TaiKhoanLienQuanDAO {

    /**
     * Lấy tài khoản Liên Quân theo mã tài khoản
     * @param maTaiKhoan mã tài khoản cần tìm
     * @return TaiKhoanLienQuan nếu có, ngược lại null
     */
    public TaiKhoanLienQuan getByMaTaiKhoan(Integer maTaiKhoan) {
        String sql = "SELECT tk.*, lq.* FROM TAIKHOAN tk " +
                "JOIN TAIKHOAN_LIENQUAN lq ON tk.maTaiKhoan = lq.maTaiKhoan " +
                "WHERE tk.maTaiKhoan = ?";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maTaiKhoan);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    TaiKhoanLienQuan acc = new TaiKhoanLienQuan();

                    // ---- Bảng TAIKHOAN ----
                    acc.setMaTaiKhoan(rs.getInt("maTaiKhoan"));
                    acc.setMaDanhMuc(rs.getInt("maDanhMuc"));
                    acc.setGiaGoc(rs.getBigDecimal("giaGoc"));
                    acc.setGiaBan(rs.getBigDecimal("giaBan"));
                    acc.setTrangThai(rs.getString("trangThai"));
                    acc.setDiemNoiBat(rs.getString("diemNoiBat"));
                    acc.setLuotXem(rs.getInt("luotXem"));
                    Timestamp ts = rs.getTimestamp("thoiGianDang");
                    if (ts != null) {
                        acc.setThoiGianDang(ts.toLocalDateTime());
                    }

                    // ---- Bảng TAIKHOAN_LIENQUAN ----
                    acc.setTenDangNhap(rs.getString("tenDangNhap"));
                    acc.setMatKhau(rs.getString("matKhau"));
                    acc.setHangRank(rs.getString("hangRank"));
                    acc.setSoTuong(rs.getInt("soTuong"));
                    acc.setSoTrangPhuc(rs.getInt("soTrangPhuc"));
                    acc.setBacNgoc(rs.getInt("bacNgoc"));
                    acc.setLoaiDangKy(rs.getString("loaiDangKy"));

                    return acc;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Thêm mới một tài khoản Liên Quân
     * - Insert vào bảng TAIKHOAN trước để lấy maTaiKhoan
     * - Sau đó insert chi tiết vào bảng TAIKHOAN_LIENQUAN
     */
    public void insert(TaiKhoanLienQuan new_lq_acc) {
        String insertTaiKhoanSQL = """
                INSERT INTO TAIKHOAN (maDanhMuc, giaGoc, giaBan, trangThai, diemNoiBat, luotXem, thoiGianDang)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        String insertLienQuanSQL = """
                INSERT INTO TAIKHOAN_LIENQUAN (maTaiKhoan, tenDangNhap, matKhau, hangRank, soTuong, soTrangPhuc, bacNgoc, loaiDangKy)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = DBContext.getConnection()) {
            conn.setAutoCommit(false);

            // ---- Insert TAIKHOAN ----
            try (PreparedStatement psTaiKhoan = conn.prepareStatement(insertTaiKhoanSQL, Statement.RETURN_GENERATED_KEYS)) {
                psTaiKhoan.setInt(1, new_lq_acc.getMaDanhMuc());
                psTaiKhoan.setBigDecimal(2, new_lq_acc.getGiaGoc());
                psTaiKhoan.setBigDecimal(3, new_lq_acc.getGiaBan());
                psTaiKhoan.setString(4, new_lq_acc.getTrangThai());
                psTaiKhoan.setString(5, new_lq_acc.getDiemNoiBat());
                psTaiKhoan.setInt(6, new_lq_acc.getLuotXem());
                psTaiKhoan.setTimestamp(7, Timestamp.valueOf(
                        new_lq_acc.getThoiGianDang() != null ? new_lq_acc.getThoiGianDang() : LocalDateTime.now())
                );

                psTaiKhoan.executeUpdate();

                try (ResultSet generatedKeys = psTaiKhoan.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int maTaiKhoan = generatedKeys.getInt(1);
                        new_lq_acc.setMaTaiKhoan(maTaiKhoan);

                        // ---- Insert TAIKHOAN_LIENQUAN ----
                        try (PreparedStatement psLienQuan = conn.prepareStatement(insertLienQuanSQL)) {
                            psLienQuan.setInt(1, maTaiKhoan);
                            psLienQuan.setString(2, new_lq_acc.getTenDangNhap());
                            psLienQuan.setString(3, new_lq_acc.getMatKhau());
                            psLienQuan.setString(4, new_lq_acc.getHangRank());
                            psLienQuan.setInt(5, new_lq_acc.getSoTuong());
                            psLienQuan.setInt(6, new_lq_acc.getSoTrangPhuc());
                            psLienQuan.setInt(7, new_lq_acc.getBacNgoc());
                            psLienQuan.setString(8, new_lq_acc.getLoaiDangKy());
                            psLienQuan.executeUpdate();
                        }
                    }
                }
            }

            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
