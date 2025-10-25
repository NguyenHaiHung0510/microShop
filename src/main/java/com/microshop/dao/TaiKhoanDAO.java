package com.microshop.dao;

import com.microshop.context.DBContext;
import com.microshop.model.TaiKhoan;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) - Quản lý truy xuất dữ liệu từ bảng TAIKHOAN
 */
public class TaiKhoanDAO {

    /**
     * Lấy danh sách tài khoản theo mã danh mục
     */
    public List<TaiKhoan> getByMaDanhMuc(Integer maDanhMuc) {
        List<TaiKhoan> list = new ArrayList<>();
        String sql = "SELECT * FROM TAIKHOAN WHERE maDanhMuc = ?";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maDanhMuc);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToTaiKhoan(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Lấy 1 tài khoản theo mã tài khoản
     */
    public TaiKhoan getByMaTaiKhoan(Integer maTaiKhoan) {
        String sql = "SELECT * FROM TAIKHOAN WHERE maTaiKhoan = ?";
        TaiKhoan tk = null;

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maTaiKhoan);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tk = mapResultSetToTaiKhoan(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tk;
    }

    /**
     * Cập nhật trạng thái của tài khoản
     */
    public void capNhatTrangThai(Integer maTaiKhoan, String trangThai) {
        String sql = "UPDATE TAIKHOAN SET trangThai = ? WHERE maTaiKhoan = ?";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, trangThai);
            ps.setInt(2, maTaiKhoan);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Thêm tài khoản mới và trả về mã tài khoản được DB cấp
     */
    public Integer insert(TaiKhoan newAcc) {
        String sql = "INSERT INTO TAIKHOAN (maDanhMuc, giaGoc, giaBan, trangThai, diemNoiBat, luotXem, thoiGianDang) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        Integer generatedId = null;

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, newAcc.getMaDanhMuc());
            ps.setBigDecimal(2, newAcc.getGiaGoc());
            ps.setBigDecimal(3, newAcc.getGiaBan());
            ps.setString(4, newAcc.getTrangThai());
            ps.setString(5, newAcc.getDiemNoiBat());
            ps.setInt(6, newAcc.getLuotXem());
            ps.setTimestamp(7, Timestamp.valueOf(
                    newAcc.getThoiGianDang() != null ? newAcc.getThoiGianDang() : LocalDateTime.now())
            );

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    generatedId = rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return generatedId;
    }

    /**
     * Chuyển dữ liệu từ ResultSet sang đối tượng TaiKhoan
     */
    private TaiKhoan mapResultSetToTaiKhoan(ResultSet rs) throws SQLException {
        TaiKhoan tk = new TaiKhoan();
        tk.setMaTaiKhoan(rs.getInt("maTaiKhoan"));
        tk.setMaDanhMuc(rs.getInt("maDanhMuc"));
        tk.setGiaGoc(rs.getBigDecimal("giaGoc"));
        tk.setGiaBan(rs.getBigDecimal("giaBan"));
        tk.setTrangThai(rs.getString("trangThai"));
        tk.setDiemNoiBat(rs.getString("diemNoiBat"));
        tk.setLuotXem(rs.getInt("luotXem"));
        Timestamp ts = rs.getTimestamp("thoiGianDang");
        if (ts != null) {
            tk.setThoiGianDang(ts.toLocalDateTime());
        }
        return tk;
    }
}
