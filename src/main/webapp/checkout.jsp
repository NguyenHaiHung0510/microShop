<%@page import="com.microshop.model.NguoiDung"%>
<%@page import="com.microshop.model.TaiKhoan"%>
<%@page import="com.microshop.model.TaiKhoanSteam"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="common/header.jsp">
    <jsp:param name="pageTitle" value="Xác Nhận Thanh Toán"/>
</jsp:include>

<c:set var="user" value="${sessionScope.user}" />
<c:set var="sanPham" value="${requestScope.sanPhamThanhToan}" />
<c:set var="thoiGianConLaiGiay" value="${requestScope.thoiGianConLai}" />
<c:set var="maGame" value="${requestScope.maGame}" />
<c:set var="type" value="${requestScope.type}" />

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

            <c:choose>
                <%-- Type 1: Tài khoản Game (LQ, FF, Riot) --%>
                <c:when test="${type == 1}">
                    <div class="product-item">
                        <span>
                            <strong>Mã TK #${sanPham.maTaiKhoan}</strong>: ${sanPham.diemNoiBat}
                        </span>
                        <span style="font-weight:600;">
                            <fmt:formatNumber value="${sanPham.giaBan}" type="currency" currencyCode="VND" maxFractionDigits="0"/> VNĐ
                        </span>
                    </div>
                </c:when>

                <%-- Type khác: Slot Steam --%>
                <c:otherwise>
                    <div class="product-item">
                        <span>
                            <strong>Mã TK #${sanPham.maTaiKhoanSteam}</strong>
                            <strong>Mã Game #${maGame}</strong>
                        </span>
                        <span style="font-weight:600;">
                            <fmt:formatNumber value="${requestScope.giaBanDau}" type="currency" currencyCode="VND" maxFractionDigits="0"/> VNĐ
                        </span>
                    </div>
                </c:otherwise>
            </c:choose>

            <%-- Phần Chiết khấu và Tổng cộng --%>
            <div class="product-item" style="color: #28a745;">
                <span>
                    <strong>Chiết khấu (Hạng ${requestScope.tenHang}):</strong>
                </span>
                <span style="font-weight:600;">
                    - <fmt:formatNumber value="${requestScope.tienChietKhau}" type="currency" currencyCode="VND" maxFractionDigits="0"/> VNĐ
                </span>
            </div>

            <div class="total-amount">
                Tổng cộng:
                <fmt:formatNumber value="${requestScope.giaCuoiCung}" type="currency" currencyCode="VND" maxFractionDigits="0"/> VNĐ
            </div>
        </div>

        <div class="order-summary">
            <h3>Thông tin người mua:</h3>
            <p><strong>Người dùng:</strong> ${user.tenDangNhap}</p>
        </div>

        <form id="checkoutForm" action="${pageContext.request.contextPath}/payment/success" method="POST">

            <c:choose>
                <c:when test="${type == 1}">
                    <input type="hidden" name="maTaiKhoan" value="${sanPham.maTaiKhoan}" />
                </c:when>
                <c:otherwise>
                    <input type="hidden" name="maTaiKhoan" value="${sanPham.maTaiKhoanSteam}" />
                </c:otherwise>
            </c:choose>
            <input type="hidden" name="type" value="${requestScope.type}">
            <input type="hidden" name="maGame" value="${maGame}">
            <input type="hidden" name="gia" value="${requestScope.giaCuoiCung}">
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
                <img src="${pageContext.request.contextPath}/assets/images/root_images/sample_qr.png" alt="Mã QR Thanh Toán Giả Lập">
                <p>Tổng tiền cần thanh toán:
                    <span style="color:#c92a2a;">
                        <fmt:formatNumber value="${requestScope.giaCuoiCung}" type="currency" currencyCode="VND" maxFractionDigits="0"/> VNĐ
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

    let countdownInterval;

    const maSanPhamInput = document.querySelector('input[name="maTaiKhoan"]');
    const giaBanInput = document.querySelector('input[name="gia"]');
    const type = document.querySelector('input[name="type"]');
    const maGame = document.querySelector('input[name="maGame"]');

    initiateBtn.addEventListener('click', () => {
        const selected = document.querySelector('input[name="tempMethod"]:checked');
        if (!selected) {
            alert('Vui lòng chọn phương thức thanh toán.');
            return;
        }

        const paymentMethod = selected.value;
        finalMethod.value = paymentMethod; // Cập nhật form chính

        initiateBtn.disabled = true;
        initiateBtn.innerText = "Đang xử lý...";
        confirmBtn.innerText = "XÁC NHẬN ĐÃ THANH TOÁN";
        confirmBtn.style.backgroundColor = "";
        confirmBtn.disabled = false;

        // GỌI AJAX ĐỂ TẠO ĐƠN HÀNG
        fetch('${pageContext.request.contextPath}/payment/initiate', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: new URLSearchParams({
                'maSanPham': maSanPhamInput.value,
                'type': type.value,
                'maGame': maGame.value,
                'giaBan': giaBanInput.value,
                'paymentMethod': paymentMethod
            })
        })
                .then(response => {
                    if (!response.ok) {
                        return response.json().then(err => {
                            throw new Error(err.error || 'Lỗi không xác định');
                        });
                    }
                    return response.json();
                })
                .then(data => {
                    if (data.success) {
                        selectionBlock.style.display = 'none';
                        qrBlock.style.display = 'block';
                        startCountdown(data.thoiGianConLai);
                    } else {
                        throw new Error(data.error || 'Lỗi khi khởi tạo thanh toán.');
                    }
                })
                .catch(error => {
                    alert('Lỗi: ' + error.message);
                    initiateBtn.disabled = false;
                    initiateBtn.innerText = "TIẾN HÀNH THANH TOÁN";
                });
    });

    goBackBtn.addEventListener('click', () => {
        selectionBlock.style.display = 'block';
        qrBlock.style.display = 'none';
        initiateBtn.disabled = false;
        initiater.innerText = "TIẾN HÀNH THANH TOÁN";
    });

    function startCountdown(duration) {
        let time = duration;
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
            }
        }, 1000);
    }

    function setFinalMethod() {
        return true;
    }
</script>

<jsp:include page="common/footer.jsp" />