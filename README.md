# WebDriver Project with Cucumber-JVM Integration

Adopt the previous task (WebDriver project with patterns implemented) to make possible test execution with Cucumber-JVM. 
Follow relevant configuration steps for jUnit/TestNg depending on the existing project architecture.

## Test Execution via Maven Commands

### Available Options
- **Browsers**: All browsers are supported (e.g., Chrome, Edge, Firefox, etc.).
- **Environments**: Both `qa` and `dev` environments are available for testing.

### Maven Commands

#### Smoke Testing
Run smoke tests using the `@Smoke` tag:
```bash
mvn test -D"cucumber.filter.tags=@Smoke" -Dbrowser=edge -Denvironment=qa
```
#### Regression Testing
Run smoke tests using the `@Smoke` tag:
```bash
mvn test -D"cucumber.filter.tags=@Smoke" -Dbrowser=edge -Denvironment=qa
```

#### Execute all tests:
```bash
mvn -Dbrowser=edge -Denvironment=qa -Dtest=TestRunner clean test
```


## Acceptance Criteria

- At least one test should use the "Scenario Outline" keyword and the “Examples:” section.
- Apply the “Background” keyword for preconditions/recurrent steps.
- Use filtering/regular expressions in Gherkin step definitions for parametrization and flexibility.
