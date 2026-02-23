@echo off

:: Dinh nghia ma mau ANSI
for /F %%a in ('echo prompt $E ^| cmd') do set "ESC=%%a"
set "C_RED=%ESC%[31m"
set "C_GREEN=%ESC%[32m"
set "C_YELLOW=%ESC%[33m"
set "C_CYAN=%ESC%[36m"
set "C_RESET=%ESC%[0m"

echo %C_CYAN%===================================================%C_RESET%
echo %C_YELLOW%Cai dat Moi truong DevSecOps - GitLeaks + PMD + SpotBugs%C_RESET%
echo %C_YELLOW%Chiu trach nhiem tool: Nguyen Hai Hung - Hoc vien Cong nghe Buu Chinh Vien Thong%C_RESET%
echo %C_CYAN%===================================================%C_RESET%

:: 1. Kiểm tra Python
python --version >nul 2>&1
IF %ERRORLEVEL% NEQ 0 (
    echo %C_RED%[LOI] Python chua duoc cai dat. Vui long cai dat Python!%C_RESET%
    pause
    exit /b
)

:: 2. Cài đặt pre-commit
echo %C_CYAN%[INFO] Kiem tra cap nhat pip%C_RESET%
python -m pip install --upgrade pip
echo %C_CYAN%[INFO] Cai dat pre-commit%C_RESET%
python -m pip install pre-commit

:: 3. Kích hoạt hooks vào thư mục .git
echo %C_CYAN%[INFO] Tich hop hooks vao Git...%C_RESET%
python -m pre_commit install

:: 4. Cài đặt các thư viện phụ thuộc (GitLeaks, etc)
echo %C_CYAN%[INFO] Dang cap nhat moi truong pre-commit...%C_RESET%
python -m pre_commit autoupdate

echo %C_CYAN%===================================================%C_RESET%
echo %C_GREEN%HOAN TAT!%C_RESET%
echo %C_GREEN%He thong da cau hinh thanh cong!%C_RESET%
echo %C_CYAN%===================================================%C_RESET%
pause
