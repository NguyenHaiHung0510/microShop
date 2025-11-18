-- -----------------------------------------------------
-- SỬ DỤNG DATABASE
-- -----------------------------------------------------
USE `microshop_db`;

-- -----------------------------------------------------
-- PHẦN 2: BỘ DỮ LIỆU MỚI CHO FREE FIRE (20 TÀI KHOẢN)
-- Mã Danh Mục = 1
-- -----------------------------------------------------

-- Tài khoản 1 (acc1)
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(1, 450000, 300000, 'DANG_BAN', 'Rank Đại Cao Thủ, 50 skin súng', 150, NOW() - INTERVAL 1 DAY, 'uploads/acc1_img1.png');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_FREEFIRE (MaTaiKhoan, TenDangNhap, MatKhau, CoTheVoCuc, SoSkinSung, HangRank, LoaiDangKy) VALUES
(@last_tk_id, 'ff_acc_01', 'pass123', 0, 50, 'Đại Cao Thủ', 'Facebook');
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/acc1_img1.png'),
(@last_tk_id, 'uploads/acc1_img2.png'),
(@last_tk_id, 'uploads/acc1_img3.png');

-- Tài khoản 2 (acc2)
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(1, 150000, 99000, 'DANG_BAN', 'Acc Kim Cương, 20 skin súng', 80, NOW() - INTERVAL 1 DAY, 'uploads/acc2_img1.png');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_FREEFIRE (MaTaiKhoan, TenDangNhap, MatKhau, CoTheVoCuc, SoSkinSung, HangRank, LoaiDangKy) VALUES
(@last_tk_id, 'ff_acc_02', 'pass123', 0, 20, 'Kim Cương I', 'Google');
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/acc2_img1.png'),
(@last_tk_id, 'uploads/acc2_img2.png');

-- Tài khoản 3 (acc3)
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(1, 1200000, 950000, 'DANG_BAN', 'Có Thẻ Vô Cực, Rank Huyền Thoại, 80 skin súng', 300, NOW() - INTERVAL 2 DAY, 'uploads/acc3_img1.png');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_FREEFIRE (MaTaiKhoan, TenDangNhap, MatKhau, CoTheVoCuc, SoSkinSung, HangRank, LoaiDangKy) VALUES
(@last_tk_id, 'ff_acc_03', 'pass123', 1, 80, 'Huyền Thoại', 'VK');
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/acc3_img1.png'),
(@last_tk_id, 'uploads/acc3_img2.png'),
(@last_tk_id, 'uploads/acc3_img3.png'),
(@last_tk_id, 'uploads/acc3_img4.png'),
(@last_tk_id, 'uploads/acc3_img5.png');

-- Tài khoản 4 (acc4)
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(1, 80000, 49000, 'DANG_BAN', 'Acc trắng thông tin, Rank Vàng', 45, NOW() - INTERVAL 2 DAY, 'uploads/acc4_img1.png');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_FREEFIRE (MaTaiKhoan, TenDangNhap, MatKhau, CoTheVoCuc, SoSkinSung, HangRank, LoaiDangKy) VALUES
(@last_tk_id, 'ff_acc_04', 'pass123', 0, 5, 'Vàng II', 'Google');
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/acc4_img1.png'),
(@last_tk_id, 'uploads/acc4_img2.png'),
(@last_tk_id, 'uploads/acc4_img3.png');

-- Tài khoản 5 (acc5)
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(1, 2000000, 1500000, 'DANG_BAN', 'Acc VIP nhiều đồ, súng ống, rank Đại Cao Thủ', 510, NOW() - INTERVAL 3 DAY, 'uploads/acc5_img1.png');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_FREEFIRE (MaTaiKhoan, TenDangNhap, MatKhau, CoTheVoCuc, SoSkinSung, HangRank, LoaiDangKy) VALUES
(@last_tk_id, 'ff_acc_05', 'pass123', 0, 70, 'Đại Cao Thủ', 'VK');
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/acc5_img1.png'),
(@last_tk_id, 'uploads/acc5_img2.png');

-- Tài khoản 6 (acc6)
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(1, 100000, 79000, 'DANG_BAN', 'Acc cùi rank Bạch Kim IV', 110, NOW() - INTERVAL 3 DAY, 'uploads/acc6_img1.png');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_FREEFIRE (MaTaiKhoan, TenDangNhap, MatKhau, CoTheVoCuc, SoSkinSung, HangRank, LoaiDangKy) VALUES
(@last_tk_id, 'ff_acc_06', 'pass123', 0, 10, 'Bạch Kim IV', 'Google');
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/acc6_img1.png'),
(@last_tk_id, 'uploads/acc6_img2.png');

-- Tài khoản 7 (acc7)
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(1, 350000, 250000, 'DANG_BAN', 'Rank Cao Thủ, 30 skin súng', 180, NOW() - INTERVAL 4 DAY, 'uploads/acc7_img1.png');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_FREEFIRE (MaTaiKhoan, TenDangNhap, MatKhau, CoTheVoCuc, SoSkinSung, HangRank, LoaiDangKy) VALUES
(@last_tk_id, 'ff_acc_07', 'pass123', 0, 30, 'Cao Thủ', 'Facebook');
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/acc7_img1.png'),
(@last_tk_id, 'uploads/acc7_img2.png');

-- Tài khoản 8 (acc8)
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(1, 800000, 650000, 'DANG_BAN', 'Acc 60 skin súng, Rank Đại Cao Thủ', 240, NOW() - INTERVAL 4 DAY, 'uploads/acc8_img1.png');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_FREEFIRE (MaTaiKhoan, TenDangNhap, MatKhau, CoTheVoCuc, SoSkinSung, HangRank, LoaiDangKy) VALUES
(@last_tk_id, 'ff_acc_08', 'pass123', 0, 60, 'Đại Cao Thủ', 'VK');
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/acc8_img1.png'),
(@last_tk_id, 'uploads/acc8_img2.png');

-- Tài khoản 9 (acc9)
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(1, 1000000, 799000, 'DANG_BAN', 'Có Thẻ Vô Cực, 65 skin súng, Rank Huyền Thoại', 600, NOW() - INTERVAL 5 DAY, 'uploads/acc9_img1.png');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_FREEFIRE (MaTaiKhoan, TenDangNhap, MatKhau, CoTheVoCuc, SoSkinSung, HangRank, LoaiDangKy) VALUES
(@last_tk_id, 'ff_acc_09', 'pass123', 1, 65, 'Huyền Thoại', 'VK');
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/acc9_img1.png'),
(@last_tk_id, 'uploads/acc9_img2.png');

-- Tài khoản 10 (acc10)
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(1, 200000, 149000, 'DANG_BAN', 'Acc giá rẻ, Rank Kim Cương II, 22 skin súng', 210, NOW() - INTERVAL 5 DAY, 'uploads/acc10_img1.png');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_FREEFIRE (MaTaiKhoan, TenDangNhap, MatKhau, CoTheVoCuc, SoSkinSung, HangRank, LoaiDangKy) VALUES
(@last_tk_id, 'ff_acc_10', 'pass123', 0, 22, 'Kim Cương II', 'Google');
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/acc10_img1.png'),
(@last_tk_id, 'uploads/acc10_img2.png');

-- Tài khoản 11 (acc11)
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(1, 3000000, 2499000, 'DANG_BAN', 'Acc VIP Full Skin Súng, Có Thẻ Vô Cực, Rank Huyền Thoại', 1200, NOW() - INTERVAL 6 DAY, 'uploads/acc11_img1.png');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_FREEFIRE (MaTaiKhoan, TenDangNhap, MatKhau, CoTheVoCuc, SoSkinSung, HangRank, LoaiDangKy) VALUES
(@last_tk_id, 'ff_acc_11', 'pass123', 1, 150, 'Huyền Thoại', 'VK');
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/acc11_img1.png'),
(@last_tk_id, 'uploads/acc11_img2.png');

-- Tài khoản 12 (acc12)
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(1, 500000, 350000, 'DANG_BAN', 'Rank Đại Cao Thủ, nhiều skin súng hiếm', 450, NOW() - INTERVAL 6 DAY, 'uploads/acc12_img1.png');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_FREEFIRE (MaTaiKhoan, TenDangNhap, MatKhau, CoTheVoCuc, SoSkinSung, HangRank, LoaiDangKy) VALUES
(@last_tk_id, 'ff_acc_12', 'pass123', 0, 40, 'Đại Cao Thủ', 'Facebook');
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/acc12_img1.png'),
(@last_tk_id, 'uploads/acc12_img2.png');

-- Tài khoản 13 (acc13)
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(1, 100000, 65000, 'DANG_BAN', 'Acc Vàng, 8 skin súng', 70, NOW() - INTERVAL 7 DAY, 'uploads/acc13_img1.png');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_FREEFIRE (MaTaiKhoan, TenDangNhap, MatKhau, CoTheVoCuc, SoSkinSung, HangRank, LoaiDangKy) VALUES
(@last_tk_id, 'ff_acc_13', 'pass123', 0, 8, 'Vàng III', 'Google');
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/acc13_img1.png'),
(@last_tk_id, 'uploads/acc13_img2.png');

-- Tài khoản 14 (acc14)
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(1, 300000, 220000, 'DANG_BAN', 'Nhiều súng nâng cấp, Rank Cao Thủ', 210, NOW() - INTERVAL 7 DAY, 'uploads/acc14_img1.png');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_FREEFIRE (MaTaiKhoan, TenDangNhap, MatKhau, CoTheVoCuc, SoSkinSung, HangRank, LoaiDangKy) VALUES
(@last_tk_id, 'ff_acc_14', 'pass123', 0, 35, 'Cao Thủ', 'Facebook');
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/acc14_img1.png'),
(@last_tk_id, 'uploads/acc14_ịmg2.png'); -- Giữ nguyên tên file 'ịmg2.png' theo tree output

-- Tài khoản 15 (acc15)
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(1, 250000, 180000, 'DANG_BAN', 'Rank Bạch Kim 1, 15 skin súng', 130, NOW() - INTERVAL 8 DAY, 'uploads/acc15_img1.png');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_FREEFIRE (MaTaiKhoan, TenDangNhap, MatKhau, CoTheVoCuc, SoSkinSung, HangRank, LoaiDangKy) VALUES
(@last_tk_id, 'ff_acc_15', 'pass123', 0, 15, 'Bạch Kim I', 'VK');
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/acc15_img1.png'),
(@last_tk_id, 'uploads/acc15_img2.png');

-- Tài khoản 16 (acc16)
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(1, 90000, 69000, 'DANG_BAN', 'Acc rank Bạch Kim 2, 10 skin súng', 95, NOW() - INTERVAL 8 DAY, 'uploads/acc16_img1.png');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_FREEFIRE (MaTaiKhoan, TenDangNhap, MatKhau, CoTheVoCuc, SoSkinSung, HangRank, LoaiDangKy) VALUES
(@last_tk_id, 'ff_acc_16', 'pass123', 0, 10, 'Bạch Kim II', 'Google');
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/acc16_img1.png'),
(@last_tk_id, 'uploads/acc16_ịmg2.png'); -- Giữ nguyên tên file 'ịmg2.png' theo tree output

-- Tài khoản 17 (acc17)
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(1, 500000, 400000, 'DANG_BAN', 'Rank Cao Thủ, 40 skin súng', 330, NOW() - INTERVAL 9 DAY, 'uploads/acc17_img1.png');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_FREEFIRE (MaTaiKhoan, TenDangNhap, MatKhau, CoTheVoCuc, SoSkinSung, HangRank, LoaiDangKy) VALUES
(@last_tk_id, 'ff_acc_17', 'pass123', 0, 40, 'Cao Thủ', 'Facebook');
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/acc17_img1.png'),
(@last_tk_id, 'uploads/acc17_img2.png');

-- Tài khoản 18 (acc18)
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(1, 1500000, 1100000, 'DANG_BAN', 'Acc nhiều đồ, súng ống, rank Đại Cao Thủ, 75 skin', 510, NOW() - INTERVAL 9 DAY, 'uploads/acc18_img1.png');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_FREEFIRE (MaTaiKhoan, TenDangNhap, MatKhau, CoTheVoCuc, SoSkinSung, HangRank, LoaiDangKy) VALUES
(@last_tk_id, 'ff_acc_18', 'pass123', 0, 75, 'Đại Cao Thủ', 'VK');
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/acc18_img1.png'),
(@last_tk_id, 'uploads/acc18_img2.png');

-- Tài khoản 19 (acc19)
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(1, 60000, 39000, 'DANG_BAN', 'Acc Vàng, 3 skin súng', 60, NOW() - INTERVAL 10 DAY, 'uploads/acc19_img1.png');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_FREEFIRE (MaTaiKhoan, TenDangNhap, MatKhau, CoTheVoCuc, SoSkinSung, HangRank, LoaiDangKy) VALUES
(@last_tk_id, 'ff_acc_19', 'pass123', 0, 3, 'Vàng I', 'Google');
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/acc19_img1.png');

-- Tài khoản 20 (acc20)
INSERT INTO TAIKHOAN (MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(1, 2500000, 1999000, 'DANG_BAN', 'Acc Thẻ Vô Cực Mùa 1, Rank Huyền Thoại', 950, NOW() - INTERVAL 10 DAY, 'uploads/acc20_img1.png');
SET @last_tk_id = LAST_INSERT_ID();
INSERT INTO TAIKHOAN_FREEFIRE (MaTaiKhoan, TenDangNhap, MatKhau, CoTheVoCuc, SoSkinSung, HangRank, LoaiDangKy) VALUES
(@last_tk_id, 'ff_acc_20', 'pass123', 1, 120, 'Huyền Thoại', 'VK');
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/acc20_img1.png');