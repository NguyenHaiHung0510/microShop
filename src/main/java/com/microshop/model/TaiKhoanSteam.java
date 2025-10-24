package com.microshop.model;

public class TaiKhoanSteam {

    private Integer maTaiKhoanSteam;
    private String tenDangNhapSteam;
    private String matKhauSteam;
    private Integer tongSoSlot;
    private Integer soSlotDaBan;

    public TaiKhoanSteam() {
    }

    public TaiKhoanSteam(Integer maTaiKhoanSteam, String tenDangNhapSteam,
            String matKhauSteam, Integer tongSoSlot, Integer soSlotDaBan) {
        this.maTaiKhoanSteam = maTaiKhoanSteam;
        this.tenDangNhapSteam = tenDangNhapSteam;
        this.matKhauSteam = matKhauSteam;
        this.tongSoSlot = tongSoSlot;
        this.soSlotDaBan = soSlotDaBan;
    }

    public Integer getMaTaiKhoanSteam() {
        return maTaiKhoanSteam;
    }

    public void setMaTaiKhoanSteam(Integer maTaiKhoanSteam) {
        this.maTaiKhoanSteam = maTaiKhoanSteam;
    }

    public String getTenDangNhapSteam() {
        return tenDangNhapSteam;
    }

    public void setTenDangNhapSteam(String tenDangNhapSteam) {
        this.tenDangNhapSteam = tenDangNhapSteam;
    }

    public String getMatKhauSteam() {
        return matKhauSteam;
    }

    public void setMatKhauSteam(String matKhauSteam) {
        this.matKhauSteam = matKhauSteam;
    }

    public Integer getTongSoSlot() {
        return tongSoSlot;
    }

    public void setTongSoSlot(Integer tongSoSlot) {
        this.tongSoSlot = tongSoSlot;
    }

    public Integer getSoSlotDaBan() {
        return soSlotDaBan;
    }

    public void setSoSlotDaBan(Integer soSlotDaBan) {
        this.soSlotDaBan = soSlotDaBan;
    }

    @Override
    public String toString() {
        return "TaiKhoanSteam{"
                + "maTaiKhoanSteam=" + maTaiKhoanSteam
                + ", tenDangNhapSteam='" + tenDangNhapSteam + '\''
                + ", matKhauSteam='" + "[PROTECTED]" + '\''
                + ", tongSoSlot=" + tongSoSlot
                + ", soSlotDaBan=" + soSlotDaBan
                + '}';
    }
}
