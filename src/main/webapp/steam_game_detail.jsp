<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="common/header.jsp">
    <jsp:param name="pageTitle" value="Chi tiết Game Steam"/>
</jsp:include>

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
        margin: 120px auto 40px auto; /* <-- thêm 100px trên cùng */
    }

    /* LEFT SIDE */
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
        background: #99CCFF;
        border-radius: 10px;
    }

    .article h3 {
        color: #000000;
        margin-bottom: 10px;
    }

    /* BUY BUTTON */
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

    /* RIGHT SIDE */
    .right-panel {
        flex: 1;
        background-color: #ffffff;
        padding: 20px;
        border-radius: 12px;
        height: fit-content;
    }

    .right-panel h3 {
        color: #00bfff;
        border-bottom: 2px solid #00bfff;
        padding-bottom: 8px;
        margin-top: 0;
        margin-bottom: 15px;
    }

    .game-item {
        background: #EEEEEE;
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
        background: #00bfff;
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
    <!-- LEFT -->
    <div class="left-panel">
        <img src="${pageContext.request.contextPath}/${gameSteam.duongDanAnh}" alt="${gameSteam.tenGame}">
        <h2 class="game-title">${gameSteam.tenGame}</h2>

        <div class="price">
            <span class="old-price">${gameSteam.giaGoc} VNĐ</span>
            <span class="new-price">${gameSteam.giaBan} VNĐ</span>
        </div>

        <p class="desc">${gameSteam.moTaGame}</p>

        <!-- Trailer -->
        <c:if test="${not empty gameSteam.idVideoTrailer}">
            <iframe src="https://www.youtube.com/embed/${gameSteam.idVideoTrailer}"
                    allowfullscreen></iframe>
        </c:if>

        <!-- Bài viết giới thiệu -->
        <c:if test="${not empty baiViet}">
            <div class="article">
                <h3>${baiViet.tieuDeBaiViet}</h3>
                <div>${baiViet.noiDung}</div>
            </div>
        </c:if>

        <!-- Nút mua -->
        <div class="actions">
            <a href="${pageContext.request.contextPath}/payment/execute?type=steam&id=${gameSteam.maGameSteam}" class="buy-button">Mua ngay</a>
            <a href="${pageContext.request.contextPath}/shop/steam" class="buy-button back-button">Quay lại</a>
        </div>
    </div>

    <!-- RIGHT -->
    <div class="right-panel">
        <h3>Các game khác</h3>
        <c:forEach var="gs" items="${listSteamDetail}">
            <div class="game-item">
                <img src="${pageContext.request.contextPath}/${gs.duongDanAnh}" alt="${gs.tenGame}">
                <div class="game-info">
                    <a href="${pageContext.request.contextPath}/shop/steam/detail?id=${gs.maGameSteam}">${gs.tenGame}</a>
                    <div class="game-price">${gs.giaBan} VNĐ</div>
                </div>
                <a href="${pageContext.request.contextPath}/shop/steam" class="small-back">Quay lại</a>
            </div>
        </c:forEach>
    </div>
</div>
</body>
<jsp:include page="common/footer.jsp"/>
