@echo off
setlocal EnableDelayedExpansion

:: =================================================================================
:: 0. KHOI TAO MA MAU ANSI (Dung cho UI Console)
:: =================================================================================
for /F "delims=#" %%E in ('"prompt #$E# & for %%a in (1) do rem"') do set "ESC=%%E"
set "RED=%ESC%[91m"
set "GREEN=%ESC%[92m"
set "YELLOW=%ESC%[93m"
set "CYAN=%ESC%[96m"
set "WHITE=%ESC%[97m"
set "RESET=%ESC%[0m"

echo %CYAN%=====================================================================%RESET%
echo %GREEN%      Tool scan full source su dung GitLeaks + PMD + SpotBugs %RESET%
echo %YELLOW%      Chiu trach nhiem: Nguyen Hai Hung - Hoc vien Cong nghe BCVT %RESET%
echo %CYAN%=====================================================================%RESET%
echo.

:: =================================================================================
:: 1. Kiem tra moi truong
:: =================================================================================
echo %WHITE%[*] Kiem tra moi truong...%RESET%
set MISSING_TOOL=0
IF NOT EXIST "gitleaks.exe" set MISSING_TOOL=1
IF NOT EXIST "mvnw.cmd" set MISSING_TOOL=1

IF !MISSING_TOOL! EQU 1 (
    echo %YELLOW%[WARNING]%RESET% Thieu cong cu GitLeaks hoac Maven. Dang goi setup-env.bat de phuc hoi...
    call setup-env-wlove.bat
    :: Sau khi setup xong, kiem tra lai
    IF NOT EXIST "gitleaks.exe" (
        echo %RED%[ERROR]%RESET% Phuc hoi that bai. Khong tim thay Gitleaks.
        pause
        exit /b 1
    )
) ELSE (
    echo %CYAN%[INFO]%RESET%  Moi truong da san sang.
)

:: Tao thu muc target neu chua co
IF NOT EXIST "target" mkdir target
echo.

:: =================================================================================
:: 2. GitLeaks - Full Source & History
:: =================================================================================
echo %CYAN%=====================================================================%RESET%
echo %WHITE%[*] GitLeaks - full source ^& history%RESET%
echo %CYAN%=====================================================================%RESET%
gitleaks.exe detect --source . -f json -r target\gitleaks-audit.json
echo %CYAN%[INFO]%RESET%  Bao cao chi tiet Gitleaks luu tai: %WHITE%target\gitleaks-audit.json%RESET%
echo.

:: =================================================================================
:: 3. PMD & SpotBugs - Full Source (Maven Wrapper)
:: =================================================================================
echo %CYAN%=====================================================================%RESET%
echo %WHITE%[*] PMD ^& SpotBugs - full source%RESET%
echo %CYAN%=====================================================================%RESET%
echo %CYAN%[INFO]%RESET%  Dang thuc hien quet toan bo ma nguon... (Se mat khoang 15-30s, vui long doi)
echo %CYAN%[INFO]%RESET%  Let's go grab a coffee!

:: Ghi log tam ra thu muc goc de tranh bi Maven clean xoa mat
call mvnw.cmd clean compile pmd:check spotbugs:check > maven-audit-tmp.log 2>&1

:: Chuyen file log vao lai thu muc target sau khi build xong
move maven-audit-tmp.log target\maven-audit.log >nul

:: Kiem tra trang thai Build truoc tien (Fail-Fast)
findstr /C:"BUILD SUCCESS" target\maven-audit.log >nul
IF %ERRORLEVEL% EQU 0 (
    echo.
    echo %GREEN%[TONG KET MAVEN BUILD]%RESET%
    echo %CYAN%------------------------------------------------------------------------%RESET%
    echo %GREEN% BUILD SUCCESS %RESET%
    echo %CYAN%------------------------------------------------------------------------%RESET%
    findstr /C:"Total time:" target\maven-audit.log
    findstr /C:"Finished at:" target\maven-audit.log

    echo.
    echo %YELLOW%[KET QUA PMD]%RESET%
    findstr /C:"You have" target\maven-audit.log

    echo.
    echo %YELLOW%[KET QUA SPOTBUGS]%RESET%
    findstr /C:"BugInstance size is" target\maven-audit.log
    findstr /C:"Error size is" target\maven-audit.log
    findstr /C:"Total bugs:" target\maven-audit.log

    echo.
    echo %CYAN%=====================================================================%RESET%
    echo %WHITE%[HUONG DAN BASELINE - TAY TRANG LOI CU]%RESET%
    echo %YELLOW% 1. Gitleaks:%RESET% Doc 'target\gitleaks-audit.json', copy cac commit loi vao file '.gitleaksignore'
    echo %YELLOW% 2. PMD:     %RESET% Chon loc cac loi nghiem trong de sua, cac loi khac them vao file 'pmd-exclude.xml'
    echo %YELLOW% 3. SpotBugs:%RESET% Tuong tu, tao file 'spotbugs-exclude.xml' de bo qua cac loi hien tai.
    echo %CYAN%=====================================================================%RESET%
) ELSE (
    echo.
    echo %RED%[TONG KET MAVEN BUILD]%RESET%
    echo %RED%[ERROR]%RESET% Maven build that bai!
    echo %RED%[ERROR]%RESET% Qua trinh quet PMD/SpotBugs khong the hoan tat.
    echo %RED%[ERROR]%RESET% Vui long kiem tra chi tiet tai: %WHITE%target\maven-audit.log%RESET%
)

echo.
pause
