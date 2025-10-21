
INSERT INTO `DANHMUC` (`TenDanhMuc`) VALUES 
('Tài khoản Liên Quân'),
('Tài khoản Free Fire'),
('Tài khoản Riot Games');

INSERT INTO `HANGTHANHVIEN` (`TenHang`, `MucChiTieuToiThieu`, `ChietKhau`) VALUES
('Kim cương', 100000, 8.00);

INSERT INTO `HANGTHANHVIEN` (`TenHang`, `MucChiTieuToiThieu`, `ChietKhau`) VALUES
('Bạc', 1000, 1.00);

INSERT INTO `HANGTHANHVIEN` (`TenHang`, `MucChiTieuToiThieu`, `ChietKhau`) VALUES
('Vàng', 1000, 3.00);

INSERT INTO `HANGTHANHVIEN` (`TenHang`, `MucChiTieuToiThieu`, `ChietKhau`) VALUES
('Bạch Kim', 10000, 5.00);

INSERT INTO `NGUOIDUNG` (`TenDangNhap`, `MatKhau`, `Email`, `VaiTro`, `MaHangThanhVien`) VALUES
('hungnv', 'hashed_password_123', 'hung.nv@email.com', 'CUSTOMER', 1);

INSERT INTO `TAIKHOAN` (`MaDanhMuc`, `GiaGoc`, `GiaBan`, `TrangThai`, `DiemNoiBat`) VALUES
(1, 500000, 450000, 'CON_HANG', 'Nổi bật: Có skin Ryoma Samurai');

INSERT INTO `TAIKHOAN` (`MaDanhMuc`, `GiaGoc`, `GiaBan`, `TrangThai`, `DiemNoiBat`) VALUES
(1, 500000, 45000000, 'CON_HANG', 'Nổi bật: có skin yasuo?');

SET @last_acc_id = 1;

INSERT INTO `TAIKHOAN_LIENQUAN` (`MaTaiKhoan`, `HangRank`, `SoTuong`, `SoTrangPhuc`, `BacNgoc`, `LoaiDangKy`) VALUES
(@last_acc_id, 'Cao Thủ', 110, 250, 90, 'Garena');

INSERT INTO `ANH_TAIKHOAN` (`MaTaiKhoan`, `DuongDanAnh`) VALUES
(@last_acc_id, 'images/accounts/lq_acc_1_img1.png'),
(@last_acc_id, 'images/accounts/lq_acc_1_img2.png');

SET @last_acc_id = 2;

INSERT INTO `TAIKHOAN_LIENQUAN` (`MaTaiKhoan`, `HangRank`, `SoTuong`, `SoTrangPhuc`, `BacNgoc`, `LoaiDangKy`) VALUES
(@last_acc_id, 'Bố tướng', 110, 250, 90, 'Garena');

INSERT INTO `ANH_TAIKHOAN` (`MaTaiKhoan`, `DuongDanAnh`) VALUES
(@last_acc_id, 'images/accounts/yasuo_thong_thao_7.png');

