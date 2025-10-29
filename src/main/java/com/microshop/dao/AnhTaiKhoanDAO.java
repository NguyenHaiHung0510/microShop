package com.microshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.microshop.context.DBContext;
import com.microshop.model.AnhTaiKhoan;

/**
 * DAO cho bảng ANH_TAIKHOAN — chuẩn hóa theo phong cách HangThanhVienDAO.
 * Không implement interface, in lỗi ra console thay vì throw.
 */
public class AnhTaiKhoanDAO {

    // --- Ánh xạ ResultSet → Model ---
    private AnhTaiKhoan mapResultSetToAnhTaiKhoan(ResultSet rs) throws SQLException {
        AnhTaiKhoan img = new AnhTaiKhoan();
        img.setMaAnh(rs.getInt("MaAnh"));
        img.setMaTaiKhoan(rs.getInt("MaTaiKhoan"));
        img.setDuongDanAnh(rs.getString("DuongDanAnh"));
        return img;
    }

    // --- Lấy connection từ DBContext (static) ---
    private Connection getConnection() throws SQLException {
        return DBContext.getConnection(); // ✅ giống hệt HangThanhVienDAO
    }

    // --- Lấy tất cả ảnh của 1 tài khoản ---
    public List<AnhTaiKhoan> getByMaTaiKhoan(Integer maTaiKhoan) {
        List<AnhTaiKhoan> list = new ArrayList<>();
        String sql = "SELECT * FROM ANH_TAIKHOAN WHERE MaTaiKhoan = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maTaiKhoan);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToAnhTaiKhoan(rs));
                }
            }

        } catch (SQLException e) {
            System.out.println("❌ Lỗi SQL khi lấy ảnh của tài khoản ID = " + maTaiKhoan);
            e.printStackTrace();
        }
        return list;
    }

    // --- Thêm ảnh mới, trả về mã ảnh nếu thành công ---
    public Integer insert(AnhTaiKhoan newImg) {
        String sql = "INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES (?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, newImg.getMaTaiKhoan());
            ps.setString(2, newImg.getDuongDanAnh());

            int affected = ps.executeUpdate();
            if (affected == 0) return null;

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("❌ Lỗi SQL khi thêm ảnh mới cho tài khoản ID = " + newImg.getMaTaiKhoan());
            e.printStackTrace();
        }
        return null;
    }

    // --- Xóa ảnh theo mã ảnh ---
    public boolean deleteByMaAnh(Integer maAnh) {
        String sql = "DELETE FROM ANH_TAIKHOAN WHERE MaAnh = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maAnh);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Lỗi SQL khi xóa ảnh có ID = " + maAnh);
            e.printStackTrace();
            return false;
        }
    }

    // --- Xóa tất cả ảnh của 1 tài khoản ---
    public boolean deleteByMaTaiKhoan(Integer maTaiKhoan) {
        String sql = "DELETE FROM ANH_TAIKHOAN WHERE MaTaiKhoan = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maTaiKhoan);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Lỗi SQL khi xóa toàn bộ ảnh của tài khoản ID = " + maTaiKhoan);
            e.printStackTrace();
            return false;
        }
    }
}
