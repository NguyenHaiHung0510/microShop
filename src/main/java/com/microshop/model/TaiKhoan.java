package com.microshop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TaiKhoan {

    private Integer maTaiKhoan;
    private Integer maDanhMuc;
    private BigDecimal giaGoc;
    private BigDecimal giaBan;
    private String trangThai;
    private String diemNoiBat;
    private Integer luotXem;
    private LocalDateTime thoiGianDang;

    public TaiKhoan() {
    }

    public TaiKhoan(Integer maTaiKhoan, Integer maDanhMuc, BigDecimal giaGoc, BigDecimal giaBan,
                    String trangThai, String diemNoiBat, Integer luotXem, LocalDateTime thoiGianDang) {
        this.maTaiKhoan = maTaiKhoan;
        this.maDanhMuc = maDanhMuc;
        this.giaGoc = giaGoc;
        this.giaBan = giaBan;
        this.trangThai = trangThai;
        this.diemNoiBat = diemNoiBat;
        this.luotXem = luotXem;
        this.thoiGianDang = thoiGianDang;
    }

    public Integer getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(Integer maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public Integer getMaDanhMuc() {
        return maDanhMuc;
    }

    public void setMaDanhMuc(Integer maDanhMuc) {
        this.maDanhMuc = maDanhMuc;
    }

    public BigDecimal getGiaGoc() {
        return giaGoc;
    }

    public void setGiaGoc(BigDecimal giaGoc) {
        this.giaGoc = giaGoc;
    }

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getDiemNoiBat() {
        return diemNoiBat;
    }

    public void setDiemNoiBat(String diemNoiBat) {
        this.diemNoiBat = diemNoiBat;
    }

    public Integer getLuotXem() {
        return luotXem;
    }

    public void setLuotXem(Integer luotXem) {
        this.luotXem = luotXem;
    }

    public LocalDateTime getThoiGianDang() {
        return thoiGianDang;
    }

    public void setThoiGianDang(LocalDateTime thoiGianDang) {
        this.thoiGianDang = thoiGianDang;
    }

    @Override
    public String toString() {
        return "TaiKhoan{" +
                "maTaiKhoan=" + maTaiKhoan +
                ", maDanhMuc=" + maDanhMuc +
                ", giaGoc=" + giaGoc +
                ", giaBan=" + giaBan +
                ", trangThai='" + trangThai + '\'' +
                ", diemNoiBat='" + diemNoiBat + '\'' +
                ", luotXem=" + luotXem +
                ", thoiGianDang=" + thoiGianDang +
                '}';
    }
}
