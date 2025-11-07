<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="vi">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Đồng Hồ Đếm Ngược Test Độc Lập (HTML)</title>
    <style>
      body {
        font-family: Arial, sans-serif;
        text-align: center;
        background-color: #f0f8ff;
      }
      .test-box {
        margin: 100px auto;
        padding: 30px;
        border: 2px solid #3498db;
        border-radius: 8px;
        max-width: 400px;
        background-color: #ffffff;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
      }
      #countdownDisplay {
        font-size: 3em;
        font-weight: bold;
        color: #e74c3c;
        margin: 15px 0;
        display: block;
      }
      .expired {
        color: #c0392b;
      }
    </style>
  </head>
  <body>
    <div class="test-box">
      <h2>Kiểm tra Đồng hồ Độc lập</h2>
      <p>Thời gian bắt đầu đếm ngược:</p>
      <span id="countdownDisplay">00:10</span>
      <p id="status">Đang chạy...</p>
    </div>

    <script>
      document.addEventListener("DOMContentLoaded", function () {
        const displayElement = document.getElementById("countdownDisplay");
        const statusElement = document.getElementById("status");
        const DURATION = 10; // 10 giây để test
        let time = DURATION;

        function updateDisplay() {
          const minutes = String(Math.floor(time / 60)).padStart(2, "0");
          const seconds = String(time % 60).padStart(2, "0");
        displayElement.textContent = minutes + ":" + seconds;
        }

        updateDisplay(); // hiển thị ban đầu (00:10)

        const intervalId = setInterval(() => {
          time--;
          updateDisplay(); // cập nhật NGAY sau khi trừ

          if (time <= 0) {
            clearInterval(intervalId);
            displayElement.textContent = "00:00";
            displayElement.classList.add("expired");
            statusElement.textContent = "Đã hết hạn!";
          }
        }, 1000);
      });
    </script>
  </body>
</html>
