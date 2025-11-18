<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/common/header.jsp">
    <jsp:param name="pageTitle" value="Quản lý Tài Khoản Game"/>
</jsp:include>

<div class="admin-container">

    <%-- 1. Thanh Điều Hướng Admin --%>
    <nav class="admin-nav">
        <a href="${pageContext.request.contextPath}/admin/dashboard">Bảng Điều Khiển</a>
        <%-- SỬA LINK: Đây là trang active --%>
        <a href="${pageContext.request.contextPath}/admin/products/game" class="active">Quản lý Tài Khoản Game</a>
        <a href="${pageContext.request.contextPath}/admin/products/steam">Quản lý Game Steam</a>
        <a href="${pageContext.request.contextPath}/admin/orders">Quản lý Đơn Hàng</a>
    </nav>

    <div style="display: flex; justify-content: space-between; align-items: center;">
        <h2>Quản lý Tài Khoản Game (${totalRecords} tài khoản)</h2>
        <a href="${pageContext.request.contextPath}/admin/account/add" class="nav-button accent" style="color: white; border: none;">Thêm Tài Khoản Mới</a>
    </div>

    <c:if test="${not empty errorMessage}">
        <div class="alert-danger" style="margin: 15px 0;">${errorMessage}</div>
    </c:if>

    <%-- 2. Bảng Danh sách Tài Khoản Game --%>
    <section class="admin-table-wrapper">
        <table class="admin-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Ảnh</th>
                    <th>Mô Tả (Điểm Nổi Bật)</th>
                    <th>Danh Mục</th>
                    <th>Giá Bán</th>
                    <th>Trạng Thái</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
                <c:if test="${empty listAccounts}">
                    <tr>
                        <td colspan="7" style="text-align: center;">Chưa có tài khoản game nào.</td>
                    </tr>
                </c:if>
                <c:forEach items="${listAccounts}" var="tk">
                    <tr>
                        <td>${tk.maTaiKhoan}</td>
                        <td>
                            <c:choose>
                                <c:when test="${not empty tk.duongDanAnh}">
                                    <%-- URL ảo /uploads/ trỏ ra Desktop --%>
                                    <img src="${pageContext.request.contextPath}/${tk.duongDanAnh}" alt="TK ${tk.maTaiKhoan}" style="width: 100px; height: auto; border-radius: 4px;">
                                </c:when>
                                <c:otherwise>
                                    <img src="https://via.placeholder.com/100x50?text=No+Image" alt="No Image" style="width: 100px; height: auto; border-radius: 4px;">
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td style="max-width: 300px;">${tk.diemNoiBat}</td>
                        <td>
                            <%-- Tra cứu tên danh mục từ Map --%>
                            <c:out value="${categoryMap[tk.maDanhMuc]}" default="Không rõ"/>
                        </td>
                        <td><fmt:formatNumber value="${tk.giaBan}" type="number"/> VNĐ</td>
                        <td>
                            <c:choose>
                                <c:when test="${tk.trangThai == 'DANG_BAN'}">
                                    <span style="color: green; font-weight: bold;">Đang Bán</span>
                                </c:when>
                                <c:otherwise>
                                    <span style="color: red; font-weight: bold;">Đã Bán</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td style="min-width: 150px;">
                            <a href="${pageContext.request.contextPath}/admin/account/edit?id=${tk.maTaiKhoan}" class="nav-button" style="background-color: #007bff; color: white; border: none; padding: 8px 12px; font-size: 13px;">Sửa</a>

                            <%-- Form Xóa --%>
                            <form action="${pageContext.request.contextPath}/admin/account/delete" method="POST" style="display: inline;" 
                                  onsubmit="return confirm('Bạn có chắc chắn muốn xóa Tài khoản #${tk.maTaiKhoan} không?');">
                                <input type="hidden" name="id" value="${tk.maTaiKhoan}">
                                <button type="submit" class="nav-button" style="background-color: #dc3545; color: white; border: none; cursor: pointer; padding: 8px 12px; font-size: 13px;">Xóa</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </section>

    <%-- 3. Thanh Phân Trang --%>
    <div class="pagination">
        <c:if test="${currentPage > 1}">
            <a href="game?page=${currentPage - 1}">Trước</a>
        </c:if>

        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage == i}">
                    <a class="active">${i}</a>
                </c:when>
                <c:otherwise>
                    <a href="game?page=${i}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${currentPage < noOfPages}">
            <a href="game?page=${currentPage + 1}">Sau</a>
        </c:if>
    </div>

</div>

<jsp:include page="/common/footer.jsp" />