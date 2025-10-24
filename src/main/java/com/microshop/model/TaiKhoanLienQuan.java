package com.microshop.model;

public class TaiKhoanLienQuan extends TaiKhoan {

    private Integer maTaiKhoanLienQuan;
    private String hangRank;
    private Integer soTuong;
    private Integer soTrangPhuc;
    private Integer bacNgoc;
    private String loaiDangKy;

    public TaiKhoanLienQuan() {
    }

    public TaiKhoanLienQuan(Integer maTaiKhoan, Integer maTaiKhoanLienQuan, String hangRank,
                            Integer soTuong, Integer soTrangPhuc, Integer bacNgoc, String loaiDangKy) {
        super();
        this.maTaiKhoanLienQuan = maTaiKhoanLienQuan;
        this.hangRank = hangRank;
        this.soTuong = soTuong;
        this.soTrangPhuc = soTrangPhuc;
        this.bacNgoc = bacNgoc;
        this.loaiDangKy = loaiDangKy;
        this.setMaTaiKhoan(maTaiKhoan);
    }

    public Integer getMaTaiKhoanLienQuan() {
        return maTaiKhoanLienQuan;
    }

    public void setMaTaiKhoanLienQuan(Integer maTaiKhoanLienQuan) {
        this.maTaiKhoanLienQuan = maTaiKhoanLienQuan;
    }

    public String getHangRank() {
        return hangRank;
    }

    public void setHangRank(String hangRank) {
        this.hangRank = hangRank;
    }

    public Integer getSoTuong() {
        return soTuong;
    }

    public void setSoTuong(Integer soTuong) {
        this.soTuong = soTuong;
    }

    public Integer getSoTrangPhuc() {
        return soTrangPhuc;
    }

    public void setSoTrangPhuc(Integer soTrangPhuc) {
        this.soTrangPhuc = soTrangPhuc;
    }

    public Integer getBacNgoc() {
        return bacNgoc;
    }

    public void setBacNgoc(Integer bacNgoc) {
        this.bacNgoc = bacNgoc;
    }

    public String getLoaiDangKy() {
        return loaiDangKy;
    }

    public void setLoaiDangKy(String loaiDangKy) {
        this.loaiDangKy = loaiDangKy;
    }

    @Override
    public String toString() {
        return "TaiKhoanLienQuan{" +
                "maTaiKhoanLienQuan=" + maTaiKhoanLienQuan +
                ", hangRank='" + hangRank + '\'' +
                ", soTuong=" + soTuong +
                ", soTrangPhuc=" + soTrangPhuc +
                ", bacNgoc=" + bacNgoc +
                ", loaiDangKy='" + loaiDangKy + '\'' +
                '}';
    }
}
