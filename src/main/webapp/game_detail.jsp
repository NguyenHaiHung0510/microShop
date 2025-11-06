<%@page import="com.microshop.model.TaiKhoan"%>
<%@page import="com.microshop.model.TaiKhoanLienQuan"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
    
    <%-- KHỐI JSTL DỊCH TÊN DANH MỤC --%>
    <c:choose>
        <c:when test="${category eq 'lienquan'}"><c:set var="categoryName" value="Liên Quân" /></c:when>
        <c:when test="${category eq 'freefire'}"><c:set var="categoryName" value="Free Fire" /></c:when>
        <c:when test="${category eq 'riot'}"><c:set var="categoryName" value="Riot Games (LMHT/Valorant)" /></c:when>
        <c:when test="${category eq 'steam'}"><c:set var="categoryName" value="Steam" /></c:when>
        <c:otherwise><c:set var="categoryName" value="Tài Khoản Game" /></c:otherwise>
    </c:choose>

    <title>Chi Tiết ${categoryName}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"> 
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
    <style>
        * {
            box-sizing: border-box; 
            /* Áp dụng mô hình hộp tiêu chuẩn cho mọi phần tử */
        }
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f8f8f8; /* Nền trắng xám đơn giản */
            color: #333;
            line-height: 1.6;
            margin: 0;
            padding: 0;
        }

        .detail-container {
            max-width: 960px;
            margin: 30px auto;
            padding: 25px;
            background-color: #ffffff;
            border-radius: 6px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* Shadow nhẹ nhàng */
            display: flex;
            flex-wrap: wrap;
            gap: 30px; 
        }

        .image-gallery {
            flex: 1;
            min-width: 280px; 
        }
        .main-image {
            width: 100%;
            border-radius: 4px;
            object-fit: contain;
        }

        .product-details {
            flex: 2;
            min-width: 350px;
        }
        .product-details h1 {
            font-size: 2em;
            color: #333; 
            margin-bottom: 15px;
            border-bottom: 1px solid #ddd;
            padding-bottom: 10px;
        }
        .product-details h3 {
            font-size: 1.3em;
            color: #555;
            margin-top: 20px;
            margin-bottom: 10px;
        }

        .description {
            font-size: 1em;
            color: #555;
            margin-bottom: 15px;
            padding: 10px;
            border-left: 3px solid #c92a2a; /* Giữ lại điểm nhấn */
            background-color: #fefefe;
        }

        .price-section {
            padding: 15px;
            background-color: #f0f8ff; /* Nền xanh nhạt làm nổi bật giá */
            border-radius: 4px;
            margin: 15px 0;
            display: flex;
            align-items: center; 
            gap: 15px;
        }
        .price-label {
            font-size: 1.1em;
            font-weight: 500;
        }
        .old-price-detail {
            text-decoration: line-through;
            color: #999;
            font-size: 1.1em;
        }
        .new-price-detail {
            color: #e74c3c; 
            font-size: 2em; 
            font-weight: 700;
        }

        .buy-now-btn {
            background-color: #28a745; 
            color: white;
            padding: 12px 30px;
            border: none;
            border-radius: 4px; 
            font-size: 1.1em;
            font-weight: 600;
            width: 100%;
            margin-top: 15px;
            cursor: pointer;
            transition: background-color 0.2s;
        }
        .buy-now-btn:hover {
            background-color: #218838;
        }
        
        .status-info {
            text-align: center; 
            margin-top: 10px;
            font-size: 1em;
        }
        .status-info span {
            font-weight: 700;
        }
        .status-info .available {
            color: #28a745; 
        }
        .status-info .sold-out {
            color: #dc3545;
        }

        .specs-table {
            width: 100%;
            border-collapse: collapse; /* Đơn giản hóa bảng */
            margin-top: 15px;
        }
        .specs-table th, .specs-table td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #eee;
        }
        .specs-table th {
            background-color: #f4f4f4;
            color: #333;
            font-weight: 500;
            width: 40%; 
        }
        .specs-table tr:hover {
             background-color: #f9f9f9;
        }

        .back-link {
            display: block;
            margin-top: 30px;
            font-size: 1em;
            color: #007bff;
            text-decoration: none;
            transition: color 0.2s;
        }
        .back-link:hover {
            text-decoration: underline;
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            .detail-container {
                flex-direction: column;
                margin: 15px;
                padding: 15px;
            }
            .image-gallery, .product-details {
                width: 100%;
            }
            .product-details h1 {
                font-size: 1.6em;
            }
        }
    </style>
</head>
<body>

<c:set var="tk" value="${requestScope.taiKhoanChiTiet}" />
<c:set var="category" value="${requestScope.category}" />
<c:set var="categoryName" value="${categoryName}" />

<div class="detail-container">
    <div class="image-gallery">
        <img src="${pageContext.request.contextPath}/${tk.duongDanAnh}" alt="Ảnh Tài Khoản" class="main-image">
    </div>

    <div class="product-details">
        <h1>Chi Tiết Tài Khoản ${categoryName} - Mã #${tk.maTaiKhoan}</h1>
        
        <p class="description">
            ${tk.diemNoiBat}
        </p>

        <div class="price-section">
            <span class="price-label">Giá:</span>
            <span class="old-price-detail"><fmt:formatNumber value="${tk.giaGoc}" type="currency" currencyCode="VND" maxFractionDigits="0" /> VNĐ</span>
            <span class="new-price-detail"><fmt:formatNumber value="${tk.giaBan}" type="currency" currencyCode="VND" maxFractionDigits="0" /> VNĐ</span>
        </div>

        <c:choose>
            <c:when test="${tk.trangThai eq 'DANG_BAN'}">
                <%-- TRẠNG THÁI: CÒN HÀNG (Cho phép mua) --%>
                <a href="${pageContext.request.contextPath}/payment/execute?type=${category}&id=${tk.maTaiKhoan}" 
                   class="buy-now-btn"
                   style="text-align: center; display: block; text-decoration: none;">
                    MUA NGAY
                </a>
            </c:when>
            <c:otherwise>
                <%-- TRẠNG THÁI: ĐÃ BÁN / DA_HUY (Vô hiệu hóa) --%>
                <span class="buy-now-btn" 
                      style="text-align: center; display: block; text-decoration: none; 
                             background-color: #dc3545; /* Màu đỏ mờ */
                             cursor: not-allowed;
                             pointer-events: none; /* Vô hiệu hóa click */">
                    ĐÃ BÁN (HẾT HÀNG)
                </span>
            </c:otherwise>
        </c:choose>
        <p class="status-info">
            Trạng thái: 
            <span class="${tk.trangThai eq 'DANG_BAN' ? 'available' : 'sold-out'}">
                ${tk.trangThai eq 'DANG_BAN' ? 'CÒN HÀNG' : 'ĐÃ BÁN'}
            </span>
        </p>
        
        <hr style="margin: 20px 0; border-top: 1px solid #e0e0e0;">

        <h3>Thông số chi tiết</h3>
        <table class="specs-table">
            
            <tr><th>Mã Tài Khoản</th><td>${tk.maTaiKhoan}</td></tr>
            
            <c:choose>
                <c:when test="${category eq 'lienquan'}">
                    <tr><th>Số Tướng</th><td>${tk.soTuong}</td></tr>
                    <tr><th>Số Trang Phục</th><td>${tk.soTrangPhuc}</td></tr>
                </c:when>
                <c:otherwise>
                    <tr><td colspan="2">Thông số chi tiết cho danh mục này chưa được định nghĩa.</td></tr>
                </c:otherwise>
            </c:choose>
        </table>
        
        <a href="${pageContext.request.contextPath}/home" class="back-link">← Quay lại Trang Chủ</a>
    </div>
</div>

</body>
</html>