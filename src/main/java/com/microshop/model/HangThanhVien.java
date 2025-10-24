package com.microshop.model;

import java.math.BigDecimal;

public class HangThanhVien {

    // Đã sửa hết tên thuộc tính thành dạng camelCase - Hưng
    private Integer maHang;
    private String tenHang;
    private BigDecimal mucChiTieuToiThieu;
    private String duongDanIcon;
    private BigDecimal chietKhau;

    // Constructor không tham số
    public HangThanhVien() {
    }

    // Constructor đầy đủ tham số 
    public HangThanhVien(Integer maHang, String tenHang, BigDecimal mucChiTieuToiThieu,
            String duongDanIcon, BigDecimal chietKhau) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.mucChiTieuToiThieu = mucChiTieuToiThieu;
        this.duongDanIcon = duongDanIcon;
        this.chietKhau = chietKhau;
    }

    // Getter 
    public Integer getMaHang() {
        return maHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public BigDecimal getMucChiTieuToiThieu() {
        return mucChiTieuToiThieu;
    }

    public String getDuongDanIcon() {
        return duongDanIcon;
    }

    public BigDecimal getChietKhau() {
        return chietKhau;
    }

    // Setter 
    public void setMaHang(Integer maHang) {
        this.maHang = maHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public void setMucChiTieuToiThieu(BigDecimal mucChiTieuToiThieu) {
        this.mucChiTieuToiThieu = mucChiTieuToiThieu;
    }

    public void setDuongDanIcon(String duongDanIcon) {
        this.duongDanIcon = duongDanIcon;
    }

    public void setChietKhau(BigDecimal chietKhau) {
        this.chietKhau = chietKhau;
    }

    // toString 
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
