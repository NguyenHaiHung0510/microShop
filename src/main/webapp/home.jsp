<%-- Import Header (với pageTitle là "Trang Chủ") --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="common/header.jsp">
    <jsp:param name="pageTitle" value="Trang Chủ"/>
</jsp:include>

<%-- 1. Phần Banner "Game & Dịch vụ" (Giống Tedi Shop) --%>
<section class="services-banner">
    <h2>GAME & DỊCH VỤ</h2>
    <div class="services-grid">
        <a href="${pageContext.request.contextPath}/shop/game?category=lienquan" class="service-card lienquan">
            <h3>TÀI KHOẢN LIÊN QUÂN</h3>
        </a>
        <a href="${pageContext.request.contextPath}/shop/game?category=freefire" class="service-card freefire">
            <h3>TÀI KHOẢN FREE FIRE</h3>
        </a>
        <a href="${pageContext.request.contextPath}/shop/game?category=riot" class="service-card riot">
            <h3>TÀI KHOẢN RIOT</h3>
        </a>
        <a href="${pageContext.request.contextPath}/shop/steam" class="service-card steam">
            <h3>GAME STEAM OFFLINE</h3>
        </a>
    </div>
</section>

<%-- 2. Khối Tài khoản Liên Quân --%>
<section class="product-section">
    <h2>TÀI KHOẢN LIÊN QUÂN NỔI BẬT</h2>
    <div class="product-grid">
        <c:forEach items="${listLienQuan}" var="tk">
            <div class="product-card">
                <div class="product-image">
                    <%-- Tạm thời chưa có ảnh, sẽ được thêm ở Module 2 --%>
                    <img src="https://via.placeholder.com/250x150?text=Acc+Lien+Quan" alt="Ảnh ${tk.maTaiKhoan}">
                </div>
                <div class="product-info">
                    <%-- Dùng DiemNoiBat làm tiêu đề --%>
                    <h4 class="product-title">${tk.diemNoiBat}</h4>
                    <div class_ ="product-price">
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

<%-- 3. Khối Tài khoản Free Fire --%>
<section class="product-section">
    <h2>TÀI KHOẢN FREE FIRE NỔI BẬT</h2>
    <div class="product-grid">
        <c:forEach items="${listFreeFire}" var="tk">
            <div class="product-card">
                <div class="product-image">
                    <img src="https://via.placeholder.com/250x150?text=Acc+Free+Fire" alt="Ảnh ${tk.maTaiKhoan}">
                </div>
                <div class="product-info">
                    <h4 class="product-title">${tk.diemNoiBat}</h4>
                    <div class_ ="product-price">
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

<%-- 4. Khối Tài khoản Riot --%>
<section class="product-section">
    <h2>TÀI KHOẢN RIOT (LMHT & TFT) NỔI BẬT</h2>
    <div class="product-grid">
        <c:forEach items="${listRiot}" var="tk">
            <%-- (Code card tương tự như trên) --%>
            <div class="product-card">
                <div class="product-image">
                    <img src="https://via.placeholder.com/250x150?text=Acc+Riot" alt="Ảnh ${tk.maTaiKhoan}">
                </div>
                <div class="product-info">
                    <h4 class="product-title">${tk.diemNoiBat}</h4>
                    <div class_ ="product-price">
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

<%-- 5. Khối Game Steam --%>
<section class="product-section">
    <h2>GAME STEAM HOT</h2>
    <div class="product-grid">
        <c:forEach items="${listSteam}" var="gs">
            <div class="product-card">
                <div class="product-image">
                    <%-- Module 3 sẽ xử lý upload ảnh này --%>
                    <img src="https://via.placeholder.com/250x150?text=${gs.tenGame}" alt="${gs.tenGame}">
                </div>
                <div class="product-info">
                    <h4 class="product-title">${gs.tenGame}</h4>
                    <div class_ ="product-price">
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