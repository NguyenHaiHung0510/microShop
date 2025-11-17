<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<jsp:include page="/common/header.jsp">
    <jsp:param name="pageTitle" value="Quản lý Đơn Hàng"/>
</jsp:include>

<%
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
%>

<div class="admin-container">

    <nav class="admin-nav">
        <a href="${pageContext.request.contextPath}/admin/dashboard">Bảng Điều Khiển</a>
        <a href="${pageContext.request.contextPath}/admin/products/game">Quản lý Tài Khoản Game</a>
        <a href="${pageContext.request.contextPath}/admin/products/steam">Quản lý Game Steam</a>
        <a href="${pageContext.request.contextPath}/admin/orders" class="active">Quản lý Đơn Hàng</a>
    </nav>

    <h2>Quản lý Đơn Hàng</h2>

    <section class="recent-orders">
        <h3>Đơn Hàng Tài Khoản Game</h3>
        <div class="admin-table-wrapper">
            <table class="admin-table">
                <thead>
                    <tr>
                        <th>Mã Đơn</th>
                        <th>Mã User</th>
                        <th>Mã TK</th>
                        <th>Giá Mua</th>
                        <th>Thời Gian Tạo</th>
                        <th>Trạng Thái</th>
                        <th style="width: 200px;">Hành động</th>
                    </tr>
                </thead>
                <tbody>
                <c:if test="${empty listDonHang}">
                    <tr><td colspan="7" style="text-align: center;">Không có đơn hàng nào.</td></tr>
                </c:if>
                <c:forEach items="${listDonHang}" var="order">
                    <tr>
                        <td>#${order.maDonHang}</td>
                        <td>${order.maNguoiDung}</td> 
                        <td>${order.maTaiKhoan}</td>
                        <td><fmt:formatNumber value="${order.giaMua}" type="number"/> VNĐ</td>
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
                    <td><span class="status-${order.trangThai}">${order.trangThai}</span></td>
                    <td>
                    <c:if test="${order.trangThai == 'CHO_THANH_TOAN'}">
                        <form action="${pageContext.request.contextPath}/admin/order/update" method="POST" style="display: inline;">
                            <input type="hidden" name="id" value="${order.maDonHang}">
                            <input type="hidden" name="type" value="game">
                            <button type="submit" name="status" value="approve" class="nav-button" style="background-color: #28a745; color: white; border: none; cursor: pointer; padding: 8px 12px; font-size: 13px;">Duyệt</button>
                        </form>
                        <form action="${pageContext.request.contextPath}/admin/order/update" method="POST" style="display: inline;"
                              onsubmit="return confirm('Bạn có chắc muốn HỦY đơn hàng #${order.maDonHang}?');">
                            <input type="hidden" name="id" value="${order.maDonHang}">
                            <input type="hidden" name="type" value="game">
                            <button type="submit" name="status" value="cancel" class="nav-button" style="background-color: #dc3545; color: white; border: none; cursor: pointer; padding: 8px 12px; font-size: 13px;">Hủy</button>
                        </form>
                    </c:if>
                    </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="pagination">
            <c:forEach begin="1" end="${pagesGame}" var="i">
                <a href="orders?pageGame=${i}&pageSteam=${currentPageSteam}" class="${currentPageGame == i ? 'active' : ''}">${i}</a>
            </c:forEach>
        </div>
    </section>

    <hr style="margin: 30px 0;">

    <section class="recent-orders">
        <h3>Đơn Hàng Slot Game Steam</h3>
        <div class="admin-table-wrapper">
            <table class="admin-table">
                <thead>
                    <tr>
                        <th>Mã Đơn</th>
                        <th>Mã User</th>
                        <th>Mã Game</th>
                        <th>Mã TK Steam</th>
                        <th>Giá Mua</th>
                        <th>Thời Gian Tạo</th>
                        <th>Trạng Thái</th>
                        <th style="width: 200px;">Hành động</th>
                    </tr>
                </thead>
                <tbody>
                <c:if test="${empty listDonHangSteam}">
                    <tr><td colspan="8" style="text-align: center;">Không có đơn hàng nào.</td></tr>
                </c:if>
                <c:forEach items="${listDonHangSteam}" var="order">
                    <tr>
                        <td>#${order.maDonHangSlot}</td>
                        <td>${order.maNguoiDung}</td> 
                        <td>${order.maGameSteam}</td>
                        <td>${order.maTaiKhoanSteam}</td>
                        <td><fmt:formatNumber value="${order.giaMua}" type="number"/> VNĐ</td>
                        <td>
                        <%
                            com.microshop.model.DonHangSlotSteam donHangST = (com.microshop.model.DonHangSlotSteam) pageContext.getAttribute("order");
                            if (donHangST != null && donHangST.getThoiGianTao() != null) {
                                out.print(donHangST.getThoiGianTao().format(dtf));
                            } else {
                                out.print("N/A");
                            }
                        %>
                        </td>
                    
                    <td><span class="status-${order.trangThai}">${order.trangThai}</span></td>
                    <td>
                    <c:if test="${order.trangThai == 'CHO_THANH_TOAN'}">
                        <form action="${pageContext.request.contextPath}/admin/order/update" method="POST" style="display: inline;">
                            <input type="hidden" name="id" value="${order.maDonHangSlot}">
                            <input type="hidden" name="type" value="steam">
                            <button type="submit" name="status" value="approve" class="nav-button" style="background-color: #28a745; color: white; border: none; cursor: pointer; padding: 8px 12px; font-size: 13px;">Duyệt</button>
                        </form>
                        <form action="${pageContext.request.contextPath}/admin/order/update" method="POST" style="display: inline;"
                              onsubmit="return confirm('Bạn có chắc muốn HỦY đơn hàng #${order.maDonHangSlot}? Slot sẽ được hoàn lại.');">
                            <input type="hidden" name="id" value="${order.maDonHangSlot}">
                            <input type="hidden" name="type" value="steam">
                            <button type="submit" name="status" value="cancel" class="nav-button" style="background-color: #dc3545; color: white; border: none; cursor: pointer; padding: 8px 12px; font-size: 13px;">Hủy</button>
                        </form>
                    </c:if>
                    </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="pagination">
            <c:forEach begin="1" end="${pagesSteam}" var="i">
                <a href="orders?pageGame=${currentPageGame}&pageSteam=${i}" class="${currentPageSteam == i ? 'active' : ''}">${i}</a>
            </c:forEach>
        </div>
    </section>

</div>

<jsp:include page="/common/footer.jsp" />