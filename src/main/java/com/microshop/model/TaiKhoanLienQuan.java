package com.microshop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TaiKhoanLienQuan extends TaiKhoan {

    private String tenDangNhap;
    private String matKhau;
    private String hangRank;
    private Integer soTuong;
    private Integer soTrangPhuc;
    private Integer bacNgoc;
    private String loaiDangKy;

    public TaiKhoanLienQuan() {
        super();
    }

    public TaiKhoanLienQuan(Integer maTaiKhoan, Integer maDanhMuc, BigDecimal giaGoc, BigDecimal giaBan,
            String trangThai, String diemNoiBat, Integer luotXem, LocalDateTime thoiGianDang, // Tham số cha
            String tenDangNhap, String matKhau, String hangRank, Integer soTuong, // Tham số con
            Integer soTrangPhuc, Integer bacNgoc, String loaiDangKy) {
        super(maTaiKhoan, maDanhMuc, giaGoc, giaBan, trangThai, diemNoiBat, luotXem, thoiGianDang);
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.hangRank = hangRank;
        this.soTuong = soTuong;
        this.soTrangPhuc = soTrangPhuc;
        this.bacNgoc = bacNgoc;
        this.loaiDangKy = loaiDangKy;
    }

    // Getters
    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public String getHangRank() {
        return hangRank;
    }

    public Integer getSoTuong() {
        return soTuong;
    }

    public Integer getSoTrangPhuc() {
        return soTrangPhuc;
    }

    public Integer getBacNgoc() {
        return bacNgoc;
    }

    public String getLoaiDangKy() {
        return loaiDangKy;
    }

    // Setters
    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public void setHangRank(String hangRank) {
        this.hangRank = hangRank;
    }

    public void setSoTuong(Integer soTuong) {
        this.soTuong = soTuong;
    }

    public void setSoTrangPhuc(Integer soTrangPhuc) {
        this.soTrangPhuc = soTrangPhuc;
    }

    public void setBacNgoc(Integer bacNgoc) {
        this.bacNgoc = bacNgoc;
    }

    public void setLoaiDangKy(String loaiDangKy) {
        this.loaiDangKy = loaiDangKy;
    }

    @Override
    public String toString() {
        return "TaiKhoanLienQuan{"
                + "tenDangNhap='" + tenDangNhap + '\''
                + ", matKhau='" + "[PROTECTED]" + '\''
                + // Không in mật khẩu
                ", hangRank='" + hangRank + '\''
                + ", soTuong=" + soTuong
                + ", soTrangPhuc=" + soTrangPhuc
                + ", bacNgoc=" + bacNgoc
                + ", loaiDangKy='" + loaiDangKy + '\''
                + "} " + super.toString();
    }
}
