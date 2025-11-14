<%@page import="com.microshop.model.NguoiDung"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="common/header.jsp">
    <jsp:param name="pageTitle" value="Tài Khoản Đã Mua"/>
</jsp:include>

<div class="container">
    <h2>Tài Khoản Đã Mua Của Bạn</h2>

    <c:if test="${empty requestScope.purchasedAccounts and empty requestScope.steamAccounts}">
        <p>Bạn chưa mua tài khoản nào.</p>
    </c:if>

    <c:if test="${not empty requestScope.purchasedAccounts}">
        <table class="account-table">
            <thead>
                <tr>
                    <th>Mã TK</th>
                    <th>Nổi bật</th>
                    <th>Tài khoản (Game)</th>
                    <th>Mật khẩu (Game)</th>
                </tr>
            </thead>
            <tbody>
                <%-- Lặp qua danh sách tài khoản đã mua --%>
                <c:forEach var="tk" items="${requestScope.purchasedAccounts}">
                    <tr>
                        <td>#${tk.maTaiKhoan}</td>
                        <td>${tk.diemNoiBat}</td>
                        
                        <%-- 
                           HIỂN THỊ THÔNG TIN NHẠY CẢM
                           Giả định Model của bạn (ví dụ: TaiKhoanLienQuan) 
                           có các getter cho Tên đăng nhập và Mật khẩu của game.
                        --%>
                        <td class="sensitive-data">${tk.tenDangNhap}</td>
                        <td class="sensitive-data">${tk.matKhau}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div style="background-color: #fff3cd; padding: 15px; margin-top: 20px; border: 1px solid #ffeeba; border-radius: 4px;">
            <strong>Cảnh báo:</strong> Vì lý do bảo mật, vui lòng đổi mật khẩu ngay sau khi nhận tài khoản!
        </div>

    </c:if>
    <c:if test="${not empty requestScope.steamAccounts}">
        <table class="account-table">
            <thead>
                <tr>
                    <th>Mã TK</th>
                    <th>Tên Game</th>
                    <th>Tài khoản (Steam)</th>
                    <th>Mật khẩu (Steam)</th>
                </tr>
            </thead>
            <tbody>
                <%-- Lặp qua danh sách tài khoản đã mua --%>
                <c:forEach var="tk" items="${requestScope.steamAccounts}">
                    <tr>
                        <td>Steam#${tk.maTaiKhoan}</td>
                        <td>${tk.tenGame}</td>
                        
                        <%-- 
                           HIỂN THỊ THÔNG TIN NHẠY CẢM
                           Giả định Model của bạn (ví dụ: TaiKhoanLienQuan) 
                           có các getter cho Tên đăng nhập và Mật khẩu của game.
                        --%>
                        <td class="sensitive-data">${tk.tenDangNhap}</td>
                        <td class="sensitive-data">${tk.matKhau}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <div style="background-color: #fff3cd; padding: 15px; margin-top: 20px; border: 1px solid #ffeeba; border-radius: 4px;">
            <strong>Cảnh báo:</strong> Vì lý do bảo mật, vui lòng đổi mật khẩu ngay sau khi nhận tài khoản!
        </div>
        
    </c:if>
    <a href="${pageContext.request.contextPath}/profile" class="back-link">← Quay lại Hồ Sơ</a>
</div>

<jsp:include page="common/footer.jsp" />