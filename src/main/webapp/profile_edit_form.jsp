<%@page import="com.microshop.model.NguoiDung"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="common/header.jsp">
    <jsp:param name="pageTitle" value="Chỉnh Sửa Hồ Sơ"/>
</jsp:include>

<%
    // Lấy đối tượng NguoiDung từ Session
    NguoiDung user = (NguoiDung) session.getAttribute("user");
%>

<%-- Sử dụng class .form-wrapper (đã được định nghĩa trong style.css) --%>
<div class="form-wrapper profile-container">
    <h2>Chỉnh Sửa Hồ Sơ</h2>

    <c:if test="${requestScope.errorEditMessage != null}">
        <div class="alert-danger">${requestScope.errorEditMessage}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/profile/edit" method="POST">

        <div class="form-group">
            <label for="username">Tên Đăng Nhập:</label>
            <input type="text" id="username" name="username" class="form-control" 
                   value="<%= user.getTenDangNhap() != null ? user.getTenDangNhap() : ""%>" disabled
                   style="margin-bottom: 5px;"> 
            <span class="note" style="margin-top: 0;">Tên đăng nhập không thể thay đổi.</span>
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" class="form-control" 
                   value="<%= (user.getEmail() != null) ? user.getEmail() : ""%>">
        </div>

        <hr>

        <div class="form-group">
            <label for="sdt">Số Điện Thoại:</label>
            <input type="tel" id="sdt" name="sdt" class="form-control" 
                   value="<%= (user.getSoDienThoai() != null) ? user.getSoDienThoai() : ""%>">
        </div>

        <hr>

        <h3>Thay Đổi Mật Khẩu (Nếu cần)</h3>
        <div class="form-group">
            <label for="old_password">Mật Khẩu Hiện Tại:</label>
            <input type="password" id="old_password" name="old_password" class="form-control">
            <span class="note" style="margin-top: 5px;">Bắt buộc nhập nếu bạn muốn thay đổi mật khẩu mới.</span>
        </div>
        <div class="form-group">
            <label for="new_password">Mật Khẩu Mới:</label>
            <input type="password" id="new_password" name="new_password" class="form-control"
                   style="margin-bottom: 5px;">  
            <span class="note" style="margin-top: 0;">Để trống nếu bạn không muốn thay đổi mật khẩu.</span>
        </div>
        <div class="form-group">
            <label for="confirm_password">Xác Nhận Mật Khẩu Mới:</label>
            <input type="password" id="confirm_password" name="confirm_password" class="form-control">
        </div>

        <button type="submit" class="btn-submit register">Lưu Thay Đổi</button>
    </form>

    <p style="text-align: center; margin-top: 20px;">
        <a href="${pageContext.request.contextPath}/profile">Quay lại Hồ Sơ</a>
    </p>
</div>

<jsp:include page="common/footer.jsp" />