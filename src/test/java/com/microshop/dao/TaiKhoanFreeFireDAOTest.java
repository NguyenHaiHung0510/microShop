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
import com.microshop.model.TaiKhoanFreeFire;

@ExtendWith(MockitoExtension.class)
class TaiKhoanFreeFireDAOTest {

    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement ps;
    @Mock
    private ResultSet rs;

    @Mock
    private TaiKhoanDAO taiKhoanDAO;

    @InjectMocks
    private TaiKhoanFreeFireDAO taiKhoanFreeFireDAO;

    private TaiKhoanFreeFire sampleTKFF;
    private final LocalDateTime timeNow = LocalDateTime.now();

    @BeforeEach
    void setUp() {
        sampleTKFF = new TaiKhoanFreeFire();
        // Thuộc tính cha
        sampleTKFF.setMaTaiKhoan(1);
        sampleTKFF.setMaDanhMuc(1); // Danh mục Free Fire
        sampleTKFF.setGiaBan(new BigDecimal("150000"));
        sampleTKFF.setTrangThai("DANG_BAN");
        sampleTKFF.setThoiGianDang(timeNow);
        // Thuộc tính con
        sampleTKFF.setTenDangNhap("tkff_user");
        sampleTKFF.setMatKhau("123");
        sampleTKFF.setCoTheVoCuc(true);
        sampleTKFF.setSoSkinSung(50);
    }

    private void mockDatabaseConnection() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(ps);
    }

    private void mockFullTKFFResultSet() throws SQLException {
        // Mock phần cha (TAIKHOAN)
        when(rs.getObject("MaTaiKhoan", Integer.class)).thenReturn(sampleTKFF.getMaTaiKhoan());
        when(rs.getObject("MaDanhMuc", Integer.class)).thenReturn(sampleTKFF.getMaDanhMuc());
        when(rs.getBigDecimal("GiaBan")).thenReturn(sampleTKFF.getGiaBan());
        when(rs.getString("TrangThai")).thenReturn(sampleTKFF.getTrangThai());
        when(rs.getTimestamp("ThoiGianDang")).thenReturn(Timestamp.valueOf(timeNow));
        when(rs.getBigDecimal("GiaGoc")).thenReturn(sampleTKFF.getGiaGoc());
        when(rs.getString("DiemNoiBat")).thenReturn(sampleTKFF.getDiemNoiBat());
        when(rs.getObject("LuotXem", Integer.class)).thenReturn(sampleTKFF.getLuotXem());

        // Mock phần con (TAIKHOAN_FREEFIRE)
        when(rs.getString("TenDangNhap")).thenReturn(sampleTKFF.getTenDangNhap());
        when(rs.getString("MatKhau")).thenReturn(sampleTKFF.getMatKhau());
        when(rs.getBoolean("CoTheVoCuc")).thenReturn(sampleTKFF.getCoTheVoCuc());
        when(rs.getObject("SoSkinSung", Integer.class)).thenReturn(sampleTKFF.getSoSkinSung());
        when(rs.getString("HangRank")).thenReturn(sampleTKFF.getHangRank());
        when(rs.getString("LoaiDangKy")).thenReturn(sampleTKFF.getLoaiDangKy());
    }

    // ============ Test CRUD ============
    @Test
    void getById_ReturnsTKFF_WhenFound() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true);
            mockFullTKFFResultSet();

            TaiKhoanFreeFire result = taiKhoanFreeFireDAO.getById(1);

            assertNotNull(result);
            assertEquals(1, result.getMaTaiKhoan());
            assertEquals(true, result.getCoTheVoCuc());
            assertEquals(50, result.getSoSkinSung());
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
            mockFullTKFFResultSet();

            List<TaiKhoanFreeFire> result = taiKhoanFreeFireDAO.getAll();

            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(50, result.get(0).getSoSkinSung());
        }
    }

    @Test
    void insert_ReturnsGeneratedId() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            when(taiKhoanDAO.insert(any(TaiKhoan.class))).thenReturn(99);

            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeUpdate()).thenReturn(1);

            Integer newId = taiKhoanFreeFireDAO.insert(sampleTKFF);

            assertNotNull(newId);
            assertEquals(99, newId);
            verify(taiKhoanDAO).insert(any(TaiKhoan.class));
            verify(ps).setObject(1, 99); // MaTaiKhoan
            verify(ps).setObject(5, 50); // SoSkinSung
            verify(ps).setBoolean(4, true); // CoTheVoCuc
        }
    }

    @Test
    void update_ReturnsTrue() throws SQLException {
        try (MockedStatic<DBContext> mockedDBContext = Mockito.mockStatic(DBContext.class)) {
            when(taiKhoanDAO.update(any(TaiKhoan.class))).thenReturn(true);

            mockedDBContext.when(DBContext::getConnection).thenReturn(connection);
            mockDatabaseConnection();
            when(ps.executeUpdate()).thenReturn(1);

            boolean success = taiKhoanFreeFireDAO.update(sampleTKFF);

            assertTrue(success);
            verify(taiKhoanDAO).update(any(TaiKhoan.class));
            verify(ps).setObject(7, 1); // MaTaiKhoan (WHERE)
            verify(ps).setObject(4, 50); // SoSkinSung
        }
    }

    @Test
    void delete_ReturnsTrue() throws SQLException {
        when(taiKhoanDAO.delete(1)).thenReturn(true);

        boolean success = taiKhoanFreeFireDAO.delete(1);

        assertTrue(success);
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
            mockFullTKFFResultSet();

            List<TaiKhoanFreeFire> result = taiKhoanFreeFireDAO.getByTrangThai("DANG_BAN");

            assertNotNull(result);
            assertEquals(1, result.size());
            verify(ps).setString(1, "DANG_BAN");
        }
    }

    @Test
    void updateTrangThai_CallsParentDAO() throws SQLException {
        taiKhoanFreeFireDAO.updateTrangThai(1, "DA_BAN");
        verify(taiKhoanDAO, times(1)).updateTrangThai(1, "DA_BAN");
    }
}
