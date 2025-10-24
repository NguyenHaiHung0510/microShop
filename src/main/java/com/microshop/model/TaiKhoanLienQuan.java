package com.microshop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TaiKhoanLienQuan extends TaiKhoan {

    private Integer soTuong;
    private Integer soTrangPhuc;
    private String hangHienTai;
    private String mucNap;
    private String moTa;

    public TaiKhoanLienQuan() {
    }

    public TaiKhoanLienQuan(Integer maTaiKhoan, String tenDangNhap, String matKhau,
                            BigDecimal giaBan, String trangThai, Integer maNguoiBan,
                            LocalDateTime thoiGianDang, Integer soTuong, Integer soTrangPhuc,
                            String hangHienTai, String mucNap, String moTa) {
        super(maTaiKhoan, tenDangNhap, matKhau, giaBan, trangThai, maNguoiBan, thoiGianDang);
        this.soTuong = soTuong;
        this.soTrangPhuc = soTrangPhuc;
        this.hangHienTai = hangHienTai;
        this.mucNap = mucNap;
        this.moTa = moTa;
    }

    public Integer getSoTuong() {
        return soTuong;
    }

    public Integer getSoTrangPhuc() {
        return soTrangPhuc;
    }

    public String getHangHienTai() {
        return hangHienTai;
    }

    public String getMucNap() {
        return mucNap;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setSoTuong(Integer soTuong) {
        this.soTuong = soTuong;
    }

    public void setSoTrangPhuc(Integer soTrangPhuc) {
        this.soTrangPhuc = soTrangPhuc;
    }

    public void setHangHienTai(String hangHienTai) {
        this.hangHienTai = hangHienTai;
    }

    public void setMucNap(String mucNap) {
        this.mucNap = mucNap;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public String toString() {
        return "TaiKhoanLienQuan{" +
                "soTuong=" + soTuong +
                ", soTrangPhuc=" + soTrangPhuc +
                ", hangHienTai='" + hangHienTai + '\'' +
                ", mucNap='" + mucNap + '\'' +
                ", moTa='" + moTa + '\'' +
                "} " + super.toString();
    }
}
