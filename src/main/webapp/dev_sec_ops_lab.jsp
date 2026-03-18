<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>MicroShop Security Testing Lab️</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <style>
        .lab-container {
            max-width: 800px;
            margin: 40px auto;
            padding: 20px;
            background: #f9f9f9;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        .test-card {
            background: #fff;
            border-left: 5px solid #e74c3c;
            padding: 20px;
            margin-bottom: 25px;
            border-radius: 4px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.05);
        }
        .test-card h3 { color: #333; margin-top: 0; }
        .form-group { margin-bottom: 15px; display: flex; flex-direction: column; }
        .form-group label { font-weight: bold; margin-bottom: 5px; color: #555; }
        .form-group input { padding: 10px; border: 1px solid #ccc; border-radius: 4px; }
        .btn-test { 
            background: #e74c3c; color: white; padding: 10px 15px; 
            border: none; border-radius: 4px; cursor: pointer; font-weight: bold;
        }
        .btn-test:hover { background: #c0392b; }
    </style>
</head>
<body>
    <div class="lab-container">
        <h2 style="text-align: center;">Khu vực kiểm thử DevSecOps (ZAP DAST)</h2>
        <p style="text-align: center; color: #666;">Trang này mô phỏng các tính năng thực tế chứa lỗ hổng OWASP Top 10 2025 để phục vụ quét động.</p>

        <div class="test-card">
            <h3>1. Tra cứu thông tin khách hàng nhanh (Lỗi A01 - IDOR)</h3>
            <p><i>Mô phỏng: Quản trị viên nhập ID để xem nhanh thông tin, nhưng thiếu cơ chế check quyền.</i></p>
            <form action="${pageContext.request.contextPath}/demo/a01-idor" method="GET">
                <div class="form-group">
                    <label>Mã người dùng (ID):</label>
                    <input type="number" name="id" value="1" placeholder="Nhập ID..." required>
                </div>
                <button type="submit" class="btn-test">Tra cứu ngay</button>
            </form>
        </div>

        <div class="test-card" style="border-left-color: #f39c12;">
            <h3>2. Tìm kiếm người dùng (Lỗi A05 - SQL Injection)</h3>
            <p><i>Mô phỏng: Tính năng search cộng chuỗi trực tiếp</i></p>
            <form action="${pageContext.request.contextPath}/demo/a05-sqli" method="GET">
                <div class="form-group">
                    <label>Tên đăng nhập:</label>
                    <input type="text" name="username" placeholder="Nhập tên đăng nhập:" required>
                </div>
                <button type="submit" class="btn-test" style="background: #f39c12;">Tìm kiếm</button>
            </form>
        </div>

        <div class="test-card" style="border-left-color: #3498db;">
            <h3>3. Thanh toán dịch vụ (Lỗi A06 - Insecure Design)</h3>
            <p><i>Mô phỏng: Form thanh toán tin tưởng tuyệt đối giá tiền (total_amount) từ trình duyệt gửi lên.</i></p>
            <form action="${pageContext.request.contextPath}/demo/a06-insecure-design" method="POST">
                <div class="form-group">
                    <label>Sản phẩm mua: Gói Tài Khoản VIP</label>
                    <input type="hidden" name="item_name" value="Gói VIP">
                </div>
                <div class="form-group">
                    <label>Giá tiền xác nhận thanh toán (VNĐ):</label>
                    <input type="number" name="total_amount" value="500000">
                </div>
                <button type="submit" class="btn-test" style="background: #3498db;">Xác nhận thanh toán</button>
            </form>
        </div>
    </div>
</body>
</html>