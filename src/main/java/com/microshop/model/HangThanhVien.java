package com.microshop.model;

import java.math.BigDecimal;

public class HangThanhVien {

    private Integer MaHang;
    private String TenHang;
    private BigDecimal MucChiTieuToiThieu;
    private String DuongDanIcon;
    private BigDecimal ChietKhau;

    // Constructor không tham số
    public HangThanhVien() {
    }

    // Constructor đầy đủ tham số
    public HangThanhVien(Integer MaHang, String TenHang, BigDecimal MucChiTieuToiThieu,
                         String DuongDanIcon, BigDecimal ChietKhau) {
        this.MaHang = MaHang;
        this.TenHang = TenHang;
        this.MucChiTieuToiThieu = MucChiTieuToiThieu;
        this.DuongDanIcon = DuongDanIcon;
        this.ChietKhau = ChietKhau;
    }

    // Getter
    public Integer getMaHang() {
        return MaHang;
    }

    public String getTenHang() {
        return TenHang;
    }

    public BigDecimal getMucChiTieuToiThieu() {
        return MucChiTieuToiThieu;
    }

    public String getDuongDanIcon() {
        return DuongDanIcon;
    }

    public BigDecimal getChietKhau() {
        return ChietKhau;
    }

    // Setter
    public void setMaHang(Integer MaHang) {
        this.MaHang = MaHang;
    }

    public void setTenHang(String TenHang) {
        this.TenHang = TenHang;
    }

    public void setMucChiTieuToiThieu(BigDecimal MucChiTieuToiThieu) {
        this.MucChiTieuToiThieu = MucChiTieuToiThieu;
    }

    public void setDuongDanIcon(String DuongDanIcon) {
        this.DuongDanIcon = DuongDanIcon;
    }

    public void setChietKhau(BigDecimal ChietKhau) {
        this.ChietKhau = ChietKhau;
    }

    // toString
    @Override
    public String toString() {
        return "HangThanhVien{" +
                "MaHang=" + MaHang +
                ", TenHang='" + TenHang + '\'' +
                ", MucChiTieuToiThieu=" + MucChiTieuToiThieu +
                ", DuongDanIcon='" + DuongDanIcon + '\'' +
                ", ChietKhau=" + ChietKhau +
                '}';
    }
}
