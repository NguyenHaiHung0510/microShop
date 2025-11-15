<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<jsp:include page="/common/header.jsp">
    <jsp:param name="pageTitle" value="Admin Dashboard"/>
</jsp:include>

<div class="admin-container">

    <nav class="admin-nav">
        <a href="${pageContext.request.contextPath}/admin/dashboard" class="active">Bảng Điều Khiển</a>
        <a href="${pageContext.request.contextPath}/admin/products/game">Quản lý Tài Khoản Game</a>
        <a href="${pageContext.request.contextPath}/admin/products/steam">Quản lý Game Steam</a>
        <a href="${pageContext.request.contextPath}/admin/orders">Quản lý Đơn Hàng</a>
        <%-- SỬA: Cập nhật tên "Nhập Hàng" --%>
    </nav>

    <h2>Bảng Điều Khiển</h2>

    <%-- (Phần Thống Kê Giữ Nguyên) --%>
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

    <%-- SỬA 3: Hiển thị 2 Bảng --%>

    <%-- Bảng 1: Đơn Hàng Tài Khoản Game --%>
    <section class="recent-orders">
        <h3>Đơn Hàng Tài Khoản Game Mới Nhất</h3>
        <div class="admin-table-wrapper">
            <table class="admin-table">
                <thead>
                    <tr>
                        <th>Mã Đơn</th>
                        <th>Mã User</th>
                        <th>Mã TK</th>
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
                            <td>#${order.maDonHang}</td>
                            <td>${order.maNguoiDung}</td> 
                            <td>${order.maTaiKhoan}</td>
                            <td><fmt:formatNumber value="${order.giaMua}" type="number"/> VNĐ</td>
                            <td><span class="status-${order.trangThai}">${order.trangThai}</span></td>
                            <td>
                                <%
                                    com.microshop.model.DonHang donHang = (com.microshop.model.DonHang) pageContext.getAttribute("order");
                                    if (donHang != null && donHang.getThoiGianTao() != null) {
                                        out.print(donHang.getThoiGianTao().format(dtf));
                                    } else {
                                        out.print("N/A");
                                    }
                                %>
                            </td>
                            <td><a href="${pageContext.request.contextPath}/admin/orders">Xem chi tiết</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="pagination">
            <c:if test="${currentPage > 1}">
                <a href="dashboard?page=${currentPage - 1}">Trước</a>
            </c:if>
            <c:forEach begin="1" end="${pageCount}" var="i">
                <a href="dashboard?page=${i}" class="${currentPage == i ? 'active' : ''}">${i}</a>
            </c:forEach>
            <c:if test="${currentPage < pageCount}">
                <a href="dashboard?page=${currentPage + 1}">Sau</a>
            </c:if>
        </div>
    </section>

    <hr style="margin: 30px 0;">

    <%-- Bảng 2: Đơn Hàng Slot Steam --%>
    <section class="recent-orders">
        <h3>Đơn Hàng Slot Steam Mới Nhất</h3>
        <div class="admin-table-wrapper">
            <table class="admin-table">
                <thead>
                    <tr>
                        <th>Mã Đơn</th>
                        <th>Mã User</th>
                        <th>Mã Game</th>
                        <th>Mã TK Steam</th>
                        <th>Giá Mua</th>
                        <th>Trạng Thái</th>
                        <th>Thời Gian Tạo</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${empty listDonHangSteam}">
                        <tr>
                            <td colspan="8" style="text-align: center;">Không có đơn hàng nào.</td>
                        </tr>
                    </c:if>
                    <c:forEach items="${listDonHangSteam}" var="orderSteam">
                        <tr>
                            <td>#${orderSteam.maDonHangSlot}</td>
                            <td>${orderSteam.maNguoiDung}</td> 
                            <td>${orderSteam.maGameSteam}</td>
                            <td>${orderSteam.maTaiKhoanSteam}</td>
                            <td><fmt:formatNumber value="${orderSteam.giaMua}" type="number"/> VNĐ</td>
                            <td><span class="status-${orderSteam.trangThai}">${orderSteam.trangThai}</span></td>
                            <td>
                                <%
                                    com.microshop.model.DonHangSlotSteam donHang = (com.microshop.model.DonHangSlotSteam) pageContext.getAttribute("orderSteam");
                                    if (donHang != null && donHang.getThoiGianTao() != null) {
                                        out.print(donHang.getThoiGianTao().format(dtf));
                                    } else {
                                        out.print("N/A");
                                    }
                                %>
                            </td>
                            <td><a href="${pageContext.request.contextPath}/admin/orders">Xem chi tiết</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="pagination">
            <c:if test="${currentPage > 1}">
                <a href="dashboard?page=${currentPage - 1}">Trước</a>
            </c:if>
            <c:forEach begin="1" end="${pageCountSteam}" var="i">
                <a href="dashboard?page=${i}" class="${currentPage == i ? 'active' : ''}">${i}</a>
            </c:forEach>
            <c:if test="${currentPage < pageCountSteam}">
                <a href="dashboard?page=${currentPage + 1}">Sau</a>
            </c:if>
        </div>
    </section>

</div>

<jsp:include page="/common/footer.jsp" />