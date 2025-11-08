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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <style>
        * { box-sizing: border-box; }
        body {
            font-family: 'Be Vietnam Pro', Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f7f6;
            color: #333;
        }
        .checkout-container {
            max-width: 700px;
            margin: 50px auto;
            padding: 30px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
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
            margin-top: 0;
        }
        .product-item {
            display: flex;
            justify-content: space-between;
            padding: 10px 0;
            border-bottom: 1px dashed #eee;
        }
        .total-amount {
            font-size: 1.5em;
            font-weight: 700;
            color: #e74c3c;
            text-align: right;
            margin-top: 15px;
            border-top: 2px solid #e0e0e0;
            padding-top: 10px;
        }
        .confirm-btn, .fake-confirm-btn {
            background-color: #2ecc71;
            color: white;
            padding: 15px;
            border: none;
            border-radius: 4px;
            font-size: 1.2em;
            cursor: pointer;
            width: 100%;
            margin-top: 10px;
        }
        .fake-confirm-btn { background-color: #f39c12; }
        .secondary-btn {
            background-color: #6c757d; 
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
            margin-top: 10px;
        }
        .qr-section {
            display: none;
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
        .countdown {
            font-size: 1.1em;
            font-weight: 600;
            color: #e74c3c;
            margin-bottom: 10px;
        }
        .expired {
            color: #c0392b;
            font-weight: bold;
        }
        .back-to-home {
            display: block;
            text-align: center;
            margin-top: 20px;
            margin-bottom: 20px;
            color: #007bff;
            text-decoration: none;
            font-weight: 500;
        }
        .back-to-home:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<c:set var="user" value="${sessionScope.user}" />
<c:set var="sanPham" value="${requestScope.sanPhamThanhToan}" />
<c:set var="thoiGianConLaiGiay" value="${requestScope.thoiGianConLai}" />
<div class="checkout-container">
    <h2>Xác Nhận Thanh Toán</h2>

    <c:if test="${sanPham == null}">
        <div class="error-message">
            Lỗi: Không tìm thấy thông tin sản phẩm để thanh toán hoặc sản phẩm đã hết hàng.
        </div>
        <p style="text-align:center;"><a href="${pageContext.request.contextPath}/home">Quay lại Trang Chủ</a></p>
    </c:if>

    <c:if test="${sanPham != null}">
        <div class="order-summary">
            <h3>Sản phẩm:</h3>
            <div class="product-item">
                <span><strong>Mã TK #${sanPham.maTaiKhoan}</strong>: ${sanPham.diemNoiBat}</span>
                <span style="font-weight:600;">
                    <fmt:formatNumber value="${sanPham.giaBan}" type="currency" currencyCode="VND" maxFractionDigits="0"/> VNĐ
                </span>
            </div>
            <div class="total-amount">
                Tổng cộng: 
                <fmt:formatNumber value="${sanPham.giaBan}" type="currency" currencyCode="VND" maxFractionDigits="0"/> VNĐ
            </div>
        </div>

        <div class="order-summary">
            <h3>Thông tin người mua:</h3>
            <p><strong>Người dùng:</strong> ${user.tenDangNhap}</p>
            <p><strong>Email nhận hàng:</strong> ${user.email}</p>
            <p style="font-size:0.9em;color:#555;">Tài khoản game sẽ được gửi đến email này sau khi thanh toán thành công.</p>
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
                <button type="button" id="initiatePaymentBtn" class="confirm-btn">TIẾN HÀNH THANH TOÁN</button>
            </div>

            <div id="qrPaymentBlock" class="qr-section">
                <h3>Vui lòng quét mã QR để thanh toán</h3>
                <div class="countdown">Thời gian còn lại: <span id="timer"></span></div>
                <img src="${pageContext.request.contextPath}/assets/images/sample_qr.png" alt="Mã QR Thanh Toán Giả Lập"> 
                <p>Tổng tiền cần thanh toán: 
                    <span style="color:#c92a2a;">
                        <fmt:formatNumber value="${sanPham.giaBan}" type="currency" currencyCode="VND" maxFractionDigits="0"/> VNĐ
                    </span>
                </p>
                <p style="color:red;font-size:0.9em;margin-top:10px;">(Đây là bước giả lập. Vui lòng nhấn Xác nhận)</p>

                <button type="submit" id="confirmBtn" class="confirm-btn fake-confirm-btn" onclick="return setFinalMethod()">XÁC NHẬN ĐÃ THANH TOÁN</button>
                <button type="button" id="goBackBtn" class="secondary-btn">← Chọn lại phương thức</button>
            </div>
        </form>             
    </c:if>
        
    <a href="${pageContext.request.contextPath}/home" class="back-to-home">
        ← Quay về Trang chủ
    </a>    
</div>

<script>
    const initiateBtn = document.getElementById('initiatePaymentBtn');
    const goBackBtn = document.getElementById('goBackBtn');
    const selectionBlock = document.getElementById('selectionBlock');
    const qrBlock = document.getElementById('qrPaymentBlock');
    const finalMethod = document.getElementById('finalMethod');
    const timerSpan = document.getElementById('timer');
    const confirmBtn = document.getElementById('confirmBtn');
    
    // Lấy giá trị từ requestScope (chỉ dùng để hiển thị ban đầu)
    let initialTime = ${requestScope.thoiGianConLai}; 
    let countdownInterval;

    // Lấy giá trị ẩn của form để gửi qua AJAX
    const maSanPhamInput = document.querySelector('input[name="maTaiKhoan"]');
    const giaBanInput = document.querySelector('input[name="gia"]');

    initiateBtn.addEventListener('click', () => {
        const selected = document.querySelector('input[name="tempMethod"]:checked');
        if (!selected) {
            alert('Vui lòng chọn phương thức thanh toán.');
            return;
        }
        
        const paymentMethod = selected.value;
        finalMethod.value = paymentMethod; // Cập nhật form chính

        // Vô hiệu hóa nút để tránh click đúp
        initiateBtn.disabled = true;
        initiateBtn.innerText = "Đang xử lý...";

        // GỌI AJAX ĐỂ TẠO ĐƠN HÀNG
        fetch('${pageContext.request.contextPath}/payment/initiate', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            // Gửi dữ liệu sản phẩm và phương thức thanh toán
            body: new URLSearchParams({
                'maSanPham': maSanPhamInput.value,
                'giaBan': giaBanInput.value,
                'paymentMethod': paymentMethod
            })
        })
        .then(response => {
            if (!response.ok) {
                // Nếu lỗi (4xx, 5xx), ném lỗi để .catch xử lý
                return response.json().then(err => { throw new Error(err.error || 'Lỗi không xác định'); });
            }
            return response.json(); // Chuyển đổi phản hồi thành JSON
        })
        .then(data => {
            // THÀNH CÔNG: Hiển thị QR và bắt đầu đếm ngược
            if (data.success) {
                selectionBlock.style.display = 'none';
                qrBlock.style.display = 'block';
                
                // Bắt đầu đếm ngược với thời gian trả về từ server
                startCountdown(data.thoiGianConLai); 
            } else {
                throw new Error(data.error || 'Lỗi khi khởi tạo thanh toán.');
            }
        })
        .catch(error => {
            // XỬ LÝ LỖI (Ví dụ: Sản phẩm đã bán, hết phiên đăng nhập)
            alert('Lỗi: ' + error.message);
            // Kích hoạt lại nút
            initiateBtn.disabled = false;
            initiateBtn.innerText = "TIẾN HÀNH THANH TOÁN";
        });
    });

    goBackBtn.addEventListener('click', () => {
        // Lưu ý: Không nên dừng đếm ngược khi quay lại
        // Nếu muốn hủy đơn hàng khi quay lại, bạn cần gọi AJAX khác
        selectionBlock.style.display = 'block';
        qrBlock.style.display = 'none';
        initiateBtn.disabled = false;
        initiateBtn.innerText = "TIẾN HÀNH THANH TOÁN";
    });

    function startCountdown(duration) {
        let time = duration;
        
        // Xóa bộ đếm cũ (nếu có)
        if (countdownInterval) {
            clearInterval(countdownInterval);
        }

        countdownInterval = setInterval(() => {
            const minutes = String(Math.floor(time / 60)).padStart(2, '0');
            const seconds = String(time % 60).padStart(2, '0');
            timerSpan.textContent = minutes + ":" + seconds;
            
            time--;
            
            if (time < 0) {
                clearInterval(countdownInterval);
                timerSpan.textContent = "00:00";
                timerSpan.classList.add("expired");
                confirmBtn.disabled = true;
                confirmBtn.style.backgroundColor = "#ccc";
                confirmBtn.innerText = "Hết thời gian thanh toán";
                // Ở đây, bạn nên gọi AJAX để hủy đơn hàng CHO_THANH_TOAN
            }
        }, 1000);
    }
    
    // Khởi tạo hiển thị thời gian ban đầu (nhưng không chạy)
//    (function initDisplay() {
//        const minutes = String(Math.floor(initialTime / 60)).padStart(2, '0');
//        const seconds = String(initialTime % 60).padStart(2, '0');
//        timerSpan.textContent = minutes + ":" + seconds;
//    })();

    function setFinalMethod() {
        // Hàm này vẫn giữ nguyên để submit form chính
        return true; 
    }
</script>
</body>
</html>
