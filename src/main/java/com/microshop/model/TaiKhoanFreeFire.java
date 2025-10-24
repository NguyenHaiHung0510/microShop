package com.microshop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TaiKhoanFreeFire extends TaiKhoan {

    private String tenDangNhap;
    private String matKhau;
    private Boolean coTheVoCuc;
    private Integer soSkinSung;
    private String hangRank;
    private String loaiDangKy;

    public TaiKhoanFreeFire() {
        super();
    }

    public TaiKhoanFreeFire(Integer maTaiKhoan, Integer maDanhMuc, BigDecimal giaGoc, BigDecimal giaBan,
            String trangThai, String diemNoiBat, Integer luotXem, LocalDateTime thoiGianDang, // Tham số cha
            String tenDangNhap, String matKhau, Boolean coTheVoCuc, Integer soSkinSung, // Tham số con
            String hangRank, String loaiDangKy) {
        super(maTaiKhoan, maDanhMuc, giaGoc, giaBan, trangThai, diemNoiBat, luotXem, thoiGianDang);
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.coTheVoCuc = coTheVoCuc;
        this.soSkinSung = soSkinSung;
        this.hangRank = hangRank;
        this.loaiDangKy = loaiDangKy;
    }

    // Getters
    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public Boolean getCoTheVoCuc() {
        return coTheVoCuc;
    }

    public Integer getSoSkinSung() {
        return soSkinSung;
    }

    public String getHangRank() {
        return hangRank;
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

    public void setCoTheVoCuc(Boolean coTheVoCuc) {
        this.coTheVoCuc = coTheVoCuc;
    }

    public void setSoSkinSung(Integer soSkinSung) {
        this.soSkinSung = soSkinSung;
    }

    public void setHangRank(String hangRank) {
        this.hangRank = hangRank;
    }

    public void setLoaiDangKy(String loaiDangKy) {
        this.loaiDangKy = loaiDangKy;
    }

    @Override
    public String toString() {
        return "TaiKhoanFreeFire{"
                + "tenDangNhap='" + tenDangNhap + '\''
                + ", matKhau='" + "[PROTECTED]" + '\''
                + ", coTheVoCuc=" + coTheVoCuc
                + ", soSkinSung=" + soSkinSung
                + ", hangRank='" + hangRank + '\''
                + ", loaiDangKy='" + loaiDangKy + '\''
                + "} " + super.toString();
    }
}
