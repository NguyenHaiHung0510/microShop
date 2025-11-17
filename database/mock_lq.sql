USE `microshop_db`;
-- -----------------------------------------------------
-- PHẦN 2: BỘ DỮ LIỆU MỚI CHO LIÊN QUÂN (5 TÀI KHOẢN)
-- Mã Danh Mục = 2
-- -----------------------------------------------------

-- ============================================
-- ACC 1: #36168
-- ============================================

INSERT INTO TAIKHOAN (
    MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, ThoiGianDang, DuongDanAnh
) VALUES (
    2, 400000, 360000, 'DANG_BAN',
    'Rank Bạch Kim III, Tướng 72, Trang phục 111, Ngọc 90',
    NOW() - INTERVAL 1 DAY,
    'uploads/lqacc168_img0.jpg'
);
SET @last_tk_id = LAST_INSERT_ID();

INSERT INTO TAIKHOAN_LIENQUAN (
    MaTaiKhoan, TenDangNhap, MatKhau, HangRank, SoTuong, SoTrangPhuc, BacNgoc, LoaiDangKy
) VALUES (
    @last_tk_id, 'acc_36168', 'pass123', 'Bạch Kim III', 72, 111, 90, 'Garena'
);

INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/lqacc168_img0.jpg');

-- ============================================
-- ACC 2: #36162
-- ============================================

INSERT INTO TAIKHOAN (
    MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, ThoiGianDang, DuongDanAnh
) VALUES (
    2, 450000, 405000, 'DANG_BAN',
    'Rank Kim Cương II, Tướng: 100, Trang phục: 97, Ngọc: 90',
    NOW() - INTERVAL 2 DAY,
    'uploads/lqacc162_img0.jpg'
);
SET @last_tk_id = LAST_INSERT_ID();

INSERT INTO TAIKHOAN_LIENQUAN (
    MaTaiKhoan, TenDangNhap, MatKhau, HangRank, SoTuong, SoTrangPhuc, BacNgoc, LoaiDangKy
) VALUES (
    @last_tk_id, 'lq_acc_36162', 'pass789', 'Kim Cương II', 100, 97, 90, 'Facebook'
);

INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/lqacc162_img0.jpg');

-- ============================================
-- ACC 3: #36157
-- ============================================

INSERT INTO TAIKHOAN (
    MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, ThoiGianDang, DuongDanAnh
) VALUES (
    2, 220000, 198000, 'DANG_BAN',
    'Rank Vàng I, Tướng: 87, Trang phục: 129, Ngọc: 90',
    NOW() - INTERVAL 3 DAY,
    'uploads/lqacc157_img0.jpg'
);
SET @last_tk_id = LAST_INSERT_ID();

INSERT INTO TAIKHOAN_LIENQUAN (
    MaTaiKhoan, TenDangNhap, MatKhau, HangRank, SoTuong, SoTrangPhuc, BacNgoc, LoaiDangKy
) VALUES (
    @last_tk_id, 'lq_acc_36157', 'pass456', 'Vàng I', 87, 129, 90, 'Garena'
);

INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/lqacc157_img0.jpg');

-- ============================================
-- ACC 4: #36153
-- ============================================

INSERT INTO TAIKHOAN (
    MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, ThoiGianDang, DuongDanAnh
) VALUES (
    2, 900000, 810000, 'DANG_BAN',
    'Rank Đại Cao Thủ, Tướng: 124, Trang phục: 307, Ngọc: 90',
    NOW() - INTERVAL 4 DAY,
    'uploads/lqacc153_img0.jpg'
);
SET @last_tk_id = LAST_INSERT_ID();

INSERT INTO TAIKHOAN_LIENQUAN (
    MaTaiKhoan, TenDangNhap, MatKhau, HangRank, SoTuong, SoTrangPhuc, BacNgoc, LoaiDangKy
) VALUES (
    @last_tk_id, 'lq_acc_36153', 'pass531', 'Đại Cao Thủ', 124, 307, 90, 'Garena'
);

INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/lqacc153_img0.jpg');

-- ============================================
-- ACC 5: #36152
-- ============================================

INSERT INTO TAIKHOAN (
    MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, ThoiGianDang, DuongDanAnh
) VALUES (
    2, 850000, 765000, 'DANG_BAN',
    'Rank Bạch Kim III, Tướng: 123, Trang phục: 278, Ngọc: 90',
    NOW() - INTERVAL 5 DAY,
    'uploads/lqacc152_img0.jpg'
);
SET @last_tk_id = LAST_INSERT_ID();

INSERT INTO TAIKHOAN_LIENQUAN (
    MaTaiKhoan, TenDangNhap, MatKhau, HangRank, SoTuong, SoTrangPhuc, BacNgoc, LoaiDangKy
) VALUES (
    @last_tk_id, 'lq_acc_36152', 'pass521', 'Bạch Kim III', 123, 278, 90, 'Facebook'
);

INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/lqacc152_img0.jpg');


-- ============================================
-- ACC 6: #36150
-- ============================================

INSERT INTO TAIKHOAN (
    MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, ThoiGianDang, DuongDanAnh
) VALUES (
    2, 2500000, 2250000, 'DANG_BAN',
    'Rank Tinh Anh I, Tướng: 105, Trang phục: 255, Ngọc: 90',
    NOW() - INTERVAL 5 DAY,
    'uploads/lqacc150_img0.jpg'
);
SET @last_tk_id = LAST_INSERT_ID();

INSERT INTO TAIKHOAN_LIENQUAN (
    MaTaiKhoan, TenDangNhap, MatKhau, HangRank, SoTuong, SoTrangPhuc, BacNgoc, LoaiDangKy
) VALUES (
    @last_tk_id, 'lq_acc_36150', 'pass150', 'Kim Cương I', 115, 250, 90, 'Facebook'
);

INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/lqacc150_img0.jpg');

-- ============================================
-- ACC 7: #36122
-- ============================================

INSERT INTO TAIKHOAN (
    MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, ThoiGianDang, DuongDanAnh
) VALUES (
    2, 650000, 585000, 'DANG_BAN',
    'Rank Kim Cương II, Tướng: 99, Trang phục: 197, Ngọc: 90',
    NOW() - INTERVAL 7 DAY,
    'uploads/lqacc122_img0.jpg'
);
SET @last_tk_id = LAST_INSERT_ID();

INSERT INTO TAIKHOAN_LIENQUAN (
    MaTaiKhoan, TenDangNhap, MatKhau, HangRank, SoTuong, SoTrangPhuc, BacNgoc, LoaiDangKy
) VALUES (
    @last_tk_id, 'lq_acc_36122', 'pass122', 'Kim Cương II', 99, 197, 90, 'Facebook'
);

INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/lqacc122_img0.jpg');

-- ============================================
-- ACC 8: #36148
-- ============================================

INSERT INTO TAIKHOAN (
    MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, ThoiGianDang, DuongDanAnh
) VALUES (
    2, 750000, 675000, 'DANG_BAN',
    'Rank Kim Cương V, Tướng: 124, Trang phục: 239, Ngọc: 90',
    NOW() - INTERVAL 8 DAY,
    'uploads/lqacc148_img0.jpg'
);
SET @last_tk_id = LAST_INSERT_ID();

INSERT INTO TAIKHOAN_LIENQUAN (
    MaTaiKhoan, TenDangNhap, MatKhau, HangRank, SoTuong, SoTrangPhuc, BacNgoc, LoaiDangKy
) VALUES (
    @last_tk_id, 'lq_acc_36148', 'pass148', 'Kim Cương V', 124, 239, 90, 'Facebook'
);

INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/lqacc148_img0.jpg');

-- ============================================
-- ACC 9: #36146
-- ============================================

INSERT INTO TAIKHOAN (
    MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, ThoiGianDang, DuongDanAnh
) VALUES (
    2, 400000, 360000, 'DANG_BAN',
    'Rank Bạch Kim II, Tướng: 116, Trang phục: 206, Ngọc: 90',
    NOW() - INTERVAL 9 DAY,
    'uploads/lqacc146_img0.jpg'
);
SET @last_tk_id = LAST_INSERT_ID();

INSERT INTO TAIKHOAN_LIENQUAN (
    MaTaiKhoan, TenDangNhap, MatKhau, HangRank, SoTuong, SoTrangPhuc, BacNgoc, LoaiDangKy
) VALUES (
    @last_tk_id, 'lq_acc_36146', 'pass146', 'Bạch Kim II', 116, 206, 90, 'Garena'
);

INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/lqacc146_img0.jpg');

-- ============================================
-- ACC 10: #36145
-- ============================================

INSERT INTO TAIKHOAN (
    MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, ThoiGianDang, DuongDanAnh
) VALUES (
    2, 600000, 360000, 'DANG_BAN',
    'Rank Tinh Anh I, Tướng: 116, Trang phục: 206, Ngọc: 90',
    NOW() - INTERVAL 10 DAY,
    'uploads/lqacc145_img0.jpg'
);
SET @last_tk_id = LAST_INSERT_ID();

INSERT INTO TAIKHOAN_LIENQUAN (
    MaTaiKhoan, TenDangNhap, MatKhau, HangRank, SoTuong, SoTrangPhuc, BacNgoc, LoaiDangKy
) VALUES (
    @last_tk_id, 'lq_acc_36145', 'pass145', 'Tinh Anh I', 121, 211, 90, 'Garena'
);

INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/lqacc145_img0.jpg');

-- ============================================
-- ACC 11: #36144
-- ============================================

INSERT INTO TAIKHOAN (
    MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, ThoiGianDang, DuongDanAnh
) VALUES (
    2, 600000, 540000, 'DANG_BAN',
    'Rank Kim Cương I, Tướng: 116, Trang phục: 208, Ngọc: 90',
    NOW() - INTERVAL 11 DAY,
    'uploads/lqacc144_img0.jpg'
);
SET @last_tk_id = LAST_INSERT_ID();

INSERT INTO TAIKHOAN_LIENQUAN (
    MaTaiKhoan, TenDangNhap, MatKhau, HangRank, SoTuong, SoTrangPhuc, BacNgoc, LoaiDangKy
) VALUES (
    @last_tk_id, 'lq_acc_36144', 'pass144', 'Kim Cương I', 121, 211, 90, 'Facebook'
);

INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/lqacc144_img0.jpg');

-- ============================================
-- ACC 12: #36141
-- ============================================

INSERT INTO TAIKHOAN (
    MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, ThoiGianDang, DuongDanAnh
) VALUES (
    2, 400000, 360000, 'DANG_BAN',
    'Rank Kim Cương III, Tướng: 95, Trang phục: 174, Ngọc: 90',
    NOW() - INTERVAL 12 DAY,
    'uploads/lqacc141_img0.jpg'
);
SET @last_tk_id = LAST_INSERT_ID();

INSERT INTO TAIKHOAN_LIENQUAN (
    MaTaiKhoan, TenDangNhap, MatKhau, HangRank, SoTuong, SoTrangPhuc, BacNgoc, LoaiDangKy
) VALUES (
    @last_tk_id, 'lq_acc_36141', 'pass141', 'Kim Cương III', 95, 174, 90, 'Facebook'
);

INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/lqacc141_img0.jpg');

-- ============================================
-- ACC 13: #36140
-- ============================================

INSERT INTO TAIKHOAN (
    MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, ThoiGianDang, DuongDanAnh
) VALUES (
    2, 700000, 630000, 'DANG_BAN',
    'Rank Bạch Kim III, Tướng: 93, Trang phục: 171, Ngọc: 90',
    NOW() - INTERVAL 13 DAY,
    'uploads/lqacc140_img0.jpg'
);
SET @last_tk_id = LAST_INSERT_ID();

INSERT INTO TAIKHOAN_LIENQUAN (
    MaTaiKhoan, TenDangNhap, MatKhau, HangRank, SoTuong, SoTrangPhuc, BacNgoc, LoaiDangKy
) VALUES (
    @last_tk_id, 'lq_acc_36140', 'pass140', 'Bạch Kim III', 93, 171, 90, 'Garena'
);

INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/lqacc140_img0.jpg');

-- ============================================
-- ACC 14: #36138
-- ============================================

INSERT INTO TAIKHOAN (
    MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, ThoiGianDang, DuongDanAnh
) VALUES (
    2, 500000, 450000, 'DANG_BAN',
    'Rank Tinh Anh IV, Tướng: 121, Trang phục: 191, Ngọc: 90',
    NOW() - INTERVAL 14 DAY,
    'uploads/lqacc138_img0.jpg'
);
SET @last_tk_id = LAST_INSERT_ID();

INSERT INTO TAIKHOAN_LIENQUAN (
    MaTaiKhoan, TenDangNhap, MatKhau, HangRank, SoTuong, SoTrangPhuc, BacNgoc, LoaiDangKy
) VALUES (
    @last_tk_id, 'lq_acc_36138', 'pass138', 'Tinh Anh IV', 121, 191, 90, 'Garena'
);

INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/lqacc138_img0.jpg');

-- ============================================
-- ACC 15: #36135
-- ============================================

INSERT INTO TAIKHOAN (
    MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, ThoiGianDang, DuongDanAnh
) VALUES (
    2, 450000, 4055000, 'DANG_BAN',
    'Rank Bạch Kim V, Tướng: 85, Trang phục: 130, Ngọc: 90',
    NOW() - INTERVAL 15 DAY,
    'uploads/lqacc135_img0.jpg'
);
SET @last_tk_id = LAST_INSERT_ID();

INSERT INTO TAIKHOAN_LIENQUAN (
    MaTaiKhoan, TenDangNhap, MatKhau, HangRank, SoTuong, SoTrangPhuc, BacNgoc, LoaiDangKy
) VALUES (
    @last_tk_id, 'lq_acc_36135', 'pass135', 'Bạch Kim V', 85, 130, 90, 'Garena'
);

INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/lqacc135_img0.jpg');

-- ============================================
-- ACC 16: #36133
-- ============================================

INSERT INTO TAIKHOAN (
    MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, ThoiGianDang, DuongDanAnh
) VALUES (
    2, 220000, 198000, 'DANG_BAN',
    'Rank Bạch Kim V, Tướng: 74, Trang phục: 125, Ngọc: 90',
    NOW() - INTERVAL 16 DAY,
    'uploads/lqacc133_img0.jpg'
);
SET @last_tk_id = LAST_INSERT_ID();

INSERT INTO TAIKHOAN_LIENQUAN (
    MaTaiKhoan, TenDangNhap, MatKhau, HangRank, SoTuong, SoTrangPhuc, BacNgoc, LoaiDangKy
) VALUES (
    @last_tk_id, 'lq_acc_36133', 'pass133', 'Bạch Kim V', 74, 125, 90, 'Garena'
);

INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/lqacc133_img0.jpg');

-- ============================================
-- ACC 17: #36132
-- ============================================

INSERT INTO TAIKHOAN (
    MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, ThoiGianDang, DuongDanAnh
) VALUES (
    2, 280000, 252000, 'DANG_BAN',
    'Rank Bạch Kim V, Tướng: 83, Trang phục: 120, Ngọc: 90',
    NOW() - INTERVAL 17 DAY,
    'uploads/lqacc132_img0.jpg'
);
SET @last_tk_id = LAST_INSERT_ID();

INSERT INTO TAIKHOAN_LIENQUAN (
    MaTaiKhoan, TenDangNhap, MatKhau, HangRank, SoTuong, SoTrangPhuc, BacNgoc, LoaiDangKy
) VALUES (
    @last_tk_id, 'lq_acc_36132', 'pass132', 'Bạch Kim V', 83, 120, 90, 'Facebook'
);

INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/lqacc132_img0.jpg');

-- ============================================
-- ACC 18: #36126
-- ============================================

INSERT INTO TAIKHOAN (
    MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, ThoiGianDang, DuongDanAnh
) VALUES (
    2, 800000, 720000, 'DANG_BAN',
    'Rank Tinh Anh I, Tướng: 124, Trang phục: 284, Ngọc: 90',
    NOW() - INTERVAL 18 DAY,
    'uploads/lqacc126_img0.jpg'
);
SET @last_tk_id = LAST_INSERT_ID();

INSERT INTO TAIKHOAN_LIENQUAN (
    MaTaiKhoan, TenDangNhap, MatKhau, HangRank, SoTuong, SoTrangPhuc, BacNgoc, LoaiDangKy
) VALUES (
    @last_tk_id, 'lq_acc_36126', 'pass126', 'Tinh Anh I', 124, 284, 90, 'Facebook'
);

INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/lqacc126_img0.jpg');

-- ============================================
-- ACC 19: #36125
-- ============================================

INSERT INTO TAIKHOAN (
    MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, ThoiGianDang, DuongDanAnh
) VALUES (
    2, 600000, 540000, 'DANG_BAN',
    'Rank Tinh Anh IV, Tướng: 122, Trang phục: 250, Ngọc: 90',
    NOW() - INTERVAL 18 DAY,
    'uploads/lqacc125_img0.jpg'
);
SET @last_tk_id = LAST_INSERT_ID();

INSERT INTO TAIKHOAN_LIENQUAN (
    MaTaiKhoan, TenDangNhap, MatKhau, HangRank, SoTuong, SoTrangPhuc, BacNgoc, LoaiDangKy
) VALUES (
    @last_tk_id, 'lq_acc_36125', 'pass125', 'Tinh Anh IV', 122, 250, 90, 'Facebook'
);

INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/lqacc125_img0.jpg');

-- ============================================
-- ACC 20: #36122
-- ============================================

INSERT INTO TAIKHOAN (
    MaDanhMuc, GiaGoc, GiaBan, TrangThai, DiemNoiBat, ThoiGianDang, DuongDanAnh
) VALUES (
    2, 500000, 450000, 'DANG_BAN',
    'Rank Tinh Anh IV, Tướng: 122, Trang phục: 250, Ngọc: 90',
    NOW() - INTERVAL 18 DAY,
    'uploads/lqacc122_img00.jpg'
);
SET @last_tk_id = LAST_INSERT_ID();

INSERT INTO TAIKHOAN_LIENQUAN (
    MaTaiKhoan, TenDangNhap, MatKhau, HangRank, SoTuong, SoTrangPhuc, BacNgoc, LoaiDangKy
) VALUES (
    @last_tk_id, 'lq_acc_36122', 'pass122', 'Tinh Anh IV', 121, 224, 90, 'Garena'
);

INSERT INTO ANH_TAIKHOAN (MaTaiKhoan, DuongDanAnh) VALUES
(@last_tk_id, 'uploads/lqacc122_img00.jpg');