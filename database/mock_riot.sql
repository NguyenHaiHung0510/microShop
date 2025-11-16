USE `microshop_db`;

-- -----------------------------------------------------
-- PHẦN 2: BỘ DỮ LIỆU MỚI CHO RIOT (20 TÀI KHOẢN)
-- -----------------------------------------------------
-- Tài khoản 1: riotvip1
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 25000000, 20000000, 'DANG_BAN', '433 cấp, 1713 trang phục, Bạc II', 0, NOW() - INTERVAL 1 DAY, 'uploads/riot_acc1_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip1', '6OBoETbl', 433, 170, 1713, 161, 498, 451, 'Bạc II', 'Bạc', 938, 73, 162);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc1_img1.jpg'), (@last_tk_id, 'uploads/riot_acc1_img2.jpg'), (@last_tk_id, 'uploads/riot_acc1_img3.jpg'), (@last_tk_id, 'uploads/riot_acc1_img4.jpg');

-- Tài khoản 2: riotvip2
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 37500000, 30000000, 'DANG_BAN', '566 cấp, 1641 trang phục, chưa rank', 0, NOW() - INTERVAL 2 DAY, 'uploads/riot_acc2_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip2', 'Hf4M7lrd', 566, 167, 1641, 199, 514, 738, 'Chưa Rank', 'Chưa có khung', 1535, 80, 203);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc2_img1.jpg'), (@last_tk_id, 'uploads/riot_acc2_img2.jpg'), (@last_tk_id, 'uploads/riot_acc2_img3.jpg'), (@last_tk_id, 'uploads/riot_acc2_img4.jpg');

-- Tài khoản 3: riotvip3
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 18750000, 15000000, 'DANG_BAN', '798 cấp, 1613 trang phục, chưa rank', 0, NOW() - INTERVAL 3 DAY, 'uploads/riot_acc3_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip3', 'y7rauULb', 798, 170, 1613, 311, 488, 390, 'Chưa Rank', 'Bạc', 493, 24, 44);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc3_img1.jpg'), (@last_tk_id, 'uploads/riot_acc3_img2.jpg'), (@last_tk_id, 'uploads/riot_acc3_img3.jpg'), (@last_tk_id, 'uploads/riot_acc3_img4.jpg');

-- Tài khoản 4: riotvip4
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 18750000, 15000000, 'DANG_BAN', '91 cấp, 3589 đá sắc, Lục Bảo III', 0, NOW() - INTERVAL 4 DAY, 'uploads/riot_acc4_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip4', '5GweRgjn', 91, 171, 1404, 3589, 32, 64, 'Lục Bảo III', 'Lục Bảo', 6, 1, 1);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc4_img1.jpg'), (@last_tk_id, 'uploads/riot_acc4_img2.jpg'), (@last_tk_id, 'uploads/riot_acc4_img3.jpg'), (@last_tk_id, 'uploads/riot_acc4_img4.jpg');

-- Tài khoản 5: riotvip5
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 18750000, 15000000, 'DANG_BAN', '540 cấp, 1372 trang phục, chưa rank', 0, NOW() - INTERVAL 5 DAY, 'uploads/riot_acc5_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip5', 'x4LTVDY0', 540, 165, 1372, 390, 392, 513, 'Chưa Rank', 'Chưa có khung', 337, 51, 111);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc5_img1.jpg'), (@last_tk_id, 'uploads/riot_acc5_img2.jpg'), (@last_tk_id, 'uploads/riot_acc5_img3.jpg'), (@last_tk_id, 'uploads/riot_acc5_img4.jpg');

-- Tài khoản 6: riotvip6
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 31250000, 25000000, 'DANG_BAN', '32 cấp, 1338 trang phục, chưa rank', 0, NOW() - INTERVAL 6 DAY, 'uploads/riot_acc6_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip6', '5FjQZxl5', 32, 158, 1338, 3217, 19, 1348, 'Chưa Rank', 'Chưa có khung', 69, 4, 33);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc6_img1.jpg'), (@last_tk_id, 'uploads/riot_acc6_img2.jpg'), (@last_tk_id, 'uploads/riot_acc6_img3.jpg'), (@last_tk_id, 'uploads/riot_acc6_img4.jpg');

-- Tài khoản 7: riotvip7
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 10000000, 8000000, 'DANG_BAN', '556 cấp, 449 trang phục, chưa rank', 0, NOW() - INTERVAL 7 DAY, 'uploads/riot_acc7_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip7', 'hkcu1GSv', 556, 171, 449, 101, 302, 347, 'Chưa Rank', 'Chưa có khung', 64, 11, 9);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc7_img1.jpg'), (@last_tk_id, 'uploads/riot_acc7_img2.jpg'), (@last_tk_id, 'uploads/riot_acc7_img3.jpg'), (@last_tk_id, 'uploads/riot_acc7_img4.jpg');

-- Tài khoản 8: riotvip8
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 3000000, 2400000, 'DANG_BAN', '467 cấp, 400 trang phục, Vàng III', 0, NOW() - INTERVAL 8 DAY, 'uploads/riot_acc8_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip8', 'n5Ok1gCi', 467, 171, 400, 130, 330, 324, 'Vàng III', 'Vàng', 172, 14, 26);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc8_img1.jpg'), (@last_tk_id, 'uploads/riot_acc8_img2.jpg'), (@last_tk_id, 'uploads/riot_acc8_img3.jpg'), (@last_tk_id, 'uploads/riot_acc8_img4.jpg');

-- Tài khoản 9: riotvip9
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 1500000, 1200000, 'DANG_BAN', '465 cấp, 375 trang phục, Bạc I', 0, NOW() - INTERVAL 9 DAY, 'uploads/riot_acc9_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip9', 'ZLUxVUIZ', 465, 170, 375, 44, 278, 342, 'Bạc I', 'Chưa có khung', 153, 22, 32);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc9_img1.jpg'), (@last_tk_id, 'uploads/riot_acc9_img2.jpg'), (@last_tk_id, 'uploads/riot_acc9_img3.jpg'), (@last_tk_id, 'uploads/riot_acc9_img4.jpg');

-- Tài khoản 10: riotvip10
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 1250000, 1000000, 'DANG_BAN', '584 cấp, 360 trang phục, Bạc IV', 0, NOW() - INTERVAL 10 DAY, 'uploads/riot_acc10_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip10', 'kTIVft3X', 584, 171, 360, 47, 256, 297, 'Bạc IV', 'Bạc', 88, 13, 6);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc10_img1.jpg'), (@last_tk_id, 'uploads/riot_acc10_img2.jpg'), (@last_tk_id, 'uploads/riot_acc10_img3.jpg'), (@last_tk_id, 'uploads/riot_acc10_img4.jpg');

-- Tài khoản 11: riotvip11
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 2000000, 1600000, 'DANG_BAN', '500 cấp, 420 trang phục, Vàng I', 0, NOW() - INTERVAL 11 DAY, 'uploads/riot_acc11_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip11', 'pwd11', 500, 165, 420, 50, 200, 300, 'Vàng I', 'Vàng', 120, 15, 20);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc11_img1.jpg'), (@last_tk_id, 'uploads/riot_acc11_img2.jpg'), (@last_tk_id, 'uploads/riot_acc11_img3.jpg');

-- Tài khoản 12: riotvip12
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 1800000, 1440000, 'DANG_BAN', '480 cấp, 400 trang phục, Bạc I', 0, NOW() - INTERVAL 12 DAY, 'uploads/riot_acc12_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip12', 'pwd12', 480, 160, 400, 45, 180, 290, 'Bạc I', 'Chưa có khung', 110, 12, 18);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc12_img1.jpg'), (@last_tk_id, 'uploads/riot_acc12_img2.jpg'), (@last_tk_id, 'uploads/riot_acc12_img3.jpg');

-- Tài khoản 13: riotvip13
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 2200000, 1760000, 'DANG_BAN', '520 cấp, 430 trang phục, Vàng II', 0, NOW() - INTERVAL 13 DAY, 'uploads/riot_acc13_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip13', 'pwd13', 520, 168, 430, 55, 210, 310, 'Vàng II', 'Vàng', 130, 18, 22);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc13_img1.jpg'), (@last_tk_id, 'uploads/riot_acc13_img2.jpg'), (@last_tk_id, 'uploads/riot_acc13_img3.jpg');

-- Tài khoản 14: riotvip14
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 1600000, 1280000, 'DANG_BAN', '460 cấp, 390 trang phục, Bạc II', 0, NOW() - INTERVAL 14 DAY, 'uploads/riot_acc14_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip14', 'pwd14', 460, 158, 390, 42, 170, 280, 'Bạc II', 'Chưa có khung', 100, 10, 16);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc14_img1.jpg'), (@last_tk_id, 'uploads/riot_acc14_img2.jpg'), (@last_tk_id, 'uploads/riot_acc14_img3.jpg');

-- Tài khoản 15: riotvip15
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 1900000, 1520000, 'DANG_BAN', '500 cấp, 410 trang phục, Vàng III', 0, NOW() - INTERVAL 15 DAY, 'uploads/riot_acc15_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip15', 'pwd15', 500, 165, 410, 48, 190, 300, 'Vàng III', 'Vàng', 115, 13, 18);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc15_img1.jpg'), (@last_tk_id, 'uploads/riot_acc15_img2.jpg'), (@last_tk_id, 'uploads/riot_acc15_img3.jpg');

-- Tài khoản 16: riotvip16
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 2100000, 1680000, 'DANG_BAN', '520 cấp, 440 trang phục, Vàng II', 0, NOW() - INTERVAL 16 DAY, 'uploads/riot_acc16_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip16', 'pwd16', 520, 170, 440, 60, 220, 320, 'Vàng II', 'Vàng', 140, 20, 25);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc16_img1.jpg'), (@last_tk_id, 'uploads/riot_acc16_img2.jpg'), (@last_tk_id, 'uploads/riot_acc16_img3.jpg');

-- Tài khoản 17: riotvip17
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 2300000, 1840000, 'DANG_BAN', '540 cấp, 460 trang phục, Vàng I', 0, NOW() - INTERVAL 17 DAY, 'uploads/riot_acc17_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip17', 'pwd17', 540, 172, 460, 65, 230, 330, 'Vàng I', 'Vàng', 150, 22, 28);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc17_img1.jpg'), (@last_tk_id, 'uploads/riot_acc17_img2.jpg'), (@last_tk_id, 'uploads/riot_acc17_img3.jpg');

-- Tài khoản 18: riotvip18
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 2400000, 1920000, 'DANG_BAN', '550 cấp, 470 trang phục, Vàng III', 0, NOW() - INTERVAL 18 DAY, 'uploads/riot_acc18_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip18', 'pwd18', 550, 174, 470, 70, 240, 340, 'Vàng III', 'Vàng', 160, 25, 30);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc18_img1.jpg'), (@last_tk_id, 'uploads/riot_acc18_img2.jpg'), (@last_tk_id, 'uploads/riot_acc18_img3.jpg');

-- Tài khoản 19: riotvip19
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 2500000, 2000000, 'DANG_BAN', '560 cấp, 480 trang phục, Vàng II', 0, NOW() - INTERVAL 19 DAY, 'uploads/riot_acc19_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip19', 'pwd19', 560, 175, 480, 75, 250, 350, 'Vàng II', 'Vàng', 170, 28, 32);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc19_img1.jpg'), (@last_tk_id, 'uploads/riot_acc19_img2.jpg'), (@last_tk_id, 'uploads/riot_acc19_img3.jpg');

-- Tài khoản 20: riotvip20
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 2600000, 2080000, 'DANG_BAN', '570 cấp, 490 trang phục, Vàng I', 0, NOW() - INTERVAL 20 DAY, 'uploads/riot_acc20_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip20', 'pwd20', 570, 176, 490, 80, 260, 360, 'Vàng I', 'Vàng', 180, 30, 35);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc20_img1.jpg'), (@last_tk_id, 'uploads/riot_acc20_img2.jpg'), (@last_tk_id, 'uploads/riot_acc20_img3.jpg');
