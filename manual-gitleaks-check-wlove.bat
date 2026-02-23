@echo off
setlocal EnableDelayedExpansion

:: Dinh nghia ma mau ANSI
for /F %%a in ('echo prompt $E ^| cmd') do set "ESC=%%a"
set "C_RED=%ESC%[31m"
set "C_GREEN=%ESC%[32m"
set "C_YELLOW=%ESC%[33m"
set "C_CYAN=%ESC%[36m"
set "C_RESET=%ESC%[0m"

echo %C_CYAN%=========================================================%C_RESET%
echo %C_YELLOW%Tools quet toan bo lich su commit su dung GitLeaks%C_RESET%
echo %C_YELLOW%Chiu trach nhiem tool: Nguyen Hai Hung - Hoc vien Cong nghe Buu Chinh Vien Thong%C_RESET%
echo %C_CYAN%=========================================================%C_RESET%

:: 0. Kiem tra su ton tai cua Git tren he thong
git --version >nul 2>&1
IF %ERRORLEVEL% NEQ 0 (
    echo %C_RED%[ERROR] Khong tim thay lenh 'git' tren may nay.%C_RESET%
    echo %C_CYAN%[INFO] GitLeaks can Git de doc lich su commit. Vui long cai dat Git for Windows!%C_RESET%
    pause
    exit /b 1
)

:: 1. Goi GitHub API de lay phien ban on dinh moi nhat
echo %C_CYAN%[INFO] Dang kiem tra phien ban GitLeaks moi nhat tren GitHub...%C_RESET%
FOR /F "tokens=*" %%v IN ('powershell -Command "(Invoke-RestMethod -Uri 'https://api.github.com/repos/gitleaks/gitleaks/releases/latest').tag_name.TrimStart('v')"') DO SET LATEST_VERSION=%%v
echo %C_CYAN%[INFO] Phien ban moi nhat hien tai la: v!LATEST_VERSION!%C_RESET%

set GITLEAKS_EXE=gitleaks.exe
set NEED_DOWNLOAD=1

:: 2. Kiem tra phien dang co tren may (neu co)
IF EXIST "%GITLEAKS_EXE%" (
    FOR /F "tokens=*" %%c IN ('%GITLEAKS_EXE% version') DO SET CURRENT_VERSION=%%c
    echo %C_CYAN%[INFO] GitLeaks da co tren he thong: Phien ban !CURRENT_VERSION!%C_RESET%
    
    :: So sanh phien ban
    echo !CURRENT_VERSION! | findstr /C:"!LATEST_VERSION!" >nul
    IF !ERRORLEVEL! EQU 0 (
        echo %C_GREEN%[INFO] Phien ban GitLeaks hien tai la moi nhat, khong can cap nhat%C_RESET%
        
        set NEED_DOWNLOAD=0
    ) ELSE (
        echo %C_YELLOW%[INFO] Phien ban tren may khong khop voi ban moi nhat. Bat dau cap nhat tu dong...%C_RESET%
        set NEED_DOWNLOAD=1
    )
) ELSE (
    echo %C_YELLOW%[INFO] Khong tim thay GitLeaks tren may. Bat dau cai dat tu dong...%C_RESET%
)

:: 3. Tien hanh tai xuong va giai nen neu can thiet
IF !NEED_DOWNLOAD! EQU 1 (
    echo %C_CYAN%[INFO] Dang tai xuong GitLeaks v!LATEST_VERSION! tu GitHub...%C_RESET%
    set DOWNLOAD_URL=https://github.com/gitleaks/gitleaks/releases/download/v!LATEST_VERSION!/gitleaks_!LATEST_VERSION!_windows_x64.zip
    powershell -Command "Invoke-WebRequest -Uri '!DOWNLOAD_URL!' -OutFile 'gitleaks.zip'"
    
    IF NOT EXIST "gitleaks.zip" (
        echo %C_RED%[ERROR] Tai xuong that bai. Vui long kiem tra ket noi mang!%C_RESET%
        pause
        exit /b 1
    )
    
    echo %C_CYAN%[INFO] Dang giai nen...%C_RESET%
    powershell -Command "Expand-Archive -Path 'gitleaks.zip' -DestinationPath '.' -Force"
    del gitleaks.zip
    echo %C_GREEN%[INFO] Hoan tat cau hinh GitLeaks! Phien ban hien tai: v!LATEST_VERSION!%C_RESET%
)

:: 4. Kiem tra va tao thu muc target (neu chua co)
IF NOT EXIST "target" (
    mkdir target
    echo %C_CYAN%[INFO] Da tao thu muc 'target' de luu tru bao cao.%C_RESET%
)

:: 5. Thuc thi lenh quet
echo %C_CYAN%[INFO] Bat dau quet toan bo source code...%C_RESET%
echo %C_CYAN%---------------------------------------------------------%C_RESET%
%GITLEAKS_EXE% detect --source . -v -f json -r target\gitleaks-report.json
echo %C_CYAN%---------------------------------------------------------%C_RESET%

echo %C_GREEN%[INFO] HOAN TAT! Bao cao da duoc xuat ra tai: target\gitleaks-report.json%C_RESET%
echo %C_CYAN%=========================================================%C_RESET%
pause
