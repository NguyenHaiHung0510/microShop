<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="common/header.jsp">
    <jsp:param name="pageTitle" value="Đăng Ký"/>
</jsp:include>

<%-- Sử dụng class "form-wrapper" mới (đã được định nghĩa trong style.css) --%>
<div class="form-wrapper">
    <h2>Đăng Ký Tài Khoản</h2>

    <%
        String error = (String) request.getAttribute("registerError");
        if (error != null && !error.isEmpty()) {
    %>
    <p style="color: red; text-align: center; margin-bottom: 15px;"><%= error%></p>
    <%
        }
    %>

    <form action="${pageContext.request.contextPath}/register" method="POST">
        <div class="form-group">
            <label for="username">Tên người dùng:</label>
            <input type="text" id="username" name="username" required placeholder="Nhập tên người dùng...">
        </div>

        <div class="form-group">
            <label for="email">Email <span style="color:gray;">(tùy chọn)</span>:</label>
            <input type="email" id="email" name="email" placeholder="Nhập email...">
        </div>

        <div class="form-group">
            <label for="sdt">Số điện thoại: <span style="color:gray;">(tùy chọn)</span>:</label>
            <input type="text" id="sdt" name="sdt" placeholder="Nhập số điện thoại...">
        </div>

        <div class="form-group">
            <label for="password">Mật khẩu:</label>
            <input type="password" id="password" name="password" required placeholder="Nhập mật khẩu...">
        </div>

        <div class="form-group">
            <label for="confirm_password">Xác nhận mật khẩu:</label>
            <input type="password" id="confirm_password" name="confirm_password" required placeholder="Xác nhận mật khẩu...">
        </div>

        <%-- Thêm class "register" để có màu xanh lá --%>
        <button type="submit" class="btn-submit register">Đăng Ký</button>
    </form>

    <p style="text-align:center; margin-top: 15px;">
        Đã có tài khoản? <a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
    </p>
</div>

<jsp:include page="common/footer.jsp" />