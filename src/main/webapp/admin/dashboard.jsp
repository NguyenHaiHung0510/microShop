<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- 
    Lưu ý: Bạn cần sửa file `common/header.jsp`
    để thêm link "Admin Panel" nếu user là ADMIN, ví dụ:
    
    <c:if test="${sessionScope.user.vaiTro == 'ADMIN'}">
        <a href="${pageContext.request.contextPath}/admin/dashboard" class="nav-button admin">Admin Panel</a>
    </c:if>
--%>
<jsp:include page="/common/header.jsp">
    <jsp:param name="pageTitle" value="Admin Dashboard"/>
</jsp:include>

<%-- Thêm CSS riêng cho trang Admin vào đây (hoặc link tới 1 file css admin riêng) --%>
<style>
    .admin-container {
        width: 90%;
        max-width: 1400px;
        margin: 20px auto;
        padding: 20px;
    }
    .admin-nav {
        background-color: #343a40;
        padding: 10px;
        border-radius: 8px;
        margin-bottom: 20px;
        display: flex;
        flex-wrap: wrap; /* Cho phép xuống dòng trên di động */
        gap: 10px;
    }
    .admin-nav a {
        color: white;
        padding: 10px 15px;
        text-decoration: none;
        border-radius: 5px;
        font-weight: 500;
        transition: background-color 0.2s;
    }
    .admin-nav a:hover, .admin-nav a.active {
        background-color: #c92a2a;
    }
    .stat-cards {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(250px, 1fr)); /* Responsive */
        gap: 20px;
        margin-bottom: 30px;
    }
    .card {
        background: #fff;
        padding: 25px;
        border-radius: 8px;
        box-shadow: 0 2px 5px rgba(0,0,0,0.05);
    }
    .card h3 {
        margin-top: 0;
        color: #555;
    }
    .card .stat-number {
        font-size: 2.5em;
        font-weight: 700;
        color: #c92a2a;
    }

    .admin-table-wrapper {
        background: #fff;
        border-radius: 8px;
        overflow-x: auto; /* Cho phép cuộn ngang nếu bảng quá rộng */
        box-shadow: 0 2px 5px rgba(0,0,0,0.05);
    }
    .admin-table {
        width: 100%;
        border-collapse: collapse;
    }
    .admin-table th, .admin-table td {
        padding: 15px;
        text-align: left;
        border-bottom: 1px solid #eee;
    }
    .admin-table th {
        background-color: #f8f9fa;
    }
    .admin-table tr:hover {
        background-color: #fcfcfc;
    }

    .pagination {
        margin-top: 20px;
        text-align: center;
    }
    .pagination a {
        color: #c92a2a;
        padding: 8px 12px;
        text-decoration: none;
        border: 1px solid #ddd;
        margin: 0 2px;
        border-radius: 4px;
    }
    .pagination a.active {
        background-color: #c92a2a;
        color: white;
        border-color: #c92a2a;
    }
    .pagination a.disabled {
        color: #ccc;
        pointer-events: none;
        border-color: #eee;
    }
</style>

<div class="admin-container">

    <%-- 1. Thanh Điều Hướng Admin --%>
    <nav class="admin-nav">
        <a href="${pageContext.request.contextPath}/admin/dashboard" class="active">Bảng Điều Khiển</a>
        <a href="${pageContext.request.contextPath}/admin/products?type=game">Quản lý Tài Khoản Game</a>
        <a href="${pageContext.request.contextPath}/admin/products?type=steam">Quản lý Game Steam</a>
        <a href="${pageContext.request.contextPath}/admin/orders">Quản lý Đơn Hàng</a>
        <a href="${pageContext.request.contextPath}/admin/users">Quản lý Người Dùng</a>
        <a href="${pageContext.request.contextPath}/admin/import">Import Dữ Liệu</a>
    </nav>

    <h2>Bảng Điều Khiển</h2>

    <%-- 2. Các thẻ Thống Kê Nhanh (Dữ liệu thật) --%>
    <section class="stat-cards">
        <div class="card">
            <h3>Tổng Tài Khoản</h3>
            <div class="stat-number"><fmt:formatNumber value="${totalTaiKhoan}" type="number"/></div>
        </div>
        <div class="card">
            <h3>Tổng Người Dùng</h3>
            <div class="stat-number"><fmt:formatNumber value="${totalNguoiDung}" type="number"/></div>
        </div>
        <div class="card">
            <h3>Tổng Đơn Hàng</h3>
            <div class="stat-number"><fmt:formatNumber value="${totalDonHang}" type="number"/></div>
        </div>
        <div class="card">
            <h3>Đơn Chờ Xử Lý</h3>
            <div class="stat-number"><fmt:formatNumber value="${totalDonHangCho}" type="number"/></div>
        </div>
    </section>

    <%-- 3. Bảng Phân Trang (Dữ liệu thật) --%>
    <section class="recent-orders">
        <h3>Đơn Hàng Tài Khoản Game Mới Nhất</h3>
        <div class="admin-table-wrapper">
            <table class="admin-table">
                <thead>
                    <tr>
                        <th>Mã Đơn</th>
                        <th>Mã Người Dùng</th>
                        <th>Mã Tài Khoản</th>
                        <th>Giá Mua</th>
                        <th>Trạng Thái</th>
                        <th>Thời Gian Tạo</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                <c:if test="${empty listDonHang}">
                    <tr>
                        <td colspan="7" style="text-align: center;">Không có đơn hàng nào.</td>
                    </tr>
                </c:if>
                <c:forEach items="${listDonHang}" var="order">
                    <tr>
                        <td>${order.maDonHang}</td>
                        <td>${order.maNguoiDung}</td> 
                        <td>${order.maTaiKhoan}</td>
                        <td><fmt:formatNumber value="${order.giaMua}" type="number"/> VNĐ</td>
                    <td>${order.trangThai}</td>
                    <td><fmt:formatDate value="${order.thoiGianTao}" pattern="HH:mm dd-MM-yyyy"/></td>
                    <td><a href="${pageContext.request.contextPath}/admin/orders/edit?type=game&id=${order.maDonHang}">Chi tiết</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <%-- 4. Thanh Phân Trang (Pagination) --%>
        <%-- SỬA: Dùng ${pageCount} thay vì ${noOfPages} --%>
        <div class="pagination">
            <c:if test="${currentPage > 1}">
                <a href="dashboard?page=${currentPage - 1}">Trước</a>
            </c:if>

            <c:forEach begin="1" end="${pageCount}" var="i">
                <c:choose>
                    <c:when test="${currentPage == i}">
                        <a class="active">${i}</a>
                    </c:when>
                    <c:otherwise>
                        <a href="dashboard?page=${i}">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${currentPage < pageCount}">
                <a href="dashboard?page=${currentPage + 1}">Sau</a>
            </c:if>
        </div>
    </section>

</div>

<jsp:include page="/common/footer.jsp" />