package com.microshop.model;

public class DanhMuc {

    private Integer MaDanhMuc;
    private String TenDanhMuc;

    // Constructor không tham số
    public DanhMuc() {
    }

    // Constructor đầy đủ tham số
    public DanhMuc(Integer MaDanhMuc, String TenDanhMuc) {
        this.MaDanhMuc = MaDanhMuc;
        this.TenDanhMuc = TenDanhMuc;
    }

    // Getter
    public Integer getMaDanhMuc() {
        return MaDanhMuc;
    }

    public String getTenDanhMuc() {
        return TenDanhMuc;
    }

    // Setter
    public void setMaDanhMuc(Integer MaDanhMuc) {
        this.MaDanhMuc = MaDanhMuc;
    }

    public void setTenDanhMuc(String TenDanhMuc) {
        this.TenDanhMuc = TenDanhMuc;
    }

    // toString
    @Override
    public String toString() {
        return "DanhMuc{" +
                "MaDanhMuc=" + MaDanhMuc +
                ", TenDanhMuc='" + TenDanhMuc + '\'' +
                '}';
    }
}
