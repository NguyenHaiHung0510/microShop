<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="common/header.jsp">
    <jsp:param name="pageTitle" value="Danh sách Game Steam"/>
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
</style>

<section class="product-section">
    <h2>Danh sách Game Steam</h2>

    <div class="product-grid">
        <c:if test="${empty listSteam}">
            <p>Chưa có game nào trong danh sách.</p>
        </c:if>

        <!-- Vòng lặp hiển thị 4 game của trang hiện tại -->
        <c:forEach var="gs" items="${listSteam}">
            <div class="product-card">
                <div class="product-image">
                    <!-- Click vào ảnh -> sang trang chi tiết -->
                    <a href="${pageContext.request.contextPath}/shop/steam/detail?id=${gs.maGameSteam}">
                        <img src="${pageContext.request.contextPath}/${gs.duongDanAnh}" alt="${gs.tenGame}">
                    </a>
                </div>

                <div class="product-info">
                    <!-- Click vào tên -> sang trang chi tiết -->
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

    <!-- PHÂN TRANG -->
    <c:if test="${totalPages > 1}">
        <div class="pagination">
            <!-- Nút trước -->
            <c:if test="${currentPage > 1}">
                <a href="${pageContext.request.contextPath}/shop/steam?page=${currentPage - 1}" class="page-btn">&lt;</a>
            </c:if>

            <!-- Các trang -->
            <c:forEach begin="1" end="${totalPages}" var="i">
                <c:choose>
                    <c:when test="${i == currentPage}">
                        <span class="page-btn active">${i}</span>
                    </c:when>
                    <c:when test="${i == 1 || i == totalPages || (i >= currentPage - 2 && i <= currentPage + 2)}">
                        <a href="${pageContext.request.contextPath}/shop/steam?page=${i}" class="page-btn">${i}</a>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${i == currentPage - 3 || i == currentPage + 3}">
                            <span class="page-dots">...</span>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <!-- Nút sau -->
            <c:if test="${currentPage < totalPages}">
                <a href="${pageContext.request.contextPath}/shop/steam?page=${currentPage + 1}" class="page-btn">&gt;</a>
            </c:if>
        </div>
    </c:if>
</section>

<jsp:include page="common/footer.jsp" />
