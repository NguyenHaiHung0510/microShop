package com.microshop.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.microshop.context.DBContext;
import com.microshop.model.HangThanhVien;

@ExtendWith(MockitoExtension.class)
class HangThanhVienDAOTest {

    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement ps;
    @Mock
    private ResultSet rs;

    @InjectMocks
    private HangThanhVienDAO hangThanhVienDAO;

    private void mockDatabaseConnection() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(ps);
    }

    @Test
    void getAll_ReturnsList_WhenDataExists() throws SQLException {
        // Arrange
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();

            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false); // 2 bản ghi

            // Act
            List<HangThanhVien> result = hangThanhVienDAO.getAll();

            // Assert
            assertNotNull(result);
            assertEquals(2, result.size());
        }
    }

    @Test
    void getAll_ReturnsEmptyList_WhenNoData() throws SQLException {
        // Arrange
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();

            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(false); // Không có bản ghi

            // Act
            List<HangThanhVien> result = hangThanhVienDAO.getAll();

            // Assert
            assertNotNull(result);
            assertTrue(result.isEmpty());
        }
    }

    @Test
    void getById_ReturnsHangThanhVien_WhenFound() throws SQLException {
        // Arrange
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();

            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true);
            when(rs.getObject("MaHang", Integer.class)).thenReturn(1);
            when(rs.getString("TenHang")).thenReturn("Đồng");

            // Act
            HangThanhVien result = hangThanhVienDAO.getById(1);

            // Assert
            assertNotNull(result);
            assertEquals(1, result.getMaHang());
            assertEquals("Đồng", result.getTenHang());
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
            HangThanhVien result = hangThanhVienDAO.getById(99);

            // Assert
            assertNull(result);
            verify(ps).setObject(1, 99);
        }
    }

    @Test
    void getAll_ThrowsSQLException_WhenDBError() throws SQLException {
        // Arrange
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();

            when(ps.executeQuery()).thenThrow(new SQLException("Test DB Error"));

            // Act & Assert
            assertThrows(SQLException.class, () -> {
                hangThanhVienDAO.getAll();
            });
        }
    }
}
