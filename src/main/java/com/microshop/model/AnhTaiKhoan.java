package com.microshop.model;

public class AnhTaiKhoan {

    private Integer maAnh;
    private Integer maTaiKhoan;
    private String duongDanAnh;

    public AnhTaiKhoan() {
    }

    public AnhTaiKhoan(Integer maAnh, Integer maTaiKhoan, String duongDanAnh) {
        this.maAnh = maAnh;
        this.maTaiKhoan = maTaiKhoan;
        this.duongDanAnh = duongDanAnh;
    }

    public Integer getMaAnh() {
        return maAnh;
    }

    public Integer getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public String getDuongDanAnh() {
        return duongDanAnh;
    }

    public void setMaAnh(Integer maAnh) {
        this.maAnh = maAnh;
    }

    public void setMaTaiKhoan(Integer maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public void setDuongDanAnh(String duongDanAnh) {
        this.duongDanAnh = duongDanAnh;
    }

    @Override
    public String toString() {
        return "AnhTaiKhoan{" +
                "maAnh=" + maAnh +
                ", maTaiKhoan=" + maTaiKhoan +
                ", duongDanAnh='" + duongDanAnh + '\'' +
                '}';
    }
}
