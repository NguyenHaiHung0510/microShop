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

echo %CYAN%=================================================================================%RESET%
echo %GREEN%        Tool scan full source su dung GitLeaks + PMD + SpotBugs %RESET%
echo %YELLOW%        Chiu trach nhiem: Nguyen Hai Hung - Hoc vien Cong nghe BCVT %RESET%
echo %CYAN%=================================================================================%RESET%
echo.

:: =================================================================================
:: 1. Kiem tra moi truong
:: =================================================================================
echo %WHITE%[*] Kiem tra moi truong...%RESET%
set MISSING_TOOL=0
IF NOT EXIST "gitleaks.exe" set MISSING_TOOL=1
IF NOT EXIST "mvnw.cmd" set MISSING_TOOL=1

IF !MISSING_TOOL! EQU 1 (
    echo %YELLOW%[WARN]%RESET%  Thieu cong cu GitLeaks hoac Maven. Dang goi setup-env.bat de phuc hoi...
    call setup-env.bat
    :: Sau khi setup xong, kiem tra lai
    IF NOT EXIST "gitleaks.exe" (
        echo %RED%[ERROR]%RESET% Phuc hoi that bai. Khong tim thay Gitleaks.
        pause
        exit /b 1
    )
) ELSE (
    echo %CYAN%[INFO]%RESET%  Moi truong da san sang %GREEN%[OK]%RESET%
)
echo %CYAN%---------------------------------------------------------------------------------%RESET%
echo.

:: =================================================================================
:: 2. GitLeaks - full source & history
:: =================================================================================
echo %CYAN%=================================================================================%RESET%
echo %WHITE%[*] GitLeaks - full source ^& history%RESET%
echo %CYAN%=================================================================================%RESET%
gitleaks.exe detect --source . -f json -r audit-reports\gitleaks-audit.json
echo %CYAN%[INFO]%RESET%  Bao cao chi tiet Gitleaks luu tai: %YELLOW%audit-reports\gitleaks-audit.json%RESET%
echo.

:: =================================================================================
:: 3. PMD & SpotBugs - full source
:: =================================================================================
echo %CYAN%=================================================================================%RESET%
echo %WHITE%[*] PMD ^& SpotBugs - full source%RESET%
echo %CYAN%=================================================================================%RESET%
echo %CYAN%[INFO]%RESET%  Dang thuc hien quet, qua trinh se mat khoang 15-30s...
echo %CYAN%[INFO]%RESET%  Let's go grab a coffee!
call mvnw.cmd clean compile pmd:check spotbugs:check > audit-reports\maven-audit.log 2>&1

:: Kiem tra trang thai Build truoc tien (Fail-Fast)
findstr /C:"BUILD SUCCESS" audit-reports\maven-audit.log >nul
IF %ERRORLEVEL% EQU 0 (
    echo.
    echo %CYAN%[TONG KET MAVEN BUILD]%RESET%
    echo %CYAN%------------------------------------------------------------------------%RESET%
    echo %GREEN%[INFO] BUILD SUCCESS%RESET%
    echo %CYAN%------------------------------------------------------------------------%RESET%
    findstr /C:"Total time:" audit-reports\maven-audit.log
    findstr /C:"Finished at:" audit-reports\maven-audit.log

    echo.
    echo %YELLOW%[KET QUA PMD]%RESET%
    findstr /C:"You have" audit-reports\maven-audit.log

    echo.
    echo %YELLOW%[KET QUA SPOTBUGS]%RESET%
    findstr /C:"BugInstance size is" audit-reports\maven-audit.log
    findstr /C:"Error size is" audit-reports\maven-audit.log
    findstr /C:"Total bugs:" audit-reports\maven-audit.log

    echo.
    echo %CYAN%=====================================================================================================%RESET%
    echo %WHITE%[HUONG DAN BASELINE - TAY TRANG LOI CU]%RESET%
    echo %WHITE%1.%RESET% Gitleaks: Doc '%YELLOW%audit-reports\gitleaks-audit.json%RESET%', copy cac commit loi vao file '%YELLOW%.gitleaksignore%RESET%'
    echo %WHITE%2.%RESET% PMD: Chon loc cac loi nghiem trong de sua, cac loi khac them vao file '%YELLOW%pmd-exclude.xml%RESET%'
    echo %WHITE%3.%RESET% SpotBugs: Tuong tu, tao file '%YELLOW%spotbugs-exclude.xml%RESET%' de bo qua cac loi hien tai.
    echo %CYAN%=====================================================================================================%RESET%
) ELSE (
    echo.
    echo %CYAN%[TONG KET MAVEN BUILD]%RESET%
    echo %RED%[ERROR]%RESET% Maven build that bai!
    echo %RED%[ERROR]%RESET% Qua trinh quet PMD/SpotBugs khong the hoan tat.
    echo %RED%[ERROR]%RESET% Vui long kiem tra chi tiet tai: %YELLOW%audit-reports\maven-audit.log%RESET%
)

pause