<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="common/header.jsp">
    <jsp:param name="pageTitle" value="Chi tiết game Steam"/>
</jsp:include>

<style>
    body {
        background-color: #f5f6fa;
        font-family: 'Segoe UI', Arial, sans-serif;
    }

    .product-section {
        max-width: 1000px;
        margin: 50px auto;
        padding: 20px;
        background: #fff;
        border-radius: 16px;
        box-shadow: 0 4px 12px rgba(0,0,0,0.08);
    }

    .product-section h2 {
        text-align: center;
        color: #333;
        font-size: 28px;
        font-weight: 700;
        margin-bottom: 30px;
    }

    .product-grid {
        display: flex;
        justify-content: center;
    }

    .product-card {
        width: 100%;
    }

    .product-image {
        display: flex;
        flex-wrap: wrap;
        gap: 15px;
        justify-content: center;
        padding: 10px 0;
    }

    .product-image div {
        width: 300px;
        height: 300px;
        border-radius: 12px;
        overflow: hidden;
        box-shadow: 0 2px 6px rgba(0,0,0,0.1);
        transition: transform 0.25s ease;
    }

    .product-image div:hover {
        transform: scale(1.05);
    }

    .product-image img {
        width: 100%;
        height: 100%;
        object-fit: cover;
        display: block;
    }

    .product-info {
        margin-top: 25px;
        text-align: left;
    }

    .product-title {
        font-size: 22px;
        color: #2c3e50;
        text-align: center;
        font-weight: bold;
        margin-bottom: 15px;
    }

    .product-price {
        text-align: center;
        margin-bottom: 20px;
    }

    .product-price .old-price {
        color: #888;
        text-decoration: line-through;
        margin-right: 10px;
        font-size: 16px;
    }

    .product-price .new-price {
        color: #e74c3c;
        font-weight: bold;
        font-size: 20px;
    }

    .highlight {
        background: #f9fafc;
        padding: 20px;
        border-radius: 12px;
        line-height: 1.7;
        color: #333;
        font-size: 15px;
        border: 1px solid #eee;
    }

    .highlight p {
        margin: 6px 0;
    }

    .highlight ul {
        margin: 8px 0 0 25px;
        list-style: disc;
    }

    .actions {
        margin-top: 30px;
        display: flex;
        justify-content: center;
        gap: 20px;
    }

    .buy-button {
        display: inline-block;
        padding: 12px 28px;
        border-radius: 10px;
        font-size: 16px;
        font-weight: 600;
        text-decoration: none;
        color: white;
        background: #007bff;
        transition: 0.3s;
    }

    .buy-button:hover {
        background: #0056b3;
        transform: translateY(-2px);
    }

    .buy-button.secondary {
        background: #ccc;
        color: #333;
    }

    .buy-button.secondary:hover {
        background: #bbb;
    }
</style>

<section class="product-section">
    <h2>Chi tiết game Steam</h2>

    <div class="product-grid">
        <div class="product-card">

            <!-- Thư viện ảnh -->
            <div class="product-image">
                <div>
                    <img src="${pageContext.request.contextPath}/${gameSteam.duongDanAnh != null ? gameSteam.duongDanAnh : 'images/noimage.png'}"
                         alt="Ảnh game ${gameSteam.tenGame}">
                </div>
            </div>

            <!-- Thông tin -->
            <div class="product-info">
                <h3 class="product-title">${gameSteam.tenGame}</h3>

                <div class="product-price">
                    <span class="old-price">${gameSteam.giaGoc} VNĐ</span>
                    <span class="new-price">${gameSteam.giaBan} VNĐ</span>
                </div>

                <div class="highlight">
                    <p><b>Mã game:</b> ${gameSteam.maGameSteam}</p>
                    <p><b>Lượt xem:</b> ${gameSteam.luotXem}</p>
                    <p><b>Thời gian đăng:</b> 
                        <c:set var="thoiGianDang" value="${gameSteam.thoiGianDang}" />
                        <%
                            java.time.LocalDateTime ldt = (java.time.LocalDateTime) pageContext.getAttribute("thoiGianDang");
                            java.time.format.DateTimeFormatter dtf = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                            String formattedDate = ldt != null ? ldt.format(dtf) : "";
                        %>
                        <%= formattedDate %>
                    </p>
                    <p><b>Mô tả:</b> ${gameSteam.moTaGame}</p>

                    <c:if test="${not empty baiViet}">
                        <hr style="margin:12px 0; border:none; border-top:1px solid #ddd;">
                        <p><b>Bài viết giới thiệu:</b> ${baiViet.noiDung}</p>
                    </c:if>
                </div>
            </div>

            <!-- Nút hành động -->
            <div class="actions">
                <a href="${pageContext.request.contextPath}/payment/execute?type=steam&id=${gameSteam.maGameSteam}"
                   class="buy-button">Mua ngay</a>

                <a href="${pageContext.request.contextPath}/shop/steam"
                   class="buy-button secondary">Quay lại</a>
            </div>

        </div>
    </div>
</section>

<jsp:include page="common/footer.jsp"/>
