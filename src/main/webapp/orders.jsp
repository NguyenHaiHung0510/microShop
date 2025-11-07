<%@page import="com.microshop.model.NguoiDung"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.time.format.DateTimeFormatter"%> <%-- THÊM IMPORT NÀY --%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Lịch Sử Đơn Hàng</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <style>
        /* CSS Cục bộ cho bảng lịch sử đơn hàng */
        * { box-sizing: border-box; }
        body {
            font-family: 'Be Vietnam Pro', Arial, sans-serif;
            margin: 0; padding: 0;
            background-color: #f4f7f6;
            color: #333;
        }
        .container {
            max-width: 960px;
            margin: 30px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.05);
        }
        .container h2 {
            text-align: center;
            color: #c92a2a;
            margin-bottom: 25px;
        }
        .order-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        .order-table th, .order-table td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #eee;
        }
        .order-table th {
            background-color: #f4f4f4;
            font-weight: 600;
        }
        .order-table tr:hover {
            background-color: #f9f9f9;
        }
        .status-CHO_THANH_TOAN, .status-DA_HUY {
            color: #f39c12; /* Vàng */
            font-weight: 500;
        }
        .status-DA_HOAN_THANH {
            color: #28a745; /* Xanh lá */
            font-weight: 500;
        }
        .status-DA_HUY {
            color: #dc3545; /* Đỏ */
            text-decoration: line-through;
        }
        .action-link {
            color: #007bff;
            text-decoration: none;
            font-weight: 500;
        }
        .action-link:hover {
            text-decoration: underline;
        }
        .back-link {
            display: inline-block;
            margin-top: 20px;
            font-weight: 500;
        }
    </style>
</head>
<body>
    
<div class="container">
    <h2>Lịch Sử Đơn Hàng</h2>

    <%-- Kiểm tra nếu danh sách trống --%>
    <c:if test="${empty requestScope.orderList}">
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
            </tbody>
        </table>
    </c:if>
    
    <a href="${pageContext.request.contextPath}/profile" class="back-link">← Quay lại Hồ Sơ</a>
</div>

</body>
</html>