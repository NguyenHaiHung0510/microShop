
USE `microshop_db`;

-- -----------------------------------------------------
-- PHẦN 2: BỘ DỮ LIỆU MỚI CHO RIOT (20 TÀI KHOẢN)
-- -----------------------------------------------------
-- Tài khoản 1: riotvip1
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 25000000, 20000000, 'DANG_BAN', 'Acc Siêu Vip', 0, NOW() - INTERVAL 1 DAY, 'uploads/riot_acc1_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip1', '6OBoETbl', 433, 170, 1713, 161, 498, 451, 'Bạc II', 'Bạc', 938, 73, 162);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc1_img1.jpg'), (@last_tk_id, 'uploads/riot_acc1_img2.jpg'), (@last_tk_id, 'uploads/riot_acc1_img3.jpg'), (@last_tk_id, 'uploads/riot_acc1_img4.jpg');

-- Tài khoản 2: riotvip2
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 37500000, 30000000, 'DANG_BAN', 'Acc Siêu Vip', 0, NOW() - INTERVAL 2 DAY, 'uploads/riot_acc2_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip2', 'Hf4M7lrd', 566, 167, 1641, 199, 514, 738, 'Chưa Rank', 'Chưa có khung', 1535, 80, 203);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc2_img1.jpg'), (@last_tk_id, 'uploads/riot_acc2_img2.jpg'), (@last_tk_id, 'uploads/riot_acc2_img3.jpg'), (@last_tk_id, 'uploads/riot_acc2_img4.jpg');

-- Tài khoản 3: riotvip3
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 18750000, 15000000, 'DANG_BAN', 'Acc Siêu Vip', 0, NOW() - INTERVAL 3 DAY, 'uploads/riot_acc3_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip3', 'y7rauULb', 798, 170, 1613, 311, 488, 390, 'Chưa Rank', 'Bạc', 493, 24, 44);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc3_img1.jpg'), (@last_tk_id, 'uploads/riot_acc3_img2.jpg'), (@last_tk_id, 'uploads/riot_acc3_img3.jpg'), (@last_tk_id, 'uploads/riot_acc3_img4.jpg');

-- Tài khoản 4: riotvip4
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 18750000, 15000000, 'DANG_BAN', 'Acc Siêu Vip', 0, NOW() - INTERVAL 4 DAY, 'uploads/riot_acc4_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip4', '5GweRgjn', 91, 171, 1404, 3589, 32, 64, 'Lục Bảo III', 'Lục Bảo', 6, 1, 1);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc4_img1.jpg'), (@last_tk_id, 'uploads/riot_acc4_img2.jpg'), (@last_tk_id, 'uploads/riot_acc4_img3.jpg'), (@last_tk_id, 'uploads/riot_acc4_img4.jpg');

-- Tài khoản 5: riotvip5
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 18750000, 15000000, 'DANG_BAN', 'Acc Siêu Vip', 0, NOW() - INTERVAL 5 DAY, 'uploads/riot_acc5_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip5', 'x4LTVDY0', 540, 165, 1372, 390, 392, 513, 'Chưa Rank', 'Chưa có khung', 337, 51, 111);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc5_img1.jpg'), (@last_tk_id, 'uploads/riot_acc5_img2.jpg'), (@last_tk_id, 'uploads/riot_acc5_img3.jpg'), (@last_tk_id, 'uploads/riot_acc5_img4.jpg');

-- Tài khoản 6: riotvip6
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 31250000, 25000000, 'DANG_BAN', 'Acc Siêu Vip', 0, NOW() - INTERVAL 6 DAY, 'uploads/riot_acc6_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip6', '5FjQZxl5', 32, 158, 1338, 3217, 19, 1348, 'Chưa Rank', 'Chưa có khung', 69, 4, 33);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc6_img1.jpg'), (@last_tk_id, 'uploads/riot_acc6_img2.jpg'), (@last_tk_id, 'uploads/riot_acc6_img3.jpg'), (@last_tk_id, 'uploads/riot_acc6_img4.jpg');

-- Tài khoản 7: riotvip7
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 10000000, 8000000, 'DANG_BAN', 'Acc Siêu Vip', 0, NOW() - INTERVAL 7 DAY, 'uploads/riot_acc7_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip7', 'hkcu1GSv', 556, 171, 449, 101, 302, 347, 'Chưa Rank', 'Chưa có khung', 64, 11, 9);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc7_img1.jpg'), (@last_tk_id, 'uploads/riot_acc7_img2.jpg'), (@last_tk_id, 'uploads/riot_acc7_img3.jpg'), (@last_tk_id, 'uploads/riot_acc7_img4.jpg');

-- Tài khoản 8: riotvip8
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 3000000, 2400000, 'DANG_BAN', 'Acc Siêu Vip', 0, NOW() - INTERVAL 8 DAY, 'uploads/riot_acc8_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip8', 'n5Ok1gCi', 467, 171, 400, 130, 330, 324, 'Vàng III', 'Vàng', 172, 14, 26);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc8_img1.jpg'), (@last_tk_id, 'uploads/riot_acc8_img2.jpg'), (@last_tk_id, 'uploads/riot_acc8_img3.jpg'), (@last_tk_id, 'uploads/riot_acc8_img4.jpg');

-- Tài khoản 9: riotvip9
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 1500000, 1200000, 'DANG_BAN', 'Acc Siêu Vip', 0, NOW() - INTERVAL 9 DAY, 'uploads/riot_acc9_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip9', 'ZLUxVUIZ', 465, 170, 375, 44, 278, 342, 'Bạc I', 'Chưa có khung', 153, 22, 32);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc9_img1.jpg'), (@last_tk_id, 'uploads/riot_acc9_img2.jpg'), (@last_tk_id, 'uploads/riot_acc9_img3.jpg'), (@last_tk_id, 'uploads/riot_acc9_img4.jpg');

-- Tài khoản 10: riotvip10
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 1250000, 1000000, 'DANG_BAN', 'Acc Siêu Vip', 0, NOW() - INTERVAL 10 DAY, 'uploads/riot_acc10_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip10', 'kTIVft3X', 584, 171, 360, 47, 256, 297, 'Bạc IV', 'Bạc', 88, 13, 6);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc10_img1.jpg'), (@last_tk_id, 'uploads/riot_acc10_img2.jpg'), (@last_tk_id, 'uploads/riot_acc10_img3.jpg'), (@last_tk_id, 'uploads/riot_acc10_img4.jpg');

-- Tài khoản 11: riotvip11
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 1250000, 1000000, 'DANG_BAN', 'Acc Siêu Vip', 0, NOW() - INTERVAL 11 DAY, 'uploads/riot_acc11_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip11', 'Fq5GK6Fy', 451, 171, 314, 106, 337, 320, 'Bạch Kim IV', 'Bạch kim', 96, 12, 9);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc11_img1.jpg'), (@last_tk_id, 'uploads/riot_acc11_img2.jpg'), (@last_tk_id, 'uploads/riot_acc11_img3.jpg'), (@last_tk_id, 'uploads/riot_acc11_img4.jpg');

-- Tài khoản 12: riotvip12
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 1250000, 1000000, 'DANG_BAN', 'Acc Siêu Vip', 0, NOW() - INTERVAL 12 DAY, 'uploads/riot_acc12_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip12', 'xBQhmjpd', 698, 170, 302, 68, 266, 246, 'Bạc IV', 'Bạc', 94, 14, 10);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc12_img1.jpg'), (@last_tk_id, 'uploads/riot_acc12_img2.jpg'), (@last_tk_id, 'uploads/riot_acc12_img3.jpg'), (@last_tk_id, 'uploads/riot_acc12_img4.jpg');

-- Tài khoản 13: riotvip13
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 1500000, 1200000, 'DANG_BAN', 'Acc Siêu Vip', 0, NOW() - INTERVAL 13 DAY, 'uploads/riot_acc13_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip13', 'TfjKIpve', 632, 169, 301, 83, 297, 254, 'Bạc II', 'Bạch kim', 140, 17, 22);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc13_img1.jpg'), (@last_tk_id, 'uploads/riot_acc13_img2.jpg'), (@last_tk_id, 'uploads/riot_acc13_img3.jpg');

-- Tài khoản 14: riotvip14
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 1500000, 1200000, 'DANG_BAN', 'Acc Siêu Vip', 0, NOW() - INTERVAL 14 DAY, 'uploads/riot_acc14_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip14', 'tvWB1qYg', 424, 171, 300, 13, 293, 265, 'Đồng I', 'Đồng', 250, 34, 77);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc14_img1.jpg'), (@last_tk_id, 'uploads/riot_acc14_img2.jpg'), (@last_tk_id, 'uploads/riot_acc14_img3.jpg'), (@last_tk_id, 'uploads/riot_acc14_img4.jpg');

-- Tài khoản 15: riotvip15
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 1500000, 1200000, 'DANG_BAN', 'Acc Siêu Vip', 0, NOW() - INTERVAL 15 DAY, 'uploads/riot_acc15_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip15', 't3YyvQ1D', 848, 170, 297, 121, 215, 222, 'Vàng II', 'Vàng', 45, 4, 1);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc15_img1.jpg'), (@last_tk_id, 'uploads/riot_acc15_img2.jpg'), (@last_tk_id, 'uploads/riot_acc15_img3.jpg'), (@last_tk_id, 'uploads/riot_acc15_img4.jpg');

-- Tài khoản 16: riotvip16
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 875000, 700000, 'DANG_BAN', 'Acc Siêu Vip', 0, NOW() - INTERVAL 16 DAY, 'uploads/riot_acc16_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip16', 'ZYGUuSwi', 0, 149, 287, 26, 164, 198, 'Chưa Rank', 'Chưa có khung', 46, 13, 8);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc16_img1.jpg'), (@last_tk_id, 'uploads/riot_acc16_img2.jpg'), (@last_tk_id, 'uploads/riot_acc16_img3.jpg'), (@last_tk_id, 'uploads/riot_acc16_img4.jpg'), (@last_tk_id, 'uploads/riot_acc16_img5.jpg');

-- Tài khoản 17: riotvip17
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 875000, 700000, 'DANG_BAN', 'Acc Siêu Vip', 0, NOW() - INTERVAL 17 DAY, 'uploads/riot_acc17_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip17', 'TsSPpuwj', 716, 171, 283, 107, 299, 320, 'Sắt III', 'Sắt', 167, 15, 13);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc17_img1.jpg'), (@last_tk_id, 'uploads/riot_acc17_img2.jpg'), (@last_tk_id, 'uploads/riot_acc17_img3.jpg'), (@last_tk_id, 'uploads/riot_acc17_img4.jpg');

-- Tài khoản 18: riotvip18
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 900000, 720000, 'DANG_BAN', 'Acc Siêu Vip', 0, NOW() - INTERVAL 18 DAY, 'uploads/riot_acc18_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip18', 'BuNqDQWj', 317, 171, 282, 18, 275, 255, 'Đồng II', 'Đồng', 235, 30, 41);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc18_img1.jpg'), (@last_tk_id, 'uploads/riot_acc18_img2.jpg'), (@last_tk_id, 'uploads/riot_acc18_img3.jpg'), (@last_tk_id, 'uploads/riot_acc18_img4.jpg');

-- Tài khoản 19: riotvip19
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 700000, 560000, 'DANG_BAN', 'Acc Siêu Vip', 0, NOW() - INTERVAL 19 DAY, 'uploads/riot_acc19_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip19', 'FEKSC3c2', 434, 171, 280, 12, 241, 233, 'Chưa Rank', 'Chưa có khung', 83, 13, 9);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc19_img1.jpg'), (@last_tk_id, 'uploads/riot_acc19_img2.jpg'), (@last_tk_id, 'uploads/riot_acc19_img3.jpg'), (@last_tk_id, 'uploads/riot_acc19_img4.jpg');

-- Tài khoản 20: riotvip20
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 1000000, 800000, 'DANG_BAN', 'Acc Siêu Vip', 0, NOW() - INTERVAL 20 DAY, 'uploads/riot_acc20_img1.jpg');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(@last_tk_id, 'riotvip20', 'YmvRKDAD', 499, 171, 279, 82, 311, 317, 'Vàng IV', 'Vàng', 121, 12, 22);
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/riot_acc20_img1.jpg'), (@last_tk_id, 'uploads/riot_acc20_img2.jpg'), (@last_tk_id, 'uploads/riot_acc20_img3.jpg'), (@last_tk_id, 'uploads/riot_acc20_img4.jpg');
