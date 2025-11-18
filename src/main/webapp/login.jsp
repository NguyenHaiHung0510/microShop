<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="common/header.jsp">
    <jsp:param name="pageTitle" value="Đăng Nhập"/>
</jsp:include>

<%-- Sử dụng class "form-wrapper" mới (đã được định nghĩa trong style.css) --%>
<div class="form-wrapper">
    <h2>Đăng Nhập Hệ Thống</h2>

    <%
        String error = (String) request.getAttribute("loginError");
        if (error != null && !error.isEmpty()) {
    %>
    <p style="color: red; text-align: center; margin-bottom: 15px;"><%= error%></p>
    <%
        }
    %>

    <form action="${pageContext.request.contextPath}/login" method="POST">
        <div class="form-group">
            <label for="username">Tên người dùng:</label>
            <input type="text" id="username" name="username" required
                   placeholder="Nhập tên người dùng...">
        </div>

        <div class="form-group">
            <label for="password">Mật khẩu:</label>
            <input type="password" id="password" name="password" required
                   placeholder="Nhập mật khẩu...">
        </div>

        <%-- Thêm class "login" để có màu xanh dương --%>
        <button type="submit" class="btn-submit login">Đăng Nhập</button>
    </form>
    <p style="text-align:center; margin-top: 15px;">
        Chưa có tài khoản? <a href="${pageContext.request.contextPath}/register">Đăng ký</a>
    </p>
</div>


<%-- 
    Phần Toast (Thông báo) cho Đăng ký thành công
    (Di chuyển ra ngoài form wrapper)
--%>
<%
    String registerSuccess = request.getParameter("register");
    boolean showSuccessToast = "success".equals(registerSuccess);
%>
<% if (showSuccessToast) {%>
<div id="toast" class="toast">🎉 Đăng ký tài khoản thành công! Hãy đăng nhập để tiếp tục.</div>
<% } %>

<%
    String fromPage = request.getParameter("from");
    if ("payment".equals(fromPage)) {
%>
<div id="toast" class="toast">⚠️ Vui lòng đăng nhập để tiếp tục thanh toán.</div>
<%
    }
%>

<script>
    // Hiển thị toast khi trang được tải
    window.addEventListener("load", () => {
        const toast = document.getElementById("toast");
        if (toast) {
            toast.classList.add("show");
            setTimeout(() => toast.classList.remove("show"), 4000); // Tự ẩn sau 4 giây
        }
    });
</script>

<jsp:include page="common/footer.jsp" />