<%@page import="com.microshop.model.NguoiDung"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chỉnh Sửa Hồ Sơ</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css"> 
    <style>
        /* CSS Nội bộ cho Form Cập nhật */
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
            color: #c92a2a; /* Màu đỏ accent */
            margin-bottom: 25px;
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
            width: 100%; /* Làm nút rộng hết cỡ */
            transition: background-color 0.2s;
            margin-top: 10px;
        }
        .btn-update:hover {
            background-color: #218838;
        }
        .alert-danger {
            padding: 10px;
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
            border-radius: 4px;
            margin-bottom: 20px;
        }
        .note {
            font-size: 12px;
            color: #6c757d;
            margin-top: -5px;
            margin-bottom: 10px;
            display: block;
        }
    </style>
</head>
<body>

<%
    // Lấy đối tượng NguoiDung từ Session
    NguoiDung user = (NguoiDung) session.getAttribute("user");
%>

<div class="profile-container">
    <h2>Chỉnh Sửa Hồ Sơ</h2>

    <c:if test="${requestScope.errorEditMessage != null}">
        <div class="alert-danger">${requestScope.errorEditMessage}</div>
    </c:if>
    
    <form action="${pageContext.request.contextPath}/profile/edit" method="POST">
        
        <div class="form-group">
        <label for="username">Tên Đăng Nhập:</label>
            <input type="text" id="username" name="username" class="form-control" 
                   value="<%= user.getTenDangNhap() != null ? user.getTenDangNhap() : "" %>" disabled
                   style="margin-bottom: 5px;"> 
            <span class="note" style="margin-top: 0;">Tên đăng nhập không thể thay đổi.</span>
        </div>
        
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" class="form-control" 
                   value="<%= user.getEmail() %>">
        </div>
        
        <hr>
        
        <div class="form-group">
            <label for="sdt">Số Điện Thoại:</label>
            <input type="tel" id="sdt" name="sdt" class="form-control" 
                   value="<%= user.getSoDienThoai() != null ? user.getSoDienThoai() : "" %>">
        </div>
        
        <hr>
        
        <h3>Thay Đổi Mật Khẩu (Nếu cần)</h3>
        <div class="form-group">
            <label for="old_password">Mật Khẩu Hiện Tại:</label>
            <input type="password" id="old_password" name="old_password" class="form-control">
            <span class="note" style="margin-top: 5px;">Bắt buộc nhập nếu bạn muốn thay đổi mật khẩu mới.</span>
        </div>
        <div class="form-group">
            <label for="new_password">Mật Khẩu Mới:</label>
            <input type="password" id="new_password" name="new_password" class="form-control"
                   style="margin-bottom: 5px;">  
            <span class="note" style="margin-top: 0;">Để trống nếu bạn không muốn thay đổi mật khẩu.</span>
        </div>
        <div class="form-group">
            <label for="confirm_password">Xác Nhận Mật Khẩu Mới:</label>
            <input type="password" id="confirm_password" name="confirm_password" class="form-control">
        </div>
        
        <button type="submit" class="btn-update">Lưu Thay Đổi</button>
    </form>
    
    <p style="text-align: center; margin-top: 20px;">
        <a href="${pageContext.request.contextPath}/profile">Quay lại Hồ Sơ</a>
    </p>
</div>

</body>
</html>