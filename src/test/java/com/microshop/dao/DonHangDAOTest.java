package com.microshop.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
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
import com.microshop.model.DonHang;

@ExtendWith(MockitoExtension.class)
class DonHangDAOTest {

    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement ps;
    @Mock
    private ResultSet rs;
    @Mock
    private ResultSet generatedKeysRs;

    @InjectMocks
    private DonHangDAO donHangDAO;

    private DonHang sampleDonHang;

    @BeforeEach
    void setUp() {
        sampleDonHang = new DonHang();
        sampleDonHang.setMaDonHang(1);
        sampleDonHang.setMaNguoiDung(1);
        sampleDonHang.setMaTaiKhoan(1);
        sampleDonHang.setGiaMua(new BigDecimal("100000"));
        sampleDonHang.setTrangThai("CHO_THANH_TOAN");
        sampleDonHang.setThoiGianTao(LocalDateTime.now());
    }

    private void mockDatabaseConnection() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(ps);
    }

    private void mockDatabaseConnectionWithKeys() throws SQLException {
        when(connection.prepareStatement(anyString(), eq(Statement.RETURN_GENERATED_KEYS))).thenReturn(ps);
    }

    @Test
    void getById_ReturnsDonHang_WhenFound() throws SQLException {
        // Arrange
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();

            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true);

            // SỬA: Mock rs.getObject thay vì rs.getInt
            when(rs.getObject("MaDonHang", Integer.class)).thenReturn(1);
            when(rs.getObject("MaNguoiDung", Integer.class)).thenReturn(1);
            when(rs.getObject("MaTaiKhoan", Integer.class)).thenReturn(1);
            when(rs.getString("TrangThai")).thenReturn("CHO_THANH_TOAN");

            // Act
            DonHang result = donHangDAO.getById(1);

            // Assert
            assertNotNull(result);
            assertEquals(1, result.getMaDonHang());
            assertEquals("CHO_THANH_TOAN", result.getTrangThai());

            // SỬA: Verify ps.setObject thay vì ps.setInt
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
            DonHang result = donHangDAO.getById(1);

            // Assert
            assertNull(result);
        }
    }

    @Test
    void getById_ThrowsSQLException_WhenDBError() throws SQLException {
        // (Không cần thay đổi)
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();

            when(ps.executeQuery()).thenThrow(new SQLException("Test DB Error"));

            // Act & Assert
            assertThrows(SQLException.class, () -> {
                donHangDAO.getById(1);
            });
        }
    }

    @Test
    void getAll_ReturnsListOfDonHang() throws SQLException {
        // Arrange
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();

            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false); // 2 bản ghi

            // Act
            List<DonHang> result = donHangDAO.getAll();

            // Assert
            assertNotNull(result);
            assertEquals(2, result.size());
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
            when(generatedKeysRs.getInt(1)).thenReturn(99); // ID được generate

            // Act
            Integer newId = donHangDAO.insert(sampleDonHang);

            // Assert
            assertNotNull(newId);
            assertEquals(99, newId);

            // SỬA: Verify ps.setObject
            verify(ps).setObject(1, sampleDonHang.getMaNguoiDung());
            verify(ps).setObject(2, sampleDonHang.getMaTaiKhoan());
        }
    }

    @Test
    void update_ReturnsTrue_WhenSuccessful() throws SQLException {
        // Arrange
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();

            when(ps.executeUpdate()).thenReturn(1); // 1 hàng bị ảnh hưởng

            // Act
            boolean success = donHangDAO.update(sampleDonHang);

            // Assert
            assertTrue(success);

            // SỬA: Verify ps.setObject
            verify(ps).setObject(1, sampleDonHang.getMaNguoiDung());
            verify(ps).setObject(2, sampleDonHang.getMaTaiKhoan());
            verify(ps).setObject(7, sampleDonHang.getMaDonHang());
        }
    }

    @Test
    void delete_ReturnsTrue_WhenSuccessful() throws SQLException {
        // Arrange
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();

            when(ps.executeUpdate()).thenReturn(1); // 1 hàng bị ảnh hưởng

            // Act
            boolean success = donHangDAO.delete(1);

            // Assert
            assertTrue(success);

            // SỬA: Verify ps.setObject
            verify(ps).setObject(1, 1);
        }
    }

    // ============ Tương tự cho 3 hàm custom ============
    @Test
    void getByMaNguoiDung_ReturnsList() throws SQLException {
        // Arrange
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();

            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true).thenReturn(false); // 1 bản ghi

            // Act
            List<DonHang> result = donHangDAO.getByMaNguoiDung(5);

            // Assert
            assertNotNull(result);
            assertEquals(1, result.size());

            // SỬA: Verify ps.setObject
            verify(ps).setObject(1, 5);
        }
    }

    @Test
    void getByMaTaiKhoan_ReturnsDonHang() throws SQLException {
        // Arrange
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();

            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true);

            // Act
            DonHang result = donHangDAO.getByMaTaiKhoan(10);

            // Assert
            assertNotNull(result);

            // SỬA: Verify ps.setObject
            verify(ps).setObject(1, 10);
        }
    }

    @Test
    void updateTrangThai_ReturnsTrue() throws SQLException {
        // Arrange
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();

            when(ps.executeUpdate()).thenReturn(1);
            LocalDateTime time = LocalDateTime.now();

            // Act
            boolean success = donHangDAO.updateTrangThai(1, "DA_HOAN_THANH", time);

            // Assert
            assertTrue(success);
            verify(ps).setString(1, "DA_HOAN_THANH");

            // SỬA: Verify ps.setObject
            verify(ps).setObject(3, 1);
        }
    }
}
