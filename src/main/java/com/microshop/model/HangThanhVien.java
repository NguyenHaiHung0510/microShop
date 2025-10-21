package com.microshop.model;

import java.math.BigDecimal;

// test DAO thôi, class này gpt gen
// Viết lại cái này nhé

/**
 * Lớp mô tả bảng HANGTHANHVIEN trong cơ sở dữ liệu.
 *
 * Cấu trúc bảng: MaHang (INT, PK) TenHang (VARCHAR) MucChiTieuToiThieu
 * (DECIMAL) DuongDanIcon (VARCHAR) ChietKhau (DECIMAL)
 */
public class HangThanhVien {

    private int maHang;
    private String tenHang;
    private BigDecimal mucChiTieuToiThieu;
    private String duongDanIcon;
    private BigDecimal chietKhau;

    // ===== Constructor =====
    public HangThanhVien() {
    }

    public HangThanhVien(int maHang, String tenHang, BigDecimal mucChiTieuToiThieu,
            String duongDanIcon, BigDecimal chietKhau) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.mucChiTieuToiThieu = mucChiTieuToiThieu;
        this.duongDanIcon = duongDanIcon;
        this.chietKhau = chietKhau;
    }

    // ===== Getter & Setter =====
    public int getMaHang() {
        return maHang;
    }

    public void setMaHang(int maHang) {
        this.maHang = maHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public BigDecimal getMucChiTieuToiThieu() {
        return mucChiTieuToiThieu;
    }

    public void setMucChiTieuToiThieu(BigDecimal mucChiTieuToiThieu) {
        this.mucChiTieuToiThieu = mucChiTieuToiThieu;
    }

    public String getDuongDanIcon() {
        return duongDanIcon;
    }

    public void setDuongDanIcon(String duongDanIcon) {
        this.duongDanIcon = duongDanIcon;
    }

    public BigDecimal getChietKhau() {
        return chietKhau;
    }

    public void setChietKhau(BigDecimal chietKhau) {
        this.chietKhau = chietKhau;
    }

    @Override
    public String toString() {
        return "HangThanhVien{"
                + "maHang=" + maHang
                + ", tenHang='" + tenHang + '\''
                + ", mucChiTieuToiThieu=" + mucChiTieuToiThieu
                + ", duongDanIcon='" + duongDanIcon + '\''
                + ", chietKhau=" + chietKhau
                + '}';
    }
}
