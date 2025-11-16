<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="common/header.jsp">
    <jsp:param name="pageTitle" value="Chi tiết Game Steam"/>
</jsp:include>

<%-- (Phần <style> ... </style> giữ nguyên) --%>
<style>
    body {
        font-family: 'Be Vietnam Pro', Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #F7F7F7;
        color: #333;
        overflow-x: hidden;
    }
    .container {
        display: flex;
        flex-direction: row;
        gap: 25px;
        max-width: 1200px;
        margin: 120px auto 40px auto;
    }
    .left-panel {
        flex: 2;
        background-color: #ffffff;
        border-radius: 12px;
        padding: 25px;
        box-shadow: 0 0 8px rgba(255, 255, 255, 0.1);
    }
    .left-panel img {
        width: 100%;
        border-radius: 10px;
        margin-bottom: 20px;
    }
    .left-panel iframe {
        width: 100%;
        height: 360px;
        border-radius: 10px;
        border: none;
        margin-top: 20px;
    }
    .game-title {
        font-size: 26px;
        color: #000000;
        margin-bottom: 10px;
        font-weight: 700;
    }
    .price {
        font-size: 18px;
        margin: 10px 0;
    }
    .old-price {
        color: #888;
        text-decoration: line-through;
        margin-right: 8px;
    }
    .new-price {
        color: #E54646;
        font-weight: 600;
    }
    .desc {
        line-height: 1.6;
        color: #808080;
        margin-top: 15px;
    }
    .article {
        margin-top: 30px;
        padding: 20px;
        background: #ffffff;
        border-radius: 10px;
    }
    .article h3 {
        color: #000000;
        margin-bottom: 10px;
    }
    .article-content {
        font-size: 15px;  
        line-height: 1.7;
    }
    .actions {
        margin-top: 25px;
    }
    .buy-button {
        display: inline-block;
        background: #00bfff;
        color: white;
        padding: 12px 12px;
        border-radius: 10px;
        text-decoration: none;
        font-weight: 600;
        transition: 0.3s;
    }
    .buy-button:hover {
        background: #009fd6;
        transform: translateY(-2px);
    }
    .back-button {
        background: #444;
    }
    .back-button:hover {
        background: #555;
    }
    .right-panel {
        flex: 1;
        background-color: #ffffff;
        padding: 20px;
        border-radius: 12px;
        height: fit-content;
    }
    .right-panel h3 {
        color: #FF3333;
        border-bottom: 2px solid #00bfff;
        padding-bottom: 8px;
        margin-top: 0;
        margin-bottom: 15px;
    }
    .game-item {
        background: #f5f5f5;
        border-radius: 10px;
        padding: 10px;
        margin-bottom: 15px;
        display: flex;
        align-items: center;
        gap: 10px;
        transition: 0.3s;
    }
    .game-item:hover {
        background: #FCDAD5;
    }
    .game-item img {
        width: 60px;
        height: 60px;
        border-radius: 8px;
        object-fit: cover;
    }
    .game-info {
        flex: 1;
    }
    .game-info a {
        color: #000000;
        text-decoration: none;
        font-weight: 500;
    }
    .game-info a:hover {
        color: #00bfff;
    }
    .game-price {
        color: #E54646;
        font-size: 0.9rem;
        font-weight: 600;
    }
    .small-back {
        display: inline-block;
        background: #000000;
        color: white;
        padding: 6px 10px;
        border-radius: 6px;
        text-decoration: none;
        font-size: 0.85rem;
        transition: 0.3s;
    }
    .small-back:hover {
        background: #009fd6;
    }
</style>

<body class="steam-body">
    <div class="container">
        <div class="left-panel">
            <img src="${pageContext.request.contextPath}/${gameSteam.duongDanAnh}" alt="${gameSteam.tenGame}">
            <h2 class="game-title">${gameSteam.tenGame}</h2>

            <div class="price">
                <span class="old-price"><fmt:formatNumber value="${gameSteam.giaGoc}" type="number"/> VNĐ</span>
                <span class="new-price"><fmt:formatNumber value="${gameSteam.giaBan}" type="number"/> VNĐ</span>
            </div>

            <p class="desc">${gameSteam.moTaGame}</p>

            <c:if test="${not empty baiVietCauHinh}">
                <div class="article" style="background-color: #f5f5f5; margin-top: 30px;">
                    <h3>${baiVietCauHinh.tieuDeBaiViet}</h3>
                    <div class="article-content">
                        <c:out value="${baiVietCauHinh.noiDung}" escapeXml="false"/>
                    </div>
                </div>
            </c:if>

            <c:if test="${not empty gameSteam.idVideoTrailer}">
                <iframe src="https://www.youtube.com/embed/${gameSteam.idVideoTrailer}"
                        allowfullscreen style="margin-top: 20px;"></iframe>
                </c:if>

            <c:if test="${not empty baiVietGame}">
                <div class="article" style="background-color: #f5f5f5; margin-top: 30px;">
                    <h3>${baiVietGame.tieuDeBaiViet}</h3>
                    <div class="article-content">
                        <c:out value="${baiVietGame.noiDung}" escapeXml="false"/>
                    </div>
                </div>
            </c:if>


            <c:choose>
                <%-- SỬA: Kiểm tra cờ isSoldOut do Servlet gửi sang --%>
                <c:when test="${isSoldOut eq true}">
                    <div style="color: #ff4d4f; font-weight:600; margin-top:20px; padding: 10px; background: #fff1f0; border: 1px solid #ffccc7; border-radius: 8px;">
                        Game đã hết hàng — vui lòng quay lại sau.
                    </div>
                    <div class="actions" style="margin-top: 15px;">
                        <a href="${pageContext.request.contextPath}/shop/steam"
                           class="buy-button back-button">Quay lại</a>
                    </div>
                </c:when>

                <c:otherwise>
                    <div class="actions">
                        <a href="${pageContext.request.contextPath}/payment/execute?type=steam&id=${gameSteam.maGameSteam}"
                           class="buy-button">Mua ngay</a>
                        <a href="${pageContext.request.contextPath}/shop/steam"
                           class="buy-button back-button">Quay lại</a>
                    </div>
                </c:otherwise>
            </c:choose>


        </div>

        <div class="right-panel">
            <h3>Các game khác</h3>
            <c:set var="count" value="0"/>
            <c:forEach var="gs" items="${listSteamDetail}">
                <c:if test="${gs.maGameSteam != gameSteam.maGameSteam}">
                    <c:if test="${count < 15}">
                        <div class="game-item">
                            <img src="${pageContext.request.contextPath}/${gs.duongDanAnh}" alt="${gs.tenGame}">
                            <div class="game-info">
                                <a href="${pageContext.request.contextPath}/shop/steam/detail?id=${gs.maGameSteam}">${gs.tenGame}</a>
                                <div class="game-price"><fmt:formatNumber value="${gs.giaBan}" type="number"/> VNĐ</div>
                            </div>
                            <a href="${pageContext.request.contextPath}/shop/steam" class="small-back">Quay lại</a>
                        </div>
                        <c:set var="count" value="${count + 1}"/>
                    </c:if>
                </c:if>
            </c:forEach>
        </div>
    </div>
</body>
<jsp:include page="common/footer.jsp"/>