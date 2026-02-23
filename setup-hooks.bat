@echo off
echo ===================================================
echo Cai dat Moi truong DevSecOps - GitLeaks + PMD + SpotBugs
echo Chiu trach nhiem tool: Nguyen Hai Hung - Hoc vien Cong nghe Buu Chinh Vien Thong
echo ===================================================

:: 1. Kiểm tra Python
python --version >nul 2>&1
IF %ERRORLEVEL% NEQ 0 (
    echo [LOI] Python chua duoc cai dat. Vui long cai dat Python!
    pause
    exit /b
)

:: 2. Cài đặt pre-commit
echo [INFO] Kiem tra cap nhat pip
python -m pip install --upgrade pip
echo [INFO] Cai dat pre-commit
python -m pip install pre-commit

:: 3. Kích hoạt hooks vào thư mục .git
echo [INFO] Tich hop hooks vao Git...
python -m pre_commit install

:: 4. Cài đặt các thư viện phụ thuộc (GitLeaks, etc)
echo [INFO] Dang cap nhat moi truong pre-commit...
python -m pre_commit autoupdate

echo ===================================================
echo HOAN TAT! He thong da cau hinh thanh cong!
echo ===================================================
pause
