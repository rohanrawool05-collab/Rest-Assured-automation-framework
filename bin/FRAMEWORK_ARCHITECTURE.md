# Framework Architecture

## High-Level Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                    TEST EXECUTION LAYER                      │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐      │
│  │   Maven      │  │   Jenkins    │  │   IDE        │      │
│  │   Command    │  │   Pipeline   │  │   Runner     │      │
│  └──────┬───────┘  └──────┬───────┘  └──────┬───────┘      │
└─────────┼──────────────────┼──────────────────┼─────────────┘
          │                  │                  │
          └──────────────────┼──────────────────┘
                             │
┌────────────────────────────┼─────────────────────────────────┐
│                    TEST RUNNER LAYER                          │
│                   ┌────────┴────────┐                         │
│                   │  TestRunner.java │                        │
│                   │  (Cucumber)      │                        │
│                   └────────┬────────┘                         │
└────────────────────────────┼─────────────────────────────────┘
                             │
┌────────────────────────────┼─────────────────────────────────┐
│                    FEATURE FILES LAYER                        │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐       │
│  │ device_crud  │  │ parameterized│  │ api_         │       │
│  │ .feature     │  │ _tests       │  │ validations  │       │
│  └──────┬───────┘  └──────┬───────┘  └──────┬───────┘       │
└─────────┼──────────────────┼──────────────────┼──────────────┘
          │                  │                  │
          └──────────────────┼──────────────────┘
                             │
┌────────────────────────────┼─────────────────────────────────┐
│                  STEP DEFINITIONS LAYER                       │
│                   ┌────────┴────────┐                         │
│                   │  DeviceSteps    │                         │
│                   │  .java          │                         │
│                   └────────┬────────┘                         │
└────────────────────────────┼─────────────────────────────────┘
                             │
          ┌──────────────────┼──────────────────┐
          │                  │                  │
┌─────────┴─────────┐ ┌──────┴──────┐ ┌────────┴────────┐
│   UTILS LAYER     │ │  POJO LAYER │ │  DATA LAYER     │
│ ┌───────────────┐ │ │ ┌─────────┐ │ │ ┌─────────────┐ │
│ │RequestSpec    │ │ │ │Device   │ │ │ │TestData     │ │
│ │Builder        │ │ │ │Request  │ │ │ │Builder      │ │
│ └───────────────┘ │ │ └─────────┘ │ │ └─────────────┘ │
│ ┌───────────────┐ │ │ ┌─────────┐ │ │ ┌─────────────┐ │
│ │ConfigReader   │ │ │ │Device   │ │ │ │config.      │ │
│ │               │ │ │ │Response │ │ │ │properties   │ │
│ └───────────────┘ │ │ └─────────┘ │ │ └─────────────┘ │
└───────────────────┘ └─────────────┘ └─────────────────┘
          │                  │                  │
          └──────────────────┼──────────────────┘
                             │
┌────────────────────────────┼─────────────────────────────────┐
│                    API RESOURCES LAYER                        │
│                   ┌────────┴────────┐                         │
│                   │  APIResources   │                         │
│                   │  (Enum)         │                         │
│                   └────────┬────────┘                         │
└────────────────────────────┼─────────────────────────────────┘
                             │
┌────────────────────────────┼─────────────────────────────────┐
│                    REST ASSURED LAYER                         │
│                   ┌────────┴────────┐                         │
│                   │  REST Assured   │                         │
│                   │  Library        │                         │
│                   └────────┬────────┘                         │
└────────────────────────────┼─────────────────────────────────┘
                             │
                             ▼
                    ┌────────────────┐
                    │   REST API     │
                    │   Endpoint     │
                    └────────────────┘
```

## Component Interaction Flow

```
1. Test Execution Trigger
   ↓
2. TestRunner loads Feature Files
   ↓
3. Cucumber parses Gherkin scenarios
   ↓
4. Step Definitions execute
   ↓
5. Utils build Request Specification
   ↓
6. POJO serializes request payload
   ↓
7. REST Assured sends HTTP request
   ↓
8. API returns response
   ↓
9. POJO deserializes response
   ↓
10. Assertions validate response
    ↓
11. Hooks execute post-conditions
    ↓
12. Reports generated
```

## Data Flow

```
config.properties
       ↓
   ConfigReader
       ↓
RequestSpecBuilder → REST Assured → API
       ↑                              ↓
   TestData                       Response
   Builder                            ↓
       ↑                          POJO
   Feature                    Deserialization
   File                              ↓
       ↓                        Validations
   Step                              ↓
   Definitions                   Assertions
       ↓                              ↓
   Hooks                          Reports
```

## Reporting Flow

```
Test Execution
       ↓
Cucumber Plugin
       ↓
   cucumber.json
       ↓
ReportGenerator
       ↓
┌──────┴──────┬──────────┬──────────┐
│             │          │          │
HTML      JSON       XML      Logs
Report    Report    Report   Files
```

## CI/CD Pipeline Flow

```
Git Push
    ↓
Jenkins Webhook
    ↓
Checkout Code
    ↓
Maven Build
    ↓
Run Tests (with parameters)
    ↓
Generate Reports
    ↓
Publish Reports
    ↓
Archive Artifacts
    ↓
Send Notifications
```

## Key Design Patterns

1. **Builder Pattern** - RequestSpecBuilder
2. **Factory Pattern** - TestDataBuilder
3. **Singleton Pattern** - ConfigReader
4. **Page Object Model** - API equivalent structure
5. **Strategy Pattern** - Different HTTP methods

## Framework Layers Explained

### 1. Execution Layer
- Entry points for test execution
- Maven, Jenkins, IDE

### 2. Runner Layer
- Cucumber test runner
- Configuration and plugins

### 3. Feature Layer
- Gherkin scenarios
- Business-readable tests

### 4. Step Definition Layer
- Glue code between features and implementation
- Test logic

### 5. Utils Layer
- Reusable components
- Request/Response builders

### 6. POJO Layer
- Data models
- Serialization/Deserialization

### 7. Data Layer
- Test data management
- Configuration

### 8. API Resources Layer
- Endpoint definitions
- Centralized management

### 9. REST Assured Layer
- HTTP client
- Request/Response handling

## Scalability

The framework is designed to scale:
- ✅ Add new feature files
- ✅ Add new step definitions
- ✅ Add new POJO classes
- ✅ Add new API endpoints
- ✅ Add new test data
- ✅ Add new utilities

## Maintainability

Easy to maintain:
- ✅ Separation of concerns
- ✅ Single responsibility
- ✅ DRY principles
- ✅ Clear naming conventions
- ✅ Comprehensive documentation
