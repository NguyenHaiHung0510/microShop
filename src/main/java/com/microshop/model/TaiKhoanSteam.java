package com.microshop.model;

public class TaiKhoanSteam {

    private int maTaiKhoanSteam;
    private String tenDangNhapSteam;
    private String matKhauSteam;
    private int tongSoSlot;
    private int soSlotDaBan;

    public TaiKhoanSteam() {
    }

    public TaiKhoanSteam(int maTaiKhoanSteam, String tenDangNhapSteam,
            String matKhauSteam, int tongSoSlot, int soSlotDaBan) {
        this.maTaiKhoanSteam = maTaiKhoanSteam;
        this.tenDangNhapSteam = tenDangNhapSteam;
        this.matKhauSteam = matKhauSteam;
        this.tongSoSlot = tongSoSlot;
        this.soSlotDaBan = soSlotDaBan;
    }

    public int getMaTaiKhoanSteam() {
        return maTaiKhoanSteam;
    }

    public String getMatKhauSteam() {
        return matKhauSteam;
    }

    public int getTongSoSlot() {
        return tongSoSlot;
    }

    public int getSoSlotDaBan() {
        return soSlotDaBan;
    }

    public void setMaTaiKhoanSteam(int maTaiKhoanSteam) {
        this.maTaiKhoanSteam = maTaiKhoanSteam;
    }

    public void setTenDangNhapSteam(String tenDangNhapSteam) {
        this.tenDangNhapSteam = tenDangNhapSteam;
    }

    public void setMatKhauSteam(String matKhauSteam) {
        this.matKhauSteam = matKhauSteam;
    }

    public void setTongSoSlot(int tongSoSlot) {
        this.tongSoSlot = tongSoSlot;
    }

    public void setSoSlotDaBan(int soSlotDaBan) {
        this.soSlotDaBan = soSlotDaBan;
    }

    @Override
    public String toString() {
        return "TaiKhoanSteam{"
                + "maTaiKhoanSteam=" + maTaiKhoanSteam
                + ", tenDangNhapSteam='" + tenDangNhapSteam + '\''
                + ", matKhauSteam='" + "[PROTECTED]" + '\''
                + // Không bao giờ in mật khẩu ra log
                ", tongSoSlot=" + tongSoSlot
                + ", soSlotDaBan=" + soSlotDaBan
                + '}';
    }
}
