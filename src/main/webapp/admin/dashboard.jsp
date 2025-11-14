<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<jsp:include page="/common/header.jsp">
    <jsp:param name="pageTitle" value="Admin Dashboard"/>
</jsp:include>

<div class="admin-container">

    <%-- 1. Thanh Điều Hướng Admin --%>
    <nav class="admin-nav">
        <a href="${pageContext.request.contextPath}/admin/dashboard" class="active">Bảng Điều Khiển</a>
        <a href="${pageContext.request.contextPath}/admin/products?type=game">Quản lý Tài Khoản Game</a>
        <a href="${pageContext.request.contextPath}/admin/products/steam">Quản lý Game Steam</a>
        <a href="${pageContext.request.contextPath}/admin/orders">Quản lý Đơn Hàng</a>
        <a href="${pageContext.request.contextPath}/admin/users">Quản lý Người Dùng</a>
        <a href="${pageContext.request.contextPath}/admin/import">Nhập Hàng</a>
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
                    <%
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
                    %>

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

                            <%-- SỬA: Đây là dòng 176 bị lỗi --%>
                            <td>
                                <%
                                    // 1. Lấy đối tượng DonHang từ biến "order" của vòng lặp
                                    com.microshop.model.DonHang donHang = (com.microshop.model.DonHang) pageContext.getAttribute("order");
                                    // 2. Kiểm tra null và gọi .format()
                                    if (donHang != null && donHang.getThoiGianTao() != null) {
                                        out.print(donHang.getThoiGianTao().format(dtf));
                                    } else {
                                        out.print("N/A");
                                    }
                                %>
                            </td>

                            <td><a href="${pageContext.request.contextPath}/admin/orders/edit?type=game&id=${order.maDonHang}">Chi tiết</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <%-- 4. Thanh Phân Trang (Pagination) --%>
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