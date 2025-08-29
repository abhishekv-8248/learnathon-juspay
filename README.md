# Snapdeal E-commerce Automation

## 📋 Overview
This project automates the Snapdeal e-commerce website from product search to payment page using Selenium WebDriver with Java. The framework follows Behavior Driven Development (BDD) approach using Cucumber and implements Page Object Model design pattern for maintainable and scalable test automation.

## 🏗️ Architecture
- **Framework**: BDD (Behavior Driven Development) with Cucumber
- **Design Pattern**: Page Object Model (POM)
- **Programming Language**: Java
- **Automation Tool**: Selenium WebDriver
- **Build Tool**: Maven
- **Test Runner**: Cucumber JUnit Runner

## 📁 Project Structure
```
juspay/
├── src/main/java
│   └── resources/
│       └── paymentpage.java
├── src/test/java
│   ├── pages/                    # Page Object Model classes
│   │   ├── Homepage.java
│   │   ├── Loginpage.java
│   │   └── Productpage.java
│   ├── runner/                   # Cucumber Test Runner
│   │   └── Testrunner.java
│   └── stepDefinations/          # Step Definitions
│       └── SnapdealStepDefinition.java
├── src/test/resources
│   └── feature/                  # Feature files
│       └── snapdeal.feature
├── pom.xml                       # Maven configuration
└── test-output/                  # Test reports and results
```

## 🛠️ Technology Stack
- **Java** - Core programming language
- **Selenium WebDriver** - Browser automation
- **Cucumber** - BDD framework for writing test scenarios
- **JUnit** - Test execution and assertions
- **Maven** - Dependency management and build automation
- **TestNG/JUnit Runner** - Test execution management

## 🚀 Features
- ✅ Automated navigation through Snapdeal website
- ✅ Product search and selection functionality
- ✅ User login automation
- ✅ Shopping cart management
- ✅ Checkout process automation up to payment page
- ✅ BDD test scenarios in Gherkin language
- ✅ Page Object Model for better code organization
- ✅ Comprehensive test reporting

## 📋 Prerequisites
- Java JDK 8 or higher
- Maven 3.6 or higher
- Chrome/Firefox browser
- ChromeDriver/GeckoDriver (managed via WebDriverManager recommended)
- IDE (IntelliJ IDEA/Eclipse)

## ⚙️ Installation & Setup

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd juspay
   ```

2. **Install dependencies**
   ```bash
   mvn clean install
   ```

3. **Update browser drivers**
   - Ensure ChromeDriver/GeckoDriver is in your system PATH
   - Or use WebDriverManager for automatic driver management

## 🏃‍♂️ Running Tests

### Execute all tests
```bash
mvn test
```

### Run specific feature
```bash
mvn test -Dcucumber.options="src/test/resources/feature/snapdeal.feature"
```

### Run with specific tags (if implemented)
```bash
mvn test -Dcucumber.options="--tags @smoke"
```

## 📊 Test Reports
- Test execution reports are generated in the `test-output/` directory
- Cucumber HTML reports provide detailed test execution results
- Screenshots are captured for failed test steps (if configured)

## 🔧 Configuration
- **Browser Configuration**: Update driver paths and browser settings in test runner
- **Test Data**: Modify test data in feature files or create separate data files
- **Environment URLs**: Configure base URLs in configuration files

## 📝 Test Scenarios
The automation covers the following user journey:
1. Navigate to Snapdeal homepage
2. User login functionality
3. Product search and selection
4. Add products to cart
5. Proceed through checkout process
6. Navigate to payment page

