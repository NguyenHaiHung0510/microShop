<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="common/header.jsp">
    <jsp:param name="pageTitle" value="Trang Chủ"/>
</jsp:include>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Be+Vietnam+Pro:wght@400;500;700&display=swap" rel="stylesheet">

        <title>MicroShop - Đăng Ký</title> 
        
        <style>
            .login-wrapper {
                max-width: 400px;
                margin: 50px auto;
                padding: 40px;
                background-color: #ffffff;
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
                border-radius: 8px;
            }
            .form-group {
                margin-bottom: 20px;
            }
            .form-group label {
                display: block;
                margin-bottom: 8px;
                font-weight: 500;
            }
            .form-group input {
                width: 100%;
                padding: 12px 15px;
                border: 1px solid #ddd;
                border-radius: 4px;
                box-sizing: border-box;
            }
            .btn-submit {
                width: 100%;
                padding: 12px;
                background-color: #28a745; /* Màu xanh lá cho đăng ký */
                color: white;
                border: none;
                border-radius: 4px;
                font-weight: 700;
                cursor: pointer;
                transition: background-color 0.3s;
            }
            .btn-submit:hover {
                background-color: #218838;
            }
            .login-wrapper p a {
                color: #007bff;
                text-decoration: none;
            }
            .login-wrapper p a:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <div class="login-wrapper">
            <h2>Đăng Ký Tài Khoản</h2>
            
            <% 
                String error = (String) request.getAttribute("registerError");
                if (error != null && !error.isEmpty()) { 
            %>
                    <p style="color: red; text-align: center; margin-bottom: 15px;"><%= error %></p>
            <%
                }
            %>
            
            <form action="${pageContext.request.contextPath}/register" method="POST">
                <div class="form-group">
                    <label for="username">Tên người dùng:</label>
                    <input type="text" id="username" name="username" required placeholder="Nhập tên người dùng...">
                </div>
                
                <div class="form-group">
                    <label for="email">Email <span style="color:gray;">(tùy chọn)</span>:</label>
                    <input type="email" id="email" name="email" placeholder="Nhập email...">
                </div>
                
                <div class="form-group">
                    <label for="sdt">Số điện thoại: <span style="color:gray;">(tùy chọn)</span>:</label>
                    <input type="text" id="sdt" name="sdt" placeholder="Nhập số điện thoại...">
                </div>
                
                <div class="form-group">
                    <label for="password">Mật khẩu:</label>
                    <input type="password" id="password" name="password" required placeholder="Nhập mật khẩu...">
                </div>
                
                <div class="form-group">
                    <label for="confirm_password">Xác nhận mật khẩu:</label>
                    <input type="password" id="confirm_password" name="confirm_password" required placeholder="Xác nhận mật khẩu...">
                </div>
                
                <button type="submit" class="btn-submit">Đăng Ký</button>
            </form>
            
            <p style="text-align:center; margin-top: 15px;">
                Đã có tài khoản? <a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
            </p>
        </div>
    </body>
</html>

<jsp:include page="common/footer.jsp" />