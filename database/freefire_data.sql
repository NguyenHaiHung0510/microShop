-- ============================================
-- PHẦN 2: TÀI KHOẢN FREE FIRE
-- Mã Danh Mục = 1 (Tài khoản Free Fire)
-- ============================================

-- === Acc 1 ===
INSERT INTO TAIKHOAN (MaTaiKhoan, MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, DuongDanAnh)
VALUES (1, 1, 2000000, 1500000, 'DANG_BAN', 'Acc Huyền Thoại, 100 Skin Súng, Có Vô Cực', 'assets/images/ff/acc1_img1.png');

INSERT INTO TAIKHOAN_FREEFIRE (MaTaiKhoan, TenDangNhap, MatKhau, CoTheVoCuc, SoSkinSung, HangRank, LoaiDangKy)
VALUES (1, 'ff_huyenthoai_01', 'ffpass1', 1, 100, 'Huyền Thoại', 'VK');

INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(1, 'assets/images/ff/acc1_img1.png'),
(1, 'assets/images/ff/acc1_img2.png'),
(1, 'assets/images/ff/acc1_img3.png');

-- === Acc 2 ===
INSERT INTO TAIKHOAN VALUES (2, 1, 800000, 500000, 'DANG_BAN', 'Đại Cao Thủ, 40 Skin Súng', 0, NOW(), 'assets/images/ff/acc2_img1.png');
INSERT INTO TAIKHOAN_FREEFIRE VALUES (2, 'ff_daicaothu_02', 'ffpass2', 0, 40, 'Đại Cao Thủ', 'Facebook');
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(2, 'assets/images/ff/acc2_img1.png'),
(2, 'assets/images/ff/acc2_img2.png');

-- === Acc 3 ===
INSERT INTO TAIKHOAN VALUES (3, 1, 100000, 49000, 'DANG_BAN', 'Acc trắng thông tin, 10 Skin Súng', 0, NOW(), 'assets/images/ff/acc3_img1.png');
INSERT INTO TAIKHOAN_FREEFIRE VALUES (3, 'ff_giare_03', 'ffpass3', 0, 10, 'Kim Cương I', 'Google');
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(3, 'assets/images/ff/acc3_img1.png'),
(3, 'assets/images/ff/acc3_img2.png'),
(3, 'assets/images/ff/acc3_img3.png'),
(3, 'assets/images/ff/acc3_img4.png'),
(3, 'assets/images/ff/acc3_img5.png');

-- === Acc 4 ===
INSERT INTO TAIKHOAN VALUES (4, 1, 2500000, 2200000, 'DANG_BAN', 'Acc Pro, 150 Skin Súng, Rank Huyền Thoại', 0, NOW(), 'assets/images/ff/acc4_img1.png');
INSERT INTO TAIKHOAN_FREEFIRE VALUES (4, 'ff_pro_04', 'ffpro4', 1, 150, 'Huyền Thoại', 'VK');
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(4, 'assets/images/ff/acc4_img1.png'),
(4, 'assets/images/ff/acc4_img2.png'),
(4, 'assets/images/ff/acc4_img3.png');

-- === Acc 5 ===
INSERT INTO TAIKHOAN VALUES (5, 1, 1800000, 1600000, 'DANG_BAN', 'Acc VIP, nhiều trang phục hiếm', 0, NOW(), 'assets/images/ff/acc5_img1.png');
INSERT INTO TAIKHOAN_FREEFIRE VALUES (5, 'ff_vip_05', 'ffvip5', 1, 85, 'Kim Cương II', 'Facebook');
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(5, 'assets/images/ff/acc5_img1.png'),
(5, 'assets/images/ff/acc5_img2.png');

-- === Acc 6 ===
INSERT INTO TAIKHOAN VALUES (6, 1, 950000, 700000, 'DANG_BAN', 'Rank cao, skin event', 0, NOW(), 'assets/images/ff/acc6_img1.png');
INSERT INTO TAIKHOAN_FREEFIRE VALUES (6, 'ff_rankcao_06', 'ffrank6', 0, 55, 'Kim Cương I', 'Google');
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(6, 'assets/images/ff/acc6_img1.png'),
(6, 'assets/images/ff/acc6_img2.png');

-- === Acc 7 ===
INSERT INTO TAIKHOAN VALUES (7, 1, 120000, 90000, 'DANG_BAN', 'Acc trắng, giá siêu rẻ', 0, NOW(), 'assets/images/ff/acc7_img1.png');
INSERT INTO TAIKHOAN_FREEFIRE VALUES (7, 'ff_trang_07', 'fftrang7', 0, 5, 'Bạch Kim', 'Facebook');
INSERT INTO ANH_TAIKHOAN VALUES
(NULL, 7, 'assets/images/ff/acc7_img1.png'),
(NULL, 7, 'assets/images/ff/acc7_img2.png');

-- === Acc 8 ===
INSERT INTO TAIKHOAN VALUES (8, 1, 600000, 450000, 'DANG_BAN', 'Nhiều skin event giới hạn', 0, NOW(), 'assets/images/ff/acc8_img1.png');
INSERT INTO TAIKHOAN_FREEFIRE VALUES (8, 'ff_event_08', 'ffevent8', 0, 30, 'Kim Cương III', 'VK');
INSERT INTO ANH_TAIKHOAN VALUES
(NULL, 8, 'assets/images/ff/acc8_img1.png'),
(NULL, 8, 'assets/images/ff/acc8_img2.png');

-- === Acc 9 ===
INSERT INTO TAIKHOAN VALUES (9, 1, 1400000, 1100000, 'DANG_BAN', 'Acc tốc độ, nhiều item', 0, NOW(), 'assets/images/ff/acc9_img1.png');
INSERT INTO TAIKHOAN_FREEFIRE VALUES (9, 'ff_sieutoc_09', 'ffsieutoc9', 1, 70, 'Kim Cương I', 'Google');
INSERT INTO ANH_TAIKHOAN VALUES
(NULL, 9, 'assets/images/ff/acc9_img1.png'),
(NULL, 9, 'assets/images/ff/acc9_img2.png');

-- === Acc 10 ===
INSERT INTO TAIKHOAN VALUES (10, 1, 300000, 250000, 'DANG_BAN', 'Combo giá rẻ, tặng thêm quà', 0, NOW(), 'assets/images/ff/acc10_img1.png');
INSERT INTO TAIKHOAN_FREEFIRE VALUES (10, 'ff_combo_10', 'ffcombo10', 0, 12, 'Bạch Kim', 'Facebook');
INSERT INTO ANH_TAIKHOAN VALUES
(NULL, 10, 'assets/images/ff/acc10_img1.png'),
(NULL, 10, 'assets/images/ff/acc10_img2.png');

-- === Acc 11 ===
INSERT INTO TAIKHOAN VALUES (11, 1, 2200000, 1950000, 'DANG_BAN', 'Nhiều skin hiếm, rank cao', 0, NOW(), 'assets/images/ff/acc11_img1.png');
INSERT INTO TAIKHOAN_FREEFIRE VALUES (11, 'ff_skinhiem_11', 'ffskin11', 1, 130, 'Huyền Thoại', 'VK');
INSERT INTO ANH_TAIKHOAN VALUES
(NULL, 11, 'assets/images/ff/acc11_img1.png'),
(NULL, 11, 'assets/images/ff/acc11_img2.png');

-- === Acc 12 ===
INSERT INTO TAIKHOAN VALUES (12, 1, 80000, 60000, 'DANG_BAN', 'Acc mới, giá mềm', 0, NOW(), 'assets/images/ff/acc12_img1.png');
INSERT INTO TAIKHOAN_FREEFIRE VALUES (12, 'ff_new_12', 'ffnew12', 0, 4, 'Bạch Kim', 'Google');
INSERT INTO ANH_TAIKHOAN VALUES
(NULL, 12, 'assets/images/ff/acc12_img1.png'),
(NULL, 12, 'assets/images/ff/acc12_img2.png');

-- === Acc 13 ===
INSERT INTO TAIKHOAN VALUES (13, 1, 450000, 350000, 'DANG_BAN', 'Bundle nhiều item nhỏ', 0, NOW(), 'assets/images/ff/acc13_img1.png');
INSERT INTO TAIKHOAN_FREEFIRE VALUES (13, 'ff_bundle_13', 'ffbundle13', 0, 18, 'Kim Cương III', 'Facebook');
INSERT INTO ANH_TAIKHOAN VALUES
(NULL, 13, 'assets/images/ff/acc13_img1.png'),
(NULL, 13, 'assets/images/ff/acc13_img2.png');

-- === Acc 14 ===
INSERT INTO TAIKHOAN VALUES (14, 1, 150000, 120000, 'DANG_BAN', 'Acc thường, ổn định', 0, NOW(), 'assets/images/ff/acc14_img1.png');
INSERT INTO TAIKHOAN_FREEFIRE VALUES (14, 'ff_thuong_14', 'ffthuong14', 0, 8, 'Bạch Kim', 'VK');
INSERT INTO ANH_TAIKHOAN VALUES
(NULL, 14, 'assets/images/ff/acc14_img1.png'),
(NULL, 14, 'assets/images/ff/acc14_img2.png');

-- === Acc 15 ===
INSERT INTO TAIKHOAN VALUES (15, 1, 980000, 780000, 'DANG_BAN', 'Event combo, tặng quà', 0, NOW(), 'assets/images/ff/acc15_img1.png');
INSERT INTO TAIKHOAN_FREEFIRE VALUES (15, 'ff_event2_15', 'ffevent15', 0, 33, 'Kim Cương II', 'Google');
INSERT INTO ANH_TAIKHOAN VALUES
(NULL, 15, 'assets/images/ff/acc15_img1.png'),
(NULL, 15, 'assets/images/ff/acc15_img2.png');

-- === Acc 16 ===
INSERT INTO TAIKHOAN VALUES (16, 1, 2700000, 2400000, 'DANG_BAN', 'Acc hiếm, full skin súng', 0, NOW(), 'assets/images/ff/acc16_img1.png');
INSERT INTO TAIKHOAN_FREEFIRE VALUES (16, 'ff_rare_16', 'ffrare16', 1, 200, 'Huyền Thoại', 'VK');
INSERT INTO ANH_TAIKHOAN VALUES
(NULL, 16, 'assets/images/ff/acc16_img1.png'),
(NULL, 16, 'assets/images/ff/acc16_img2.png');

-- === Acc 17 ===
INSERT INTO TAIKHOAN VALUES (17, 1, 380000, 300000, 'DANG_BAN', 'Gói tiết kiệm cho newbie', 0, NOW(), 'assets/images/ff/acc17_img1.png');
INSERT INTO TAIKHOAN_FREEFIRE VALUES (17, 'ff_bundle2_17', 'ffbundle17', 0, 15, 'Bạch Kim', 'Facebook');
INSERT INTO ANH_TAIKHOAN VALUES
(NULL, 17, 'assets/images/ff/acc17_img1.png'),
(NULL, 17, 'assets/images/ff/acc17_img2.png');

-- === Acc 18 ===
INSERT INTO TAIKHOAN VALUES (18, 1, 670000, 520000, 'DANG_BAN', 'Acc tầm trung, nhiều skin thường', 0, NOW(), 'assets/images/ff/acc18_img1.png');
INSERT INTO TAIKHOAN_FREEFIRE VALUES (18, 'ff_acc18', 'ffpass18', 0, 28, 'Kim Cương III', 'Google');
INSERT INTO ANH_TAIKHOAN VALUES
(NULL, 18, 'assets/images/ff/acc18_img1.png'),
(NULL, 18, 'assets/images/ff/acc18_img2.png');

-- === Acc 19 ===
INSERT INTO TAIKHOAN VALUES (19, 1, 430000, 350000, 'DANG_BAN', 'Acc giá mềm, sạch thông tin', 0, NOW(), 'assets/images/ff/acc19_img1.png');
INSERT INTO TAIKHOAN_FREEFIRE VALUES (19, 'ff_acc19', 'ffpass19', 0, 14, 'Bạch Kim', 'VK');
INSERT INTO ANH_TAIKHOAN VALUES
(NULL, 19, 'assets/images/ff/acc19_img1.png');

-- === Acc 20 ===
INSERT INTO TAIKHOAN VALUES (20, 1, 1150000, 950000, 'DANG_BAN', 'Acc rank cao, skin sự kiện', 0, NOW(), 'assets/images/ff/acc20_img1.png');
INSERT INTO TAIKHOAN_FREEFIRE VALUES (20, 'ff_acc20', 'ffpass20', 0, 60, 'Kim Cương I', 'Facebook');
INSERT INTO ANH_TAIKHOAN VALUES
(NULL, 20, 'assets/images/ff/acc20_img1.png');
