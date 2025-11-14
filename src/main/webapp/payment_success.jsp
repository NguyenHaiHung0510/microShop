<%@page import="com.microshop.model.NguoiDung"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="common/header.jsp">
    <jsp:param name="pageTitle" value="Thanh Toán Thành Công"/>
</jsp:include>

<c:set var="user" value="${sessionScope.user}" />
<c:set var="status" value="${requestScope.paymentSuccessStatus}" />

<%-- Sử dụng class .success-container (đã được định nghĩa trong style.css) --%>
<div class="success-container">

    <%-- 1. Xử lý LỖI ĐĂNG NHẬP: Hiển thị nếu user == null --%>
    <c:if test="${user == null}">
        <h2 class="error-icon">🛑 Lỗi Truy Cập</h2>
        <p>Vui lòng đăng nhập để xem chi tiết giao dịch.</p>
        <div class="action-links">
            <a href="${pageContext.request.contextPath}/login" class="btn-home">Đăng Nhập</a>
        </div>
    </c:if>

    <%-- 2. KHỐI NỘI DUNG CHÍNH (Chỉ hiển thị khi có user) --%>
    <c:if test="${user != null}">
        <c:choose>
            <c:when test="${status eq true}">
                <%-- THANH TOÁN THÀNH CÔNG --%>
                <div class="icon success-icon">✔</div>
                <h2 style="color: #2ecc71;">Giao Dịch Thành Công!</h2>
                <p>Tài khoản game đã được giao và gửi vào email của bạn.</p>

                <div class="details-box">
                    <p><strong>Mã Giao Dịch:</strong> ${requestScope.transactionId}</p>
                    <p><strong>Phương Thức:</strong> ${requestScope.transactionMethod}</p>
                    <p><strong>Người Nhận:</strong> ${user.email}</p>
                </div>

            </c:when>

            <c:otherwise>
                <%-- THANH TOÁN THẤT BẠI HOẶC LỖI HỆ THỐNG --%>
                <div class="icon error-icon">❌</div>
                <h2 style="color: #e74c3c;">Giao Dịch Thất Bại</h2>
                <p>Đã xảy ra lỗi trong quá trình xử lý giao dịch của bạn.</p>

                <c:if test="${not empty requestScope.errorMessage}">
                    <p style="color: #e74c3c; font-weight: 500;">Chi tiết: ${requestScope.errorMessage}</p>
                </c:if>

            </c:otherwise>
        </c:choose>

        <%-- LIÊN KẾT HÀNH ĐỘNG (Chỉ hiển thị nếu đã đăng nhập) --%>
        <div class="action-links">
            <a href="${pageContext.request.contextPath}/home" class="btn-home">Quay lại Trang Chủ</a>
            <a href="${pageContext.request.contextPath}/profile/view-account" class="btn-profile">Xem Tài Khoản Đã Mua</a>
        </div>
    </c:if>
</div>

<jsp:include page="common/footer.jsp" />