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
import com.microshop.model.TaiKhoanLienQuan;

@ExtendWith(MockitoExtension.class)
class TaiKhoanLienQuanDAOTest {

    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement ps;
    @Mock
    private ResultSet rs;

    // Mock DAO cha, vì đây là một dependency
    @Mock
    private TaiKhoanDAO taiKhoanDAO;

    @InjectMocks
    private TaiKhoanLienQuanDAO taiKhoanLienQuanDAO;

    private TaiKhoanLienQuan sampleTKLQ;
    private final LocalDateTime timeNow = LocalDateTime.now();
    private final String sampleAnh = "path/to/lq.jpg"; // THÊM MỚI

    @BeforeEach
    void setUp() {
        sampleTKLQ = new TaiKhoanLienQuan();
        // Thuộc tính cha
        sampleTKLQ.setMaTaiKhoan(1);
        sampleTKLQ.setMaDanhMuc(2); // Danh mục Liên Quân
        sampleTKLQ.setGiaBan(new BigDecimal("200000"));
        sampleTKLQ.setTrangThai("DANG_BAN");
        sampleTKLQ.setLuotXem(50);
        sampleTKLQ.setThoiGianDang(timeNow);
        sampleTKLQ.setDuongDanAnh(sampleAnh); // THÊM MỚI
        // Thuộc tính con
        sampleTKLQ.setTenDangNhap("tklq_user");
        sampleTKLQ.setMatKhau("123456");
        sampleTKLQ.setHangRank("Cao Thủ");
        sampleTKLQ.setSoTuong(110);
        sampleTKLQ.setSoTrangPhuc(200);
    }

    private void mockDatabaseConnection() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(ps);
    }

    // Tiện ích mock đầy đủ cho mapResultSetToTaiKhoanLienQuan
    private void mockFullTKLQResultSet() throws SQLException {
        // Mock phần cha (TAIKHOAN)
        when(rs.getObject("MaTaiKhoan", Integer.class)).thenReturn(sampleTKLQ.getMaTaiKhoan());
        when(rs.getObject("MaDanhMuc", Integer.class)).thenReturn(sampleTKLQ.getMaDanhMuc());
        when(rs.getBigDecimal("GiaBan")).thenReturn(sampleTKLQ.getGiaBan());
        when(rs.getString("TrangThai")).thenReturn(sampleTKLQ.getTrangThai());
        when(rs.getObject("LuotXem", Integer.class)).thenReturn(sampleTKLQ.getLuotXem());
        when(rs.getTimestamp("ThoiGianDang")).thenReturn(Timestamp.valueOf(timeNow));
        when(rs.getBigDecimal("GiaGoc")).thenReturn(sampleTKLQ.getGiaGoc());
        when(rs.getString("DiemNoiBat")).thenReturn(sampleTKLQ.getDiemNoiBat());
        when(rs.getString("DuongDanAnh")).thenReturn(sampleTKLQ.getDuongDanAnh()); // THÊM MỚI

        // Mock phần con (TAIKHOAN_LIENQUAN)
        when(rs.getString("TenDangNhap")).thenReturn(sampleTKLQ.getTenDangNhap());
        when(rs.getString("MatKhau")).thenReturn(sampleTKLQ.getMatKhau());
        when(rs.getString("HangRank")).thenReturn(sampleTKLQ.getHangRank());
        when(rs.getObject("SoTuong", Integer.class)).thenReturn(sampleTKLQ.getSoTuong());
        when(rs.getObject("SoTrangPhuc", Integer.class)).thenReturn(sampleTKLQ.getSoTrangPhuc());
        when(rs.getObject("BacNgoc", Integer.class)).thenReturn(sampleTKLQ.getBacNgoc());
        when(rs.getString("LoaiDangKy")).thenReturn(sampleTKLQ.getLoaiDangKy());
    }

    // ============ Test CRUD ============
    @Test
    void getById_ReturnsTKLQ_WhenFound() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true);
            mockFullTKLQResultSet();

            TaiKhoanLienQuan result = taiKhoanLienQuanDAO.getById(1);

            assertNotNull(result);
            assertEquals(1, result.getMaTaiKhoan());
            assertEquals("Cao Thủ", result.getHangRank());
            assertEquals(110, result.getSoTuong());
            assertEquals(sampleAnh, result.getDuongDanAnh()); // THÊM MỚI: Assert
            verify(ps).setObject(1, 1);
        }
    }

    @Test
    void getAll_ReturnsList() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true).thenReturn(false); // 1 bản ghi
            mockFullTKLQResultSet();

            List<TaiKhoanLienQuan> result = taiKhoanLienQuanDAO.getAll();

            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals("Cao Thủ", result.get(0).getHangRank());
            assertEquals(sampleAnh, result.get(0).getDuongDanAnh()); // THÊM MỚI: Assert
        }
    }

    @Test
    void insert_ReturnsGeneratedId() throws SQLException {
        // ... (Không thay đổi logic, vì taiKhoanDAO đã được mock)
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            // 1. Mock DAO cha
            when(taiKhoanDAO.insert(any(TaiKhoan.class))).thenReturn(99); // Trả về ID mới

            // 2. Mock DAO con
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection(); // Không cần keys
            when(ps.executeUpdate()).thenReturn(1);

            // Act
            Integer newId = taiKhoanLienQuanDAO.insert(sampleTKLQ);

            // Assert
            assertNotNull(newId);
            assertEquals(99, newId);

            // Verify DAO cha được gọi
            verify(taiKhoanDAO).insert(any(TaiKhoan.class));

            // Verify DAO con được gọi với ID đúng
            verify(ps).setObject(1, 99); // MaTaiKhoan
            verify(ps).setString(2, "tklq_user"); // TenDangNhap
            verify(ps).setObject(5, 110); // SoTuong
        }
    }

    @Test
    void update_ReturnsTrue() throws SQLException {
        // ... (Không thay đổi logic, vì taiKhoanDAO đã được mock)
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            // 1. Mock DAO cha
            when(taiKhoanDAO.update(any(TaiKhoan.class))).thenReturn(true);

            // 2. Mock DAO con
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeUpdate()).thenReturn(1);

            // Act
            boolean success = taiKhoanLienQuanDAO.update(sampleTKLQ);

            // Assert
            assertTrue(success);

            // Verify DAO cha được gọi
            verify(taiKhoanDAO).update(any(TaiKhoan.class));

            // Verify DAO con được gọi
            verify(ps).setObject(8, 1); // MaTaiKhoan (WHERE)
            verify(ps).setString(3, "Cao Thủ"); // HangRank
        }
    }

    @Test
    void delete_ReturnsTrue() throws SQLException {
        // ... (Không thay đổi)
        // 1. Mock DAO cha
        when(taiKhoanDAO.delete(1)).thenReturn(true);

        // Act
        boolean success = taiKhoanLienQuanDAO.delete(1);

        // Assert
        assertTrue(success);

        // Verify CHỈ DAO cha được gọi
        verify(taiKhoanDAO, times(1)).delete(1);
    }

    // ============ Test Custom ============
    @Test
    void getByTrangThai_ReturnsList() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true).thenReturn(false); // 1 bản ghi
            mockFullTKLQResultSet();

            List<TaiKhoanLienQuan> result = taiKhoanLienQuanDAO.getByTrangThai("DANG_BAN");

            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(sampleAnh, result.get(0).getDuongDanAnh()); // THÊM MỚI: Assert
            verify(ps).setString(1, "DANG_BAN");
        }
    }

    @Test
    void updateTrangThai_CallsParentDAO() throws SQLException {
        // ... (Không thay đổi)
        // Không cần mock DBContext vì hàm này chỉ gọi DAO cha

        // Act
        taiKhoanLienQuanDAO.updateTrangThai(1, "DA_BAN");

        // Verify
        verify(taiKhoanDAO, times(1)).updateTrangThai(1, "DA_BAN");
    }
}
