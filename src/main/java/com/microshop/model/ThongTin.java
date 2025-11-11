/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.microshop.model;

public class ThongTin {
    // SỬA 1: Đổi tên biến sang chữ thường
    Integer maTaiKhoan;
    String tenGame;
    String tenDangNhap;
    String matKhau;

    public ThongTin(Integer maTaiKhoan, String tenGame, String tenDangNhap, String matKhau) {
        this.maTaiKhoan = maTaiKhoan;
        this.tenGame = tenGame;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
    }

    public Integer getMaTaiKhoan() { return maTaiKhoan; }
    public String getTenGame() { return tenGame; }
    public String getTenDangNhap() { return tenDangNhap; }
    public String getMatKhau() { return matKhau; }

    public void setMaTaiKhoan(Integer maTaiKhoan) { this.maTaiKhoan = maTaiKhoan; }
    public void setTenGame(String tenGame) { this.tenGame = tenGame; }
    public void setTenDangNhap(String tenDangNhap) { this.tenDangNhap = tenDangNhap; } // Sửa tên hàm từ setTaiKhoan
    public void setMatKhau(String matKhau) { this.matKhau = matKhau; }
}