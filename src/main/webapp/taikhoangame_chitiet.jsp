<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="common/header.jsp">
    <jsp:param name="pageTitle" value="Chi tiết tài khoản"/>
</jsp:include>

<style>
    body {
        background-color: #f5f6fa;
        font-family: 'Segoe UI', Arial, sans-serif;
    }

    .product-section {
        max-width: 1000px;
        margin: 50px auto;
        padding: 20px;
        background: #fff;
        border-radius: 16px;
        box-shadow: 0 4px 12px rgba(0,0,0,0.08);
    }

    .product-section h2 {
        text-align: center;
        color: #333;
        font-size: 28px;
        font-weight: 700;
        margin-bottom: 30px;
    }

    .product-grid {
        display: flex;
        justify-content: center;
    }

    .product-card {
        width: 100%;
    }

    /* --- Thư viện ảnh --- */
    .product-image {
        display: flex;
        flex-wrap: wrap;
        gap: 15px;
        justify-content: center;
        padding: 10px 0;
    }

    .product-image div {
        width: 180px;
        height: 180px;
        border-radius: 12px;
        overflow: hidden;
        box-shadow: 0 2px 6px rgba(0,0,0,0.1);
        transition: transform 0.25s ease;
    }

    .product-image div:hover {
        transform: scale(1.05);
    }

    .product-image img {
        width: 100%;
        height: 100%;
        object-fit: cover;
        display: block;
    }

    /* --- Thông tin --- */
    .product-info {
        margin-top: 25px;
        text-align: left;
    }

    .product-title {
        font-size: 22px;
        color: #2c3e50;
        text-align: center;
        font-weight: bold;
        margin-bottom: 15px;
    }

    .product-price {
        text-align: center;
        margin-bottom: 20px;
    }

    .product-price .old-price {
        color: #888;
        text-decoration: line-through;
        margin-right: 10px;
        font-size: 16px;
    }

    .product-price .new-price {
        color: #e74c3c;
        font-weight: bold;
        font-size: 20px;
    }

    .highlight {
        background: #f9fafc;
        padding: 20px;
        border-radius: 12px;
        line-height: 1.7;
        color: #333;
        font-size: 15px;
        border: 1px solid #eee;
    }

    .highlight p {
        margin: 6px 0;
    }

    .highlight ul {
        margin: 8px 0 0 25px;
        list-style: disc;
    }

    /* --- Nút hành động --- */
    .actions {
        margin-top: 30px;
        display: flex;
        justify-content: center;
        gap: 20px;
        /* Thêm hỗ trợ xuống dòng cho thông báo lỗi */
        flex-wrap: wrap;
    }

    .buy-button {
        display: inline-block;
        padding: 12px 28px;
        border-radius: 10px;
        font-size: 16px;
        font-weight: 600;
        text-decoration: none;
        color: white;
        background: #007bff;
        transition: 0.3s;
        text-align: center; /* Đảm bảo text căn giữa */
    }

    .buy-button:hover {
        background: #0056b3;
        transform: translateY(-2px);
    }

    .buy-button.secondary {
        background: #ccc;
        color: #333;
    }

    .buy-button.secondary:hover {
        background: #bbb;
    }
</style>

<section class="product-section">
    <h2>
        <c:choose>
            <c:when test="${category == 'lienquan'}">Chi tiết tài khoản Liên Quân</c:when>
            <c:when test="${category == 'freefire'}">Chi tiết tài khoản Free Fire</c:when>
            <c:when test="${category == 'riot'}">Chi tiết tài khoản Riot Games</c:when>
            <c:otherwise>Chi tiết tài khoản</c:otherwise>
        </c:choose>
    </h2>

    <div class="product-grid">
        <div class="product-card">

            <div class="product-image">
                <c:choose>
                    <c:when test="${not empty dsAnh}">
                        <c:forEach var="anh" items="${dsAnh}">
                            <div>
                                <img src="${pageContext.request.contextPath}/${anh.duongDanAnh}" alt="Ảnh tài khoản">
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div>
                            <img src="${pageContext.request.contextPath}/images/noimage.png" alt="Không có ảnh">
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="product-info">
                <h3 class="product-title">
                    ${not empty taiKhoan.diemNoiBat ? taiKhoan.diemNoiBat : "Tài khoản game"}
                </h3>

                <div class="product-price">
                    <span class="old-price">${taiKhoan.giaGoc} VNĐ</span>
                    <span class="new-price">${taiKhoan.giaBan} VNĐ</span>
                </div>

                <div class="highlight">
                    <p><b>Mã tài khoản:</b> ${taiKhoan.maTaiKhoan}</p>
                    <p><b>Trạng thái:</b> ${taiKhoan.trangThai}</p>

                    <hr style="margin:12px 0; border:none; border-top:1px solid #ddd;">

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
                            <p>Không có thông tin chi tiết cho loại tài khoản này.</p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <div class="actions">
                <c:choose>
                    <%-- TRẠNG THÁI: ĐANG GIAO DỊCH (Redirect từ PaymentExecuteServlet) --%>
                    <c:when test="${param.status eq 'in_transaction'}">
                        <span class="buy-button" 
                              style="background-color: #f39c12; cursor: not-allowed; pointer-events: none;">
                            ĐANG CÓ NGƯỜI THANH TOÁN
                        </span>
                        <%-- Thêm thông báo lỗi rõ ràng bên dưới nút --%>
                        <div style="color: red; text-align: center; margin-top: 10px; font-weight: bold; width: 100%;">
                            Tài khoản này đang được giữ, vui lòng thử lại.
                        </div>
                    </c:when>

                    <%-- TRẠNG THÁI: CÒN HÀNG (Cho phép mua) --%>
                    <c:when test="${taiKhoan.trangThai eq 'DANG_BAN'}">
                        <a href="${pageContext.request.contextPath}/payment/execute?type=${category}&id=${taiKhoan.maTaiKhoan}"
                           class="buy-button">Mua ngay</a>
                    </c:when>

                    <%-- TRẠNG THÁI: ĐÃ BÁN (Redirect 'sold' hoặc 'DA_BAN' từ DB) --%>
                    <c:otherwise>
                        <span class="buy-button" 
                              style="background-color: #dc3545; cursor: not-allowed; pointer-events: none;">
                            ĐÃ BÁN (HẾT HÀNG)
                        </span>
                    </c:otherwise>
                </c:choose>

                <%-- Nút quay lại (Giữ nguyên) --%>
                <a href="${pageContext.request.contextPath}/shop/game?category=${category}"
                   class="buy-button secondary">Quay lại</a>
            </div>

        </div>
    </div>
</section>

<script>
    // Chạy script này sau khi trang đã tải xong
    document.addEventListener('DOMContentLoaded', function () {

        // 1. Lấy các tham số URL hiện tại
        const urlParams = new URLSearchParams(window.location.search);

        // 2. Kiểm tra xem tham số 'status' có tồn tại không
        if (urlParams.has('status')) {

            // 3. Xóa tham số 'status'
            urlParams.delete('status');

            // 4. Lấy đường dẫn URL mới (đã xóa tham số 'status')
            let newRelativePathQuery = window.location.pathname;

            // Nối lại các tham số còn lại (nếu có)
            if (urlParams.toString()) {
                newRelativePathQuery += '?' + urlParams.toString();
            }

            // 5. CẬP NHẬT LỊCH SỬ TRÌNH DUYỆT
            // Thay thế URL hiện tại bằng URL mới (đã được làm sạch)
            // Lần refresh (F5) tiếp theo sẽ sử dụng URL mới này.
            history.replaceState(null, '', newRelativePathQuery);
        }
    });
</script>

<jsp:include page="common/footer.jsp" />