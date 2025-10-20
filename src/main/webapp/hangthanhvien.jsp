<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.microshop.model.HangThanhVien"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách hạng thành viên</title>
    <style>
        table {
            border-collapse: collapse;
            width: 70%;
            margin: 40px auto;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #4285f4;
            color: white;
        }
    </style>
</head>
<body>
    <h2 style="text-align:center;">Danh sách hạng thành viên</h2>

    <%
        List<HangThanhVien> dsHang = (List<HangThanhVien>) request.getAttribute("dsHang");
        if (dsHang != null && !dsHang.isEmpty()) {
    %>
        <table>
            <tr>
                <th>Mã hạng</th>
                <th>Tên hạng</th>
                <th>Mức chi tiêu tối thiểu</th>
                <th>Chiết khấu (%)</th>
                <th>Icon</th>
            </tr>
            <% for (HangThanhVien h : dsHang) { %>
                <tr>
                    <td><%= h.getMaHang() %></td>
                    <td><%= h.getTenHang() %></td>
                    <td><%= h.getMucChiTieuToiThieu() %></td>
                    <td><%= h.getChietKhau() %></td>
                    <td><img src="<%= h.getDuongDanIcon() %>" alt="icon" width="30"></td>
                </tr>
            <% } %>
        </table>
    <%
        } else {
    %>
        <p style="text-align:center;">Không có dữ liệu hạng thành viên nào.</p>
    <%
        }
    %>
</body>
</html>
