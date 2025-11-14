-- Truy vấn để lấy thông tin chi tiết của một tài khoản game
SELECT 
    -- Thông tin từ bảng TAIKHOAN
    tk.MaTaiKhoan,
    tk.GiaBan,
    tk.DiemNoiBat,
    
    -- Thông tin từ bảng DANHMUC
    dm.TenDanhMuc,
    
    -- Thông tin chi tiết từ bảng TAIKHOAN_LIENQUAN
    tklq.HangRank,
    tklq.SoTuong,
    tklq.SoTrangPhuc,
    
    -- Lấy tất cả các ảnh của tài khoản này (sẽ có nhiều dòng bị lặp, backend sẽ xử lý việc này)
    anh.DuongDanAnh

FROM 
    `TAIKHOAN` tk
-- Kết nối với bảng DANHMUC để lấy Tên Danh Mục
JOIN 
    `DANHMUC` dm ON tk.MaDanhMuc = dm.MaDanhMuc
-- Kết nối với bảng chi tiết để lấy Rank, Số Tướng...
LEFT JOIN 
    `TAIKHOAN_LIENQUAN` tklq ON tk.MaTaiKhoan = tklq.MaTaiKhoan
-- Kết nối với bảng ảnh để lấy đường dẫn các ảnh
LEFT JOIN 
    `ANH_TAIKHOAN` anh ON tk.MaTaiKhoan = anh.MaTaiKhoan

WHERE 
    tk.MaTaiKhoan;
    


USE `microshop_db`;
SELECT * FROM GAME_STEAM;
DELETE FROM GAME_STEAM as gs WHERE gs.MaGameSteam >= 0;

SELECT * FROM TAIKHOAN;
DELETE FROM TAIKHOAN as tk WHERE tk.MaTaiKhoan >=0;

select * from nguoidung;

select * from donhang_slot_steam;

select * from taikhoan_steam;
update taikhoan_steam set soslotdaban = 0 where mataikhoansteam > 0;