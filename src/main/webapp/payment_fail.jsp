<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<jsp:include page="common/header.jsp">
    <jsp:param name="pageTitle" value="Trang Chủ"/>
</jsp:include>

<html>
<head>
    <meta charset="UTF-8">
    <title>Giao Dịch Thất Bại</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <style>
        /* CSS Cục bộ cho trang Thất Bại */
        .fail-container {
            max-width: 600px;
            margin: 80px auto;
            padding: 40px;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        .fail-container h2 {
            font-size: 2em;
            margin-bottom: 20px;
            color: #e74c3c; /* Màu đỏ */
        }
        .icon {
            font-size: 4em;
            margin-bottom: 20px;
        }
        .error-icon {
            color: #e74c3c; /* Màu đỏ */
        }
        .message-box {
            background-color: #f8f8f8;
            padding: 20px;
            border-radius: 6px;
            margin-top: 25px;
            text-align: center;
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
    </style>
</head>
<body>

<div class="fail-container">
    
    <div class="icon error-icon">❌</div>
    <h2>Giao Dịch Thất Bại</h2>
    
    <div class="message-box">
        <p>Rất tiếc, đã xảy ra lỗi trong quá trình thanh toán của bạn.</p>
        
        <c:choose>
            <c:when test="${not empty requestScope.errorMessage}">
                <%-- Lỗi chung (do server) --%>
                <p style="color: #e74c3c; font-weight: 500;">${requestScope.errorMessage}</p>
            </c:when>
            <c:otherwise>
                <%-- Lỗi do sản phẩm đã bị mua (do logic trong Servlet) --%>
                <p style="color: #e74c3c; font-weight: 500;">
                    Tài khoản này đã được người khác thanh toán. Vui lòng quay lại và chọn sản phẩm khác.
                </p>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="action-links">
        <a href="${pageContext.request.contextPath}/home" class="btn-home">Quay lại Trang Chủ</a>
    </div>
</div>

</body>
</html>

<jsp:include page="common/footer.jsp" />