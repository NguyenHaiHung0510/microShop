<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

</main> 

<footer>
    <p>&copy; 2025 MicroShop - Nhóm BTL Java 9 - From PTIT with love ❤️</p>
</footer>

<style>
    .session-toast {
        display: none; /* Ẩn ban đầu */
        position: fixed;
        bottom: 20px;
        right: 20px;
        background-color: #ffc107; /* Màu vàng cảnh báo */
        color: #333;
        padding: 15px 20px;
        border-radius: 5px;
        box-shadow: 0 4px 10px rgba(0,0,0,0.2);
        z-index: 1000;
        font-family: 'Be Vietnam Pro', Arial, sans-serif;
        font-size: 0.95em;
        opacity: 0;
        transition: opacity 0.5s ease-in-out;
    }

    .session-toast.show {
        display: block;
        opacity: 1;
    }
</style>

<c:if test="${not empty sessionScope.user}">
    
    <div id="sessionWarningToast" class="session-toast"></div>

    <c:set var="sessionTimeoutSeconds" value="${pageContext.session.maxInactiveInterval}" />

    <script>
        // Lấy thời gian session từ server (đơn vị: giây)
        const SESSION_TIMEOUT_SECONDS = ${sessionTimeoutSeconds};

        // Cảnh báo trước khi session hết (ví dụ 60 giây)
        const WARNING_TIME_SECONDS = 60;

        // Lấy phần tử toast
        const warningToast = document.getElementById('sessionWarningToast');

        // Tính thời gian thực tế còn lại tính từ lúc trang tải
        let timeLeft = SESSION_TIMEOUT_SECONDS;

        // Cập nhật thông báo đếm ngược theo thời gian thực
        function updateToast() {
            if (!warningToast) return;
            const minutes = Math.floor(timeLeft / 60);
            const seconds = timeLeft % 60;
            warningToast.innerText = `⏰ Phiên làm việc của bạn sẽ hết hạn sau ` + minutes + ` phút ` + seconds + ` giây nữa.`;
        }

        // Khi còn đúng WARNING_TIME_SECONDS, hiện cảnh báo
        const warningMilliseconds = (SESSION_TIMEOUT_SECONDS - WARNING_TIME_SECONDS) * 1000;
        if (warningMilliseconds > 0 && warningToast) {
            setTimeout(() => {
                warningToast.classList.add('show');
                updateToast();

                // Cập nhật mỗi giây cho đến khi hết session
                const countdown = setInterval(() => {
                    timeLeft--;
                    updateToast();
                    if (timeLeft <= 0) {
                        clearInterval(countdown);
                        warningToast.classList.remove('show');
                    }
                }, 1000);

                // Ẩn sau khi session hết
                setTimeout(() => {
                    warningToast.classList.remove('show');
                }, WARNING_TIME_SECONDS * 1000);

            }, warningMilliseconds);
        }

        // Tự động đăng xuất sau khi hết session
        const logoutMilliseconds = SESSION_TIMEOUT_SECONDS * 1000;
        setTimeout(() => {
            window.location.href = "${pageContext.request.contextPath}/logout";
        }, logoutMilliseconds);
    </script>
</c:if>

</body>
</html>
