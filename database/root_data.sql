-- Quy định các hạng có trong dự án
INSERT INTO `HANGTHANHVIEN` (`TenHang`, `MucChiTieuToiThieu`, `ChietKhau`) VALUES
    ('Thường', 0, 0.00),
    ('Đồng', 300000, 0.02),
    ('Bạc', 700000, 0.04),
    ('Vàng', 1200000, 0.06),
    ('Kim Cương', 4000000, 0.08);

-- Quy định các danh mục có trong dự án
INSERT INTO `DANHMUC` (`TenDanhMuc`) VALUES
    ('Free Fire'),
    ('Liên Quân'),
    ('Liên Minh Huyền Thoại - TFT');

-- Tài khoản admin
-- Sử dụng tạm mật khẩu 1, giai đoạn sản phẩm sẽ chỉ được lưu các giá trị hashed
INSERT INTO `NGUOIDUNG` (`TenDangNhap`, `MatKhau`, `VaiTro`) VALUES
     ('admin', '1', 'ADMIN');

-- Các tài khoản test
INSERT INTO `NGUOIDUNG` (`TenDangNhap`, `MatKhau`, `Email`, `VaiTro`) VALUES
    ('testuser1', '1', 'usertest1@gmail.com', 'USER'),
    ('testuser2', '1', 'usertest2@gmail.com', 'USER'),
    ('testuser3', '1', 'usertest3@gmail.com', 'USER'),
    ('testuser4', '1', 'usertest4@gmail.com', 'USER'),
    ('testuser5', '1', 'usertest5@gmail.com', 'USER');
