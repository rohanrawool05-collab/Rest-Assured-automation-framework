@echo off
echo ========================================
echo REST API Automation Framework
echo ========================================
echo.

:menu
echo Select test execution option:
echo 1. Run All Tests
echo 2. Run Smoke Tests
echo 3. Run Regression Tests
echo 4. Run Data-Driven Tests
echo 5. Clean and Install
echo 6. Exit
echo.

set /p choice="Enter your choice (1-6): "

if "%choice%"=="1" (
    echo Running all tests...
    mvn clean test
    goto end
)

if "%choice%"=="2" (
    echo Running Smoke tests...
    mvn clean test -Dcucumber.filter.tags="@Smoke"
    goto end
)

if "%choice%"=="3" (
    echo Running Regression tests...
    mvn clean test -Dcucumber.filter.tags="@Regression"
    goto end
)

if "%choice%"=="4" (
    echo Running Data-Driven tests...
    mvn clean test -Dcucumber.filter.tags="@DataDriven"
    goto end
)

if "%choice%"=="5" (
    echo Cleaning and installing dependencies...
    mvn clean install -DskipTests
    goto end
)

if "%choice%"=="6" (
    echo Exiting...
    exit
)

echo Invalid choice. Please try again.
goto menu

:end
echo.
echo ========================================
echo Test execution completed!
echo Check reports at: target/cucumber-reports/
echo ========================================
pause
