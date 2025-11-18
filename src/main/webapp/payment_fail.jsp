<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="common/header.jsp">
    <jsp:param name="pageTitle" value="Giao Dịch Thất Bại"/>
</jsp:include>

<%-- Sử dụng class .fail-container (đã được định nghĩa trong style.css) --%>
<div class="fail-container">

    <div class="icon error-icon">❌</div>
    <h2>Giao Dịch Thất Bại</h2>

    <div class="message-box">
        <p>Rất tiếc, đã xảy ra lỗi trong quá trình thanh toán của bạn.</p>

        <c:choose>
            <c:when test="${not empty requestScope.errorMessage}">
                <%-- Lỗi chung (do server) --%>
                <p style="color: #e74c3c; font-weight: 500;">${requestScope.errorMessage}</p>
            </c:when>
            <c:otherwise>
                <%-- Lỗi do sản phẩm đã bị mua (do logic trong Servlet) --%>
                <p style="color: #e74c3c; font-weight: 500;">
                    Tài khoản này đã được người khác thanh toán.
                    Vui lòng quay lại và chọn sản phẩm khác.
                </p>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="action-links">
        <a href="${pageContext.request.contextPath}/home" class="btn-home">Quay lại Trang Chủ</a>
    </div>
</div>

<jsp:include page="common/footer.jsp" />