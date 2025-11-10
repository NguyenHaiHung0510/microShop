<%@page import="com.microshop.model.NguoiDung"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thanh Toán Thành Công!</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <style>
        /* CSS Cục bộ cho trang Success */
        .success-container {
            max-width: 600px;
            margin: 80px auto;
            padding: 40px;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        .success-container h2 {
            font-size: 2em;
            margin-bottom: 20px;
        }
        .icon {
            font-size: 4em;
            margin-bottom: 20px;
        }
        .success-icon {
            color: #2ecc71; /* Màu xanh lá cây */
        }
        .error-icon {
            color: #e74c3c; /* Màu đỏ */
        }
        .details-box {
            background-color: #f8f8f8;
            padding: 20px;
            border-radius: 6px;
            margin-top: 25px;
            text-align: left;
        }
        .details-box p {
            margin: 8px 0;
            font-size: 1.1em;
        }
        .action-links a {
            display: inline-block;
            margin: 15px 10px 0;
            padding: 10px 20px;
            border-radius: 5px;
            font-weight: 600;
            transition: background-color 0.2s;
        }
        .btn-home {
            background-color: #3498db;
            color: white;
        }
        .btn-home:hover {
            background-color: #2980b9;
        }
        .btn-profile {
            background-color: #f39c12;
            color: white;
        }
        .btn-profile:hover {
            background-color: #e67e22;
        }
    </style>
</head>
<body>

<c:set var="user" value="${sessionScope.user}" />
<c:set var="status" value="${requestScope.paymentSuccessStatus}" />

<div class="success-container">
    
    <%-- 1. Xử lý LỖI ĐĂNG NHẬP: Hiển thị nếu user == null --%>
    <c:if test="${user == null}">
        <h2 class="error-icon">🛑 Lỗi Truy Cập</h2>
        <p>Vui lòng đăng nhập để xem chi tiết giao dịch.</p>
        <div class="action-links">
            <a href="${pageContext.request.contextPath}/login" class="btn-home">Đăng Nhập</a>
        </div>
    </c:if>
    
    <%-- 2. KHỐI NỘI DUNG CHÍNH (Chỉ hiển thị khi có user) --%>
    <c:if test="${user != null}">
        <c:choose>
            <c:when test="${status eq true}">
                <%-- THANH TOÁN THÀNH CÔNG --%>
                <div class="icon success-icon">✔</div>
                <h2 style="color: #2ecc71;">Giao Dịch Thành Công!</h2>
                <p>Tài khoản game đã được giao và gửi vào email của bạn.</p>
                
                <div class="details-box">
                    <p><strong>Mã Giao Dịch:</strong> ${requestScope.transactionId}</p>
                    <p><strong>Phương Thức:</strong> ${requestScope.transactionMethod}</p>
                    <p><strong>Người Nhận:</strong> ${user.email}</p>
                </div>
                
            </c:when>
            
            <c:otherwise>
                <%-- THANH TOÁN THẤT BẠI HOẶC LỖI HỆ THỐNG --%>
                <div class="icon error-icon">❌</div>
                <h2 style="color: #e74c3c;">Giao Dịch Thất Bại</h2>
                <p>Đã xảy ra lỗi trong quá trình xử lý giao dịch của bạn.</p>
                
                <c:if test="${not empty requestScope.errorMessage}">
                    <p style="color: #e74c3c; font-weight: 500;">Chi tiết: ${requestScope.errorMessage}</p>
                </c:if>
                
            </c:otherwise>
        </c:choose>
        
        <%-- LIÊN KẾT HÀNH ĐỘNG (Chỉ hiển thị nếu đã đăng nhập) --%>
        <div class="action-links">
            <a href="${pageContext.request.contextPath}/home" class="btn-home">Quay lại Trang Chủ</a>
            <a href="${pageContext.request.contextPath}/profile/view-account" class="btn-profile">Xem Tài Khoản Đã Mua</a>
        </div>
    </c:if>
</div>

</body>
</html>