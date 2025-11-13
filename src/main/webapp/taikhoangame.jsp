<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="/common/header.jsp">
    <jsp:param name="pageTitle" value="Danh sách tài khoản"/>
</jsp:include>

<style>
    /* --- PHÂN TRANG --- */
    .pagination {
        display: flex;
        justify-content: center;
        align-items: center;
        gap: 8px;
        margin-top: 30px;
        flex-wrap: wrap;
    }

    .page-btn {
        background-color: #101316;
        color: white;
        border-radius: 50%;
        width: 40px;
        height: 40px;
        display: flex;
        justify-content: center;
        align-items: center;
        text-decoration: none;
        font-weight: bold;
        transition: all 0.2s ease;
    }

    .page-btn:hover {
        background-color: #f26b38;
        color: #fff;
    }

    .page-btn.active {
        border: 2px solid #f26b38;
        color: #fff;
        background-color: #101316;
    }

    .page-dots {
        color: #ccc;
        font-weight: bold;
        font-size: 18px;
        padding: 0 6px;
    }
    
    .product-card-link {
    text-decoration: none;
    color: inherit;
    display: block;
}
.product-card-link .buy-button {
    cursor: pointer;
}
</style>

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
                   
    <!-- PHÂN TRANG -->
    <c:if test="${totalPages > 1}">
        <div class="pagination">
            <!-- Nút trước -->
            <c:if test="${currentPage > 1}">
                <a href="${pageContext.request.contextPath}/shop/game?category=${category}&page=${currentPage - 1}" class="page-btn">&lt;</a>
            </c:if>

            <!-- Các trang -->
            <c:forEach begin="1" end="${totalPages}" var="i">
                <c:choose>
                    <c:when test="${i == currentPage}">
                        <span class="page-btn active">${i}</span>
                    </c:when>
                    <c:when test="${i == 1 || i == totalPages || (i >= currentPage - 1 && i <= currentPage + 1)}">
                        <a href="${pageContext.request.contextPath}/shop/game?category=${category}&page=${i}" class="page-btn">${i}</a>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${i == currentPage - 2 || i == currentPage + 2}">
                            <span class="page-dots">...</span>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <!-- Nút sau -->
            <c:if test="${currentPage < totalPages}">
                <a href="${pageContext.request.contextPath}/shop/game?category=${category}&page=${currentPage + 1}" class="page-btn">&gt;</a>
            </c:if>
        </div>
    </c:if>
</section>

<jsp:include page="/common/footer.jsp"/>

