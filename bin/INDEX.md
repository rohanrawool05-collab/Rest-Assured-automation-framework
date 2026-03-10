# REST API Automation Framework - Complete Index

## 📚 Documentation Guide

### Getting Started
1. **[README.md](README.md)** - Start here for framework overview
2. **[PROJECT_SUMMARY.md](PROJECT_SUMMARY.md)** - Quick project summary
3. **[TEST_EXECUTION_GUIDE.md](TEST_EXECUTION_GUIDE.md)** - How to run tests

### Requirements & Architecture
4. **[REQUIREMENTS_CHECKLIST.md](REQUIREMENTS_CHECKLIST.md)** - All 18 requirements mapped
5. **[FRAMEWORK_ARCHITECTURE.md](FRAMEWORK_ARCHITECTURE.md)** - Architecture diagrams

### Execution Scripts
6. **run-tests.bat** - Windows execution script
7. **run-tests.sh** - Linux/Mac execution script

### Configuration Files
8. **pom.xml** - Maven dependencies and build configuration
9. **Jenkinsfile** - CI/CD pipeline configuration
10. **.gitignore** - Version control exclusions

## 📂 Source Code Structure

### Test Code (src/test/java/)

#### Hooks
- **hooks/Hooks.java** - Pre/Post test execution hooks

#### POJO Classes
- **pojo/DeviceData.java** - Nested data object
- **pojo/DeviceRequest.java** - Request serialization
- **pojo/DeviceResponse.java** - Response deserialization

#### Resources
- **resources/APIResources.java** - API endpoints enum

#### Runners
- **runners/TestRunner.java** - Cucumber test runner
- **runners/ReportGenerator.java** - Report generation

#### Step Definitions
- **stepdefinitions/DeviceSteps.java** - Cucumber step implementations

#### Test Data
- **testdata/TestDataBuilder.java** - Dynamic test data builder

#### Utils
- **utils/ConfigReader.java** - Configuration reader
- **utils/RequestSpecBuilder.java** - Request specification builder

### Test Resources (src/test/resources/)

#### Feature Files
- **features/device_crud.feature** - CRUD operations
- **features/parameterized_tests.feature** - Data-driven tests
- **features/api_validations.feature** - Validation scenarios

#### Configuration
- **config.properties** - Global configuration
- **log4j2.xml** - Logging configuration

## 🎯 Quick Navigation

### For First-Time Users
1. Read [README.md](README.md)
2. Check [REQUIREMENTS_CHECKLIST.md](REQUIREMENTS_CHECKLIST.md)
3. Follow [TEST_EXECUTION_GUIDE.md](TEST_EXECUTION_GUIDE.md)

### For Developers
1. Review [FRAMEWORK_ARCHITECTURE.md](FRAMEWORK_ARCHITECTURE.md)
2. Explore source code structure above
3. Check inline code comments

### For DevOps/CI-CD
1. Review **Jenkinsfile**
2. Check **pom.xml** for build configuration
3. See [TEST_EXECUTION_GUIDE.md](TEST_EXECUTION_GUIDE.md) for Maven commands

## 🔍 Find What You Need

### Need to understand the framework?
→ [README.md](README.md)

### Need to run tests?
→ [TEST_EXECUTION_GUIDE.md](TEST_EXECUTION_GUIDE.md)

### Need to verify requirements?
→ [REQUIREMENTS_CHECKLIST.md](REQUIREMENTS_CHECKLIST.md)

### Need architecture details?
→ [FRAMEWORK_ARCHITECTURE.md](FRAMEWORK_ARCHITECTURE.md)

### Need quick overview?
→ [PROJECT_SUMMARY.md](PROJECT_SUMMARY.md)

### Need to add new tests?
→ Check **features/** folder and **stepdefinitions/** folder

### Need to modify configuration?
→ Edit **config.properties**

### Need to add new API endpoints?
→ Update **APIResources.java** enum

### Need to create new POJO?
→ Add to **pojo/** package

### Need to add utilities?
→ Add to **utils/** package

## 📊 Test Scenarios Index

### Smoke Tests (@Smoke)
- Create a new device
- Validate response structure

### Regression Tests (@Regression)
- Get all devices
- Create and retrieve by ID
- Create, update and verify
- Create and delete
- Response time validation
- End-to-end workflow

### Data-Driven Tests (@DataDriven)
- Parameterized device creation (5 data sets)

## 🛠️ Key Files Reference

| File | Purpose | Location |
|------|---------|----------|
| pom.xml | Dependencies | Root |
| TestRunner.java | Execute tests | runners/ |
| Hooks.java | Pre/Post conditions | hooks/ |
| DeviceSteps.java | Step definitions | stepdefinitions/ |
| APIResources.java | API endpoints | resources/ |
| config.properties | Configuration | resources/ |
| device_crud.feature | CRUD tests | features/ |
| Jenkinsfile | CI/CD pipeline | Root |

## 📈 Reports Location

After test execution:
- JSON: `target/cucumber.json`
- HTML: `target/cucumber-report.html`
- Detailed: `target/cucumber-reports/overview-features.html`
- Logs: `target/logs/automation.log`

## 🚀 Quick Commands

```bash
# Install dependencies
mvn clean install -DskipTests

# Run all tests
mvn clean test

# Run smoke tests
mvn clean test -Dcucumber.filter.tags="@Smoke"

# Run regression tests
mvn clean test -Dcucumber.filter.tags="@Regression"

# Run data-driven tests
mvn clean test -Dcucumber.filter.tags="@DataDriven"
```

## 📞 Support & Help

1. Check documentation files listed above
2. Review inline code comments
3. Check feature files for examples
4. Review test execution logs

---

**Framework Version:** 1.0.0  
**Last Updated:** 2024  
**Status:** Production Ready ✅
