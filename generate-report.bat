@echo off
echo Generating Cucumber HTML Report with Pie Charts...
mvn exec:java@generate-report
echo.
echo Report generated at: target\cucumber-reports\cucumber-html-reports\overview-features.html
pause
