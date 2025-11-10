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
VALUES (1, 'Thế giới Cyberpunk 2077: Nơi con người và máy hòa làm một',
        'Cyberpunk 2077 đưa bạn đến Night City – nơi mọi quyết định đều ảnh hưởng đến tương lai của bạn.');

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
VALUES (2, 'Elden Ring – kiệt tác thế giới mở của FromSoftware',
        'Khám phá vùng đất The Lands Between đầy bí ẩn, chinh phục những kẻ thù khổng lồ.');

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
VALUES (3, 'Red Dead Redemption 2 – đỉnh cao của thế giới miền Tây',
        'Một câu chuyện sâu sắc về danh dự, tội lỗi và sự chuộc lỗi trong thế giới cao bồi.');

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
VALUES (4, 'Resident Evil 4 Remake – nỗi kinh hoàng trở lại',
        'Cùng Leon chiến đấu chống lại dịch bệnh ký sinh Las Plagas trong phiên bản remake đình đám.');

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
VALUES (5, 'Hollow Knight – thế giới sâu thẳm của côn trùng',
        'Một kiệt tác indie với lối chơi tinh tế và đồ họa 2D tuyệt đẹp.');

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
VALUES (6, 'Baldur’s Gate 3 – tự do tuyệt đối trong thế giới D&D',
        'Tạo nhân vật, phiêu lưu, và tự do chọn cách giải quyết mọi tình huống theo ý bạn.');


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
VALUES (7, 'Stardew Valley – cuộc sống mộng mơ nơi nông trại',
        'Từ bỏ thành phố, bắt đầu cuộc sống mới tại thị trấn Pelican đầy yêu thương.');

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