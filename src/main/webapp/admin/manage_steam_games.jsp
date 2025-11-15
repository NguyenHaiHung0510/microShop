<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/common/header.jsp">
    <jsp:param name="pageTitle" value="Quản lý Game Steam"/>
</jsp:include>

<div class="admin-container">

    <nav class="admin-nav">
        <a href="${pageContext.request.contextPath}/admin/dashboard">Bảng Điều Khiển</a>
        <%-- SỬA LINK: Bỏ "?type=game" --%>
        <a href="${pageContext.request.contextPath}/admin/products/game">Quản lý Tài Khoản Game</a>
        <a href="${pageContext.request.contextPath}/admin/products/steam" class="active">Quản lý Game Steam</a>
        <a href="${pageContext.request.contextPath}/admin/orders">Quản lý Đơn Hàng</a>
    </nav>

    <%-- (Phần còn lại của file giữ nguyên) --%>
    <div style="display: flex; justify-content: space-between; align-items: center;">
        <h2>Quản lý Game Steam (${totalRecords} sản phẩm)</h2>
        <a href="${pageContext.request.contextPath}/admin/steam/add" class="nav-button accent" style="color: white; border: none;">Thêm Game Mới</a>
    </div>

    <c:if test="${not empty errorMessage}">
        <div class="alert-danger" style="margin: 15px 0;">${errorMessage}</div>
    </c:if>

    <section class="admin-table-wrapper">
        <table class="admin-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Ảnh</th>
                    <th>Tên Game</th>
                    <th>Giá Gốc</th>
                    <th>Giá Bán</th>
                    <th>Lượt Xem</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
                <c:if test="${empty listGames}">
                    <tr>
                        <td colspan="7" style="text-align: center;">Chưa có game steam nào.</td>
                    </tr>
                </c:if>
                <c:forEach items="${listGames}" var="game">
                    <tr>
                        <td>${game.maGameSteam}</td>
                        <td>
                            <c:choose>
                                <c:when test="${not empty game.duongDanAnh}">
                                    <img src="${pageContext.request.contextPath}/${game.duongDanAnh}" alt="${game.tenGame}" style="width: 100px; height: auto; border-radius: 4px;">
                                </c:when>
                                <c:otherwise>
                                    <img src="https://via.placeholder.com/100x50?text=No+Image" alt="No Image" style="width: 100px; height: auto; border-radius: 4px;">
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${game.tenGame}</td>
                        <td><fmt:formatNumber value="${game.giaGoc}" type="number"/> VNĐ</td>
                        <td><fmt:formatNumber value="${game.giaBan}" type="number"/> VNĐ</td>
                        <td><fmt:formatNumber value="${game.luotXem}" type="number"/></td>
                        <td style="min-width: 200px;">
                            <a href="${pageContext.request.contextPath}/admin/steam/edit?id=${game.maGameSteam}" class="nav-button" style="background-color: #007bff; color: white; border: none; padding: 8px 12px; font-size: 13px;">Sửa</a>

                            <form action="${pageContext.request.contextPath}/admin/steam/delete" method="POST" style="display: inline;" 
                                  onsubmit="return confirm('Bạn có chắc chắn muốn xóa game [${game.tenGame}] không? TOÀN BỘ bài viết, slot, đơn hàng liên quan sẽ bị xóa!');">
                                <input type="hidden" name="id" value="${game.maGameSteam}">
                                <button type="submit" class="nav-button" style="background-color: #dc3545; color: white; border: none; cursor: pointer; padding: 8px 12px; font-size: 13px;">Xóa</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </section>

    <div class="pagination">
        <c:if test="${currentPage > 1}">
            <a href="products/steam?page=${currentPage - 1}">Trước</a>
        </c:if>
        <c:forEach begin="1" end="${noOfPages}" var="i">
            <a href="products/steam?page=${i}" class="${currentPage == i ? 'active' : ''}">${i}</a>
        </c:forEach>
        <c:if test="${currentPage < noOfPages}">
            <a href="products/steam?page=${currentPage + 1}">Sau</a>
        </c:if>
    </div>
</div>

<jsp:include page="/common/footer.jsp" />