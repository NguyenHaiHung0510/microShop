<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="/common/header.jsp">
    <jsp:param name="pageTitle" value="Danh sách tài khoản"/>
</jsp:include>

<section class="product-section">
    <c:choose>
        <c:when test="${category == 'lienquan'}">
            <h2>Tài Khoản Liên Quân</h2>
        </c:when>
        <c:when test="${category == 'freefire'}">
            <h2>Tài Khoản Free Fire</h2>
        </c:when>
        <c:when test="${category == 'riot'}">
            <h2>Tài Khoản LMHT & TFT</h2>
        </c:when>
        <c:otherwise>
            <h2>Danh Sách Tài Khoản Game</h2>
        </c:otherwise>
    </c:choose>

    <div class="product-grid">
        <c:if test="${empty dsTaiKhoan}">
            <p>Hiện chưa có tài khoản nào trong danh mục này.</p>
        </c:if>

        <c:forEach var="acc" items="${dsTaiKhoan}">
            <a href="${pageContext.request.contextPath}/shop/game/detail?id=${acc.maTaiKhoan}&category=${category}" class="product-card-link">
                <div class="product-card">
                    <div class="product-image">
                        <img src="${pageContext.request.contextPath}/${acc.duongDanAnh != null ? acc.duongDanAnh : 'images/noimage.png'}"
                             alt="Ảnh tài khoản ${acc.maTaiKhoan}">
                    </div>
                    <div class="product-info">
                        <h4 class="product-title">
                            ${not empty acc.diemNoiBat ? acc.diemNoiBat : "Tài khoản"}
                        </h4>
                        <div class="product-price">
                            <span class="old-price">${acc.giaGoc} VNĐ</span>
                            <span class="new-price">${acc.giaBan} VNĐ</span>
                        </div>
                    </div>
                    <div class="buy-button">Xem Chi Tiết</div>
                </div>
            </a>
        </c:forEach>
    </div>
</section>

<jsp:include page="/common/footer.jsp"/>

<!-- ===== CSS bổ sung ===== -->
<style>
.product-card-link {
    text-decoration: none;
    color: inherit;
    display: block;
}
.product-card-link .buy-button {
    cursor: pointer;
}
</style>
