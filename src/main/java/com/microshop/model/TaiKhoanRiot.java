package com.microshop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TaiKhoanRiot extends TaiKhoan {

    private String tenDangNhap;
    private String matKhau;
    private Integer capDoRiot;
    private Integer soTuongLMHT;
    private Integer soTrangPhucLMHT;
    private Integer soDaSacLMHT;
    private Integer soBieuCamLMHT;
    private Integer soBieuTuongLMHT;
    private String hangRankLMHT;
    private String khungRankLMHT;
    private Integer soThuCungTFT;
    private Integer soSanDauTFT;
    private Integer soChuongLucTFT;

    public TaiKhoanRiot() {
        super();
    }

    public TaiKhoanRiot(Integer maTaiKhoan, Integer maDanhMuc, BigDecimal giaGoc, BigDecimal giaBan,
            String trangThai, String diemNoiBat, Integer luotXem, LocalDateTime thoiGianDang, // Tham số cha
            String tenDangNhap, String matKhau, Integer capDoRiot, Integer soTuongLMHT, // Tham số con
            Integer soTrangPhucLMHT, Integer soDaSacLMHT, Integer soBieuCamLMHT,
            Integer soBieuTuongLMHT, String hangRankLMHT, String khungRankLMHT,
            Integer soThuCungTFT, Integer soSanDauTFT, Integer soChuongLucTFT) {
        super(maTaiKhoan, maDanhMuc, giaGoc, giaBan, trangThai, diemNoiBat, luotXem, thoiGianDang);
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.capDoRiot = capDoRiot;
        this.soTuongLMHT = soTuongLMHT;
        this.soTrangPhucLMHT = soTrangPhucLMHT;
        this.soDaSacLMHT = soDaSacLMHT;
        this.soBieuCamLMHT = soBieuCamLMHT;
        this.soBieuTuongLMHT = soBieuTuongLMHT;
        this.hangRankLMHT = hangRankLMHT;
        this.khungRankLMHT = khungRankLMHT;
        this.soThuCungTFT = soThuCungTFT;
        this.soSanDauTFT = soSanDauTFT;
        this.soChuongLucTFT = soChuongLucTFT;
    }

    // Getters
    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public Integer getCapDoRiot() {
        return capDoRiot;
    }

    public Integer getSoTuongLMHT() {
        return soTuongLMHT;
    }

    public Integer getSoTrangPhucLMHT() {
        return soTrangPhucLMHT;
    }

    public Integer getSoDaSacLMHT() {
        return soDaSacLMHT;
    }

    public Integer getSoBieuCamLMHT() {
        return soBieuCamLMHT;
    }

    public Integer getSoBieuTuongLMHT() {
        return soBieuTuongLMHT;
    }

    public String getHangRankLMHT() {
        return hangRankLMHT;
    }

    public String getKhungRankLMHT() {
        return khungRankLMHT;
    }

    public Integer getSoThuCungTFT() {
        return soThuCungTFT;
    }

    public Integer getSoSanDauTFT() {
        return soSanDauTFT;
    }

    public Integer getSoChuongLucTFT() {
        return soChuongLucTFT;
    }

    // Setters
    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public void setCapDoRiot(Integer capDoRiot) {
        this.capDoRiot = capDoRiot;
    }

    public void setSoTuongLMHT(Integer soTuongLMHT) {
        this.soTuongLMHT = soTuongLMHT;
    }

    public void setSoTrangPhucLMHT(Integer soTrangPhucLMHT) {
        this.soTrangPhucLMHT = soTrangPhucLMHT;
    }

    public void setSoDaSacLMHT(Integer soDaSacLMHT) {
        this.soDaSacLMHT = soDaSacLMHT;
    }

    public void setSoBieuCamLMHT(Integer soBieuCamLMHT) {
        this.soBieuCamLMHT = soBieuCamLMHT;
    }

    public void setSoBieuTuongLMHT(Integer soBieuTuongLMHT) {
        this.soBieuTuongLMHT = soBieuTuongLMHT;
    }

    public void setHangRankLMHT(String hangRankLMHT) {
        this.hangRankLMHT = hangRankLMHT;
    }

    public void setKhungRankLMHT(String khungRankLMHT) {
        this.khungRankLMHT = khungRankLMHT;
    }

    public void setSoThuCungTFT(Integer soThuCungTFT) {
        this.soThuCungTFT = soThuCungTFT;
    }

    public void setSoSanDauTFT(Integer soSanDauTFT) {
        this.soSanDauTFT = soSanDauTFT;
    }

    public void setSoChuongLucTFT(Integer soChuongLucTFT) {
        this.soChuongLucTFT = soChuongLucTFT;
    }

    @Override
    public String toString() {
        return "TaiKhoanRiot{"
                + "tenDangNhap='" + getTenDangNhap() + '\''
                + // Lấy từ lớp cha qua getter
                ", matKhau='" + "[PROTECTED]" + '\''
                + // Không bao giờ in mật khẩu
                ", capDoRiot=" + capDoRiot
                + ", soTuongLMHT=" + soTuongLMHT
                + ", soTrangPhucLMHT=" + soTrangPhucLMHT
                + ", soDaSacLMHT=" + soDaSacLMHT
                + ", soBieuCamLMHT=" + soBieuCamLMHT
                + ", soBieuTuongLMHT=" + soBieuTuongLMHT
                + ", hangRankLMHT='" + hangRankLMHT + '\''
                + ", khungRankLMHT='" + khungRankLMHT + '\''
                + ", soThuCungTFT=" + soThuCungTFT
                + ", soSanDauTFT=" + soSanDauTFT
                + ", soChuongLucTFT=" + soChuongLucTFT
                + "} " + super.toString();
    }
}
