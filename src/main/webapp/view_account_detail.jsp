<%@page import="com.microshop.model.NguoiDung"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tài Khoản Đã Mua</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <style>
        /* CSS Cục bộ cho trang danh sách tài khoản */
        * { box-sizing: border-box; }
        body {
            font-family: 'Be Vietnam Pro', Arial, sans-serif;
            margin: 0; padding: 0;
            background-color: #f4f7f6; color: #333;
        }
        .container {
            max-width: 960px;
            margin: 30px auto;
            padding: 20px;
        }
        .account-table {
            width: 100%;
            background-color: #fff;
            border-collapse: collapse;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
            border-radius: 8px;
            overflow: hidden;
        }
        .account-table th, .account-table td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #eee;
        }
        .account-table th {
            background-color: #c92a2a; /* Màu accent */
            color: white;
            font-weight: 600;
        }
        .account-table tr:hover {
            background-color: #f9f9f9;
        }
        .sensitive-data {
            background-color: #ffe6e6;
            color: #c0392b;
            font-weight: bold;
            padding: 5px;
            border-radius: 3px;
        }
        .back-link {
            display: inline-block;
            margin-top: 20px;
            color: #007bff;
            font-weight: 500;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Tài Khoản Đã Mua Của Bạn</h2>

    <c:if test="${empty requestScope.purchasedAccounts and empty requestScope.steamAccounts}">
        <p>Bạn chưa mua tài khoản nào.</p>
    </c:if>

    <c:if test="${not empty requestScope.purchasedAccounts}">
        <table class="account-table">
            <thead>
                <tr>
                    <th>Mã TK</th>
                    <th>Nổi bật</th>
                    <th>Tài khoản (Game)</th>
                    <th>Mật khẩu (Game)</th>
                </tr>
            </thead>
            <tbody>
                <%-- Lặp qua danh sách tài khoản đã mua --%>
                <c:forEach var="tk" items="${requestScope.purchasedAccounts}">
                    <tr>
                        <td>#${tk.maTaiKhoan}</td>
                        <td>${tk.diemNoiBat}</td>
                        
                        <%-- 
                           HIỂN THỊ THÔNG TIN NHẠY CẢM
                           Giả định Model của bạn (ví dụ: TaiKhoanLienQuan) 
                           có các getter cho Tên đăng nhập và Mật khẩu của game.
                        --%>
                        <td class="sensitive-data">${tk.tenDangNhap}</td>
                        <td class="sensitive-data">${tk.matKhau}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <div style="background-color: #fff3cd; padding: 15px; margin-top: 20px; border: 1px solid #ffeeba; border-radius: 4px;">
            <strong>Cảnh báo:</strong> Vì lý do bảo mật, vui lòng đổi mật khẩu ngay sau khi nhận tài khoản!
        </div>
        
    </c:if>
    <c:if test="${not empty requestScope.steamAccounts}">
        <table class="account-table">
            <thead>
                <tr>
                    <th>Mã TK</th>
                    <th>Tên Game</th>
                    <th>Tài khoản (Steam)</th>
                    <th>Mật khẩu (Steam)</th>
                </tr>
            </thead>
            <tbody>
                <%-- Lặp qua danh sách tài khoản đã mua --%>
                <c:forEach var="tk" items="${requestScope.steamAccounts}">
                    <tr>
                        <td>Steam#${tk.maTaiKhoan}</td>
                        <td>${tk.tenGame}</td>
                        
                        <%-- 
                           HIỂN THỊ THÔNG TIN NHẠY CẢM
                           Giả định Model của bạn (ví dụ: TaiKhoanLienQuan) 
                           có các getter cho Tên đăng nhập và Mật khẩu của game.
                        --%>
                        <td class="sensitive-data">${tk.tenDangNhap}</td>
                        <td class="sensitive-data">${tk.matKhau}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <div style="background-color: #fff3cd; padding: 15px; margin-top: 20px; border: 1px solid #ffeeba; border-radius: 4px;">
            <strong>Cảnh báo:</strong> Vì lý do bảo mật, vui lòng đổi mật khẩu ngay sau khi nhận tài khoản!
        </div>
        
    </c:if>
    <a href="${pageContext.request.contextPath}/profile" class="back-link">← Quay lại Hồ Sơ</a>
</div>

</body>
</html>