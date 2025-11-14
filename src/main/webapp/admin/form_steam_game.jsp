<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/common/header.jsp">
    <jsp:param name="pageTitle" value="${empty game.maGameSteam ? 'Thêm Game Steam' : 'Sửa Game Steam'}"/>
</jsp:include>

<%-- CSS Admin đã được thêm vào style.css --%>

<div class="admin-container">
    <nav class="admin-nav">
        <a href="${pageContext.request.contextPath}/admin/dashboard">Bảng Điều Khiển</a>
        <a href="${pageContext.request.contextPath}/admin/products?type=game">Quản lý Tài Khoản Game</a>
        <a href="${pageContext.request.contextPath}/admin/products/steam" class="active">Quản lý Game Steam</a>
        <a href="${pageContext.request.contextPath}/admin/orders">Quản lý Đơn Hàng</a>
        <a href="${pageContext.request.contextPath}/admin/users">Quản lý Người Dùng</a>
        <a href="${pageContext.request.contextPath}/admin/import">Nhập Hàng</a>
    </nav>
</div>

<div class="admin-form-container">
    <form action="${pageContext.request.contextPath}/admin/steam/save" method="POST">
        <h2>
            <c:if test="${empty game.maGameSteam}">Thêm Game Steam Mới</c:if>
            <c:if test="${not empty game.maGameSteam}">Cập nhật Game: ${game.tenGame}</c:if>
            </h2>

        <c:if test="${not empty game.maGameSteam}">
            <input type="hidden" name="maGameSteam" value="${game.maGameSteam}" />
        </c:if>

        <div class="form-grid">

            <div class="form-group full-width">
                <label for="tenGame">Tên Game</label>
                <input type="text" id="tenGame" name="tenGame" value="${game.tenGame}" required>
            </div>

            <div class="form-group">
                <label for="giaGoc">Giá Gốc (VNĐ)</label>
                <input type="number" id="giaGoc" name="giaGoc" value="${game.giaGoc}" required>
            </div>

            <div class="form-group">
                <label for="giaBan">Giá Bán Slot (VNĐ)</label>
                <input type="number" id="giaBan" name="giaBan" value="${game.giaBan}" required>
            </div>

            <div class="form-group">
                <label for="duongDanAnh">Đường dẫn ảnh</label>
                <input type="text" id="duongDanAnh" name="duongDanAnh" value="${game.duongDanAnh}">
            </div>

            <div class="form-group">
                <label for="idVideoTrailer">ID Video Trailer YouTube</label>
                <input type="text" id="idVideoTrailer" name="idVideoTrailer" value="${game.idVideoTrailer}">
            </div>

            <div class="form-group full-width">
                <label for="moTaGame">Mô Tả Ngắn</label>
                <textarea id="moTaGame" name="moTaGame">${game.moTaGame}</textarea>
            </div>

            <fieldset class="form-group full-width" style="border: 1px solid #ddd; padding: 15px; border-radius: 8px;">
                <legend style="font-weight: 500;">Bài Viết 1 (Giới thiệu)</legend>
                <input type="hidden" name="maBaiViet1" value="${baiViet1.maBaiViet}">
                <div class="form-group">
                    <label for="tieuDe1">Tiêu Đề Bài Viết 1</label>
                    <input type="text" id="tieuDe1" name="tieuDe1" value="${baiViet1.tieuDeBaiViet}">
                </div>
                <div class="form-group" style="margin-bottom: 0;">
                    <label for="noiDung1">Nội Dung Bài Viết 1</label>
                    <textarea id="noiDung1" name="noiDung1">${baiViet1.noiDung}</textarea>
                </div>
            </fieldset>

            <fieldset class="form-group full-width" style="border: 1px solid #ddd; padding: 15px; border-radius: 8px;">
                <legend style="font-weight: 500;">Bài Viết 2 (Cấu hình)</legend>
                <input type="hidden" name="maBaiViet2" value="${baiViet2.maBaiViet}">

                <input type="hidden" name="tieuDe2" value="Cấu hình game:" />

                <div class="form-group" style="margin-bottom: 0;">
                    <label for="noiDung2">Nội Dung Bài Viết 2</label>
                    <textarea id="noiDung2" name="noiDung2">${baiViet2.noiDung}</textarea>
                </div>
            </fieldset>

            <div class="form-actions">
                <a href="${pageContext.request.contextPath}/admin/products/steam" class="nav-button">Hủy</a>
                <button type="submit" class="nav-button accent" style="color: white; border: none; cursor: pointer;">Lưu Game Steam</button>
            </div>
        </div>
    </form>
</div>

<jsp:include page="/common/footer.jsp" />