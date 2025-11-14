<%@page import="com.microshop.model.NguoiDung"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.time.format.DateTimeFormatter"%> <%-- THÊM IMPORT NÀY --%>

<jsp:include page="common/header.jsp">
    <jsp:param name="pageTitle" value="Lịch Sử Đơn Hàng"/>
</jsp:include>

<div class="container">
    <h2>Lịch Sử Đơn Hàng</h2>

    <%-- Kiểm tra nếu danh sách trống --%>
    <c:if test="${empty requestScope.orderList and empty requestScope.steamOrderList}">
        <p style="text-align: center;">Bạn chưa thực hiện đơn hàng nào.</p>
    </c:if>

    <%-- Hiển thị bảng nếu danh sách không trống --%>
    <c:if test="${not empty requestScope.orderList}">
        <table class="order-table">
            <thead>
                <tr>
                    <th>Mã Đơn Hàng</th>
                    <th>Thời Gian Tạo</th> <%-- SỬA CỘT NÀY --%>
                    <th>Tổng Tiền</th>
                    <th>Trạng Thái</th>
                    <th>Chi Tiết</th>
                </tr>
            </thead>
            <tbody>
                <%-- 
                    KHỞI TẠO FORMATTER (ĐỊNH DẠNG)
                    Chúng ta cần khởi tạo DateTimeFormatter để sử dụng bên trong vòng lặp.
                --%>
                <%
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                %>

                <c:forEach var="order" items="${requestScope.orderList}">
                    <tr>
                        <td>#${order.maDonHang}</td>

                        <%-- SỬA LỖI HIỂN THỊ THỜI GIAN --%>
                        <td>
                            <%
                                // 1. Lấy đối tượng DonHang từ biến "order" của vòng lặp
                                com.microshop.model.DonHang donHang = (com.microshop.model.DonHang) pageContext.getAttribute("order");

                                // 2. Kiểm tra null và gọi .format()
                                if (donHang != null && donHang.getThoiGianTao() != null) {
                                    out.print(donHang.getThoiGianTao().format(dtf));
                                } else {
                                    out.print("N/A"); // Hoặc để trống
                                }
                            %>
                        </td>

                        <td>
                            <fmt:formatNumber value="${order.giaMua}" type="currency" currencyCode="VND" maxFractionDigits="0"/> VNĐ
                        </td>
                        <td>
                            <%-- ... (Phần Trạng thái không đổi) ... --%>
                            <span class="status-${order.trangThai}">
                                <c:choose>
                                    <c:when test="${order.trangThai eq 'DA_HOAN_THANH'}">Đã Hoàn Thành</c:when>
                                    <c:when test="${order.trangThai eq 'CHO_THANH_TOAN'}">Đang chờ thanh toán</c:when>
                                    <c:when test="${order.trangThai eq 'DA_HUY'}">Đã Hủy</c:when>
                                    <c:otherwise>${order.trangThai}</c:otherwise>
                                </c:choose>
                            </span>
                        </td>
                        <td>
                            <%-- ... (Phần Link Chi tiết không đổi) ... --%>
                        </td>
                    </tr>
                </c:forEach>
                    
                <c:forEach var="steamOrder" items="${requestScope.steamOrderList}">
                    <tr>
                        <td>Steam#${steamOrder.maDonHangSlot}</td>
                        
                        <%-- SỬA LỖI HIỂN THỊ THỜI GIAN --%>
                        <td>
                            <%
                                // 1. Lấy đối tượng DonHang từ biến "order" của vòng lặp
                                com.microshop.model.DonHangSlotSteam donHangSlotSteam = (com.microshop.model.DonHangSlotSteam) pageContext.getAttribute("steamOrder");
                                
                                // 2. Kiểm tra null và gọi .format()
                                if (donHangSlotSteam != null && donHangSlotSteam.getThoiGianTao() != null) {
                                    out.print(donHangSlotSteam.getThoiGianTao().format(dtf));
                                } else {
                                    out.print("N/A"); // Hoặc để trống
                                }
                            %>
                        </td>
                        
                        <td>
                            <fmt:formatNumber value="${steamOrder.giaMua}" type="currency" currencyCode="VND" maxFractionDigits="0"/> VNĐ
                        </td>
                        <td>
                            <%-- ... (Phần Trạng thái không đổi) ... --%>
                            <span class="status-${steamOrder.trangThai}">
                                <c:choose>
                                    <c:when test="${steamOrder.trangThai eq 'DA_HOAN_THANH'}">Đã Hoàn Thành</c:when>
                                    <%--<c:when test="${order.trangThai eq 'CHO_THANH_TOAN'}">Đang chờ thanh toán</c:when>--%>
                                    <c:when test="${steamOrder.trangThai eq 'DA_HUY'}">Đã Hủy</c:when>
                                    <c:otherwise>${steamOrder.trangThai}</c:otherwise>
                                </c:choose>
                            </span>
                        </td>
                        <td>
                            <%-- ... (Phần Link Chi tiết không đổi) ... --%>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <a href="${pageContext.request.contextPath}/profile" class="back-link">← Quay lại Hồ Sơ</a>
</div>

<jsp:include page="common/footer.jsp" />