# Test Automation Framework

## Overview
This repository contains an automated testing framework built using **Java, Selenium, Cucumber, TestNG, and JUnit**. The framework is designed for UI automation and supports **BDD-style** testing.

We are testing the functionality of **https://demo.nopcommerce.com/**, ensuring its key features work as expected through automated test cases.

---
## Pre-Requisites

Before setting up and running the project, ensure that the following dependencies and tools are installed on your system.

---

### **1. Java (JDK 8 or Later)**
- Required for running automation scripts.
- [Download and install from Oracle JDK](https://www.oracle.com/java/technologies/javase-downloads.html) or use OpenJDK.

#### **Check Installation**
```sh
   java -version
```

### **2. Apache Maven**
- Used for dependency management and project build automation.
- [Download from Apache Maven or install via a package manager.](https://maven.apache.org/download.cgi)

#### **Check Installation**
```sh
   mvn -version
```

### **3. Git**
- [Used for version control and managing the source code.](https://git-scm.com/downloads)
- Download and install from Git.

#### **Check Installation**
```sh
   git --version
```
---

## Technologies & Libraries Used
- **Java 8** - Programming language
- **Maven** - Build and dependency management tool
- **Selenium WebDriver** - Browser automation
- **Cucumber** - BDD framework for writing test scenarios
- **TestNG & JUnit** - Test execution and assertions
- **WebDriverManager** - Manages browser drivers
- **Log4j** - Logging framework for test execution tracking

---

## Project Structure

test-automation-java/  
│-- src/  
│   ├── main/  
│   │   ├── java/  
│   │   │   │   ├── utilities/            # Utility classes (e.g., WebDriver setup, Config readers)  
│   ├── test/  
│   │   ├── java/  
│   │   │   │   ├── features/             # Cucumber feature files  
│   │   │   │   ├── runners/          # Cucumber/TestNG/JUnit test runners  
│   │   │   │   ├── stepDefinitions/   # Cucumber Step Definitions  
│   │   │   │   ├── pages/            # Page Object Model classes   
│-- pom.xml                            # Maven project configuration  
│-- README.md                          # Project documentation  
│-- .gitignore                         # ingore the files when pushing to remote 

---

## Feature Files (`features/`)
Feature files contain **Gherkin syntax** scenarios.  
Example scenario for **Advanced Search**:


---

## Step Definitions (`stepdefinitions/`)
Contains Java methods implementing Cucumber steps. These methods interact with Page Object classes to perform UI actions.

---

## Page Object Model (`pages/`)
Defines page-specific elements and actions.  
Each class corresponds to a web page and follows the **Page Object Model (POM)** pattern.

---

## Utilities (`utilities/`)
Contains reusable helper classes such as:
- **DriverManager** - Handles WebDriver setup
- **ConfigReader** - Reads configuration properties
- **LoggerUtility** - Manages logging using Log4j

---

## How to Run the Tests

### Using Maven Command:
Run all tests:

mvn test -PRegression

Run Cucumber tests:

mvn test -Dcucumber.options="--tags @Regression"


---

## Reports
Test execution reports are stored in the `reports/` directory.  
- **TestNG Reports** - Generated after TestNG execution.
- **Cucumber HTML Reports** - Displays BDD test execution results.

---

## `.gitignore` Usage
The `.gitignore` file prevents unwanted files from being pushed to Git.  
Common ignored files:
- `target/` (Compiled files)
- `reports/` (Generated test reports)
- `*.log` (Log files)
- `.idea/` (IDE-specific configurations)

---

## `pom.xml` - Maven Dependencies
The `pom.xml` file manages dependencies such as:
- Selenium (`selenium-java`, `selenium-support`)
- Cucumber (`cucumber-java`, `cucumber-junit`, `cucumber-junit-platform-engine`)
- TestNG
- JUnit
- WebDriverManager
- Log4j for logging

---
## Error Screenshot Mechanism

<img width="1354" alt="image" src="https://github.com/user-attachments/assets/f3ac141c-e423-453c-bd24-0d4c55c37e83" />

---
## Allure-Reporting Mechanism

After the tests are run,use the below command to generate the report .Make sure allure is installed on your system.

```sh
   allure serve
```
<img width="1470" alt="image" src="https://github.com/user-attachments/assets/fa6a8ea8-1f2f-424c-84c8-9afc3767f4b9" />

## Conclusion
This automation framework provides an efficient way to execute UI tests using **Cucumber BDD, TestNG, and Selenium**.  
For any improvements or contributions, feel free to create a pull request.

---

## Author
(Test Automation Engineer)
