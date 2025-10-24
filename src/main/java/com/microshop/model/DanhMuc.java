package com.microshop.model;

public class DanhMuc {

    // Đã đổi hết tên biến về dạng camelCase - Hưng
    private Integer maDanhMuc;
    private String tenDanhMuc;

    // Constructor không tham số
    public DanhMuc() {
    }

    // Constructor đầy đủ tham số
    public DanhMuc(Integer maDanhMuc, String tenDanhMuc) {
        this.maDanhMuc = maDanhMuc;
        this.tenDanhMuc = tenDanhMuc;
    }

    // Getter 
    public Integer getMaDanhMuc() {
        return maDanhMuc;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    // Setter 
    public void setMaDanhMuc(Integer maDanhMuc) {
        this.maDanhMuc = maDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    // toString
    @Override
    public String toString() {
        return "DanhMuc{"
                + "maDanhMuc=" + maDanhMuc
                + ", tenDanhMuc='" + tenDanhMuc + '\''
                + '}';
    }
}
