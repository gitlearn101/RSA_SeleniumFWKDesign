# Selenium Framework Design

This repository contains a Selenium WebDriver automation framework designed for robust and scalable web application testing. It demonstrates best practices for structuring a test automation project, including Page Object Model (POM), data-driven testing, and reporting.

## Significance

This framework aims to provide:
-   **Maintainability:** Using the Page Object Model ensures that UI changes require minimal test code modifications.
-   **Reusability:** Common components and utility methods reduce code duplication.
-   **Readability:** Clear separation of concerns makes tests easy to understand and debug.
-   **Scalability:** Designed to easily accommodate a growing number of test cases and features.
-   **Reporting:** Integrated with Extent Reports for detailed and visually appealing test execution reports.

## Local Development Setup

Follow these steps to set up the project on your local machine.

### Prerequisites

Before you begin, ensure you have the following installed:
-   **Java Development Kit (JDK) 8 or higher:** [Download JDK](https://www.oracle.com/java/technologies/downloads/)
-   **Apache Maven 3.6.0 or higher:** [Download Maven](https://maven.apache.org/download.cgi)
-   **Git:** [Download Git](https://git-scm.com/downloads)
-   **Integrated Development Environment (IDE):** IntelliJ IDEA, Eclipse, or VS Code with Java extensions.

### Getting Started

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/gitlearn101/RSA_SeleniumFWKDesign.git
    cd RSA_SeleniumFWKDesign
    ```

2.  **Build the project:**
    Navigate to the project root directory and build the project using Maven:
    ```bash
    mvn clean install
    ```
    This command will download all necessary dependencies and compile the project.

### Running Tests

Tests can be executed using Maven with TestNG XML suite files.

#### Default Test Suite

To run the default test suite (`testng.xml`):
```bash
mvn test
```

#### Running Specific Test Suites (Profiles)

The `pom.xml` includes profiles to run specific test suites:

-   **Regression Suite:**
    ```bash
    mvn test -PRegression
    ```
    This will execute tests defined in `testSuites/testng.xml`.

-   **Purchase Flow Tests:**
    ```bash
    mvn test -PPurchase
    ```
    This will execute tests defined in `testSuites/Purchase.xml`.

-   **Error Validation Tests:**
    ```bash
    mvn test -PErrorValidation
    ```
    This will execute tests defined in `testSuites/ErrorValidationTest.xml`.

#### Running Tests with Specific Browser

You can specify the browser at runtime using the `browser` system property. For example, to run tests on Firefox:
```bash
mvn test -PRegression -Dbrowser=firefox
```
Or for Chrome headless mode:
```bash
mvn test -PRegression -Dbrowser=chromeheadless
```

#### Test Reports

Test execution reports are generated using Extent Reports in the `reports/` directory after test execution. Open `reports/index.html` in your browser to view detailed reports.

## Project Structure

-   `src/main/java`: Contains Page Object classes, Abstract Components, and utility resources.
-   `src/test/java`: Contains test classes, test data (JSON), and test components (BaseTest, Listeners, Retry).
-   `testSuites`: Contains TestNG XML suite files for organizing test executions.
-   `pom.xml`: Maven Project Object Model file, defining project dependencies and build profiles.

## Contributing

Contributions are welcome! Please follow these steps to contribute:
1.  Fork the repository.
2.  Create a new branch (`git checkout -b feature/your-feature-name`).
3.  Make your changes.
4.  Commit your changes (`git commit -m 'feat: Add new feature'`).
5.  Push to the branch (`git push origin feature/your-feature-name`).
6.  Open a Pull Request.

---

**Note:** Ensure your local environment variables for Java and Maven are correctly set up.
