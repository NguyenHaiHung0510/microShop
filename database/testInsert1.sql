-- ===== DANH MỤC =====
INSERT INTO `DANHMUC` (`TenDanhMuc`) VALUES
('Tài khoản Liên Quân'),
('Tài khoản Free Fire'),
('Tài khoản Riot Games');

-- ===== HẠNG THÀNH VIÊN =====
INSERT INTO `HANGTHANHVIEN` (`TenHang`, `MucChiTieuToiThieu`, `ChietKhau`) VALUES
('Bạc', 1000, 1.00),
('Vàng', 10000, 3.00),
('Bạch Kim', 50000, 5.00),
('Kim cương', 100000, 8.00);

-- ===== NGƯỜI DÙNG =====
INSERT INTO `NGUOIDUNG` (`TenDangNhap`, `MatKhau`, `Email`, `VaiTro`, `MaHangThanhVien`) VALUES
('hungnv', 'hashed_password_123', 'hung.nv@email.com', 'USER', 1);

-- =====================================================
-- =============== TÀI KHOẢN LIÊN QUÂN =================
-- =====================================================
INSERT INTO `TAIKHOAN` (`MaDanhMuc`, `GiaGoc`, `GiaBan`, `TrangThai`, `DiemNoiBat`, `DuongDanAnh`)
VALUES (1, 500000, 450000, 'DANG_BAN', 'Nổi bật: Có skin Ryoma Samurai', 'assets/images/lq_acc_1_img1.png');

SET @last_acc_id = LAST_INSERT_ID();

INSERT INTO `TAIKHOAN_LIENQUAN`
(`MaTaiKhoan`, `TenDangNhap`, `MatKhau`, `HangRank`, `SoTuong`, `SoTrangPhuc`, `BacNgoc`, `LoaiDangKy`)
VALUES
(@last_acc_id, 'acc_lq_hero', 'pass123', 'Cao Thủ', 110, 250, 90, 'Garena');

INSERT INTO `ANH_TAIKHOAN` (`MaTaiKhoan`, `DuongDanAnh`) VALUES
(@last_acc_id, 'assets/images/lq_acc_1_img1.png'),
(@last_acc_id, 'assets/images/lq_acc_1_img2.png');


-- ===== TÀI KHOẢN LIÊN QUÂN #2 =====
INSERT INTO `TAIKHOAN` (`MaDanhMuc`, `GiaGoc`, `GiaBan`, `TrangThai`, `DiemNoiBat`, `DuongDanAnh`)
VALUES (1, 600000, 490000, 'DANG_BAN', 'Nổi bật: Có skin Yasuo', 'assets/images/lq_acc_2_img1.png');

SET @last_acc_id = LAST_INSERT_ID();

INSERT INTO `TAIKHOAN_LIENQUAN`
(`MaTaiKhoan`, `TenDangNhap`, `MatKhau`, `HangRank`, `SoTuong`, `SoTrangPhuc`, `BacNgoc`, `LoaiDangKy`)
VALUES
(@last_acc_id, 'acc_yasuo_pro', 'pass123', 'Cao Thủ', 120, 270, 100, 'Garena');

INSERT INTO `ANH_TAIKHOAN` (`MaTaiKhoan`, `DuongDanAnh`) VALUES
(@last_acc_id, 'assets/images/lq_acc_2_img1.png');

-- =====================================================
-- =============== TÀI KHOẢN FREE FIRE =================
-- =====================================================
INSERT INTO `TAIKHOAN` (`MaDanhMuc`, `GiaGoc`, `GiaBan`, `TrangThai`, `DiemNoiBat`, `DuongDanAnh`)
VALUES (2, 200000, 50000, 'DANG_BAN', 'Nổi bật: Có súng siêu xịn', 'assets/images/ff_acc_1_img1.png');

SET @last_acc_id = LAST_INSERT_ID();

INSERT INTO `TAIKHOAN_FREEFIRE`
(`MaTaiKhoan`, `TenDangNhap`, `MatKhau`, `CoTheVoCuc`, `SoSkinSung`, `HangRank`, `LoaiDangKy`)
VALUES
(@last_acc_id, 'ffplayer123', '123456', TRUE, 45, 'Huyền thoại', 'Facebook');

INSERT INTO `ANH_TAIKHOAN` (`MaTaiKhoan`, `DuongDanAnh`) VALUES
(@last_acc_id, 'assets/images/ff_acc_1_img1.png');

-- =====================================================
-- ================ TÀI KHOẢN RIOT =====================
-- =====================================================
INSERT INTO `TAIKHOAN` (`MaDanhMuc`, `GiaGoc`, `GiaBan`, `TrangThai`, `DiemNoiBat`, `DuongDanAnh`)
VALUES (3, 300000, 90000, 'DANG_BAN', 'Nổi bật: Có linh thú xịn', 'assets/images/riot_acc_1_img1.png');

SET @last_acc_id = LAST_INSERT_ID();

INSERT INTO `TAIKHOAN_RIOT`
(`MaTaiKhoan`, `TenDangNhap`, `MatKhau`, `CapDoRiot`, `SoTuongLMHT`, `SoTrangPhucLMHT`, `SoDaSacLMHT`,
 `SoBieuCamLMHT`, `SoBieuTuongLMHT`, `HangRankLMHT`, `KhungRankLMHT`,
 `SoThuCungTFT`, `SoSanDauTFT`, `SoChuongLucTFT`)
VALUES
(@last_acc_id, 'riotpro99', 'abcdef', 215, 140, 120, 15, 20, 30, 'Bạch Kim IV', 'Vàng', 6, 4, 10);

INSERT INTO `ANH_TAIKHOAN` (`MaTaiKhoan`, `DuongDanAnh`) VALUES
(@last_acc_id, 'assets/images/riot_acc_1_img1.png');
