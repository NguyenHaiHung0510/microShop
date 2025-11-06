<%@page import="com.microshop.model.NguoiDung"%>
<%@page import="com.microshop.model.TaiKhoan"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Xác Nhận Thanh Toán</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        /* Áp dụng Global Box-Sizing Reset để ngăn tràn */
        * {
            box-sizing: border-box; 
        }
        
        /* CSS Cục bộ cho trang Checkout */
        body {
            font-family: 'Be Vietnam Pro', Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f7f6;
            color: #333;
            line-height: 1.6;
        }
        a {
            text-decoration: none;
            color: inherit;
        }
        
        .checkout-container {
            max-width: 700px;
            margin: 50px auto;
            padding: 30px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }
        .checkout-container h2 {
            text-align: center;
            color: #c92a2a;
            margin-bottom: 25px;
            border-bottom: 2px solid #e0e0e0;
            padding-bottom: 10px;
        }
        
        .order-summary, .payment-options {
            margin-bottom: 30px;
            padding: 15px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .order-summary h3, .payment-options h3 {
            font-weight: 700;
            color: #333;
            margin-top: 0;
            margin-bottom: 15px;
        }
        
        /* Tóm tắt sản phẩm */
        .product-item {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px 0;
            border-bottom: 1px dashed #eee;
            font-size: 1em;
        }
        .total-amount {
            font-size: 1.5em;
            font-weight: 700;
            color: #e74c3c;
            text-align: right;
            margin-top: 15px;
            padding-top: 10px;
            border-top: 2px solid #e0e0e0;
        }
        
        /* Nút xác nhận */
        .confirm-btn, .fake-confirm-btn {
            background-color: #2ecc71;
            color: white;
            padding: 15px;
            border: none;
            border-radius: 4px;
            font-size: 1.2em;
            font-weight: 700;
            cursor: pointer;
            width: 100%;
            transition: background-color 0.2s;
        }
        .confirm-btn:hover, .fake-confirm-btn:hover {
            background-color: #27ae60;
        }
        .fake-confirm-btn {
            background-color: #f39c12; /* Màu cam nổi bật cho QR */
        }
        .secondary-btn {
            background-color: #6c757d; 
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
            margin-top: 10px;
            transition: background-color 0.2s;
        }
        .secondary-btn:hover {
            background-color: #5a6268;
        }


        /* Tùy chọn thanh toán */
        .payment-options label {
            display: block;
            margin-bottom: 10px;
            cursor: pointer;
            padding: 10px;
            border: 1px solid #fff;
            border-radius: 4px;
            transition: background-color 0.2s;
        }
        .payment-options input[type="radio"]:checked + label {
            background-color: #f0f8ff;
            border-color: #b3d4ff;
        }
        .payment-options input[type="radio"] {
            margin-right: 10px;
        }
        .error-message {
            padding: 15px;
            margin-bottom: 20px;
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
            border-radius: 4px;
            text-align: center;
        }
        
        /* CSS cho QR Section */
        .qr-section {
            display: none; /* Mặc định ẩn */
            text-align: center;
            padding: 30px 15px;
            border: 2px solid #2ecc71; 
            border-radius: 8px;
            margin-top: 20px;
        }
        .qr-section img {
            max-width: 200px;
            height: auto;
            margin-bottom: 15px;
            border: 1px solid #ddd;
        }
        .qr-section p {
            font-size: 1.1em;
            font-weight: 500;
            color: #2ecc71;
        }
    </style>
</head>
<body>
    
<c:set var="user" value="${sessionScope.user}" />
<c:set var="sanPham" value="${requestScope.sanPhamThanhToan}" />

<div class="checkout-container">
    <h2>Xác Nhận Thanh Toán</h2>

    <%-- 1. XỬ LÝ LỖI (Hiển thị lỗi nếu sản phẩm null) --%>
    <c:if test="${sanPham == null}">
        <div class="error-message">
            Lỗi: Không tìm thấy thông tin sản phẩm để thanh toán hoặc sản phẩm đã hết hàng.
        </div>
        <p style="text-align: center;"><a href="${pageContext.request.contextPath}/home">Quay lại Trang Chủ</a></p>
    </c:if>

    <%-- 2. KHỐI NỘI DUNG CHÍNH (Chỉ hiển thị khi có sản phẩm) --%>
    <c:if test="${sanPham != null}">

        <div class="order-summary">
            <h3>Sản phẩm:</h3>
            <div class="product-item">
                <span>
                    <strong>Mã TK #${sanPham.maTaiKhoan}</strong>: ${sanPham.diemNoiBat}
                </span>
                <span style="font-weight: 600;">
                    <fmt:formatNumber value="${sanPham.giaBan}" type="currency" currencyCode="VND" maxFractionDigits="0" /> VNĐ
                </span>
            </div>
            
            <div class="total-amount">
                Tổng cộng: 
                <fmt:formatNumber value="${sanPham.giaBan}" type="currency" currencyCode="VND" maxFractionDigits="0" /> VNĐ
            </div>
        </div>
        
        <div class="order-summary">
            <h3>Thông tin người mua:</h3>
            <p><strong>Người dùng:</strong> ${user.tenDangNhap}</p>
            <p><strong>Email nhận hàng:</strong> ${user.email}</p>
            <p style="font-size: 0.9em; color: #555;">Tài khoản game sẽ được gửi đến email này sau khi thanh toán thành công.</p>
        </div>

        <form id="checkoutForm" action="${pageContext.request.contextPath}/payment/success" method="POST">
            
            <input type="hidden" name="maTaiKhoan" value="${sanPham.maTaiKhoan}">
            <input type="hidden" name="gia" value="${sanPham.giaBan}">
            <input type="hidden" id="finalMethod" name="paymentMethod" value=""> 
            
            <div id="selectionBlock">
                <div class="payment-options">
                    <h3>Chọn phương thức thanh toán:</h3>
                    
                    <div>
                        <input type="radio" id="momo" name="tempMethod" value="MOMO" required>
                        <label for="momo">Thanh toán qua Ví Momo</label>
                    </div>
                    
                    <div>
                        <input type="radio" id="bank" name="tempMethod" value="BANK">
                        <label for="bank">Chuyển khoản Ngân hàng (Vietcombank, Techcombank,...)</label>
                    </div>
                </div>
                
                <%-- Nút này dùng để KÍCH HOẠT HIỂN THỊ QR --%>
                <button type="button" id="initiatePaymentBtn" class="confirm-btn">TIẾN HÀNH THANH TOÁN</button>
            </div>
            
            <%-- KHU VỰC HIỂN THỊ QR CODE (ẨN) --%>
            <div id="qrPaymentBlock" class="qr-section">
                <h3>Vui lòng quét mã QR để thanh toán</h3>
                <img src="${pageContext.request.contextPath}/images/sample_qr.png" alt="Mã QR Thanh Toán Giả Lập"> 
                <p>Tổng tiền cần thanh toán: <span id="displayTotal" style="color: #c92a2a;">
                    <fmt:formatNumber value="${sanPham.giaBan}" type="currency" currencyCode="VND" maxFractionDigits="0" /> VNĐ</span>
                </p>
                <p style="color: red; font-size: 0.9em; margin-top: 10px;">(Đây là bước giả lập. Vui lòng nhấn Xác nhận)</p>

                <%-- Nút submit cuối cùng --%>
                <button type="submit" class="confirm-btn fake-confirm-btn" onclick="return setFinalMethod()">
                    XÁC NHẬN ĐÃ THANH TOÁN
                </button>
                
                <%-- NÚT TRỞ VỀ ĐÃ BỔ SUNG --%>
                <button type="button" id="goBackBtn" class="secondary-btn">
                    ← Chọn lại phương thức
                </button>
            </div>
            
        </form>
    </c:if>
</div>

<script>
    document.getElementById('initiatePaymentBtn').addEventListener('click', function() {
        const selectedMethod = document.querySelector('input[name="tempMethod"]:checked');
        
        if (selectedMethod) {
            // Lưu phương thức đã chọn vào trường ẩn
            document.getElementById('finalMethod').value = selectedMethod.value;

            // Hiển thị khối QR và ẩn khối lựa chọn
            document.getElementById('selectionBlock').style.display = 'none';
            document.getElementById('qrPaymentBlock').style.display = 'block';

        } else {
            alert('Vui lòng chọn một phương thức thanh toán.');
        }
    });
    
    // GẮN SỰ KIỆN CHO NÚT TRỞ VỀ
    document.getElementById('goBackBtn').addEventListener('click', function() {
        // Ẩn khối QR và hiển thị khối lựa chọn
        document.getElementById('selectionBlock').style.display = 'block';
        document.getElementById('qrPaymentBlock').style.display = 'none';
    });

    function setFinalMethod() {
        return true; 
    }
</script>

</body>
</html>