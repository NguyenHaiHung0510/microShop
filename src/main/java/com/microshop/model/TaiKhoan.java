package com.microshop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TaiKhoan {

    private Integer maTaiKhoan;         // PK
    private Integer maDanhMuc;          // FK
    private BigDecimal giaGoc;          // DECIMAL
    private BigDecimal giaBan;          // DECIMAL
    private String trangThai;           // VARCHAR
    private String diemNoiBat;          // TEXT
    private Integer luotXem;            // INT
    private LocalDateTime thoiGianDang; // DATETIME
    private String duongDanAnh;         // Cập nhật 02/11/2025 ( đọc trong prj_note.txt để biết thêm chi tiết )

    public TaiKhoan() {
    }

    public TaiKhoan(Integer maTaiKhoan, Integer maDanhMuc, BigDecimal giaGoc, BigDecimal giaBan,
            String trangThai, String diemNoiBat, Integer luotXem, LocalDateTime thoiGianDang, String duongDanAnh) {
        this.maTaiKhoan = maTaiKhoan;
        this.maDanhMuc = maDanhMuc;
        this.giaGoc = giaGoc;
        this.giaBan = giaBan;
        this.trangThai = trangThai;
        this.diemNoiBat = diemNoiBat;
        this.luotXem = luotXem;
        this.thoiGianDang = thoiGianDang;
        this.duongDanAnh = duongDanAnh;
    }

    // Getters
    public Integer getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public Integer getMaDanhMuc() {
        return maDanhMuc;
    }

    public BigDecimal getGiaGoc() {
        return giaGoc;
    }

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public String getDiemNoiBat() {
        return diemNoiBat;
    }

    public Integer getLuotXem() {
        return luotXem;
    }

    public LocalDateTime getThoiGianDang() {
        return thoiGianDang;
    }

    public String getDuongDanAnh() {
        return duongDanAnh;
    }
    
    // Setters
    public void setMaTaiKhoan(Integer maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public void setMaDanhMuc(Integer maDanhMuc) {
        this.maDanhMuc = maDanhMuc;
    }

    public void setGiaGoc(BigDecimal giaGoc) {
        this.giaGoc = giaGoc;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public void setDiemNoiBat(String diemNoiBat) {
        this.diemNoiBat = diemNoiBat;
    }

    public void setLuotXem(Integer luotXem) {
        this.luotXem = luotXem;
    }

    public void setThoiGianDang(LocalDateTime thoiGianDang) {
        this.thoiGianDang = thoiGianDang;
    }

    public void setDuongDanAnh(String duongDanAnh) {
        this.duongDanAnh = duongDanAnh;
    }
    

    @Override
    public String toString() {
        return "TaiKhoan{"
                + "maTaiKhoan=" + maTaiKhoan
                + ", maDanhMuc=" + maDanhMuc
                + ", giaGoc=" + giaGoc
                + ", giaBan=" + giaBan
                + ", trangThai='" + trangThai + '\''
                + ", diemNoiBat='" + diemNoiBat + '\''
                + ", luotXem=" + luotXem
                + ", thoiGianDang=" + thoiGianDang
                + ", duongDanAnh=" + duongDanAnh
                + '}';
    }
}
