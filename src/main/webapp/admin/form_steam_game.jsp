<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/common/header.jsp">
    <jsp:param name="pageTitle" value="${empty game.maGameSteam ? 'Thêm Game Steam' : 'Sửa Game Steam'}"/>
</jsp:include>

<div class="admin-container">
    <nav class="admin-nav">
        <a href="${pageContext.request.contextPath}/admin/dashboard">Bảng Điều Khiển</a>
        <%-- SỬA LINK: Bỏ "?type=game" --%>
        <a href="${pageContext.request.contextPath}/admin/products/game">Quản lý Tài Khoản Game</a>
        <a href="${pageContext.request.contextPath}/admin/products/steam" class="active">Quản lý Game Steam</a>
        <a href="${pageContext.request.contextPath}/admin/orders">Quản lý Đơn Hàng</a>
        <%-- SỬA TÊN: "Nhập Hàng" --%>
    </nav>
</div>

<div class="admin-form-container">
    <form action="${pageContext.request.contextPath}/admin/steam/save" method="POST" enctype="multipart/form-data">
        <h2>
            <c:if test="${empty game.maGameSteam}">Thêm Game Steam Mới</c:if>
            <c:if test="${not empty game.maGameSteam}">Cập nhật Game: ${game.tenGame}</c:if>
            </h2>

        <c:if test="${not empty errorMessage}">
            <div class="alert-danger" style="margin-bottom: 20px;">${errorMessage}</div>
        </c:if>

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
                <label for="idVideoTrailer">ID Video Trailer YouTube (Vd: E3Huy2CdjfA)</label>
                <input type="text" id="idVideoTrailer" name="idVideoTrailer" value="${game.idVideoTrailer}">
            </div>

            <div class="form-group">
                <label for="fileAnh">Ảnh Bìa Game (Ảnh chính)</label>
                <input type="file" id="fileAnh" name="fileAnh" accept="image/png, image/jpeg">
                <input type="hidden" name="anhHienTai" value="${game.duongDanAnh}">

                <c:if test="${not empty game.duongDanAnh}">
                    <span class="note" style="margin-top: 5px;">
                        Ảnh hiện tại: 
                        <img src="${pageContext.request.contextPath}/${game.duongDanAnh}" width="100" style="display: block; margin-top: 5px; border-radius: 4px;">
                    </span>
                    <span class="note">Bỏ trống nếu không muốn thay đổi ảnh.</span>
                </c:if>
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
                    <input type="text" id="tieuDe1" name="tieuDe1" value="${baiViet1.tieuDeBaiViet}" placeholder="Vd: Giới thiệu Elden Ring">
                </div>
                <div class="form-group" style="margin-bottom: 0;">
                    <label for="noiDung1">Nội Dung Bài Viết 1</label>
                    <textarea id="noiDung1" name="noiDung1">${baiViet1.noiDung}</textarea>
                </div>
            </fieldset>

            <fieldset class="form-group full-width" style="border: 1px solid #ddd; padding: 15px; border-radius: 8px;">
                <legend style="font-weight: 500;">Bài Viết 2 (Thông tin game)</legend>
                <input type="hidden" name="maBaiViet2" value="${baiViet2.maBaiViet}">
                <div class="form-group">
                    <label for="tieuDe2">Tiêu Đề Bài Viết 2</label>
                    <input type="hidden" id="tieuDe2" name="tieuDe2" value="${baiViet2.tieuDeBaiViet}" placeholder="Vd: Thông tin game">
                </div>
                <div class="form-group" style="margin-bottom: 0;">
                    <label for="noiDung2">Nội Dung Bài Viết 2</label>
                    <textarea id="noiDung2" name="noiDung2">${baiViet2.noiDung}</textarea>
                </div>
            </fieldset>
                
            <fieldset class="form-group full-width" style="border: 1px solid #ddd; padding: 15px; border-radius: 8px;">
                <legend style="font-weight: 500;">Bài Viết 3 (Cấu hình)</legend>
                <input type="hidden" name="maBaiViet3" value="${baiViet3.maBaiViet}">
                <div class="form-group">
                    <label for="tieuDe3">Tiêu Đề Bài Viết 3</label>
                    <input type="hidden" id="tieuDe3" name="tieuDe3" value="${baiViet3.tieuDeBaiViet}" placeholder="Vd: Cấu hình yêu cầu">
                </div>
                <div class="form-group" style="margin-bottom: 0;">
                    <label for="noiDung3">Nội Dung Bài Viết 3</label>
                    <textarea id="noiDung3" name="noiDung3">${baiViet3.noiDung}</textarea>
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