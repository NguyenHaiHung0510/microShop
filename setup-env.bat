@echo off
setlocal EnableDelayedExpansion
echo =================================================================================
echo Tool cai dat moi truong pha 1 (GitLeaks + PMD + SpotBugs)
echo Chiu trach nhiem tool: Nguyen Hai Hung - Hoc vien Cong nghe Buu Chinh Vien Thong
echo =================================================================================

:: 1. Check Git
git --version >nul 2>&1
IF %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Khong tim thay 'git'. Vui long cai dat Git for Windows!
    pause
    exit /b 1
) ELSE (
    FOR /F "tokens=*" %%v IN ('git --version') DO echo [INFO] Tim thay [GIT]    : %%v [OK]
) 

:: 2. Check Python va Phien ban (Yeu cau >= 3.9)
python --version >nul 2>&1
IF %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Khong tim thay 'python'. Vui long kiem tra lai!
    pause
    exit /b 1
) ELSE (
    :: Luu tam chuoi phien ban vao bien PY_VER
    FOR /F "tokens=*" %%v IN ('python --version 2^>^&1') DO set "PY_VER=%%v"
    
    :: Dung chinh Python de check version cua no
    python -c "import sys; sys.exit(0 if sys.version_info >= (3,9) else 1)"
    
    :: Dung !ERRORLEVEL! vi dang kiem tra ben trong khoi lenh ELSE (...)
    IF !ERRORLEVEL! EQU 0 (
        echo [INFO] Tim thay [PYTHON] : !PY_VER! [OK]
    ) ELSE (
        echo [INFO] Tim thay [PYTHON] : !PY_VER!
        echo [WARNING] Python cua ban qua cu. Vui long cap nhat len Python 3.9 tro len de chay pre-commit!
    )
)

:: 3. Check JAVA_HOME
IF "%JAVA_HOME%"=="" (
    echo [WARNING] Chua thiet lap bien moi truong JAVA_HOME! 
) ELSE (
    echo [INFO] Tim thay [JAVA_HOME]: %JAVA_HOME% [OK]
)

:: 4. Check Java va Phien ban (Yeu cau Java 21)
java -version >nul 2>&1
IF %ERRORLEVEL% NEQ 0 (
    echo [WARNING] Khong tim thay 'java' trong PATH. Vui long kiem tra lai!
) ELSE (
    :: Dung bien dem (count) de chi lay dong dau tien thay vi dung GOTO (GOTO trong khoi IF/ELSE de gay loi)
    set /a j_count=0
    FOR /F "tokens=*" %%v IN ('java -version 2^>^&1') DO (
        IF !j_count! EQU 0 (
            set "JAVA_VER=%%v"
            set /a j_count=1
        )
    )

    java -version 2>&1 | findstr "21." >nul
    IF !ERRORLEVEL! EQU 0 (
        echo [INFO] Tim thay [JAVA]   : !JAVA_VER! [OK]
    ) ELSE (
        echo [INFO] Tim thay [JAVA]   : !JAVA_VER!
        echo [WARNING] Du an yeu cau Java 21. Phien ban Java hien tai khong khop!
        echo           Vui long kiem tra lai bien moi truong PATH va JAVA_HOME.
    )
)
