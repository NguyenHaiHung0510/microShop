package com.microshop.dao;

import com.microshop.context.DBContext;
import com.microshop.model.DanhMuc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO cho DanhMuc implement CrudDAO chuẩn
 * Phiên bản không dùng Logger — in lỗi ra console
 */
public class DanhMucDAO implements CrudDAO<DanhMuc, Integer> {

    // --- Map ResultSet → DanhMuc ---
    private DanhMuc mapResultSetToDanhMuc(ResultSet rs) throws SQLException {
        DanhMuc dm = new DanhMuc();
        dm.setMaDanhMuc(rs.getInt("MaDanhMuc"));
        dm.setTenDanhMuc(rs.getString("TenDanhMuc"));
        return dm;
    }

    private Connection getConnection() throws SQLException {
        return DBContext.getConnection();
    }

    // --- GET ALL ---
    @Override
    public List<DanhMuc> getAll() {
        List<DanhMuc> list = new ArrayList<>();
        String sql = "SELECT * FROM DANHMUC";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToDanhMuc(rs));
            }

        } catch (SQLException e) {
            System.out.println("❌ Lỗi SQL khi lấy tất cả danh mục: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    // --- GET BY ID ---
    @Override
    public DanhMuc getById(Integer id) {
        DanhMuc result = null;
        String sql = "SELECT * FROM DANHMUC WHERE MaDanhMuc = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = mapResultSetToDanhMuc(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("❌ Lỗi SQL khi lấy danh mục theo ID: " + id);
            e.printStackTrace();
        }
        return result;
    }

    // --- INSERT ---
    @Override
    public Integer insert(DanhMuc entity) {
        String sql = "INSERT INTO DANHMUC (TenDanhMuc) VALUES (?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, entity.getTenDanhMuc());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) return null;

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.out.println("❌ Lỗi SQL khi thêm danh mục: " + entity.getTenDanhMuc());
            e.printStackTrace();
        }
        return null;
    }

    // --- UPDATE ---
    @Override
    public boolean update(DanhMuc entity) {
        String sql = "UPDATE DANHMUC SET TenDanhMuc=? WHERE MaDanhMuc=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, entity.getTenDanhMuc());
            ps.setInt(2, entity.getMaDanhMuc());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Lỗi SQL khi cập nhật danh mục ID: " + entity.getMaDanhMuc());
            e.printStackTrace();
            return false;
        }
    }

    // --- DELETE ---
    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM DANHMUC WHERE MaDanhMuc=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Lỗi SQL khi xóa danh mục ID: " + id);
            e.printStackTrace();
            return false;
        }
    }

    // --- TÌM THEO PREFIX (OPTIONAL) ---
    public List<DanhMuc> getByPrefix(String prefix) {
        List<DanhMuc> list = new ArrayList<>();
        String sql = "SELECT * FROM DANHMUC WHERE TenDanhMuc LIKE ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, prefix + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToDanhMuc(rs));
                }
            }

        } catch (SQLException e) {
            System.out.println("❌ Lỗi SQL khi tìm danh mục theo prefix: " + prefix);
            e.printStackTrace();
        }
        return list;
    }
}
