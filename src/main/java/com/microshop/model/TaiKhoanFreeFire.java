package com.microshop.model;

public class TaiKhoanFreeFire extends TaiKhoan {

    private Integer maTaiKhoanFreeFire;
    private Boolean coTheVoCuc;
    private Integer soSkinSung;
    private String hangRank;
    private String loaiTaiKhoanDangKy;

    public TaiKhoanFreeFire() {
    }

    public TaiKhoanFreeFire(Integer maTaiKhoan, Integer maTaiKhoanFreeFire, Boolean coTheVoCuc,
                            Integer soSkinSung, String hangRank, String loaiTaiKhoanDangKy) {
        super();
        this.maTaiKhoanFreeFire = maTaiKhoanFreeFire;
        this.coTheVoCuc = coTheVoCuc;
        this.soSkinSung = soSkinSung;
        this.hangRank = hangRank;
        this.loaiTaiKhoanDangKy = loaiTaiKhoanDangKy;
        this.setMaTaiKhoan(maTaiKhoan);
    }

    public Integer getMaTaiKhoanFreeFire() {
        return maTaiKhoanFreeFire;
    }

    public void setMaTaiKhoanFreeFire(Integer maTaiKhoanFreeFire) {
        this.maTaiKhoanFreeFire = maTaiKhoanFreeFire;
    }

    public Boolean getCoTheVoCuc() {
        return coTheVoCuc;
    }

    public void setCoTheVoCuc(Boolean coTheVoCuc) {
        this.coTheVoCuc = coTheVoCuc;
    }

    public Integer getSoSkinSung() {
        return soSkinSung;
    }

    public void setSoSkinSung(Integer soSkinSung) {
        this.soSkinSung = soSkinSung;
    }

    public String getHangRank() {
        return hangRank;
    }

    public void setHangRank(String hangRank) {
        this.hangRank = hangRank;
    }

    public String getLoaiTaiKhoanDangKy() {
        return loaiTaiKhoanDangKy;
    }

    public void setLoaiTaiKhoanDangKy(String loaiTaiKhoanDangKy) {
        this.loaiTaiKhoanDangKy = loaiTaiKhoanDangKy;
    }

    @Override
    public String toString() {
        return "TaiKhoanFreeFire{" +
                "maTaiKhoanFreeFire=" + maTaiKhoanFreeFire +
                ", coTheVoCuc=" + coTheVoCuc +
                ", soSkinSung=" + soSkinSung +
                ", hangRank='" + hangRank + '\'' +
                ", loaiTaiKhoanDangKy='" + loaiTaiKhoanDangKy + '\'' +
                '}';
    }
}
