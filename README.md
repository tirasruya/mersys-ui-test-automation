# Mersys UI Automation Project

[![Java](https://img.shields.io/badge/Java-17+-blue)](https://www.java.com/)
[![Selenium](https://img.shields.io/badge/Selenium-WebDriver-green)](https://www.selenium.dev/)
[![Cucumber](https://img.shields.io/badge/Cucumber-BDD-orange)](https://cucumber.io/)
[![TestNG](https://img.shields.io/badge/TestNG-Framework-red)](https://testng.org/doc/)
[![Allure](https://img.shields.io/badge/Allure-Reports-purple)](https://docs.qameta.io/allure/)

---

## Overview
This project contains **UI automation tests** for the [Mersys](https://test.mersys.io) platform using **Selenium WebDriver**, **Cucumber (BDD)**, and **TestNG**.  

Tests cover login functionality, error validation, and other UI behaviors.

---

## Technology Stack
- **Java 17+**  
- **Selenium WebDriver** – browser automation  
- **Cucumber** – BDD test scenarios  
- **TestNG** – test execution and assertions  
- **Allure** – reporting and screenshots  
- **Log4j2** – logging  
- **Maven** – dependency management  
- **Chrome / Firefox** – supported browsers  

---

## Running Tests
### 1. Using Runner (Recommended)
Run tests via CucumberRunner.java:
```bash
mvn clean test -Dcucumber.options="--tags @login"
```

### 2. Overriding Browser
```bash
mvn clean test -Dbrowser=firefox
```

### 3. Allure Report
Generate and serve report:
```bash
mvn clean test
mvn allure:serve 
```
