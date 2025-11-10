<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="common/header.jsp">
    <jsp:param name="pageTitle" value="Danh sách Game Steam"/>
</jsp:include>

<section class="product-section">
    <h2>Danh sách Game Steam</h2>

    <div class="product-grid">
        <c:if test="${empty listSteam}">
            <p>Chưa có game nào trong danh sách.</p>
        </c:if>

        <c:forEach var="gs" items="${listSteam}">
            <div class="product-card">
                <div class="product-image">
                    <!-- Khi click vào ảnh sẽ dẫn tới trang chi tiết -->
                    <a href="${pageContext.request.contextPath}/shop/steam/detail?id=${gs.maGameSteam}">
                        <img src="${pageContext.request.contextPath}/${gs.duongDanAnh}" alt="${gs.tenGame}">
                    </a>
                </div>
                <div class="product-info">
                    <!-- Khi click vào tên cũng dẫn tới trang chi tiết -->
                    <h4 class="product-title">
                        <a href="${pageContext.request.contextPath}/shop/steam/detail?id=${gs.maGameSteam}">
                            ${gs.tenGame}
                        </a>
                    </h4>
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

<jsp:include page="common/footer.jsp" />
