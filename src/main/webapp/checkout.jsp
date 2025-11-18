<%@page import="com.microshop.model.NguoiDung"%>
<%@page import="com.microshop.model.TaiKhoan"%>
<%@page import="com.microshop.model.TaiKhoanSteam"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="common/header.jsp">
    <jsp:param name="pageTitle" value="Xác Nhận Thanh Toán"/>
</jsp:include>

<%-- Lấy các giá trị từ PaymentExecuteServlet --%>
<c:set var="user" value="${sessionScope.user}" />
<c:set var="sanPham" value="${requestScope.sanPhamThanhToan}" />
<c:set var="thoiGianConLaiGiay" value="${requestScope.thoiGianConLai}" />
<c:set var="maGame" value="${requestScope.maGame}" />
<c:set var="type" value="${requestScope.type}" />
<%-- SỬA LỖI: Thêm giá trị mặc định false. Đây là dòng code quan trọng bị thiếu. --%>
<c:set var="isWaiting" value="${not empty requestScope.isWaiting ? requestScope.isWaiting : false}" />


<div class="checkout-container">
    <h2>Xác Nhận Thanh Toán</h2>

    <c:if test="${sanPham == null}">
        <div class="error-message">
            Lỗi: Không tìm thấy thông tin sản phẩm để thanh toán hoặc sản phẩm đã hết hàng.
        </div>
        <p style="text-align:center;"><a href="${pageContext.request.contextPath}/home">Quay lại Trang Chủ</a></p>
    </c:if>

    <c:if test="${sanPham != null}">
        <%-- Tóm tắt đơn hàng (Đã sửa ở Servlet) --%>
        <div class="order-summary">
            <h3>Sản phẩm:</h3>

            <c:choose>
                <c:when test="${type == 1}"> <%-- Tài khoản Game --%>
                    <div class="product-item">
                        <span>
                            <strong>Mã TK #${sanPham.maTaiKhoan}</strong>: ${sanPham.diemNoiBat}
                        </span>
                        <span style="font-weight:600;">
                            <fmt:formatNumber value="${requestScope.giaBanDau}" type="currency" currencyCode="VND" maxFractionDigits="0"/> VNĐ
                        </span>
                    </div>
                </c:when>
                <c:otherwise> <%-- Slot Steam --%>
                    <div class="product-item">
                        <span>
                            <strong>Slot Game (Mã Game #${maGame})</strong>
                            (Tài khoản: ${sanPham.tenDangNhapSteam})
                        </span>
                        <span style="font-weight:600;">
                            <fmt:formatNumber value="${requestScope.giaBanDau}" type="currency" currencyCode="VND" maxFractionDigits="0"/> VNĐ
                        </span>
                    </div>
                </c:otherwise>
            </c:choose>
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
        

        <form id="checkoutForm">
            <%-- Các input hidden chứa thông tin cho JS --%>
            <c:choose>
                <c:when test="${type == 1}">
                    <input type="hidden" name="maTaiKhoan" value="${sanPham.maTaiKhoan}" />
                </c:when>
                <c:otherwise>
                    <%-- SỬA: Dùng MaGameSteam làm mã sản phẩm cho type 2 --%>
                    <input type="hidden" name="maTaiKhoan" value="${maGame}" />
                </c:otherwise>
            </c:choose>
            <input type="hidden" name="type" value="${requestScope.type}">
            <input type="hidden" name="maGame" value="${maGame}">
            <input type="hidden" name="gia" value="${requestScope.giaCuoiCung}">
            <input type="hidden" id="finalMethod" name="paymentMethod" value="">

            <%-- SỬA LỖI: Dùng <c:if> thay vì style="" để đảm bảo hiển thị --%>
            
            <%-- Panel 1: Chọn thanh toán (Chỉ hiện khi isWaiting=false) --%>
            <div id="selectionBlock" <c:if test="${isWaiting}">style="display: none;"</c:if>>
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

            <%-- Panel 2: Chờ admin (Chỉ hiện khi isWaiting=true) --%>
            <div id="qrPaymentBlock" class="qr-section" <c:if test="${not isWaiting}">style="display: none;"</c:if>>
                <h3>Vui lòng quét mã QR để thanh toán</h3>
                <div class="countdown">Thời gian còn lại: <span id="timer"></span></div>
                <img src="${pageContext.request.contextPath}/assets/images/root_images/sample_qr.png" alt="Mã QR Thanh Toán Giả Lập">
                <p>Tổng tiền cần thanh toán:
                    <span style="color:#c92a2a;">
                        <fmt:formatNumber value="${requestScope.giaCuoiCung}" type="currency" currencyCode="VND" maxFractionDigits="0"/> VNĐ
                    </span>
                </p>
                <div class="wait-message" style="padding: 15px; background-color: #f8f9fa; border: 1px solid #dee2e6; border-radius: 4px; text-align: center; margin-top: 20px;">
                    <p style="font-weight: 500; margin: 0;">Đơn hàng đang chờ Admin xác nhận thanh toán.</p>
                    <p style="font-size: 0.9em; margin: 5px 0 0 0;">Bạn vui lòng vào "Trang cá nhân" / "Lịch sử đơn hàng" để xem tình trạng duyệt nhé!</p>
                </div>
            </div>
        </form>
    </c:if>

    <a href="${pageContext.request.contextPath}/home" class="back-to-home">
        ← Quay về Trang chủ
    </a>
</div>

<%-- Script (Không thay đổi, đã đúng từ trước) --%>
<script>
    const initiateBtn = document.getElementById('initiatePaymentBtn');
    const selectionBlock = document.getElementById('selectionBlock');
    const qrBlock = document.getElementById('qrPaymentBlock');
    const finalMethod = document.getElementById('finalMethod');
    const timerSpan = document.getElementById('timer');
    const isWaiting = ${isWaiting eq true}; // Đọc cờ từ JSP
    
    let countdownInterval;

    const maSanPhamInput = document.querySelector('input[name="maTaiKhoan"]');
    const giaBanInput = document.querySelector('input[name="gia"]');
    const typeInput = document.querySelector('input[name="type"]');
    const maGameInput = document.querySelector('input[name="maGame"]');

    if (!isWaiting) {
        initiateBtn.addEventListener('click', () => {
            const selected = document.querySelector('input[name="tempMethod"]:checked');
            if (!selected) {
                alert('Vui lòng chọn phương thức thanh toán.');
                return;
            }

            const paymentMethod = selected.value;
            finalMethod.value = paymentMethod;
            initiateBtn.disabled = true;
            initiateBtn.innerText = "Đang xử lý...";

            fetch('${pageContext.request.contextPath}/payment/initiate', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: new URLSearchParams({
                    'maSanPham': (typeInput.value == 2) ? maGameInput.value : maSanPhamInput.value,
                    'type': typeInput.value,
                    'maGame': maGameInput.value,
                    'giaBan': giaBanInput.value,
                    'paymentMethod': paymentMethod
                })
            })
            .then(response => {
                return response.json().then(data => ({ status: response.status, body: data }));
            })
            .then(({ status, body }) => {
                if (status === 200 && body.success) {
                    selectionBlock.style.display = 'none';
                    qrBlock.style.display = 'block';
                    startCountdown(body.thoiGianConLai);
                } else {
                    throw new Error(body.error || 'Lỗi không xác định khi tạo đơn hàng.');
                }
            })
            .catch(error => {
                alert('Lỗi: ' + error.message);
                initiateBtn.disabled = false;
                initiateBtn.innerText = "TIẾN HÀNH THANH TOÁN";
            });
        });
    }

    if (isWaiting) {
        startCountdown(${thoiGianConLaiGiay});
    }

    function startCountdown(duration) {
        let time = duration;
        if (countdownInterval) {
            clearInterval(countdownInterval);
        }

        countdownInterval = setInterval(() => {
            if (time < 0) {
                clearInterval(countdownInterval);
                timerSpan.textContent = "00:00";
                timerSpan.classList.add("expired");
                window.location.reload();
                return;
            }
            
            const minutes = String(Math.floor(time / 60)).padStart(2, '0');
            const seconds = String(time % 60).padStart(2, '0');
            timerSpan.textContent = minutes + ":" + seconds;

            time--;

        }, 1000);
    }
</script>

<jsp:include page="common/footer.jsp" />