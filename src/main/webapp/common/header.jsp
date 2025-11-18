<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Be+Vietnam+Pro:wght@400;500;700&display=swap" rel="stylesheet">

        <title>MicroShop - ${param.pageTitle}</title> 

        <style>
            /* --- Sticky Header Style --- */
            header {
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                z-index: 1000;
                background-color: #ffffff;
                transition: top 0.3s ease;
            }

            body {
                padding-top: 120px; /* Chừa chỗ cho header khi cố định */
            }
        </style>
    </head>

    <body>
        <header id="mainHeader">
            <div class="top-bar">
                <div class="logo">
                    <a href="${pageContext.request.contextPath}/home" class="logo-link">
                        <img src="${pageContext.request.contextPath}/assets/images/root_images/microshop_logo.png" alt="MicroShop Logo">
                    </a>
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
                <a href="${pageContext.request.contextPath}/shop/game?category=lienquan&page=1">Tài khoản Liên Quân</a>
                <a href="${pageContext.request.contextPath}/shop/game?category=freefire&page=1">Tài khoản Free Fire</a>
                <a href="${pageContext.request.contextPath}/shop/game?category=riot&page=1">Tài khoản LMHT & TFT</a>
                <a href="${pageContext.request.contextPath}/shop/steam?page=1">Dịch vụ Game Steam</a>
                <a href="${pageContext.request.contextPath}/policy">Chính sách bảo hành</a>
            </nav>
        </header>

        <main>
            <script>
                // --- Hiệu ứng header ẩn khi cuộn xuống, hiện khi cuộn lên ---
                let lastScrollY = window.scrollY;
                const header = document.getElementById("mainHeader");

                window.addEventListener("scroll", () => {
                    if (window.scrollY > lastScrollY && window.scrollY > 100) {
                        // Cuộn xuống -> ẩn header
                        header.style.top = "-120px";
                    } else {
                        // Cuộn lên -> hiện lại header
                        header.style.top = "0";
                    }
                    lastScrollY = window.scrollY;
                });
            </script>
    </body>