@echo off
setlocal EnableDelayedExpansion
echo =========================================================
echo Tools quet toan bo lich su commit su dung GitLeaks
echo Chiu trach nhiem tool: Nguyen Hai Hung - Hoc vien Cong nghe Buu Chinh Vien Thong
echo =========================================================

:: 1. Goi GitHub API de lay phien ban on dinh moi nhat
echo [INFO] Dang kiem tra phien ban GitLeaks moi nhat tren GitHub...
FOR /F "tokens=*" %%v IN ('powershell -Command "(Invoke-RestMethod -Uri 'https://api.github.com/repos/gitleaks/gitleaks/releases/latest').tag_name.TrimStart('v')"') DO SET LATEST_VERSION=%%v
echo [INFO] Phien ban moi nhat hien tai la: v!LATEST_VERSION!

set GITLEAKS_EXE=gitleaks.exe
set NEED_DOWNLOAD=1

:: 2. Kiem tra phien dang co tren may (neu co)
IF EXIST "%GITLEAKS_EXE%" (
    FOR /F "tokens=*" %%c IN ('%GITLEAKS_EXE% version') DO SET CURRENT_VERSION=%%c
    echo [INFO] GitLeaks da co tren he thong: Phien ban !CURRENT_VERSION!
    
    :: So sanh phien ban
    echo !CURRENT_VERSION! | findstr /C:"!LATEST_VERSION!" >nul
    IF !ERRORLEVEL! EQU 0 (
        echo [INFO] Phien ban GitLeaks hien tai la moi nhat, khong can cap nhat
        set NEED_DOWNLOAD=0
    ) ELSE (
        echo [INFO] Phien ban tren may khong khop voi ban moi nhat. Bat dau cap nhat tu dong...
        set NEED_DOWNLOAD=1
    )
) ELSE (
    echo [INFO] Khong tim thay GitLeaks tren may. Bat dau cai dat tu dong...
)

:: 3. Tien hanh tai xuong va giai nen neu can thiet
IF !NEED_DOWNLOAD! EQU 1 (
    echo [INFO] Dang tai xuong GitLeaks v!LATEST_VERSION! tu GitHub...
    set DOWNLOAD_URL=https://github.com/gitleaks/gitleaks/releases/download/v!LATEST_VERSION!/gitleaks_!LATEST_VERSION!_windows_x64.zip
    powershell -Command "Invoke-WebRequest -Uri '!DOWNLOAD_URL!' -OutFile 'gitleaks.zip'"
    
    IF NOT EXIST "gitleaks.zip" (
        echo [ERROR] Tai xuong that bai. Vui long kiem tra ket noi mang!
        pause
        exit /b 1
    )
    
    echo [INFO] Dang giai nen...
    powershell -Command "Expand-Archive -Path 'gitleaks.zip' -DestinationPath '.' -Force"
    del gitleaks.zip
    echo [INFO] Hoan tat cau hinh GitLeaks! Phien ban hien tai: v!LATEST_VERSION!
)

:: 4. Kiem tra va tao thu muc target (neu chua co)
IF NOT EXIST "target" (
    mkdir target
    echo [INFO] Da tao thu muc 'target' de luu tru bao cao.
)

:: 5. Thuc thi lenh quet
echo [INFO] Bat dau quet toan bo source code...
echo ---------------------------------------------------------
%GITLEAKS_EXE% detect --source . -v -f json -r target\gitleaks-report.json
echo ---------------------------------------------------------

echo [INFO] HOAN TAT! Bao cao da duoc xuat ra tai: target\gitleaks-report.json
echo =========================================================
pause