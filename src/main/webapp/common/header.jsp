<%-- BƯỚC 1: Thêm dòng này để sửa lỗi font Tiếng Việt --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">

        <%-- BƯỚC 2: Thêm Google Font (Be Vietnam Pro) để chữ đẹp hơn --%>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Be+Vietnam+Pro:wght@400;500;700&display=swap" rel="stylesheet">

        <title>MicroShop - ${param.pageTitle}</title> 
    </head>
    <body>
        <header>
            <div class="top-bar">
                <div class="logo">
                    <a href="${pageContext.request.contextPath}/home">
                        <%-- BƯỚC 3: Đảm bảo logo của bạn tên là 'logo.png' và nằm trong 'assets/images/' --%>
                        <img src="${pageContext.request.contextPath}/assets/images/microshop_logo.png" alt="MicroShop Logo">
                    </a>
                </div>

                <div class="search-bar">
                    <input type="text" placeholder="Nhập nội dung tìm kiếm...">
                    <button type="submit">Tìm</button>
                </div>

                <div class="user-actions">
                    <c:if test="${empty sessionScope.user}">
                        <a href="${pageContext.request.contextPath}/login" class="nav-button">Đăng Nhập</a>
                        <a href="${pageContext.request.contextPath}/register" class="nav-button accent">Đăng Ký</a>
                    </c:if>

                    <c:if test="${not empty sessionScope.user}">
                        <span>Chào, ${sessionScope.user.tenDangNhap}!</span>
                        <c:if test="${sessionScope.user.vaiTro == 'ADMIN'}">
                            <a href="${pageContext.request.contextPath}/admin/dashboard" class="nav-button admin">Admin Panel</a>
                        </c:if>
                        <a href="${pageContext.request.contextPath}/profile" class="nav-button">Trang cá nhân</a>
                        <a href="${pageContext.request.contextPath}/logout" class="nav-button">Đăng Xuất</a>
                    </c:if>
                </div>
            </div>

            <nav class="main-nav">
                <a href="${pageContext.request.contextPath}/shop/game?category=lienquan">Tài khoản Liên Quân</a>
                <a href="${pageContext.request.contextPath}/shop/game?category=freefire">Tài khoản Free Fire</a>
                <a href="${pageContext.request.contextPath}/shop/game?category=riot">Tài khoản LMHT & TFT</a>
                <a href="${pageContext.request.contextPath}/shop/steam">Dịch vụ Game Steam</a>

                <%-- BƯỚC 4: Thêm menu "Liên hệ Admin" theo yêu cầu --%>
                <a href="${pageContext.request.contextPath}/contact">Liên hệ Admin</a>
            </nav>
        </header>

        <main>
            <%-- Phần thân trang (home.jsp) sẽ được chèn vào đây --%>