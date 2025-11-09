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

        <title>MicroShop - Đăng Nhập</title> 
        
        <style>
            .login-wrapper {
                max-width: 400px;
                margin: 50px auto;
                padding: 40px;
                background-color: #ffffff; /* Thêm nền trắng */
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
                box-sizing: border-box; /* Quan trọng để padding không làm tăng chiều rộng */
            }
            .btn-submit {
                width: 100%;
                padding: 12px;
                background-color: #007bff; /* Màu xanh dương tiêu chuẩn */
                color: white;
                border: none;
                border-radius: 4px;
                font-weight: 700;
                cursor: pointer;
                transition: background-color 0.3s;
            }
            .btn-submit:hover {
                background-color: #0056b3;
            }
            .login-wrapper p a {
                color: #007bff;
                text-decoration: none;
            }
            .login-wrapper p a:hover {
                text-decoration: underline;
            }
            /* Hiển thị hộp thông báo nhỏ ở dưới */
            .toast {
                position: fixed;
                bottom: 30px;
                right: 30px;
                background-color: #28a745;
                color: white;
                padding: 15px 25px;
                border-radius: 6px;
                font-weight: 500;
                font-size: 15px;
                box-shadow: 0 4px 10px rgba(0,0,0,0.2);
                opacity: 0;
                transform: translateY(30px);
                transition: opacity 0.5s, transform 0.5s;
                z-index: 9999;
            }
            .toast.show {
                opacity: 1;
                transform: translateY(0);
            }
        </style>
    </head>

    <script>
        // Hiển thị toast khi trang được tải
        window.addEventListener("load", () => {
            const toast = document.getElementById("toast");
            if (toast) {
                toast.classList.add("show");
                setTimeout(() => toast.classList.remove("show"), 4000); // Tự ẩn sau 4 giây
            }
        });
    </script>

    <body>
        <div class="login-wrapper">
            <h2>Đăng Nhập Hệ Thống</h2>
            
            <% 
                String error = (String) request.getAttribute("loginError");
                if (error != null && !error.isEmpty()) { 
            %>
                    <p style="color: red; text-align: center; margin-bottom: 15px;"><%= error %></p>
            <%
                }
            %>
            
            <form action="${pageContext.request.contextPath}/login" method="POST">
                <div class="form-group">
                    <label for="username">Tên người dùng:</label>
                    <input type="text" id="username" name="username" required 
                           placeholder="Nhập tên người dùng...">
                </div>
                
                <div class="form-group">
                    <label for="password">Mật khẩu:</label>
                    <input type="password" id="password" name="password" required
                           placeholder="Nhập mật khẩu...">
                </div>
                
                <button type="submit" class="btn-submit">Đăng Nhập</button>
            </form>
            <p style="text-align:center; margin-top: 15px;">
                Chưa có tài khoản? <a href="${pageContext.request.contextPath}/register">Đăng ký</a>
            </p>
        </div>
    </body>
    <%
    String registerSuccess = request.getParameter("register");
    boolean showSuccessToast = "success".equals(registerSuccess);
    %>
    <% if (showSuccessToast) { %>
        <div id="toast" class="toast">🎉 Đăng ký tài khoản thành công! Hãy đăng nhập để tiếp tục.</div>
    <% } %>
</html>

<jsp:include page="common/footer.jsp" />