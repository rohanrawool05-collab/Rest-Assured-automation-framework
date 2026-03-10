#!/bin/bash

echo "========================================"
echo "REST API Automation Framework"
echo "========================================"
echo ""

echo "Select test execution option:"
echo "1. Run All Tests"
echo "2. Run Smoke Tests"
echo "3. Run Regression Tests"
echo "4. Run Data-Driven Tests"
echo "5. Clean and Install"
echo "6. Exit"
echo ""

read -p "Enter your choice (1-6): " choice

case $choice in
    1)
        echo "Running all tests..."
        mvn clean test
        ;;
    2)
        echo "Running Smoke tests..."
        mvn clean test -Dcucumber.filter.tags="@Smoke"
        ;;
    3)
        echo "Running Regression tests..."
        mvn clean test -Dcucumber.filter.tags="@Regression"
        ;;
    4)
        echo "Running Data-Driven tests..."
        mvn clean test -Dcucumber.filter.tags="@DataDriven"
        ;;
    5)
        echo "Cleaning and installing dependencies..."
        mvn clean install -DskipTests
        ;;
    6)
        echo "Exiting..."
        exit 0
        ;;
    *)
        echo "Invalid choice"
        ;;
esac

echo ""
echo "========================================"
echo "Test execution completed!"
echo "Check reports at: target/cucumber-reports/"
echo "========================================"
