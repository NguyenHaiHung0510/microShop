<%@page import="com.microshop.model.NguoiDung"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="common/header.jsp">
    <jsp:param name="pageTitle" value="Hồ Sơ Của Tôi"/>
</jsp:include>

<%
    // Lấy đối tượng NguoiDung từ Session
    NguoiDung user = (NguoiDung) session.getAttribute("user");
%>
<%
    // Lấy tên hạng thành viên mà ProfileServlet đã chuẩn bị
    String tenHTV = (String) request.getAttribute("HangNguoiDung");
%>

<%-- Sử dụng class .form-wrapper (đã được định nghĩa trong style.css) --%>
<div class="form-wrapper profile-container">
    <h2>Thông Tin Cá Nhân</h2>

    <c:if test="${param.update eq 'success'}">
        <div class="alert-success">Cập nhật hồ sơ thành công!</div>
    </c:if>

    <div class="profile-info">
        <%-- Kiểm tra user null để tránh lỗi nếu truy cập trực tiếp trang profile mà chưa đăng nhập --%>
        <% if (user != null) {%>
        <p><strong>ID Người Dùng:</strong> <%= user.getMaNguoiDung()%></p>
        <p><strong>Tên Đăng Nhập:</strong> <%= user.getTenDangNhap()%></p>
        <p><strong>Email:</strong> <%= (user.getEmail() != null && !user.getEmail().isEmpty()) ? user.getEmail() : "(Chưa cập nhật)"%></p>
        <p><strong>SĐT:</strong> <%= (user.getSoDienThoai() != null && !user.getSoDienThoai().isEmpty()) ? user.getSoDienThoai() : "(Chưa cập nhật)"%></p>
        <p><strong>Tổng tiền đã chi:</strong> <%= user.getTongTienDaChi()%></p>
        <p><strong>Hạng Thành Viên:</strong> <%= (tenHTV != null) ? tenHTV : "(Chưa có hạng)"%></p>
        <% } else { %>
        <p>Lỗi: Không tìm thấy thông tin người dùng. Vui lòng đăng nhập lại.</p>
        <% }%>
    </div>

    <hr>

    <h3>Các Tùy Chọn Khác</h3>
    <div class="profile-links">
        <a href="${pageContext.request.contextPath}/profile/orders">Lịch Sử Đơn Hàng</a>
        <a href="${pageContext.request.contextPath}/profile/view-account">Xem Tài Khoản Đã Mua</a>
    </div>

    <hr>

    <h3>Cập Nhật Thông Tin</h3>
    <div style="text-align: center;">
        <a href="${pageContext.request.contextPath}/profile/edit" class="btn-submit register" style="display: inline-block; text-decoration: none; width: auto; padding: 10px 15px;">
            Chỉnh Sửa Hồ Sơ
        </a>
    </div>

    <p style="text-align: center; margin-top: 20px;">
        <a href="${pageContext.request.contextPath}/home">Quay lại Trang Chủ</a> |
        <a href="${pageContext.request.contextPath}/logout">Đăng Xuất</a>
    </p>
</div>

<jsp:include page="common/footer.jsp" />