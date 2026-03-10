<<<<<<< HEAD
# REST API Automation Framework

A comprehensive REST API automation framework built with REST Assured, Cucumber BDD, and Maven.

## Framework Features

✅ **Maven Project Structure** - Standard Maven project with proper dependency management  
✅ **Cucumber BDD Framework** - Behavior-driven development with Gherkin syntax  
✅ **REST Assured** - Powerful REST API testing library  
✅ **POJO Classes** - Serialization and Deserialization support  
✅ **Enum for API Resources** - Centralized endpoint management  
✅ **Utils Classes** - Reusable request and response specifications  
✅ **Test Data Builder** - Dynamic test data generation  
✅ **Data-Driven Testing** - Parameterized tests with Cucumber Examples  
✅ **Hooks** - Pre and Post conditions for test execution  
✅ **Logging** - Log4j2 integration for detailed logging  
✅ **Cucumber Reports** - HTML reports with detailed test execution results  
✅ **Properties File** - Externalized configuration management  
✅ **CI/CD Ready** - Jenkins integration support  

## Project Structure

```
rest-api-framework/
├── src/
│   └── test/
│       ├── java/
│       │   ├── hooks/
│       │   │   └── Hooks.java
│       │   ├── pojo/
│       │   │   ├── DeviceData.java
│       │   │   ├── DeviceRequest.java
│       │   │   └── DeviceResponse.java
│       │   ├── resources/
│       │   │   └── APIResources.java
│       │   ├── runners/
│       │   │   ├── TestRunner.java
│       │   │   └── ReportGenerator.java
│       │   ├── stepdefinitions/
│       │   │   └── DeviceSteps.java
│       │   ├── testdata/
│       │   │   └── TestDataBuilder.java
│       │   └── utils/
│       │       ├── ConfigReader.java
│       │       └── RequestSpecBuilder.java
│       └── resources/
│           ├── features/
│           │   ├── device_crud.feature
│           │   └── parameterized_tests.feature
│           ├── config.properties
│           └── log4j2.xml
├── pom.xml
└── README.md
```

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- IDE (IntelliJ IDEA / Eclipse)

## Installation

1. Clone the repository
2. Navigate to project directory
3. Run `mvn clean install`

## Running Tests

### Run all tests
```bash
mvn clean test
```

### Run specific tags
```bash
mvn clean test -Dcucumber.filter.tags="@Smoke"
mvn clean test -Dcucumber.filter.tags="@Regression"
mvn clean test -Dcucumber.filter.tags="@DataDriven"
```

### Run from IDE
Right-click on `TestRunner.java` and select "Run"

## Reports

After test execution, reports are generated at:
- **Cucumber JSON**: `target/cucumber.json`
- **Cucumber HTML**: `target/cucumber-report.html`
- **Masterthought Report**: `target/cucumber-reports/cucumber-html-reports/overview-features.html`
- **Logs**: `target/logs/automation.log`

## Configuration

Update `src/test/resources/config.properties` to modify:
- Base URL
- Timeouts
- Environment settings
- Logging levels

## Test Scenarios

### CRUD Operations
- Create a new device
- Get all devices
- Get device by ID
- Update device
- Delete device

### Data-Driven Tests
- Parameterized tests with multiple data sets
- Cucumber Scenario Outline with Examples

## Assertions & Validations
- Status code validation
- Response field validation
- Response time validation
- JSON deserialization validation

## CI/CD Integration

### Jenkins
Use the provided `Jenkinsfile` for pipeline integration

### Command Line
```bash
mvn clean test -Dcucumber.filter.tags="@Smoke"
```

## Best Practices Implemented

1. **Page Object Model** equivalent for APIs
2. **Separation of Concerns** - Utils, POJO, Test Data separated
3. **Reusable Components** - Request specs, builders
4. **Logging** - Comprehensive logging at each step
5. **Reporting** - Multiple report formats
6. **Configuration Management** - Externalized properties
7. **Data-Driven** - Parameterized test support
8. **Hooks** - Setup and teardown mechanisms

## Author

REST API Automation Framework - 2024
=======
# Rest-Assured-automation-framework
>>>>>>> 6be29aa065ee689b08c2a341310701ad70df81bb
