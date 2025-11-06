<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>
        <c:choose>
            <c:when test="${category == 'lienquan'}">Chi tiết tài khoản Liên Quân</c:when>
            <c:when test="${category == 'freefire'}">Chi tiết tài khoản Free Fire</c:when>
            <c:when test="${category == 'riot'}">Chi tiết tài khoản Riot Games</c:when>
            <c:otherwise>Chi tiết tài khoản</c:otherwise>
        </c:choose>
    </title>

    <style>
        body { font-family: Arial, sans-serif; background-color: #f5f5f5; margin: 40px; }
        .container {
            max-width: 1000px; margin: 0 auto; background: white;
            border-radius: 16px; box-shadow: 0 3px 12px rgba(0,0,0,0.15);
            padding: 25px;
        }
        h2 { margin-bottom: 10px; font-size: 26px; text-transform: none; }
        .gallery {
            display: flex; flex-wrap: wrap; gap: 12px; margin-top: 15px;
        }
        .gallery img {
            width: 180px; height: 180px; object-fit: cover;
            border-radius: 10px; border: 1px solid #ddd;
            transition: transform 0.2s;
        }
        .gallery img:hover { transform: scale(1.05); }
        .info { margin-top: 25px; }
        .info p { margin: 6px 0; font-size: 16px; }
        .price {
            color: #c62828; font-weight: bold;
            font-size: 20px; margin-top: 10px;
        }
        .actions {
            margin-top: 30px; display: flex; gap: 20px;
        }
        .btn {
            display: inline-block;
            padding: 10px 18px;
            border-radius: 8px;
            text-decoration: none;
            font-weight: bold;
            transition: background-color 0.2s;
        }
        .btn-buy { background-color: #007bff; color: white; }
        .btn-buy:hover { background-color: #0056b3; }
        .btn-back { background-color: #e0e0e0; color: #333; }
        .btn-back:hover { background-color: #bdbdbd; }
        .highlight {
            background: #fafafa; padding: 15px;
            border-radius: 10px; margin-top: 15px;
        }
    </style>
</head>
<body>

<div class="container">

    <h2>
        <c:choose>
            <c:when test="${category == 'lienquan'}">Chi tiết tài khoản Liên Quân</c:when>
            <c:when test="${category == 'freefire'}">Chi tiết tài khoản Free Fire</c:when>
            <c:when test="${category == 'riot'}">Chi tiết tài khoản Riot Games</c:when>
            <c:otherwise>Chi tiết tài khoản</c:otherwise>
        </c:choose>
    </h2>

    <!-- Thư viện ảnh -->
    <div class="gallery">
        <c:choose>
            <c:when test="${not empty dsAnh}">
                <c:forEach var="anh" items="${dsAnh}">
                    <img src="${anh.duongDanAnh}" alt="Ảnh tài khoản">
                </c:forEach>
            </c:when>
            <c:otherwise>
                <img src="${pageContext.request.contextPath}/images/noimage.png" alt="Không có ảnh">
            </c:otherwise>
        </c:choose>
    </div>

    <!-- Thông tin cơ bản -->
    <div class="info">
        <p><b>Mã tài khoản:</b> ${taiKhoan.maTaiKhoan}</p>
        <p><b>Tên đăng nhập:</b> ${taiKhoan.tenDangNhap}</p>
        <p><b>Mật khẩu:</b> ${taiKhoan.matKhau}</p>
        <p><b>Giá bán:</b> <span class="price">${taiKhoan.giaBan} VNĐ</span></p>
        <p><b>Trạng thái:</b> ${taiKhoan.trangThai}</p>

        <!-- Phần chi tiết riêng cho từng loại -->
        <div class="highlight">
            <c:choose>
                <c:when test="${category == 'lienquan'}">
                    <p><b>Rank:</b> ${taiKhoan.hangRank}</p>
                    <p><b>Số tướng:</b> ${taiKhoan.soTuong}</p>
                    <p><b>Số trang phục:</b> ${taiKhoan.soTrangPhuc}</p>
                    <p><b>Bậc ngọc:</b> ${taiKhoan.bacNgoc}</p>
                    <p><b>Loại đăng ký:</b> ${taiKhoan.loaiDangKy}</p>
                </c:when>

                <c:when test="${category == 'freefire'}">
                    <p><b>Rank:</b> ${taiKhoan.hangRank}</p>
                    <p><b>Có Thẻ Vô Cực:</b>
                        <c:choose>
                            <c:when test="${taiKhoan.coTheVoCuc}">Có</c:when>
                            <c:otherwise>Không</c:otherwise>
                        </c:choose>
                    </p>
                    <p><b>Số skin súng:</b> ${taiKhoan.soSkinSung}</p>
                    <p><b>Loại đăng ký:</b> ${taiKhoan.loaiDangKy}</p>
                </c:when>

                <c:when test="${category == 'riot'}">
                    <p><b>Cấp độ tài khoản:</b> ${taiKhoan.capDoRiot}</p>
                    <p><b>Số tướng LMHT:</b> ${taiKhoan.soTuongLMHT}</p>
                    <p><b>Số trang phục LMHT:</b> ${taiKhoan.soTrangPhucLMHT}</p>
                    <p><b>Số đa sắc LMHT:</b> ${taiKhoan.soDaSacLMHT}</p>
                    <p><b>Số biểu cảm LMHT:</b> ${taiKhoan.soBieuCamLMHT}</p>
                    <p><b>Số biểu tượng LMHT:</b> ${taiKhoan.soBieuTuongLMHT}</p>
                    <p><b>Hạng:</b> ${taiKhoan.hangRankLMHT}</p>
                    <p><b>Khung Rank:</b> ${taiKhoan.khungRankLMHT}</p>
                    <p><b>Đấu trường chân lý:</b></p>
                    <ul>
                        <li>Số thú cưng: ${taiKhoan.soThuCungTFT}</li>
                        <li>Số sàn đấu: ${taiKhoan.soSanDauTFT}</li>
                        <li>Số chương lực: ${taiKhoan.soChuongLucTFT}</li>
                    </ul>
                </c:when>

                <c:otherwise>
                    <p>Không tìm thấy thông tin chi tiết cho loại tài khoản này.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <!-- Nút hành động -->
    <div class="actions">
        <a class="btn btn-buy"
           href="${pageContext.request.contextPath}/payment/execute?type=game&id=${taiKhoan.maTaiKhoan}">
            Mua ngay
        </a>

        <a class="btn btn-back"
           href="${pageContext.request.contextPath}/shop/game?category=${category}">
            Quay lại
        </a>
    </div>
</div>

</body>
</html>