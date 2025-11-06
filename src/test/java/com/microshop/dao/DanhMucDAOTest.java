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
import com.microshop.model.DanhMuc;

@ExtendWith(MockitoExtension.class)
class DanhMucDAOTest {

    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement ps;
    @Mock
    private ResultSet rs;

    @InjectMocks
    private DanhMucDAO danhMucDAO;

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
            when(rs.next()).thenReturn(true).thenReturn(false); // 1 bản ghi

            // Act
            List<DanhMuc> result = danhMucDAO.getAll();

            // Assert
            assertNotNull(result);
            assertEquals(1, result.size());
        }
    }

    @Test
    void getById_ReturnsDanhMuc_WhenFound() throws SQLException {
        // Arrange
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();

            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true);
            when(rs.getObject("MaDanhMuc", Integer.class)).thenReturn(1);
            when(rs.getString("TenDanhMuc")).thenReturn("Tài khoản Liên Quân");

            // Act
            DanhMuc result = danhMucDAO.getById(1);

            // Assert
            assertNotNull(result);
            assertEquals(1, result.getMaDanhMuc());
            assertEquals("Tài khoản Liên Quân", result.getTenDanhMuc());
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
            DanhMuc result = danhMucDAO.getById(99);

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
                danhMucDAO.getAll();
            });
        }
    }
}
