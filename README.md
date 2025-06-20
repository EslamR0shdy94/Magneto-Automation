# E-commerce Website Automation - TestNG & Selenium

## 🎯 Objective
This project contains automated tests for an e-commerce website ([Magento Demo](https://magento.softwaretestingboard.com)) focusing on:
- ✅ Product Search & Validation
- ✅ Adding Items to Cart & Persistence
- ✅ Checkout Process (Valid & Invalid Shipping Info)

---

## 🧪 Test Cases
| Test Case | Description |
|-----------|------------|
| TC01      | Search for valid/invalid product |
| TC02      | Add product to cart and verify contents persist |
| TC03      | Complete checkout successfully and handle invalid shipping data |

Each test case includes: objective, steps, expected results, and pass/fail criteria.

---

## 🧑‍💻 Tech Stack
- Java 17+
- Selenium WebDriver
- TestNG
- Maven
- Page Object Model (POM)

---

## 🏗️ Project Structure

```bash
AutomationTest [ecommerce_automation]
│
├── src
│   └── main
│       └── java
│           ├── pages          # Page Object classes for each page
│           └── utils          # Utility classes (e.g. WebDriverManager)
│
├── src
│   └── test
│       └── java
│           └── tests          # TestNG test classes
├── test-outputs
│       └── target
│             └── surefire-reports
│                     └── index.html  # TestNG generated report
│
├── pom.xml                    # Maven dependencies & build config
├── testng.xml                 # TestNG suite configuration
└── README.md                  # Project documentation
```
## ⚙️ Setup Instructions

### ✅ Prerequisites
- JDK 17 or higher
- Maven installed and configured
- IntelliJ IDEA or Eclipse

### 📥 Installation Steps
```bash
git clone https://github.com/EslamR0shdy94/Magneto-Automation.git
mvn clean install
```

### 🧪 Running Tests
```bash
mvn clean test
```

---

## 👨‍💻 Author

- **Eslam Roshdy**
- [LinkedIn](www.linkedin.com/in/eslam-roshdy-a638b2175)
- Email: esroshdy22@gmail.com

---