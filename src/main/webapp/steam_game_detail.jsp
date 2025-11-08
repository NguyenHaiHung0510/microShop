<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="common/header.jsp">
    <jsp:param name="pageTitle" value="Chi tiết game Steam"/>
</jsp:include>

<section class="product-section">
    <h2>Chi tiết game Steam</h2>

    <div class="product-image">
        <img src="${pageContext.request.contextPath}/${gameSteam.duongDanAnh != null ? gameSteam.duongDanAnh : 'images/noimage.png'}"
             alt="Ảnh game ${gameSteam.tenGame}">
    </div>

    <div class="product-info">
        <h3 class="product-title">${gameSteam.tenGame}</h3>

        <div class="product-price">
            <span class="old-price">${gameSteam.giaGoc} VNĐ</span>
            <span class="new-price">${gameSteam.giaBan} VNĐ</span>
        </div>

        <div class="highlight">
            <p><b>Mã game:</b> ${gameSteam.maGameSteam}</p>
            <p><b>Lượt xem:</b> ${gameSteam.luotXem}</p>
            <p><b>Thời gian đăng:</b> <fmt:formatDate value="${gameSteam.thoiGianDang}" pattern="dd/MM/yyyy HH:mm"/></p>
            <p><b>Mô tả:</b> ${gameSteam.moTaGame}</p>

            <c:if test="${not empty baiViet}">
                <hr>
                <p><b>Bài viết giới thiệu:</b> ${baiViet.noiDung}</p>
            </c:if>
        </div>
    </div>

    <div class="actions">
        <a href="${pageContext.request.contextPath}/payment/execute?type=steam&id=${gameSteam.maGameSteam}"
           class="buy-button">Mua ngay</a>

        <a href="${pageContext.request.contextPath}/shop/steam"
           class="buy-button secondary">Quay lại</a>
    </div>
</section>

<jsp:include page="common/footer.jsp"/>
