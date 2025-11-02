package com.microshop.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.microshop.context.DBContext;
import com.microshop.model.TaiKhoan;

@ExtendWith(MockitoExtension.class)
class TaiKhoanDAOTest {

    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement ps;
    @Mock
    private ResultSet rs;
    @Mock
    private ResultSet generatedKeysRs;

    @InjectMocks
    private TaiKhoanDAO taiKhoanDAO;

    private TaiKhoan sampleTaiKhoan;
    private final LocalDateTime timeNow = LocalDateTime.now();
    private final String sampleAnh = "path/to/image.jpg"; // THÊM MỚI

    @BeforeEach
    void setUp() {
        sampleTaiKhoan = new TaiKhoan();
        sampleTaiKhoan.setMaTaiKhoan(1);
        sampleTaiKhoan.setMaDanhMuc(1);
        sampleTaiKhoan.setGiaBan(new BigDecimal("100000"));
        sampleTaiKhoan.setGiaGoc(new BigDecimal("150000"));
        sampleTaiKhoan.setTrangThai("DANG_BAN");
        sampleTaiKhoan.setLuotXem(10);
        sampleTaiKhoan.setThoiGianDang(timeNow);
        sampleTaiKhoan.setDuongDanAnh(sampleAnh); // THÊM MỚI
    }

    private void mockDatabaseConnection() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(ps);
    }

    private void mockDatabaseConnectionWithKeys() throws SQLException {
        when(connection.prepareStatement(anyString(), eq(Statement.RETURN_GENERATED_KEYS))).thenReturn(ps);
    }

    // Tiện ích mock đầy đủ cho mapResultSetToTaiKhoan
    private void mockFullTaiKhoanResultSet() throws SQLException {
        when(rs.getObject("MaTaiKhoan", Integer.class)).thenReturn(sampleTaiKhoan.getMaTaiKhoan());
        when(rs.getObject("MaDanhMuc", Integer.class)).thenReturn(sampleTaiKhoan.getMaDanhMuc());
        when(rs.getBigDecimal("GiaGoc")).thenReturn(sampleTaiKhoan.getGiaGoc());
        when(rs.getBigDecimal("GiaBan")).thenReturn(sampleTaiKhoan.getGiaBan());
        when(rs.getString("TrangThai")).thenReturn(sampleTaiKhoan.getTrangThai());
        when(rs.getString("DiemNoiBat")).thenReturn(sampleTaiKhoan.getDiemNoiBat());
        when(rs.getObject("LuotXem", Integer.class)).thenReturn(sampleTaiKhoan.getLuotXem());
        when(rs.getTimestamp("ThoiGianDang")).thenReturn(Timestamp.valueOf(timeNow));
        when(rs.getString("DuongDanAnh")).thenReturn(sampleTaiKhoan.getDuongDanAnh()); // THÊM MỚI
    }

    // ============ Test CRUD ============
    @Test
    void getById_ReturnsTaiKhoan_WhenFound() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true);
            mockFullTaiKhoanResultSet(); // Mock đầy đủ các trường

            TaiKhoan result = taiKhoanDAO.getById(1);

            assertNotNull(result);
            assertEquals(1, result.getMaTaiKhoan());
            assertEquals("DANG_BAN", result.getTrangThai());
            assertEquals(sampleAnh, result.getDuongDanAnh()); // THÊM MỚI: Assert
            verify(ps).setObject(1, 1);
        }
    }

    @Test
    void getById_ReturnsNull_WhenNotFound() throws SQLException {
        // ... (Không thay đổi)
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(false);

            TaiKhoan result = taiKhoanDAO.getById(99);

            assertNull(result);
            verify(ps).setObject(1, 99);
        }
    }

    @Test
    void getAll_ReturnsList() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true).thenReturn(false); // 1 bản ghi
            mockFullTaiKhoanResultSet();

            List<TaiKhoan> result = taiKhoanDAO.getAll();

            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(sampleAnh, result.get(0).getDuongDanAnh()); // THÊM MỚI: Assert
        }
    }

    @Test
    void insert_ReturnsGeneratedId() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnectionWithKeys();
            when(ps.executeUpdate()).thenReturn(1);
            when(ps.getGeneratedKeys()).thenReturn(generatedKeysRs);
            when(generatedKeysRs.next()).thenReturn(true);
            when(generatedKeysRs.getInt(1)).thenReturn(99);

            Integer newId = taiKhoanDAO.insert(sampleTaiKhoan);

            assertNotNull(newId);
            assertEquals(99, newId);
            verify(ps).setObject(1, 1); // MaDanhMuc
            verify(ps).setObject(6, 10); // LuotXem
            verify(ps).setString(8, sampleAnh); // CẬP NHẬT: Kiểm tra DuongDanAnh
        }
    }

    @Test
    void update_ReturnsTrue() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeUpdate()).thenReturn(1);

            boolean success = taiKhoanDAO.update(sampleTaiKhoan);

            assertTrue(success);
            verify(ps).setObject(1, 1); // MaDanhMuc
            verify(ps).setObject(6, 10); // LuotXem
            verify(ps).setString(8, sampleAnh); // CẬP NHẬT: DuongDanAnh
            verify(ps).setObject(9, 1); // CẬP NHẬT: MaTaiKhoan (WHERE)
        }
    }

    @Test
    void delete_ReturnsTrue() throws SQLException {
        // ... (Không thay đổi)
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeUpdate()).thenReturn(1);

            boolean success = taiKhoanDAO.delete(1);

            assertTrue(success);
            verify(ps).setObject(1, 1);
        }
    }

    // ============ Test Custom ============
    @Test
    void getByMaDanhMuc_ReturnsList() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true).thenReturn(false); // 1 bản ghi
            mockFullTaiKhoanResultSet();

            List<TaiKhoan> result = taiKhoanDAO.getByMaDanhMuc(1);

            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(sampleAnh, result.get(0).getDuongDanAnh()); // THÊM MỚI: Assert
            verify(ps).setObject(1, 1);
        }
    }

    @Test
    void getByTrangThai_ReturnsList() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true).thenReturn(false); // 1 bản ghi
            mockFullTaiKhoanResultSet();

            List<TaiKhoan> result = taiKhoanDAO.getByTrangThai("DANG_BAN");

            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(sampleAnh, result.get(0).getDuongDanAnh()); // THÊM MỚI: Assert
            verify(ps).setString(1, "DANG_BAN");
        }
    }

    @Test
    void updateTrangThai_ExecutesUpdate() throws SQLException {
        // ... (Không thay đổi)
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeUpdate()).thenReturn(1);

            taiKhoanDAO.updateTrangThai(1, "DA_BAN");

            verify(ps).setString(1, "DA_BAN");
            verify(ps).setObject(2, 1);
            verify(ps, times(1)).executeUpdate(); // Đảm bảo nó được gọi
        }
    }
}
