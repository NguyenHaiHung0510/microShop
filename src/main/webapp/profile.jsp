    <%@page import="com.microshop.model.NguoiDung"%>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="UTF-8">
        <title>Hồ Sơ Người Dùng</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"> 
        <style>
            /* CSS nội bộ cho form profile */
            .profile-container {
                max-width: 600px;
                margin: 50px auto;
                padding: 30px;
                background-color: #fff;
                border-radius: 8px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            }
            .profile-container h2 {
                text-align: center;
                color: #c92a2a;
                margin-bottom: 25px;
            }
            .profile-info p {
                font-size: 16px;
                margin: 10px 0;
            }
            .profile-info strong {
                display: inline-block;
                width: 150px;
                font-weight: 600;
            }
            .form-group {
                margin-bottom: 15px;
            }
            .form-group label {
                display: block;
                margin-bottom: 5px;
                font-weight: 500;
            }
            .form-control {
                width: 100%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
            }
            .btn-update {
                background-color: #28a745;
                color: white;
                padding: 10px 15px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-weight: 700;
                transition: background-color 0.2s;
            }
            .btn-update:hover {
                background-color: #218838;
            }
            .alert-success {
                padding: 10px;
                background-color: #d4edda;
                color: #155724;
                border: 1px solid #c3e6cb;
                border-radius: 4px;
                margin-bottom: 20px;
            }
            .profile-links a {
                display: block;
                margin-bottom: 8px;
                padding: 8px;
                background-color: #f8f9fa;
                border: 1px solid #eee;
                border-radius: 4px;
                text-align: center;
                transition: background-color 0.2s;
            }
            .profile-links a:hover {
                background-color: #e9ecef;
            }
        </style>
    </head>
    <body>

    <%
        // Lấy đối tượng NguoiDung từ Session
        NguoiDung user = (NguoiDung) session.getAttribute("user");
    %>
    <%
        // Lấy tên hạng thành viên
        String tenHTV = (String) request.getAttribute("HangNguoiDung");
    %>
    
    <div class="profile-container">
        <h2>Thông Tin Cá Nhân</h2>

        <c:if test="${param.update eq 'success'}">
            <div class="alert-success">Cập nhật hồ sơ thành công!</div>
        </c:if>

        <div class="profile-info">
            <p><strong>ID Người Dùng:</strong> <%= user.getMaNguoiDung() %></p>
            <p><strong>Tên Đăng Nhập:</strong> <%= user.getTenDangNhap() %></p>
            <p><strong>Email:</strong> <%= user.getEmail() %></p>
            <p><strong>SĐT:</strong> <%= user.getSoDienThoai() %></p>
            <p><strong>Tổng tiền đã chi:</strong> <%= user.getTongTienDaChi() %></p>
            <p><strong>Hạng Thành Viên:</strong> <%= tenHTV%></p>
        </div>

        <hr>

        <h3>Các Tùy Chọn Khác</h3>
        <div class="profile-links">
            <a href="${pageContext.request.contextPath}/profile/orders">Lịch Sử Đơn Hàng</a>
            <a href="${pageContext.request.contextPath}/profile/view-account">Xem Tài Khoản Đã Mua</a>
        </div>

        <hr>

        <h3>Cập Nhật Thông Tin</h3>
        <div style="text-align: center;">
            <a href="${pageContext.request.contextPath}/profile/edit" class="btn-update" style="display: inline-block;">
                Chỉnh Sửa Hồ Sơ
            </a>
        </div>

        <p style="text-align: center; margin-top: 20px;">
            <a href="${pageContext.request.contextPath}/home">Quay lại Trang Chủ</a> | 
            <a href="${pageContext.request.contextPath}/logout">Đăng Xuất</a>
        </p>
    </div>

    </body>
    </html>