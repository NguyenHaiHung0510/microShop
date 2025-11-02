<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- Import Header --%>
<jsp:include page="common/header.jsp">
    <jsp:param name="pageTitle" value="Trang Chủ"/>
</jsp:include>

<%-- 1. Phần Banner "Game & Dịch vụ" (SỬA: Đã thành Carousel) --%>
<section class="services-banner">
    <h2>GAME & DỊCH VỤ</h2>

    <%-- SỬA: Thêm "wrapper" để tạo hiệu ứng carousel --%>
    <div class="service-carousel-wrapper">
        <div class="services-grid">
            <%-- 6 Dịch vụ GỐC --%>
            <a href="${pageContext.request.contextPath}/shop/game?category=lienquan" class="service-card lienquan">
                 <h3>Tài Khoản Liên Quân</h3>
            </a>
            <a href="${pageContext.request.contextPath}/shop/game?category=freefire" class="service-card freefire">
                <h3>Tài Khoản FreeFire</h3>
            </a>
            <a href="${pageContext.request.contextPath}/shop/game?category=riot" class="service-card riot">
                 <h3>Tài Khoản LMHT & TFT</h3>
            </a>
            
            <%-- SỬA: Xóa H3 cho Steam --%>
            <a href="${pageContext.request.contextPath}/shop/steam" class="service-card steam">
                <%-- Đã xóa H3 --%>
            </a>

            <%-- SỬA: Xóa H3, đổi text span --%>
            <a href="#" class="service-card youtube">
                <%-- Đã xóa H3 --%>
                <span>Dịch vụ sắp tới</span>
            </a>
            <a href="#" class="service-card netflix">
                <%-- Đã xóa H3 --%>
                <span>Dịch vụ sắp tới</span>
            </a>

            <%-- SỬA: Thêm 6 dịch vụ SAO CHÉP (để tạo hiệu ứng lặp vô tận) --%>
            <a href="${pageContext.request.contextPath}/shop/game?category=lienquan" class="service-card lienquan">
                <h3>TÀI KHOẢN LIÊN QUÂN</h3>
            </a>
            <a href="${pageContext.request.contextPath}/shop/game?category=freefire" class="service-card freefire">
                 <h3>TÀI KHOẢN FREE FIRE</h3>
            </a>
            <a href="${pageContext.request.contextPath}/shop/game?category=riot" class="service-card riot">
                <h3>TÀI KHOẢN RIOT</h3>
            </a>

            <%-- SỬA: Xóa H3 cho Steam (bản sao chép) --%>
            <a href="${pageContext.request.contextPath}/shop/steam" class="service-card steam">
                 <%-- Đã xóa H3 --%>
            </a>

            <%-- SỬA: Xóa H3, đổi text span (bản sao chép) --%>
            <a href="#" class="service-card youtube">
                <%-- Đã xóa H3 --%>
                <span>Dịch vụ sắp tới</span>
            </a>
             <a href="#" class="service-card netflix">
                <%-- Đã xóa H3 --%>
                <span>Dịch vụ sắp tới</span>
            </a>
        </div>
    </div>
</section>

<%-- 2. Khối Tài khoản Liên Quân (Giữ nguyên) --%>
<section class="product-section">
    <h2>TÀI KHOẢN LIÊN QUÂN NỔI BẬT</h2>
    <div class="product-grid">
         <c:if test="${empty listLienQuan}">
            <p>Chưa có sản phẩm nào trong danh mục này.</p>
        </c:if>
        <c:forEach items="${listLienQuan}" var="tk">
            <div class="product-card">
                <div class="product-image">
                    <img src="https://via.placeholder.com/250x150?text=Acc+Lien+Quan" alt="Ảnh ${tk.maTaiKhoan}">
                 </div>
                <div class="product-info">
                    <h4 class="product-title">${not empty tk.diemNoiBat ? tk.diemNoiBat : "Tài khoản Liên Quân"}</h4>
                    <div class="product-price">
                        <span class="old-price">${tk.giaGoc} VNĐ</span>
                        <span class="new-price">${tk.giaBan} VNĐ</span>
                    </div>
                 </div>
                <a href="${pageContext.request.contextPath}/shop/game/detail?id=${tk.maTaiKhoan}&category=lienquan" class="buy-button">
                    Xem Chi Tiết
                </a>
            </div>
        </c:forEach>
    </div>
</section>

<%-- 3. Khối Tài khoản Free Fire (Giữ nguyên) --%>
<section class="product-section">
    <h2>TÀI KHOẢN FREE FIRE NỔI BẬT</h2>
    <div class="product-grid">
        <%-- (Copy code <c:if> và <c:forEach> tương tự như trên) --%>
        <c:if test="${empty listFreeFire}">
            <p>Chưa có sản phẩm nào trong danh mục này.</p>
        </c:if>
        <c:forEach items="${listFreeFire}" var="tk">
            <div class="product-card">
                 <div class="product-image">
                    <img src="https://via.placeholder.com/250x150?text=Acc+Free+Fire" alt="Ảnh ${tk.maTaiKhoan}">
                </div>
                <div class="product-info">
                    <h4 class="product-title">${not empty tk.diemNoiBat ? tk.diemNoiBat : "Tài khoản Free Fire"}</h4>
                    <div class="product-price">
                        <span class="old-price">${tk.giaGoc} VNĐ</span>
                        <span class="new-price">${tk.giaBan} VNĐ</span>
                    </div>
                 </div>
                <a href="${pageContext.request.contextPath}/shop/game/detail?id=${tk.maTaiKhoan}&category=freefire" class="buy-button">
                    Xem Chi Tiết
                </a>
            </div>
        </c:forEach>
    </div>
</section>

<%-- 4. Khối Tài khoản Riot (Giữ nguyên) --%>
<section class="product-section">
    <h2>TÀI KHOẢN RIOT (LMHT & TFT) NỔI BẬT</h2>
    <div class="product-grid">
        <%-- (Copy code <c:if> và <c:forEach> tương tự như trên) --%>
        <c:if test="${empty listRiot}">
            <p>Chưa có sản phẩm nào trong danh portnày.</p>
        </c:if>
        <c:forEach items="${listRiot}" var="tk">
            <div class="product-card">
                 <div class="product-image">
                    <img src="https://via.placeholder.com/250x150?text=Acc+Riot" alt="Ảnh ${tk.maTaiKhoan}">
                </div>
                <div class="product-info">
                    <h4 class="product-title">${not empty tk.diemNoiBat ? tk.diemNoiBat : "Tài khoản Riot"}</h4>
                    <div class="product-price">
                        <span class="old-price">${tk.giaGoc} VNĐ</span>
                        <span class="new-price">${tk.giaBan} VNĐ</span>
                    </div>
                 </div>
                <a href="${pageContext.request.contextPath}/shop/game/detail?id=${tk.maTaiKhoan}&category=riot" class="buy-button">
                    Xem Chi Tiết
                </a>
            </div>
        </c:forEach>
    </div>
</section>

<%-- 5. Khối Game Steam (Giữ nguyên) --%>
<section class="product-section">
    <h2>GAME STEAM HOT</h2>
    <div class="product-grid">
        <%-- (Copy code <c:if> và <c:forEach> tương tự như trên) --%>
        <c:if test="${empty listSteam}">
            <p>Chưa có sản phẩm nào trong danh mục này.</p>
        </c:if>
        <c:forEach items="${listSteam}" var="gs">
            <div class="product-card">
                 <div class="product-image">
                    <img src="https://via.placeholder.com/250x150?text=${gs.tenGame}" alt="${gs.tenGame}">
                </div>
                <div class="product-info">
                    <h4 class="product-title">${gs.tenGame}</h4>
                     <div class="product-price">
                        <span class="old-price">${gs.giaGoc} VNĐ</span>
                        <span class="new-price">${gs.giaBan} VNĐ</span>
                    </div>
                </div>
                 <a href="${pageContext.request.contextPath}/shop/steam/detail?id=${gs.maGameSteam}" class="buy-button">
                    Xem Chi Tiết
                </a>
            </div>
        </c:forEach>
    </div>
</section>

<%-- Import Footer --%>
<jsp:include page="common/footer.jsp" />