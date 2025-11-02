<%-- Import Header (v?i pageTitle là "Trang Ch?") --%>
<jsp:include page="common/header.jsp">
    <jsp:param name="pageTitle" value="Trang Ch?"/>
</jsp:include>

<%-- 1. Ph?n Banner "Game & D?ch v?" (Gi?ng Tedi Shop) --%>
<section class="services-banner">
    <h2>GAME & D?CH V?</h2>
    <div class="services-grid">
        <a href="${pageContext.request.contextPath}/shop/game?category=lienquan" class="service-card lienquan">
            <h3>TÀI KHO?N LIÊN QUÂN</h3>
        </a>
        <a href="${pageContext.request.contextPath}/shop/game?category=freefire" class="service-card freefire">
            <h3>TÀI KHO?N FREE FIRE</h3>
        </a>
        <a href="${pageContext.request.contextPath}/shop/game?category=riot" class="service-card riot">
            <h3>TÀI KHO?N RIOT</h3>
        </a>
        <a href="${pageContext.request.contextPath}/shop/steam" class="service-card steam">
            <h3>GAME STEAM OFFLINE</h3>
        </a>
    </div>
</section>

<%-- 2. Kh?i Tài kho?n Liên Quân --%>
<section class="product-section">
    <h2>TÀI KHO?N LIÊN QUÂN N?I B?T</h2>
    <div class="product-grid">
        <c:forEach items="${listLienQuan}" var="tk">
            <div class="product-card">
                <div class="product-image">
                    <%-- T?m th?i ch?a có ?nh, s? ???c thêm ? Module 2 --%>
                    <img src="https://via.placeholder.com/250x150?text=Acc+Lien+Quan" alt="?nh ${tk.maTaiKhoan}">
                </div>
                <div class="product-info">
                    <%-- Dùng DiemNoiBat làm tiêu ?? --%>
                    <h4 class="product-title">${tk.diemNoiBat}</h4>
                    <div class_ ="product-price">
                        <span class="old-price">${tk.giaGoc} VN?</span>
                        <span class="new-price">${tk.giaBan} VN?</span>
                    </div>
                </div>
                <a href="${pageContext.request.contextPath}/shop/game/detail?id=${tk.maTaiKhoan}&category=lienquan" class="buy-button">
                    Xem Chi Ti?t
                </a>
            </div>
        </c:forEach>
    </div>
</section>

<%-- 3. Kh?i Tài kho?n Free Fire --%>
<section class="product-section">
    <h2>TÀI KHO?N FREE FIRE N?I B?T</h2>
    <div class="product-grid">
        <c:forEach items="${listFreeFire}" var="tk">
            <div class="product-card">
                <div class="product-image">
                    <img src="https://via.placeholder.com/250x150?text=Acc+Free+Fire" alt="?nh ${tk.maTaiKhoan}">
                </div>
                <div class="product-info">
                    <h4 class="product-title">${tk.diemNoiBat}</h4>
                    <div class_ ="product-price">
                        <span class="old-price">${tk.giaGoc} VN?</span>
                        <span class="new-price">${tk.giaBan} VN?</span>
                    </div>
                </div>
                <a href="${pageContext.request.contextPath}/shop/game/detail?id=${tk.maTaiKhoan}&category=freefire" class="buy-button">
                    Xem Chi Ti?t
                </a>
            </div>
        </c:forEach>
    </div>
</section>

<%-- 4. Kh?i Tài kho?n Riot --%>
<section class="product-section">
    <h2>TÀI KHO?N RIOT (LMHT & TFT) N?I B?T</h2>
    <div class="product-grid">
        <c:forEach items="${listRiot}" var="tk">
            <%-- (Code card t??ng t? nh? trên) --%>
            <div class="product-card">
                <div class="product-image">
                    <img src="https://via.placeholder.com/250x150?text=Acc+Riot" alt="?nh ${tk.maTaiKhoan}">
                </div>
                <div class="product-info">
                    <h4 class="product-title">${tk.diemNoiBat}</h4>
                    <div class_ ="product-price">
                        <span class="old-price">${tk.giaGoc} VN?</span>
                        <span class="new-price">${tk.giaBan} VN?</span>
                    </div>
                </div>
                <a href="${pageContext.request.contextPath}/shop/game/detail?id=${tk.maTaiKhoan}&category=riot" class="buy-button">
                    Xem Chi Ti?t
                </a>
            </div>
        </c:forEach>
    </div>
</section>

<%-- 5. Kh?i Game Steam --%>
<section class="product-section">
    <h2>GAME STEAM HOT</h2>
    <div class="product-grid">
        <c:forEach items="${listSteam}" var="gs">
            <div class="product-card">
                <div class="product-image">
                    <%-- Module 3 s? x? lý upload ?nh này --%>
                    <img src="https://via.placeholder.com/250x150?text=${gs.tenGame}" alt="${gs.tenGame}">
                </div>
                <div class="product-info">
                    <h4 class="product-title">${gs.tenGame}</h4>
                    <div class_ ="product-price">
                        <span class="old-price">${gs.giaGoc} VN?</span>
                        <span class="new-price">${gs.giaBan} VN?</span>
                    </div>
                </div>
                <a href="${pageContext.request.contextPath}/shop/steam/detail?id=${gs.maGameSteam}" class="buy-button">
                    Xem Chi Ti?t
                </a>
            </div>
        </c:forEach>
    </div>
</section>


<%-- Import Footer --%>
<jsp:include page="common/footer.jsp" />