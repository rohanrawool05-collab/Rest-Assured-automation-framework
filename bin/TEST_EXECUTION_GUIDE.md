# Test Execution Guide

## Quick Start

### 1. Setup
```bash
# Navigate to project directory
cd rest-api-framework

# Install dependencies
mvn clean install -DskipTests
```

### 2. Run Tests

#### Run All Tests
```bash
mvn clean test
```

#### Run Smoke Tests Only
```bash
mvn clean test -Dcucumber.filter.tags="@Smoke"
```

#### Run Regression Tests Only
```bash
mvn clean test -Dcucumber.filter.tags="@Regression"
```

#### Run Data-Driven Tests
```bash
mvn clean test -Dcucumber.filter.tags="@DataDriven"
```

#### Run Multiple Tags
```bash
mvn clean test -Dcucumber.filter.tags="@Smoke or @Regression"
```

### 3. View Reports

After execution, open the following reports:

**Cucumber HTML Report:**
```
target/cucumber-report.html
```

**Masterthought Report (Detailed):**
```
target/cucumber-reports/cucumber-html-reports/overview-features.html
```

**Logs:**
```
target/logs/automation.log
```

## Framework Components

### 1. POJO Classes (Serialization/Deserialization)
- `DeviceRequest.java` - Request payload
- `DeviceResponse.java` - Response payload
- `DeviceData.java` - Nested data object

### 2. Enum for API Resources
- `APIResources.java` - Centralized endpoint management

### 3. Utils Classes
- `ConfigReader.java` - Read properties file
- `RequestSpecBuilder.java` - Build reusable request specs

### 4. Test Data Builder
- `TestDataBuilder.java` - Create dynamic test data

### 5. Step Definitions
- `DeviceSteps.java` - Cucumber step implementations

### 6. Hooks
- `Hooks.java` - Before/After scenario execution

### 7. Test Runner
- `TestRunner.java` - Execute Cucumber tests

### 8. Report Generator
- `ReportGenerator.java` - Generate HTML reports

## Test Scenarios

### CRUD Operations
1. **Create Device** - POST request to create new device
2. **Get All Devices** - GET request to retrieve all devices
3. **Get Device by ID** - GET specific device
4. **Update Device** - PUT request to update device
5. **Delete Device** - DELETE request to remove device

### Chained API Tests
- Create → Retrieve → Verify
- Create → Update → Verify
- Create → Delete → Verify

### Data-Driven Tests
- Multiple devices with different data sets
- Parameterized using Cucumber Examples

## Validations Implemented

✅ Status code validation  
✅ Response field presence validation  
✅ Response field value validation  
✅ Response time validation  
✅ JSON deserialization validation  
✅ Logging at each step  

## Configuration

Edit `src/test/resources/config.properties`:

```properties
baseUrl=https://restful-api.dev/api/v1
environment=QA
connectionTimeout=10000
socketTimeout=10000
enableLogging=true
logLevel=INFO
```

## Troubleshooting

### Issue: Tests not running
**Solution:** Ensure Maven and Java are properly installed
```bash
mvn -version
java -version
```

### Issue: Reports not generated
**Solution:** Check if cucumber.json exists in target folder

### Issue: Connection timeout
**Solution:** Increase timeout in config.properties

## CI/CD Integration

### Jenkins Setup
1. Create new Pipeline job
2. Point to Jenkinsfile in repository
3. Configure parameters (TAG, ENVIRONMENT)
4. Run pipeline

### Command Line Execution
```bash
# With specific tag
mvn clean test -Dcucumber.filter.tags="@Smoke"

# With system properties
mvn clean test -DbaseUrl=https://api.example.com
```

## Best Practices

1. ✅ Use meaningful scenario names
2. ✅ Keep step definitions reusable
3. ✅ Use POJO for request/response
4. ✅ Externalize test data
5. ✅ Add proper logging
6. ✅ Validate response thoroughly
7. ✅ Use tags for test organization
8. ✅ Generate comprehensive reports

## Support

For issues or questions, refer to:
- Framework documentation
- REST Assured docs: https://rest-assured.io/
- Cucumber docs: https://cucumber.io/docs/cucumber/
