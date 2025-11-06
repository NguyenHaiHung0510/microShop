<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Shop Game - Danh sách tài khoản</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; background-color: #fafafa; }
        h2 { font-size: 26px; margin-bottom: 20px; }
        .grid { display: flex; flex-wrap: wrap; gap: 20px; }
        .card {
            width: 220px;
            background: white;
            border-radius: 12px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            overflow: hidden;
            transition: 0.25s;
        }
        .card:hover { transform: translateY(-3px); }
        .card img { width: 100%; height: 180px; object-fit: cover; }
        .info { padding: 10px; }
        .price { color: #c62828; font-weight: bold; }
        .detail-btn {
            display: inline-block;
            background-color: #007bff;
            color: white;
            padding: 6px 12px;
            border-radius: 6px;
            text-decoration: none;
        }
        .detail-btn:hover { background-color: #0056b3; }
    </style>
</head>
<body>

<h2>Danh sách tài khoản ${category}</h2>

<c:if test="${empty dsTaiKhoan}">
    <p>Không có tài khoản nào đang bán.</p>
</c:if>

<div class="grid">
    <c:forEach var="acc" items="${dsTaiKhoan}">
        <div class="card">
            <img src="<c:out value='${acc.duongDanAnh != null ? acc.duongDanAnh : "images/noimage.png"}'/>" alt="Ảnh tài khoản"/>
            <div class="info">
                <p><b>ID:</b> ${acc.maTaiKhoan}</p>
                <p class="price">Giá: ${acc.giaBan} VNĐ</p>
                <a class="detail-btn"
                   href="${pageContext.request.contextPath}/shop/game/detail?id=${acc.maTaiKhoan}&category=${category}">
                    Xem chi tiết
                </a>
            </div>
        </div>
    </c:forEach>
</div>

</body>
</html>