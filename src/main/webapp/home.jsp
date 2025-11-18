<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="common/header.jsp">
    <jsp:param name="pageTitle" value="Trang Chủ"/>
</jsp:include>

<section class="services-banner">
    <h2>TÀI KHOẢN GAME & DỊCH VỤ</h2>

    <div class="service-carousel-wrapper">
        <div class="services-grid">
            <a href="${pageContext.request.contextPath}/shop/game?category=lienquan&page=1" class="service-card lienquan">
                <h3>Tài Khoản Liên Quân</h3>
            </a>
            <a href="${pageContext.request.contextPath}/shop/game?category=freefire&page=1" class="service-card freefire">
                <h3>Tài Khoản FreeFire</h3>
            </a>
            <a href="${pageContext.request.contextPath}/shop/game?category=riot&page=1" class="service-card riot">
                <h3>Tài Khoản LMHT & TFT</h3>
            </a>

            <a href="${pageContext.request.contextPath}/shop/steam?page=1" class="service-card steam">
            </a>
    

            <a href="#" class="service-card youtube">
                <span>Dịch vụ sắp tới</span>
            </a>
            <a href="#" class="service-card netflix">
                <span>Dịch vụ sắp tới</span>
            </a>

            <a href="${pageContext.request.contextPath}/shop/game?category=lienquan&page=1" class="service-card lienquan">
                <h3>Tài Khoản Liên Quân</h3>
            </a>
            <a href="${pageContext.request.contextPath}/shop/game?category=freefire&page=1" class="service-card freefire">
                <h3>Tài Khoản FreeFire</h3>
            </a>
            <a href="${pageContext.request.contextPath}/shop/game?category=riot&page=1" class="service-card riot">
                <h3>Tài Khoản LMHT & TFT</h3>
            </a>

            <a href="${pageContext.request.contextPath}/shop/steam?page=1" class="service-card steam">
            </a>

            <a href="#" class="service-card youtube">
                <%-- Đã xóa H3 --%>
                <span>Dịch vụ sắp tới</span>
            </a>
            <a href="#" class="service-card netflix">
                <span>Dịch vụ sắp tới</span>
            </a>
        </div>
    </div>
</section>

<section class="product-section">
    <h2>Tài Khoản Liên Quân HOT</h2>
    <div class="product-grid">
        <c:if test="${empty listLienQuan}">
            <p>Chưa có sản phẩm nào trong danh mục này.</p>
        </c:if>
        <c:forEach items="${listLienQuan}" var="tk">
            <a href="${pageContext.request.contextPath}/shop/game/detail?id=${tk.maTaiKhoan}&category=lienquan" class="product-card-link">
                <div class="product-card">
                    <div class="product-image">
                        <img src="${pageContext.request.contextPath}/${tk.duongDanAnh}" alt="${tk.maTaiKhoan}">
                    </div>
                    <div class="product-info">
                        <h4 class="product-title">${not empty tk.diemNoiBat ? tk.diemNoiBat : "Tài khoản Liên Quân"}</h4>
                        <div class="product-price">
                            <span class="old-price">${tk.giaGoc} VNĐ</span>
                            <span class="new-price">${tk.giaBan} VNĐ</span>
                        </div>
                    </div>
                    <div class="buy-button">Xem Chi Tiết</div>
                </div>
            </a>
        </c:forEach>
    </div>
</section>

<section class="product-section">
    <h2>Tài Khoản FreeFire HOT</h2>
    <div class="product-grid">
        <c:if test="${empty listFreeFire}">
            <p>Chưa có sản phẩm nào trong danh mục này.</p>
        </c:if>
        <c:forEach items="${listFreeFire}" var="tk">
            <a href="${pageContext.request.contextPath}/shop/game/detail?id=${tk.maTaiKhoan}&category=freefire" class="product-card-link">
                <div class="product-card">
                    <div class="product-image">
                        <img src="${pageContext.request.contextPath}/${tk.duongDanAnh}" alt="${tk.maTaiKhoan}">
                    </div>
                    <div class="product-info">
                        <h4 class="product-title">${not empty tk.diemNoiBat ? tk.diemNoiBat : "Tài khoản Free Fire"}</h4>
                        <div class="product-price">
                            <span class="old-price">${tk.giaGoc} VNĐ</span>
                            <span class="new-price">${tk.giaBan} VNĐ</span>
                        </div>
                    </div>
                    <div class="buy-button">Xem Chi Tiết</div>
                </div>
            </a>
        </c:forEach>
    </div>
</section>

<section class="product-section">
    <h2>Tài Khoản LMHT & TFT HOT</h2>
    <div class="product-grid">
        <c:if test="${empty listRiot}">
            <p>Chưa có sản phẩm nào trong danh mục này.</p>
        </c:if>
        <c:forEach items="${listRiot}" var="tk">
            <a href="${pageContext.request.contextPath}/shop/game/detail?id=${tk.maTaiKhoan}&category=riot" class="product-card-link">
                <div class="product-card">
                    <div class="product-image">
                        <img src="${pageContext.request.contextPath}/${tk.duongDanAnh}" alt="${tk.maTaiKhoan}">
                    </div>
                    <div class="product-info">
                        <h4 class="product-title">${not empty tk.diemNoiBat ? tk.diemNoiBat : "Tài khoản Riot"}</h4>
                        <div class="product-price">
                            <span class="old-price">${tk.giaGoc} VNĐ</span>
                            <span class="new-price">${tk.giaBan} VNĐ</span>
                        </div>
                    </div>
                    <div class="buy-button">Xem Chi Tiết</div>
                </div>
            </a>
        </c:forEach>
    </div>
</section>

<section class="product-section">
    <h2>Game Steam HOT</h2>
    <div class="product-grid">
        <c:if test="${empty listSteam}">
            <p>Chưa có sản phẩm nào trong danh mục này.</p>
        </c:if>
        <c:forEach items="${listSteam}" var="gs">
            <a href="${pageContext.request.contextPath}/shop/steam/detail?id=${gs.maGameSteam}" class="product-card-link">
                <div class="product-card">
                    <div class="product-image">
                        <img src="${pageContext.request.contextPath}/${gs.duongDanAnh}" alt="${gs.tenGame}">
                    </div>
                    <div class="product-info">
                        <h4 class="product-title">${gs.tenGame}</h4>
                        <div class="product-price">
                            <span class="old-price">${gs.giaGoc} VNĐ</span>
                            <span class="new-price">${gs.giaBan} VNĐ</span>
                        </div>
                    </div>
                    <div class="buy-button">Xem Chi Tiết</div>
                </div>
            </a>
        </c:forEach>
    </div>
</section>

<jsp:include page="common/footer.jsp" />