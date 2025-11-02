<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
    Lưu ý: Để dùng JSTL (các thẻ <c:if>, <c:forEach>), 
    bạn cần thêm file .jar của JSTL vào thư mục WEB-INF/lib.
    (Nếu bạn dùng Maven, bạn cần thêm dependency 'jakarta.servlet.jsp.jstl').
--%>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <%-- Import CSS Chung từ /assets/css/style.css --%>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">

        <title>MicroShop - ${param.pageTitle}</title> 
    </head>
    <body>
        <header>
            <%-- 1. Thanh Top Bar (Logo, Search, User) --%>
            <div class="top-bar">
                <div class="logo">
                    <%-- Sử dụng logo bạn đã cung cấp --%>
                    <a href="${pageContext.request.contextPath}/home">
                        <img src="${pageContext.request.contextPath}/assets/images/logo.png" alt="MicroShop Logo">
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

            <%-- 2. Thanh Menu Chính (Kiểu Daominhha) --%>
            <nav class="main-nav">
                <%-- Đây là 4 danh mục sản phẩm chính của bạn --%>
                <a href="${pageContext.request.contextPath}/shop/game?category=lienquan">Tài khoản Liên Quân</a>
                <a href="${pageContext.request.contextPath}/shop/game?category=freefire">Tài khoản Free Fire</a>
                <a href="${pageContext.request.contextPath}/shop/game?category=riot">Tài khoản LMHT & TFT</a>
                <a href="${pageContext.request.contextPath}/shop/steam">Dịch vụ Game Steam</a>
            </nav>
        </header>

        <main>
            <%-- Phần thân trang (home.jsp) sẽ được chèn vào đây --%>