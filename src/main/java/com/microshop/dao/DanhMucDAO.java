package com.microshop.dao;

import com.microshop.context.DBContext;
import com.microshop.model.DanhMuc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DanhMucDAO implements DAO<DanhMuc> { 
    
    private static final Logger LOGGER = Logger.getLogger(DanhMucDAO.class.getName());
    
    // --- HÀM ÁNH XẠ DỮ LIỆU TỪ RESULTSET ---
    private DanhMuc mapResultSetToDanhMuc(ResultSet rs) throws SQLException {
        DanhMuc dm = new DanhMuc();
        dm.setMaDanhMuc(rs.getInt("MaDanhMuc"));
        dm.setTenDanhMuc(rs.getString("TenDanhMuc"));
        return dm;
    }
    
    private Connection getConnection() throws SQLException {
        return DBContext.getConnection();
    }

    // --- LẤY TẤT CẢ DANHMUC ---
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
            LOGGER.log(Level.SEVERE, "Lỗi SQL khi lấy tất cả danh mục.", e);
        }
        return list;
    }

    // --- LẤY THEO ID ---
    @Override
    public DanhMuc getById(String id) {
        DanhMuc result = null;
        String sql = "SELECT * FROM DANHMUC WHERE MaDanhMuc = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(id));

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = mapResultSetToDanhMuc(rs);
                }
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi SQL khi lấy danh mục theo ID: " + id, e);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, "ID không phải là số hợp lệ: " + id, e);
        }
        return result;
    }

    // --- THÊM DANHMUC ---
    @Override
    public boolean create(DanhMuc entity) {
        String sql = "INSERT INTO DANHMUC (TenDanhMuc) VALUES (?)";
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {           
            ps.setString(1, entity.getTenDanhMuc());
            
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi SQL khi tạo danh mục: " + entity.getTenDanhMuc(), e);
            return false;
        }
    }

    // --- CẬP NHẬT DANHMUC ---
    @Override
    public boolean update(DanhMuc entity) {
        String sql = "UPDATE DANHMUC SET TenDanhMuc=? WHERE MaDanhMuc=?";
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, entity.getTenDanhMuc());
            ps.setInt(2, entity.getMaDanhMuc());

            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi SQL khi cập nhật danh mục ID: " + entity.getMaDanhMuc(), e);
            return false;
        }
    }

    // --- XÓA DANHMUC ---
    @Override
    public boolean delete(String id) {
        String sql = "DELETE FROM DANHMUC WHERE MaDanhMuc = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, Integer.parseInt(id)); 
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi SQL khi xóa danh mục ID: " + id, e);
            return false;
        } catch (NumberFormatException e) {
             LOGGER.log(Level.WARNING, "ID xóa không phải là số hợp lệ: " + id, e);
             return false;
        }
    }
    
    // --- TÌM KIẾM DANHMUC THEO PREFIX ---
    @Override
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
            LOGGER.log(Level.SEVERE, "Lỗi SQL khi tìm danh mục theo prefix: " + prefix, e);
        }
        return list;
    }
}
