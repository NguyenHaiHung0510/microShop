-- Sử dụng CSDL
USE `microshop_db`;

-- -----------------------------------------------------
-- PHẦN 2: TÀI KHOẢN LIÊN QUÂN (8 TÀI KHOẢN)
-- Mã Danh Mục = 2
-- -----------------------------------------------------

-- Tài khoản 1 (Đặc biệt - 15 ảnh)
INSERT INTO TAIKHOAN (MaTaiKhoan, MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(1, 2, 2500000, 1999000, 'DANG_BAN', 'Acc VIP Full Tướng Full Trang Phục, Rank Thách Đấu 100 Sao, 90 Bảng Ngọc', 1050, NOW() - INTERVAL 1 DAY, 'assets/images/home_lienquan.png');
INSERT INTO TAIKHOAN_LIENQUAN (MaTaiKhoan, TenDangNhap, MatKhau, HangRank, SoTuong, SoTrangPhuc, BacNgoc, LoaiDangKy) VALUES
(1, 'lq_thachdau_vip', 'matkhau123', 'Thách Đấu', 116, 450, 90, 'Garena');

-- Tài khoản 2
INSERT INTO TAIKHOAN (MaTaiKhoan, MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(2, 2, 1000000, 750000, 'DANG_BAN', 'Rank Cao Thủ, 110 Tướng, 200 Trang Phục', 520, NOW() - INTERVAL 2 DAY, 'assets/images/home_lienquan.png');
INSERT INTO TAIKHOAN_LIENQUAN (MaTaiKhoan, TenDangNhap, MatKhau, HangRank, SoTuong, SoTrangPhuc, BacNgoc, LoaiDangKy) VALUES
(2, 'lq_caothu_01', 'matkhau123', 'Cao Thủ', 110, 200, 90, 'Facebook');

-- Tài khoản 3
INSERT INTO TAIKHOAN (MaTaiKhoan, MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(3, 2, 500000, 399000, 'DANG_BAN', 'Rank Tinh Anh 1, Nhiều skin hiếm', 310, NOW() - INTERVAL 3 DAY, 'assets/images/home_lienquan.png');
INSERT INTO TAIKHOAN_LIENQUAN (MaTaiKhoan, TenDangNhap, MatKhau, HangRank, SoTuong, SoTrangPhuc, BacNgoc, LoaiDangKy) VALUES
(3, 'lq_tinhanh_re', 'matkhau123', 'Tinh Anh I', 90, 120, 90, 'Garena');

-- Tài khoản 4
INSERT INTO TAIKHOAN (MaTaiKhoan, MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(4, 2, 200000, 150000, 'DANG_BAN', 'Acc Kim Cương, 70 tướng, 50 trang phục', 150, NOW() - INTERVAL 4 DAY, 'assets/images/home_lienquan.png');
INSERT INTO TAIKHOAN_LIENQUAN (MaTaiKhoan, TenDangNhap, MatKhau, HangRank, SoTuong, SoTrangPhuc, BacNgoc, LoaiDangKy) VALUES
(4, 'lq_kimcuong_1', 'matkhau123', 'Kim Cương II', 70, 50, 60, 'Garena');

-- Tài khoản 5
INSERT INTO TAIKHOAN (MaTaiKhoan, MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(5, 2, 800000, 650000, 'DANG_BAN', 'Acc Cao Thủ, 105 Tướng, nhiều skin SSS', 430, NOW() - INTERVAL 5 DAY, 'assets/images/home_lienquan.png');
INSERT INTO TAIKHOAN_LIENQUAN (MaTaiKhoan, TenDangNhap, MatKhau, HangRank, SoTuong, SoTrangPhuc, BacNgoc, LoaiDangKy) VALUES
(5, 'lq_caothu_sss', 'matkhau123', 'Cao Thủ', 105, 180, 90, 'Garena');

-- Tài khoản 6
INSERT INTO TAIKHOAN (MaTaiKhoan, MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(6, 2, 100000, 49000, 'DANG_BAN', 'Acc trắng thông tin, rank Vàng', 50, NOW() - INTERVAL 6 DAY, 'assets/images/home_lienquan.png');
INSERT INTO TAIKHOAN_LIENQUAN (MaTaiKhoan, TenDangNhap, MatKhau, HangRank, SoTuong, SoTrangPhuc, BacNgoc, LoaiDangKy) VALUES
(6, 'lq_trang_tt', 'matkhau123', 'Vàng I', 30, 10, 30, 'Facebook');

-- Tài khoản 7
INSERT INTO TAIKHOAN (MaTaiKhoan, MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(7, 2, 300000, 220000, 'DANG_BAN', 'Full tướng, Rank Tinh Anh III', 222, NOW() - INTERVAL 7 DAY, 'assets/images/home_lienquan.png');
INSERT INTO TAIKHOAN_LIENQUAN (MaTaiKhoan, TenDangNhap, MatKhau, HangRank, SoTuong, SoTrangPhuc, BacNgoc, LoaiDangKy) VALUES
(7, 'lq_fulltuong_re', 'matkhau123', 'Tinh Anh III', 116, 80, 90, 'Garena');

-- Tài khoản 8
INSERT INTO TAIKHOAN (MaTaiKhoan, MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(8, 2, 1500000, 1200000, 'DANG_BAN', 'Acc Sổ 1-20, nhiều skin hiếm, Cao Thủ', 600, NOW() - INTERVAL 8 DAY, 'assets/images/home_lienquan.png');
INSERT INTO TAIKHOAN_LIENQUAN (MaTaiKhoan, TenDangNhap, MatKhau, HangRank, SoTuong, SoTrangPhuc, BacNgoc, LoaiDangKy) VALUES
(8, 'lq_so_1_20', 'matkhau123', 'Cao Thủ', 116, 250, 90, 'Garena');


-- -----------------------------------------------------
-- PHẦN 3: TÀI KHOẢN FREE FIRE (8 TÀI KHOẢN)
-- Mã Danh Mục = 1
-- -----------------------------------------------------

-- Tài khoản 9 (Đặc biệt - 15 ảnh)
INSERT INTO TAIKHOAN (MaTaiKhoan, MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(9, 1, 3000000, 2499000, 'DANG_BAN', 'Acc VIP Full Skin Súng, Có Thẻ Vô Cực, Rank Huyền Thoại', 1200, NOW() - INTERVAL 1 DAY, 'assets/images/home_freefire.jpg');
INSERT INTO TAIKHOAN_FREEFIRE (MaTaiKhoan, TenDangNhap, MatKhau, CoTheVoCuc, SoSkinSung, HangRank, LoaiDangKy) VALUES
(9, 'ff_vip_pro', 'matkhau123', 1, 150, 'Huyền Thoại', 'VK');

-- Tài khoản 10
INSERT INTO TAIKHOAN (MaTaiKhoan, MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(10, 1, 500000, 350000, 'DANG_BAN', 'Rank Đại Cao Thủ, nhiều skin súng hiếm', 450, NOW() - INTERVAL 2 DAY, 'assets/images/home_freefire.jpg');
INSERT INTO TAIKHOAN_FREEFIRE (MaTaiKhoan, TenDangNhap, MatKhau, CoTheVoCuc, SoSkinSung, HangRank, LoaiDangKy) VALUES
(10, 'ff_daicaothu', 'matkhau123', 0, 40, 'Đại Cao Thủ', 'Facebook');

-- Tài khoản 11
INSERT INTO TAIKHOAN (MaTaiKhoan, MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(11, 1, 200000, 149000, 'DANG_BAN', 'Acc giá rẻ, Rank Kim Cương, 20 skin súng', 210, NOW() - INTERVAL 3 DAY, 'assets/images/home_freefire.jpg');
INSERT INTO TAIKHOAN_FREEFIRE (MaTaiKhoan, TenDangNhap, MatKhau, CoTheVoCuc, SoSkinSung, HangRank, LoaiDangKy) VALUES
(11, 'ff_kimcuong_re', 'matkhau123', 0, 20, 'Kim Cương I', 'Google');

-- Tài khoản 12
INSERT INTO TAIKHOAN (MaTaiKhoan, MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(12, 1, 1000000, 799000, 'DANG_BAN', 'Có Thẻ Vô Cực, Rank Huyền Thoại, 60 skin súng', 600, NOW() - INTERVAL 4 DAY, 'assets/images/home_freefire.jpg');
INSERT INTO TAIKHOAN_FREEFIRE (MaTaiKhoan, TenDangNhap, MatKhau, CoTheVoCuc, SoSkinSung, HangRank, LoaiDangKy) VALUES
(12, 'ff_vocuc_ht', 'matkhau123', 1, 60, 'Huyền Thoại', 'VK');

-- Tài khoản 13
INSERT INTO TAIKHOAN (MaTaiKhoan, MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(13, 1, 80000, 49000, 'DANG_BAN', 'Acc trắng thông tin, Rank Vàng', 80, NOW() - INTERVAL 5 DAY, 'assets/images/home_freefire.jpg');
INSERT INTO TAIKHOAN_FREEFIRE (MaTaiKhoan, TenDangNhap, MatKhau, CoTheVoCuc, SoSkinSung, HangRank, LoaiDangKy) VALUES
(13, 'ff_trang_tt', 'matkhau123', 0, 5, 'Vàng II', 'Google');

-- Tài khoản 14
INSERT INTO TAIKHOAN (MaTaiKhoan, MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(14, 1, 300000, 220000, 'DANG_BAN', 'Rank Cao Thủ, nhiều súng nâng cấp', 333, NOW() - INTERVAL 6 DAY, 'assets/images/home_freefire.jpg');
INSERT INTO TAIKHOAN_FREEFIRE (MaTaiKhoan, TenDangNhap, MatKhau, CoTheVoCuc, SoSkinSung, HangRank, LoaiDangKy) VALUES
(14, 'ff_sung_cap', 'matkhau123', 0, 35, 'Cao Thủ', 'Facebook');

-- Tài khoản 15
INSERT INTO TAIKHOAN (MaTaiKhoan, MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(15, 1, 1500000, 1100000, 'DANG_BAN', 'Acc nhiều đồ, súng ống, rank Đại Cao Thủ', 510, NOW() - INTERVAL 7 DAY, 'assets/images/home_freefire.jpg');
INSERT INTO TAIKHOAN_FREEFIRE (MaTaiKhoan, TenDangNhap, MatKhau, CoTheVoCuc, SoSkinSung, HangRank, LoaiDangKy) VALUES
(15, 'ff_nhieudo_01', 'matkhau123', 0, 70, 'Đại Cao Thủ', 'VK');

-- Tài khoản 16
INSERT INTO TAIKHOAN (MaTaiKhoan, MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(16, 1, 100000, 79000, 'DANG_BAN', 'Acc cùi rank Bạch Kim', 110, NOW() - INTERVAL 8 DAY, 'assets/images/home_freefire.jpg');
INSERT INTO TAIKHOAN_FREEFIRE (MaTaiKhoan, TenDangNhap, MatKhau, CoTheVoCuc, SoSkinSung, HangRank, LoaiDangKy) VALUES
(16, 'ff_bachkim_1', 'matkhau123', 0, 10, 'Bạch Kim IV', 'Google');

-- -----------------------------------------------------
-- PHẦN 4: TÀI KHOẢN RIOT (9 TÀI KHOẢN)
-- Mã Danh Mục = 3
-- -----------------------------------------------------

-- Tài khoản 17 (Đặc biệt - 15 ảnh)
INSERT INTO TAIKHOAN (MaTaiKhoan, MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(17, 3, 5000000, 3999000, 'DANG_BAN', 'Acc VIP 1000+ Skin LMHT, Full Tướng, Rank Thách Đấu, Full Thú Cưng TFT', 2500, NOW() - INTERVAL 1 DAY, 'assets/images/home_lmht.jpg');
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(17, 'riot_king_vn', 'matkhau123', 750, 168, 1020, 300, 150, 150, 'Thách Đấu', 'Khung Thách Đấu', 50, 20, 15);

-- Tài khoản 18
INSERT INTO TAIKHOAN (MaTaiKhoan, MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(18, 3, 1000000, 799000, 'DANG_BAN', 'Acc 300 Skin LMHT, Rank Cao Thủ, Nhiều tướng', 800, NOW() - INTERVAL 2 DAY, 'assets/images/home_lmht.jpg');
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(18, 'riot_caothu_1', 'matkhau123', 300, 150, 300, 50, 30, 30, 'Cao Thủ', 'Khung Cao Thủ', 10, 5, 3);

-- Tài khoản 19
INSERT INTO TAIKHOAN (MaTaiKhoan, MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(19, 3, 500000, 350000, 'DANG_BAN', 'Acc 150 Skin LMHT, Rank Kim Cương', 450, NOW() - INTERVAL 3 DAY, 'assets/images/home_lmht.jpg');
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(19, 'riot_kimcuong_1', 'matkhau123', 200, 120, 150, 20, 10, 10, 'Kim Cương I', 'Khung Kim Cương', 5, 2, 1);

-- Tài khoản 20
INSERT INTO TAIKHOAN (MaTaiKhoan, MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(20, 3, 150000, 99000, 'DANG_BAN', 'Acc Rank Vàng, 50 skin, 100 tướng', 200, NOW() - INTERVAL 4 DAY, 'assets/images/home_lmht.jpg');
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(20, 'riot_vang_1', 'matkhau123', 100, 100, 50, 10, 5, 5, 'Vàng II', 'Khung Vàng', 1, 1, 0);

-- Tài khoản 21
INSERT INTO TAIKHOAN (MaTaiKhoan, MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(21, 3, 2000000, 1500000, 'DANG_BAN', 'Acc 500 Skin, Full Tướng, Rank Đại Cao Thủ', 1100, NOW() - INTERVAL 5 DAY, 'assets/images/home_lmht.jpg');
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(21, 'riot_daicaothu_500', 'matkhau123', 400, 168, 500, 100, 50, 50, 'Đại Cao Thủ', 'Khung Đại Cao Thủ', 20, 10, 5);

-- Tài khoản 22
INSERT INTO TAIKHOAN (MaTaiKhoan, MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(22, 3, 50000, 29000, 'DANG_BAN', 'Acc Trắng thông tin, Level 30', 100, NOW() - INTERVAL 6 DAY, 'assets/images/home_lmht.jpg');
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(22, 'riot_trang_tt', 'matkhau123', 30, 10, 2, 0, 0, 0, 'Chưa Rank', 'Không có', 0, 0, 0);

-- Tài khoản 23
INSERT INTO TAIKHOAN (MaTaiKhoan, MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(23, 3, 250000, 180000, 'DANG_BAN', 'Acc 100 Skin, Rank Bạch Kim', 300, NOW() - INTERVAL 7 DAY, 'assets/images/home_lmht.jpg');
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(23, 'riot_bachkim_100', 'matkhau123', 150, 110, 100, 15, 10, 10, 'Bạch Kim III', 'Khung Bạch Kim', 3, 1, 1);

-- Tài khoản 24
INSERT INTO TAIKHOAN (MaTaiKhoan, MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(24, 3, 800000, 550000, 'DANG_BAN', 'Acc 250 Skin, nhiều skin Huyền Thoại', 650, NOW() - INTERVAL 8 DAY, 'assets/images/home_lmht.jpg');
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(24, 'riot_huyen_thoai', 'matkhau123', 280, 140, 250, 40, 25, 25, 'Kim Cương IV', 'Khung Kim Cương', 8, 3, 2);

-- Tài khoản 25
INSERT INTO TAIKHOAN (MaTaiKhoan, MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, LuotXem, ThoiGianDang, DuongDanAnh) VALUES
(25, 3, 300000, 199000, 'DANG_BAN', 'Acc chuyên TFT, full Linh Thú, Sàn Đấu', 410, NOW() - INTERVAL 9 DAY, 'assets/images/home_lmht.jpg');
INSERT INTO TAIKHOAN_RIOT (MaTaiKhoan, TenDangNhap, MatKhau, CapDoRiot, SoTuongLMHT, SoTrangPhucLMHT, SoDaSacLMHT, SoBieuCamLMHT, SoBieuTuongLMHT, HangRankLMHT, KhungRankLMHT, SoThuCungTFT, SoSanDauTFT, SoChuongLucTFT) VALUES
(25, 'riot_tft_only', 'matkhau123', 100, 50, 20, 5, 5, 5, 'Chưa Rank', 'Không có', 50, 20, 15);

-- -----------------------------------------------------
-- PHẦN 5: INSERT ẢNH CHO CÁC TÀI KHOẢN
-- (Sử dụng lặp lại 7 ảnh bạn có)
-- -----------------------------------------------------

-- Ảnh cho TK 1 (Đặc biệt - 15 ảnh)
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(1, 'assets/images/home_lienquan.png'),
(1, 'assets/images/home_lmht.jpg'),
(1, 'assets/images/home_freefire.jpg'),
(1, 'assets/images/home_netflix.png'),
(1, 'assets/images/home_steam.jpg'),
(1, 'assets/images/home_youtube.jpg'),
(1, 'assets/images/sample_qr.png'),
(1, 'assets/images/home_lienquan.png'),
(1, 'assets/images/home_lmht.jpg'),
(1, 'assets/images/home_freefire.jpg'),
(1, 'assets/images/home_netflix.png'),
(1, 'assets/images/home_steam.jpg'),
(1, 'assets/images/home_youtube.jpg'),
(1, 'assets/images/sample_qr.png'),
(1, 'assets/images/home_lienquan.png');

-- Ảnh cho TK 2, 3, 4, 5, 6, 7, 8 (Mỗi TK 3 ảnh)
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(2, 'assets/images/home_lienquan.png'), (2, 'assets/images/sample_qr.png'), (2, 'assets/images/home_steam.jpg'),
(3, 'assets/images/home_lienquan.png'), (3, 'assets/images/home_youtube.jpg'), (3, 'assets/images/home_lmht.jpg'),
(4, 'assets/images/home_lienquan.png'), (4, 'assets/images/home_freefire.jpg'), (4, 'assets/images/home_netflix.png'),
(5, 'assets/images/home_lienquan.png'), (5, 'assets/images/sample_qr.png'), (5, 'assets/images/home_steam.jpg'),
(6, 'assets/images/home_lienquan.png'), (6, 'assets/images/home_youtube.jpg'), (6, 'assets/images/home_lmht.jpg'),
(7, 'assets/images/home_lienquan.png'), (7, 'assets/images/home_freefire.jpg'), (7, 'assets/images/home_netflix.png'),
(8, 'assets/images/home_lienquan.png'), (8, 'assets/images/sample_qr.png'), (8, 'assets/images/home_steam.jpg');

-- Ảnh cho TK 9 (Đặc biệt - 15 ảnh)
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(9, 'assets/images/home_freefire.jpg'),
(9, 'assets/images/home_freefire.jpg'),
(9, 'assets/images/home_freefire.jpg'),
(9, 'assets/images/home_netflix.png'),
(9, 'assets/images/home_steam.jpg'),
(9, 'assets/images/home_youtube.jpg'),
(9, 'assets/images/sample_qr.png'),
(9, 'assets/images/home_freefire.jpg'),
(9, 'assets/images/home_lmht.jpg'),
(9, 'assets/images/home_freefire.jpg'),
(9, 'assets/images/home_netflix.png'),
(9, 'assets/images/home_steam.jpg'),
(9, 'assets/images/home_youtube.jpg'),
(9, 'assets/images/sample_qr.png'),
(9, 'assets/images/home_freefire.jpg');

-- Ảnh cho TK 10, 11, 12, 13, 14, 15, 16 (Mỗi TK 3 ảnh)
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(10, 'assets/images/home_freefire.jpg'), (10, 'assets/images/sample_qr.png'), (10, 'assets/images/home_netflix.png'),
(11, 'assets/images/home_freefire.jpg'), (11, 'assets/images/home_youtube.jpg'), (11, 'assets/images/home_lmht.jpg'),
(12, 'assets/images/home_freefire.jpg'), (12, 'assets/images/home_lienquan.png'), (12, 'assets/images/home_steam.jpg'),
(13, 'assets/images/home_freefire.jpg'), (13, 'assets/images/sample_qr.png'), (13, 'assets/images/home_netflix.png'),
(14, 'assets/images/home_freefire.jpg'), (14, 'assets/images/home_youtube.jpg'), (14, 'assets/images/home_lmht.jpg'),
(15, 'assets/images/home_freefire.jpg'), (15, 'assets/images/home_lienquan.png'), (15, 'assets/images/home_steam.jpg'),
(16, 'assets/images/home_freefire.jpg'), (16, 'assets/images/sample_qr.png'), (16, 'assets/images/home_netflix.png');

-- Ảnh cho TK 17 (Đặc biệt - 15 ảnh)
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(17, 'assets/images/home_lmht.jpg'),
(17, 'assets/images/home_lmht.jpg'),
(17, 'assets/images/home_lmht.jpg'),
(17, 'assets/images/home_lienquan.png'),
(17, 'assets/images/home_freefire.jpg'),
(17, 'assets/images/home_netflix.png'),
(17, 'assets/images/home_steam.jpg'),
(17, 'assets/images/home_youtube.jpg'),
(17, 'assets/images/sample_qr.png'),
(17, 'assets/images/home_lmht.jpg'),
(17, 'assets/images/home_lienquan.png'),
(17, 'assets/images/home_freefire.jpg'),
(17, 'assets/images/home_netflix.png'),
(17, 'assets/images/home_steam.jpg'),
(17, 'assets/images/home_lmht.jpg');

-- Ảnh cho TK 18, 19, 20, 21, 22, 23, 24, 25 (Mỗi TK 3 ảnh)
INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(18, 'assets/images/home_lmht.jpg'), (18, 'assets/images/sample_qr.png'), (18, 'assets/images/home_steam.jpg'),
(19, 'assets/images/home_lmht.jpg'), (19, 'assets/images/home_youtube.jpg'), (19, 'assets/images/home_lienquan.png'),
(20, 'assets/images/home_lmht.jpg'), (20, 'assets/images/home_freefire.jpg'), (20, 'assets/images/home_netflix.png'),
(21, 'assets/images/home_lmht.jpg'), (21, 'assets/images/sample_qr.png'), (21, 'assets/images/home_steam.jpg'),
(22, 'assets/images/home_lmht.jpg'), (22, 'assets/images/home_youtube.jpg'), (22, 'assets/images/home_lienquan.png'),
(23, 'assets/images/home_lmht.jpg'), (23, 'assets/images/home_freefire.jpg'), (23, 'assets/images/home_netflix.png'),
(24, 'assets/images/home_lmht.jpg'), (24, 'assets/images/sample_qr.png'), (24, 'assets/images/home_steam.jpg'),
(25, 'assets/images/home_lmht.jpg'), (25, 'assets/images/home_youtube.jpg'), (25, 'assets/images/home_lienquan.png');

-- ------------------------------------------------------
-- MẢNG STEAM
-- -----------------------------------------------------
-- PHẦN 6: GAME STEAM (8 GAME)
-- -----------------------------------------------------

--  Game 1
INSERT INTO GAME_STEAM (TenGame, MoTaGame, GiaGoc, GiaBan, LuotXem, IdVideoTrailer, DuongDanAnh)
VALUES (
  'Cyberpunk 2077',
  'Trải nghiệm thế giới tương lai đầy công nghệ, nơi con người hòa quyện với máy móc trong thành phố Night City.',
  1200000, 790000,
  320,
  '8X2kIfS6fb8',
  'assets/images/steam_cyberpunk_detail.jpg' );
INSERT INTO BAIVIET_GIOITHIEU (MaGameSteam, TieuDeBaiViet, NoiDung)
VALUES (1, 'Cyberpunk 2077 – Thành phố công nghệ và tội phạm',
	'<p><strong style="color:#FF4500;">Cyberpunk 2077</strong> đưa bạn đến Night City – thành phố tương lai nơi công nghệ và con người hòa quyện.</p>
	<p><span style="color:#1E90FF;">Khám phá</span> từng con phố neon rực rỡ, thực hiện nhiệm vụ mạo hiểm và tương tác với hàng loạt nhân vật đầy màu sắc.</p>
	<p><span style="color:#32CD32;">Điểm nổi bật của Cyberpunk 2077:</span></p>
	<ul>
	<li><span style="color:#FFD700;">Thế giới mở chi tiết:</span> Khám phá khu phố, tòa nhà cao tầng và các khu ổ chuột đầy rẫy cạm bẫy.</li>
	<li><span style="color:#FF6347;">Nhiệm vụ đa dạng:</span> Hoàn thành nhiệm vụ chính, phụ hoặc tự do khám phá mà không bị gò bó.</li>
	<li><span style="color:#1E90FF;">Nhân vật đa chiều:</span> Tạo hình và kỹ năng nhân vật tùy chỉnh theo lối chơi của bạn.</li>
	<li><span style="color:#32CD32;">Đồ họa và âm thanh:</span> Trải nghiệm thế giới sống động với hiệu ứng ánh sáng và âm nhạc hiện đại.</li>
	<li><span style="color:#FF69B4;">Chế độ tương tác:</span> Quan hệ với NPC, đối thoại, hoặc hợp tác với các băng nhóm trong thành phố.</li>
	</ul>
	<p><em style="color:#00CED1;">Cyberpunk 2077</em> không chỉ là một trò chơi, mà còn là một trải nghiệm văn hóa, khám phá và phiêu lưu vô cùng sống động.</p>');

INSERT INTO BAIVIET_GIOITHIEU (MaGameSteam, TieuDeBaiViet, NoiDung)
VALUES (1, 'Cấu hình game:', 
'<div style="display:flex; gap:20px; flex-wrap: wrap;">
    <div style="flex:1; min-width:200px; background:#f0f8ff; padding:10px; border-radius:8px;">
        <strong>🖥️ Cấu hình tối thiểu:</strong>
        <ul>
            <li>OS: Windows 10 64-bit</li>
            <li>CPU: Intel Core i5-3570K / AMD FX-8310</li>
            <li>RAM: 8 GB</li>
            <li>GPU: NVIDIA GTX 970 / AMD RX 470</li>
            <li>HDD: 70 GB</li>
        </ul>
    </div>
    <div style="flex:1; min-width:200px; background:#f0f8ff; padding:10px; border-radius:8px;">
        <strong>⚙️ Cấu hình đề nghị:</strong>
        <ul>
            <li>OS: Windows 10/11 64-bit</li>
            <li>CPU: Intel Core i7-4790 / AMD Ryzen 3 3200G</li>
            <li>RAM: 12 GB</li>
            <li>GPU: NVIDIA GTX 1060 / AMD RX 590</li>
            <li>SSD: 70 GB</li>
        </ul>
    </div>
</div>');

-- Game 2
INSERT INTO GAME_STEAM (TenGame, MoTaGame, GiaGoc, GiaBan, LuotXem, IdVideoTrailer, DuongDanAnh)
VALUES (
  'Elden Ring',
  'Một thế giới mở huyền bí, nơi người chơi du hành qua The Lands Between để khôi phục chiếc nhẫn Elden huyền thoại.',
  1200000, 890000,
  500,
  'E3Huy2cdih0',
  'assets/images/steam_elden_ring_detail.jpg');
INSERT INTO BAIVIET_GIOITHIEU (MaGameSteam, TieuDeBaiViet, NoiDung)
VALUES (2, 'Elden Ring – Kiệt tác thế giới mở huyền bí',
'<p><strong style="color:#32CD32;">Elden Ring</strong> mở ra vùng đất The Lands Between với bầu không khí huyền bí và những sinh vật khổng lồ.</p>
<p><span style="color:#FFD700;">Khám phá</span> lâu đài, rừng rậm, hang động và những bí mật cổ xưa được FromSoftware xây dựng công phu.</p>
<p><span style="color:#FF4500;">Điểm nổi bật của Elden Ring:</span></p>
<ul>
<li><span style="color:#1E90FF;">Thế giới mở rộng lớn:</span> Không giới hạn khám phá với nhiều khu vực độc đáo.</li>
<li><span style="color:#32CD32;">Chiến đấu kịch tính:</span> Hệ thống combat sâu sắc với boss hùng mạnh.</li>
<li><span style="color:#FFD700;">Hệ thống tùy chỉnh nhân vật:</span> Vũ khí, phép thuật, và kỹ năng đa dạng.</li>
<li><span style="color:#FF69B4;">Cốt truyện đa lớp:</span> Kết hợp nhiều nhân vật và tuyến nhiệm vụ phong phú.</li>
</ul>
<p><em style="color:#00CED1;">Elden Ring</em> mang đến trải nghiệm phiêu lưu, thử thách trí tuệ và kỹ năng chiến đấu vượt trội.</p>');
INSERT INTO BAIVIET_GIOITHIEU (MaGameSteam, TieuDeBaiViet, NoiDung)
VALUES (2, 'Cấu hình game:', 
'<div style="display:flex; gap:20px; flex-wrap: wrap;">
    <div style="flex:1; min-width:200px; background:#f0f8ff; padding:10px; border-radius:8px;">
        <strong>🖥️ Cấu hình tối thiểu:</strong>
        <ul>
            <li>OS: Windows 10</li>
            <li>CPU: Intel Core i5-8400 / AMD Ryzen 3 3300X</li>
            <li>RAM: 12 GB</li>
            <li>GPU: NVIDIA GTX 1060 3GB / AMD RX 580 4GB</li>
            <li>HDD: 60 GB</li>
        </ul>
    </div>
    <div style="flex:1; min-width:200px; background:#f0f8ff; padding:10px; border-radius:8px;">
        <strong>⚙️ Cấu hình đề nghị:</strong>
        <ul>
            <li>OS: Windows 10/11</li>
            <li>CPU: Intel Core i7-8700K / AMD Ryzen 5 3600</li>
            <li>RAM: 16 GB</li>
            <li>GPU: NVIDIA RTX 2070 / AMD RX 6700 XT</li>
            <li>SSD: 60 GB</li>
        </ul>
    </div>
</div>');

-- Game 3
INSERT INTO GAME_STEAM (TenGame, MoTaGame, GiaGoc, GiaBan, LuotXem, IdVideoTrailer, DuongDanAnh)
VALUES (
  'Red Dead Redemption 2',
  'Theo chân Arthur Morgan trong hành trình sinh tồn giữa miền Tây hoang dã của nước Mỹ.',
  1100000, 750000,
  700,
  'eaW0tYpxyp0',
  'assets/images/steam_red_dead_redemption_detail.jpg' );
INSERT INTO BAIVIET_GIOITHIEU (MaGameSteam, TieuDeBaiViet, NoiDung)
VALUES (3, 'Red Dead Redemption 2 – Huyền thoại miền Tây hoang dã',
'<p><strong style="color:#8A2BE2;">Red Dead Redemption 2</strong> kể câu chuyện về Arthur Morgan và băng đảng Van der Linde, sinh tồn trong miền Tây hoang dã.</p>
<p><span style="color:#FF6347;">Trải nghiệm</span> môi trường rộng lớn, từ những thị trấn nhộn nhịp đến thảo nguyên và núi cao hiểm trở.</p>
<p><span style="color:#32CD32;">Điểm nổi bật của RDR2:</span></p>
<ul>
<li><span style="color:#FFD700;">Cốt truyện sâu sắc:</span> Danh dự, tội lỗi, và sự chuộc lỗi của các nhân vật.</li>
<li><span style="color:#FF4500;">Thế giới sống động:</span> Hệ thống NPC phản ứng theo hành vi của bạn.</li>
<li><span style="color:#1E90FF;">Hoạt động đa dạng:</span> Săn bắn, cướp ngân hàng, câu cá, hoặc phiêu lưu tự do.</li>
<li><span style="color:#32CD32;">Đồ họa tuyệt đẹp:</span> Môi trường, ánh sáng, và thời tiết thay đổi theo thời gian.</li>
</ul>
<p><em style="color:#00CED1;">RDR2</em> là kiệt tác đưa người chơi trở về miền Tây nước Mỹ chân thực và sống động.</p>');
INSERT INTO BAIVIET_GIOITHIEU (MaGameSteam, TieuDeBaiViet, NoiDung)
VALUES (3, 'Cấu hình game:', 
'<div style="display:flex; gap:20px; flex-wrap: wrap;">
    <div style="flex:1; min-width:200px; background:#f0f8ff; padding:10px; border-radius:8px;">
        <strong>🖥️ Cấu hình tối thiểu:</strong>
        <ul>
            <li>OS: Windows 7 SP1</li>
            <li>CPU: Intel Core i5-2500K / AMD FX-6300</li>
            <li>RAM: 8 GB</li>
            <li>GPU: NVIDIA GTX 770 2GB / AMD R9 280 3GB</li>
            <li>HDD: 150 GB</li>
        </ul>
    </div>
    <div style="flex:1; min-width:200px; background:#f0f8ff; padding:10px; border-radius:8px;">
        <strong>⚙️ Cấu hình đề nghị:</strong>
        <ul>
            <li>OS: Windows 10</li>
            <li>CPU: Intel Core i7-4770K / AMD Ryzen 5 1500X</li>
            <li>RAM: 12 GB</li>
            <li>GPU: NVIDIA GTX 1060 6GB / AMD RX 480 4GB</li>
            <li>SSD: 150 GB</li>
        </ul>
    </div>
</div>');        

-- Game 4
INSERT INTO GAME_STEAM (TenGame, MoTaGame, GiaGoc, GiaBan, LuotXem, IdVideoTrailer, DuongDanAnh)
VALUES (
  'Resident Evil 4 Remake',
  'Phiên bản làm lại của tựa game sinh tồn kinh dị huyền thoại với đồ họa và lối chơi được nâng cấp toàn diện.',
  900000, 690000,
  450,
  't-UVfZD6a3w',
  'assets/images/steam_resident_evil_4_remake_detail.jpg' );
INSERT INTO BAIVIET_GIOITHIEU (MaGameSteam, TieuDeBaiViet, NoiDung)
VALUES (4, 'Resident Evil 4 Remake – Sống sót trong kinh dị',
'<p><strong style="color:#FF1493;">Resident Evil 4 Remake</strong> đưa Leon Kennedy trở lại chống lại dịch bệnh ký sinh Las Plagas.</p>
<p><span style="color:#00CED1;">Khám phá</span> làng, lâu đài và các khu vực đáng sợ với đồ họa và âm thanh sống động.</p>
<p><span style="color:#FFD700;">Điểm nổi bật:</span></p>
<ul>
<li><span style="color:#FF6347;">Kinh dị sống động:</span> Bầu không khí căng thẳng với quái vật và bẫy.</li>
<li><span style="color:#32CD32;">Hành động phiêu lưu:</span> Chiến đấu, giải đố, và khám phá môi trường.</li>
<li><span style="color:#1E90FF;">Cốt truyện hấp dẫn:</span> Leon chống lại âm mưu sinh học đen tối.</li>
<li><span style="color:#FFA500;">Tái tạo đồ họa:</span> Phiên bản remake nâng cấp từ bản gốc với chi tiết chân thực.</li>
</ul>
<p><em style="color:#FF4500;">Resident Evil 4 Remake</em> là trải nghiệm kinh dị đỉnh cao cho mọi fan survival horror.</p>');
INSERT INTO BAIVIET_GIOITHIEU (MaGameSteam, TieuDeBaiViet, NoiDung)
VALUES (4, 'Cấu hình game:', 
'<div style="display:flex; gap:20px; flex-wrap: wrap;">
    <div style="flex:1; min-width:200px; background:#f0f8ff; padding:10px; border-radius:8px;">
        <strong>🖥️ Cấu hình tối thiểu:</strong>
        <ul>
            <li>OS: Windows 10</li>
            <li>CPU: Intel Core i5-4460 / AMD Ryzen 3 1200</li>
            <li>RAM: 8 GB</li>
            <li>GPU: NVIDIA GTX 760 / AMD R9 280X</li>
            <li>HDD: 25 GB</li>
        </ul>
    </div>
    <div style="flex:1; min-width:200px; background:#f0f8ff; padding:10px; border-radius:8px;">
        <strong>⚙️ Cấu hình đề nghị:</strong>
        <ul>
            <li>OS: Windows 10</li>
            <li>CPU: Intel Core i7-6700 / AMD Ryzen 5 1600</li>
            <li>RAM: 16 GB</li>
            <li>GPU: NVIDIA GTX 1070 / AMD RX 590</li>
            <li>SSD: 25 GB</li>
        </ul>
    </div>
</div>');

-- Game 5
INSERT INTO GAME_STEAM (TenGame, MoTaGame, GiaGoc, GiaBan, LuotXem, IdVideoTrailer, DuongDanAnh)
VALUES (
  'Hollow Knight',
  'Phiêu lưu trong vương quốc sâu thẳm Hallownest đầy sinh vật bí ẩn và thử thách.',
  300000, 190000,
  250,
  'UAO2urG23S4',
  'assets/images/steam_hollow-knight-detail.jpg' );
INSERT INTO BAIVIET_GIOITHIEU (MaGameSteam, TieuDeBaiViet, NoiDung)
VALUES (5, 'Hollow Knight – Thế giới sâu thẳm của côn trùng',
'<p><strong style="color:#8B4513;">Hollow Knight</strong> đưa người chơi vào vương quốc Hallownest đầy bí ẩn và sinh vật kỳ dị.</p>
<p><span style="color:#1E90FF;">Khám phá</span> hang động, thành phố bị bỏ hoang, và chiến đấu với kẻ thù mạnh mẽ.</p>
<p><span style="color:#FFD700;">Điểm nổi bật:</span></p>
<ul>
<li><span style="color:#FF6347;">Gameplay tinh tế:</span> Khám phá, nhảy, chiến đấu và giải đố.</li>
<li><span style="color:#32CD32;">Đồ họa 2D đẹp mắt:</span> Phong cách gothic độc đáo.</li>
<li><span style="color:#1E90FF;">Thế giới mở kết nối:</span> Mỗi khu vực đều liên kết và mở ra bí mật.</li>
<li><span style="color:#FFA500;">Âm nhạc tuyệt vời:</span> Nhạc nền tạo cảm giác phiêu lưu và căng thẳng.</li>
</ul>
<p><em style="color:#FF4500;">Hollow Knight</em> là một kiệt tác indie khó quên, vừa thử thách vừa tuyệt đẹp.</p>');
INSERT INTO BAIVIET_GIOITHIEU (MaGameSteam, TieuDeBaiViet, NoiDung)
VALUES (5, 'Cấu hình game:', 
'<div style="display:flex; gap:20px; flex-wrap: wrap;">
    <div style="flex:1; min-width:200px; background:#f0f8ff; padding:10px; border-radius:8px;">
        <strong>🖥️ Cấu hình tối thiểu:</strong>
        <ul>
            <li>OS: Windows 7</li>
            <li>CPU: Intel Core 2 Duo E5200</li>
            <li>RAM: 4 GB</li>
            <li>GPU: NVIDIA GeForce 9600 GT / AMD HD 5670</li>
            <li>HDD: 9 GB</li>
        </ul>
    </div>
    <div style="flex:1; min-width:200px; background:#f0f8ff; padding:10px; border-radius:8px;">
        <strong>⚙️ Cấu hình đề nghị:</strong>
        <ul>
            <li>OS: Windows 10</li>
            <li>CPU: Intel Core i5-4690</li>
            <li>RAM: 8 GB</li>
            <li>GPU: NVIDIA GTX 660 / AMD HD 7870</li>
            <li>HDD: 9 GB</li>
        </ul>
    </div>
</div>');

-- Game 6
INSERT INTO GAME_STEAM (TenGame, MoTaGame, GiaGoc, GiaBan, LuotXem, IdVideoTrailer, DuongDanAnh)
VALUES (
  'Baldur’s Gate 3',
  'Trải nghiệm RPG theo phong cách Dungeons & Dragons với hàng trăm lựa chọn ảnh hưởng đến cốt truyện.',
  1300000, 950000,
  640,
  '1T22wNvoNiU',
  'assets/images/steam_baldur_gate_3_detail.jpg' );
INSERT INTO BAIVIET_GIOITHIEU (MaGameSteam, TieuDeBaiViet, NoiDung)
VALUES (6, 'Baldur’s Gate 3 – Tự do tuyệt đối trong thế giới D&D',
'<p><strong style="color:#FF6347;">Baldur’s Gate 3</strong> đưa bạn vào thế giới Dungeons & Dragons, nơi mọi quyết định ảnh hưởng đến cốt truyện.</p>
<p><span style="color:#32CD32;">Tạo nhân vật</span>, phiêu lưu cùng nhóm, và khám phá hàng trăm nhiệm vụ đa dạng.</p>
<p><span style="color:#FFD700;">Điểm nổi bật:</span></p>
<ul>
<li><span style="color:#1E90FF;">Hệ thống chiến đấu turn-based:</span> Chiến lược, phép thuật và vũ khí đa dạng.</li>
<li><span style="color:#FF4500;">Tùy chỉnh nhân vật:</span> Chọn chủng tộc, lớp, và kỹ năng theo ý muốn.</li>
<li><span style="color:#32CD32;">Thế giới mở rộng lớn:</span> Kết nối nhiều khu vực với nhiệm vụ phụ phong phú.</li>
<li><span style="color:#FFA500;">Cốt truyện phong phú:</span> Lựa chọn và hậu quả tạo nên trải nghiệm RPG độc nhất.</li>
</ul>
<p><em style="color:#00CED1;">Baldur’s Gate 3</em> là RPG sâu sắc và tự do, thích hợp cho fan D&D.</p>');
INSERT INTO BAIVIET_GIOITHIEU (MaGameSteam, TieuDeBaiViet, NoiDung)
VALUES (6, 'Cấu hình game:', 
'<div style="display:flex; gap:20px; flex-wrap: wrap;">
    <div style="flex:1; min-width:200px; background:#f0f8ff; padding:10px; border-radius:8px;">
        <strong>🖥️ Cấu hình tối thiểu:</strong>
        <ul>
            <li>OS: Windows 10</li>
            <li>CPU: Intel i5-4690 / AMD FX 4350</li>
            <li>RAM: 8 GB</li>
            <li>GPU: NVIDIA GTX 780 / AMD R9 290</li>
            <li>HDD: 70 GB</li>
        </ul>
    </div>
    <div style="flex:1; min-width:200px; background:#f0f8ff; padding:10px; border-radius:8px;">
        <strong>⚙️ Cấu hình đề nghị:</strong>
        <ul>
            <li>OS: Windows 10</li>
            <li>CPU: Intel i7-7700 / AMD Ryzen 5 3600</li>
            <li>RAM: 16 GB</li>
            <li>GPU: NVIDIA GTX 1070 / AMD RX 580</li>
            <li>SSD: 70 GB</li>
        </ul>
    </div>
</div>');

-- Game 7
INSERT INTO GAME_STEAM (TenGame, MoTaGame, GiaGoc, GiaBan, LuotXem, IdVideoTrailer, DuongDanAnh)
VALUES (
  'Stardew Valley',
  'Bắt đầu cuộc sống mới tại nông trại nhỏ, trồng trọt, chăn nuôi và xây dựng mối quan hệ trong thị trấn.',
  250000, 150000,
  900,
  'ot7uXNQskhs',
  'assets/images/steam_stardew_valley_detail.jpg' );
INSERT INTO BAIVIET_GIOITHIEU (MaGameSteam, TieuDeBaiViet, NoiDung)
VALUES (7, 'Stardew Valley – Cuộc sống mộng mơ nơi nông trại',
'<p><strong style="color:#32CD32;">Stardew Valley</strong> đưa bạn đến Pelican Town, nơi cuộc sống yên bình và nông trại đầy màu sắc.</p>
<p><span style="color:#FFD700;">Trồng trọt, chăn nuôi</span>, tham gia lễ hội, kết bạn với NPC và xây dựng mối quan hệ.</p>
<p><span style="color:#FF4500;">Điểm nổi bật:</span></p>
<ul>
<li><span style="color:#1E90FF;">Thế giới sinh động:</span> Bốn mùa, các sự kiện và thị trấn nhộn nhịp.</li>
<li><span style="color:#32CD32;">Hoạt động đa dạng:</span> Câu cá, khai thác khoáng sản, nấu ăn và chế tạo.</li>
<li><span style="color:#FFA500;">Nhân vật đáng nhớ:</span> Mỗi NPC đều có câu chuyện và tính cách riêng.</li>
<li><span style="color:#FF6347;">Thời gian trôi chậm:</span> Cho phép bạn tận hưởng nhịp sống nông trại thoải mái.</li>
</ul>
<p><em style="color:#00CED1;">Stardew Valley</em> là game nông trại dễ thương, thư giãn và cuốn hút mọi lứa tuổi.</p>');
INSERT INTO BAIVIET_GIOITHIEU (MaGameSteam, TieuDeBaiViet, NoiDung)
VALUES (7, 'Cấu hình game:', 
'<div style="display:flex; gap:20px; flex-wrap: wrap;">
    <div style="flex:1; min-width:200px; background:#f0f8ff; padding:10px; border-radius:8px;">
        <strong>🖥️ Cấu hình tối thiểu:</strong>
        <ul>
            <li>OS: Windows 7</li>
            <li>CPU: 2 GHz</li>
            <li>RAM: 2 GB</li>
            <li>GPU: 256 MB VRAM</li>
            <li>HDD: 500 MB</li>
        </ul>
    </div>
    <div style="flex:1; min-width:200px; background:#f0f8ff; padding:10px; border-radius:8px;">
        <strong>⚙️ Cấu hình đề nghị:</strong>
        <ul>
            <li>OS: Windows 10</li>
            <li>CPU: 2 GHz</li>
            <li>RAM: 4 GB</li>
            <li>GPU: 512 MB VRAM</li>
            <li>HDD: 500 MB</li>
        </ul>
    </div>
</div>');

-- Game 8
INSERT INTO GAME_STEAM (TenGame, MoTaGame, GiaGoc, GiaBan, LuotXem, IdVideoTrailer, DuongDanAnh)
VALUES (
  'Grand Theft Auto V',
  'Thế giới mở tội phạm khổng lồ với ba nhân vật chính và cốt truyện kịch tính.',
  800000, 520000,
  1500,
  'QkkoHAzjnUs',
  'assets/images/steam_gta_5_detail.jpg' );
INSERT INTO BAIVIET_GIOITHIEU (MaGameSteam, TieuDeBaiViet, NoiDung)
VALUES (8, 'Grand Theft Auto V – thế giới tội phạm không giới hạn',
        '<p><strong style="color:#FF4500;">Grand Theft Auto V (GTA V)</strong> là một trong những trò chơi hành động thế giới mở nổi tiếng nhất của Rockstar Games. Trò chơi mang đến một trải nghiệm sống động tại thành phố <em style="color:#1E90FF;">Los Santos</em>, nơi người chơi có thể khám phá, thực hiện nhiệm vụ, hoặc tự do gây rối theo cách riêng của mình.</p>

<p><span style="color:#32CD32;">Cốt truyện</span> xoay quanh ba nhân vật chính: <strong>Michael De Santa</strong>, <strong>Franklin Clinton</strong> và <strong>Trevor Philips</strong>. Mỗi nhân vật có câu chuyện, mục tiêu và kỹ năng riêng, tạo nên trải nghiệm đa chiều và liên kết giữa các nhiệm vụ.</p>

<p><span style="color:#FFD700;">Điểm nổi bật của GTA V:</span></p>
<ul>
    <li><span style="color:#FF6347;">Thế giới mở rộng lớn:</span> Los Santos và vùng Blaine County với chi tiết sống động và môi trường đa dạng.</li>
    <li><span style="color:#1E90FF;">Nhiệm vụ phong phú:</span> Hàng loạt nhiệm vụ chính, nhiệm vụ phụ, phi vụ cướp ngân hàng đầy kịch tính.</li>
    <li><span style="color:#32CD32;">Điều khiển linh hoạt:</span> Chuyển đổi giữa ba nhân vật bất kỳ, sử dụng kỹ năng đặc trưng của từng người.</li>
    <li><span style="color:#FF69B4;">Đồ họa chân thực:</span> Chi tiết môi trường, ánh sáng, bóng đổ sống động, đặc biệt trên PC và console thế hệ mới.</li>
    <li><span style="color:#FFA500;">Chế độ trực tuyến:</span> GTA Online cho phép người chơi hợp tác hoặc cạnh tranh với bạn bè và cộng đồng toàn cầu.</li>
</ul>

<p><strong style="color:#00CED1;">GTA V</strong> không chỉ là một trò chơi, mà còn là một trải nghiệm văn hóa, khám phá, và hành động cực kỳ hấp dẫn. Bạn có thể lái xe, bay máy bay, chơi golf, tham gia đua xe, hoặc chỉ đơn giản là lang thang khám phá thành phố rộng lớn.</p>

<p><em style="color:#FF8C00;">Nếu bạn yêu thích thể loại hành động, phiêu lưu, và tự do khám phá, GTA V chắc chắn sẽ mang đến cho bạn hàng giờ trải nghiệm thú vị và đầy kịch tính.</em></p>');
INSERT INTO BAIVIET_GIOITHIEU (MaGameSteam, TieuDeBaiViet, NoiDung)
VALUES (8, 'Cấu hình game: ',
'<div style="display:flex; gap:20px; flex-wrap: wrap;">
    <div style="flex:1; min-width:200px; background:#f0f8ff; padding:10px; border-radius:8px;">
        <strong>🖥️ Cấu hình tối thiểu:</strong>
        <ul>
            <li>Hệ điều hành: Windows 10 64-bit</li>
            <li>Bộ xử lý: Intel Core 2 Quad CPU Q6600 / AMD Phenom 9850 Quad-Core</li>
            <li>RAM: 4 GB</li>
            <li>Card đồ họa: NVIDIA 9800 GT 1GB / AMD HD 4870 1GB (DX 10, 10.1, 11)</li>
            <li>Dung lượng trống: 90 GB</li>
        </ul>
    </div>
    <div style="flex:1; min-width:200px; background:#f0f8ff; padding:10px; border-radius:8px;">
        <strong>⚙️ Cấu hình đề nghị:</strong>
        <ul>
            <li>Hệ điều hành: Windows 10/11 64-bit</li>
            <li>Bộ xử lý: Intel Core i5 3470 / AMD X8 FX-8350</li>
            <li>RAM: 8 GB</li>
            <li>Card đồ họa: NVIDIA GTX 660 2GB / AMD HD 7870 2GB</li>
            <li>Dung lượng trống: 90 GB</li>
        </ul>
    </div>
</div>
<p>🎮 Ghi chú: Nên cài game trên SSD để giảm thời gian load và tăng trải nghiệm mượt mà.</p>');
		

-- -----------------------------------------------------
-- PHẦN 7: TÀI KHOẢN STEAM (4 TÀI KHOẢN)
-- -----------------------------------------------------

-- Tài khoản 1
INSERT INTO TAIKHOAN_STEAM (TenDangNhapSteam, MatKhauSteam, TongSoSlot, SoSlotDaBan)
VALUES ('steam_master_1', 'steam_pass_1', 5, 2);

-- Tài khoản 2
INSERT INTO TAIKHOAN_STEAM (TenDangNhapSteam, MatKhauSteam, TongSoSlot, SoSlotDaBan)
VALUES ('steam_master_2', 'steam_pass_2', 4, 1);

-- Tài khoản 3
INSERT INTO TAIKHOAN_STEAM (TenDangNhapSteam, MatKhauSteam, TongSoSlot, SoSlotDaBan)
VALUES ('steam_master_3', 'steam_pass_3', 6, 3);

-- Tài khoản 4
INSERT INTO TAIKHOAN_STEAM (TenDangNhapSteam, MatKhauSteam, TongSoSlot, SoSlotDaBan)
VALUES ('steam_master_4', 'steam_pass_4', 3, 0);

-- -----------------------------------------------------
-- PHẦN 8: LIÊN KẾT TÀI KHOẢN
-- -----------------------------------------------------

-- Tài khoản 1 sở hữu 2 game
INSERT INTO GAME_TAIKHOAN_STEAM (MaGameSteam, MaTaiKhoanSteam) VALUES (1, 1);
INSERT INTO GAME_TAIKHOAN_STEAM (MaGameSteam, MaTaiKhoanSteam) VALUES (2, 1);

-- Tài khoản 2 sở hữu 2 game
INSERT INTO GAME_TAIKHOAN_STEAM (MaGameSteam, MaTaiKhoanSteam) VALUES (3, 2);
INSERT INTO GAME_TAIKHOAN_STEAM (MaGameSteam, MaTaiKhoanSteam) VALUES (4, 2);

-- Tài khoản 3 sở hữu 3 game
INSERT INTO GAME_TAIKHOAN_STEAM (MaGameSteam, MaTaiKhoanSteam) VALUES (5, 3);
INSERT INTO GAME_TAIKHOAN_STEAM (MaGameSteam, MaTaiKhoanSteam) VALUES (6, 3);
INSERT INTO GAME_TAIKHOAN_STEAM (MaGameSteam, MaTaiKhoanSteam) VALUES (7, 3);

-- Tài khoản 4 sở hữu 1 game
INSERT INTO GAME_TAIKHOAN_STEAM (MaGameSteam, MaTaiKhoanSteam) VALUES (8, 4);