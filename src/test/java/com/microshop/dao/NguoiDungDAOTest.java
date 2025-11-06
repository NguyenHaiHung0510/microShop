package com.microshop.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.microshop.context.DBContext;
import com.microshop.model.NguoiDung;

@ExtendWith(MockitoExtension.class)
class NguoiDungDAOTest {

    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement ps;
    @Mock
    private ResultSet rs;
    @Mock
    private ResultSet generatedKeysRs;

    @InjectMocks
    private NguoiDungDAO nguoiDungDAO;

    private NguoiDung sampleNguoiDung;

    @BeforeEach
    void setUp() {
        sampleNguoiDung = new NguoiDung();
        sampleNguoiDung.setMaNguoiDung(1);
        sampleNguoiDung.setTenDangNhap("testuser");
        sampleNguoiDung.setMatKhau("hashedpassword");
        sampleNguoiDung.setEmail("test@example.com");
        sampleNguoiDung.setMaHangThanhVien(1);
    }

    private void mockDatabaseConnection() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(ps);
    }

    private void mockDatabaseConnectionWithKeys() throws SQLException {
        when(connection.prepareStatement(anyString(), eq(Statement.RETURN_GENERATED_KEYS))).thenReturn(ps);
    }

    // --- Test 5 hàm CRUD ---
    @Test
    void getById_ReturnsNguoiDung_WhenFound() throws SQLException {
        // Arrange
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();

            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true);
            when(rs.getObject("MaNguoiDung", Integer.class)).thenReturn(1);
            when(rs.getString("TenDangNhap")).thenReturn("testuser");

            // Act
            NguoiDung result = nguoiDungDAO.getById(1);

            // Assert
            assertNotNull(result);
            assertEquals("testuser", result.getTenDangNhap());
            verify(ps).setObject(1, 1);
        }
    }

    @Test
    void getById_ReturnsNull_WhenNotFound() throws SQLException {
        // Arrange
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();

            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(false);

            // Act
            NguoiDung result = nguoiDungDAO.getById(99);

            // Assert
            assertNull(result);
            verify(ps).setObject(1, 99);
        }
    }

    @Test
    void insert_ReturnsGeneratedId_WhenSuccessful() throws SQLException {
        // Arrange
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnectionWithKeys();

            when(ps.executeUpdate()).thenReturn(1);
            when(ps.getGeneratedKeys()).thenReturn(generatedKeysRs);
            when(generatedKeysRs.next()).thenReturn(true);
            when(generatedKeysRs.getInt(1)).thenReturn(10); // ID mới

            // Act
            Integer newId = nguoiDungDAO.insert(sampleNguoiDung);

            // Assert
            assertNotNull(newId);
            assertEquals(10, newId);
            verify(ps).setString(1, "testuser");
            verify(ps).setObject(7, 1); // Test MaHangThanhVien
        }
    }

    @Test
    void update_ReturnsTrue_WhenSuccessful() throws SQLException {
        // Arrange
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();

            when(ps.executeUpdate()).thenReturn(1);

            // Act
            boolean success = nguoiDungDAO.update(sampleNguoiDung);

            // Assert
            assertTrue(success);
            verify(ps).setString(1, "testuser");
            verify(ps).setObject(9, 1); // Test MaNguoiDung ở WHERE
        }
    }

    @Test
    void delete_ReturnsTrue_WhenSuccessful() throws SQLException {
        // Arrange
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();

            when(ps.executeUpdate()).thenReturn(1);

            // Act
            boolean success = nguoiDungDAO.delete(1);

            // Assert
            assertTrue(success);
            verify(ps).setObject(1, 1);
        }
    }

    // --- Test 2 hàm Custom ---
    @Test
    void getByTenDangNhap_ReturnsNguoiDung_WhenFound() throws SQLException {
        // Arrange
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();

            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true);
            when(rs.getString("TenDangNhap")).thenReturn("testuser");

            // Act
            NguoiDung result = nguoiDungDAO.getByTenDangNhap("testuser");

            // Assert
            assertNotNull(result);
            assertEquals("testuser", result.getTenDangNhap());
            verify(ps).setString(1, "testuser");
        }
    }

    @Test
    void getByEmail_ReturnsNguoiDung_WhenFound() throws SQLException {
        // Arrange
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();

            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true);

            // SỬA: Chúng ta cần "dạy" cho rs biết trả lời TẤT CẢ các cột
            // mà hàm mapResultSetToNguoiDung sẽ hỏi.
            when(rs.getObject("MaNguoiDung", Integer.class)).thenReturn(1);
            when(rs.getString("TenDangNhap")).thenReturn("testuser");
            when(rs.getString("MatKhau")).thenReturn("password");
            when(rs.getString("Email")).thenReturn("test@example.com"); // Đây là cột ta thực sự quan tâm
            when(rs.getString("SoDienThoai")).thenReturn("123456");
            when(rs.getString("VaiTro")).thenReturn("USER");
            when(rs.getBigDecimal("TongTienDaChi")).thenReturn(null);
            when(rs.getObject("MaHangThanhVien", Integer.class)).thenReturn(1);
            when(rs.getTimestamp("ThoiGianTao")).thenReturn(null);

            // Act
            NguoiDung result = nguoiDungDAO.getByEmail("test@example.com");

            // Assert
            assertNotNull(result);
            assertEquals("test@example.com", result.getEmail());
            verify(ps).setString(1, "test@example.com");
        }
    }
}
