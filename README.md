# Campus Course & Records Manager (CCRM)

CCRM is a comprehensive Java SE console application for managing students, courses, enrollments, and academic records. It demonstrates core Java SE programming concepts and best practices.

---

## Table of Contents
- [Project Overview](#project-overview)
- [Key Features](#key-features)
- [How to Run](#how-to-run)
- [Java Platform Information](#java-platform-information)
- [Setup Instructions](#setup-instructions)
- [Project Structure](#project-structure)
- [Demonstration Map](#demonstration-map)
- [Sample Usage](#sample-usage)
- [Acknowledgments](#acknowledgments)

---

## Project Overview

Campus Course & Records Manager (CCRM) is a Java console application for educational administration. It covers:

- Object-Oriented Programming (Encapsulation, Inheritance, Abstraction, Polymorphism)
- Modern Java features (Streams, Lambda expressions, Date/Time API)
- Design patterns (Singleton, Builder)
- File I/O using NIO.2
- Exception handling strategies
- Collections framework

---

## Key Features

- Student management (CRUD operations)
- Course management with search and filtering
- Enrollment system with business rules
- Grade recording and GPA calculation
- Transcript generation
- Data import/export (CSV)
- Automated backup with timestamp
- Comprehensive reporting using Stream API

---

## How to Run

**Prerequisites:**
- JDK Version: Java 11 or higher
- IDE: Eclipse, IntelliJ IDEA, or VS Code 

**Commands:**
```bash
# Navigate to project root
cd ccrm-project

# Compile all Java files
javac -d bin src/edu/ccrm/**/*.java

# Run the application
java -cp bin edu.ccrm.cli.CCRMApplication

# Run with assertions enabled 
java -ea -cp bin edu.ccrm.cli.CCRMApplication
```

## Java Platform Information

### Java Version Evolution
- 1995: Java 1.0 – Initial release, basic OOP
- 1998: Java 1.2 – Collections framework, Swing GUI
- 2004: Java 5 – Generics, annotations, enums
- 2011: Java 7 – Try-with-resources, NIO.2
- 2014: Java 8 – Lambda, Streams
- 2017: Java 9 – Module system
- 2018: Java 10/11 – Local var, HTTP client (LTS)
- 2021: Java 17 – Pattern matching, sealed classes (LTS)
- 2024: Java 21 – Virtual threads, improved switch (LTS)

### Java Editions Comparison

| Platform  | Full Name            | Target Devices                        | Key Features                      |
|-----------|----------------------|---------------------------------------|-----------------------------------|
| Java SE   | Standard Edition     | Desktop, Servers                      | Core APIs, JVM, tools             |
| Java EE   | Enterprise Edition   | Web apps, Distributed Systems         | Servlets, JSP, EJB, JPA, Web API  |
| Java ME   | Micro Edition        | Mobile, Embedded Systems              | Lightweight APIs                  |

### Java Architecture
- **JVM:** Executes bytecode, memory management, security
- **JRE:** JVM + core libraries; required to run Java applications
- **JDK:** JRE + dev tools (javac, jar, javadoc); required for Java development

---

## Setup Instructions

### Windows Installation
1. **Download JDK:** [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://jdk.java.net/) (Java 11+)
2. **Install JDK:** Run installer (`.exe`) and note the install path (`C:\Program Files\Java\jdk-XX`)
3. **Set Environment Variables:**
   - `JAVA_HOME = C:\Program Files\Java\jdk-XX`
   - Add `%JAVA_HOME%\bin` to the `PATH` variable
4. **Verify Installation:**
```bash
java -version
javac -version
```
Expected output (Java 21 example):
```
java version "21.0.1" 2023-10-17 LTS
Java(TM) SE Runtime Environment (build 21.0.1+12-LTS-29)
Java HotSpot(TM) 64-Bit Server VM (build 21.0.1+12-LTS-29, mixed mode)
```

---

## Project Structure

```
src/
└── edu/ccrm/
    ├── cli/
    │   └── CCRMApplication.java        # Command Line Application Entry
    ├── domain/
    │   ├── Person.java                 # Abstract base class
    │   ├── Student.java                # Student entity
    │   ├── Instructor.java             # Instructor entity
    │   ├── Course.java                 # Course entity (Builder pattern)
    │   ├── Enrollment.java             # Enrollment relationship
    │   ├── Name.java                   # Immutable value class
    │   ├── Grade.java                  # Enum for grades
    │   └── Semester.java               # Enum with fields
    ├── service/
    │   ├── Persistable.java            # Generic interface
    │   ├── Searchable.java             # Interface with default methods
    │   ├── StudentService.java         # Student operations
    │   └── CourseService.java          # Course operations
    ├── io/
    │   └── ImportExportService.java    # CSV import/export, backup
    ├── util/
    │   ├── ValidationException.java    # Custom exception
    │   ├── MaxCreditLimitExceededException.java # Custom checked exception
    │   └── FileUtils.java              # Recursive utilities
    └── config/
        └── AppConfig.java              # Singleton configuration
```

---

## Demonstration Map

| Concept       | File/Class/Method           | Description                                         |
|---------------|----------------------------|-----------------------------------------------------|
| Encapsulation | Student.java                | Private fields, getters/setters                     |
| Inheritance   | Person.java → Student.java  | Abstract class inheritance                          |
| Abstraction   | Person.getRole()            | Abstract methods                                    |
| Polymorphism  | CCRMApplication.viewStudentProfile() | Virtual method calls                    |
| Singleton     | AppConfig.java              | Thread-safe singleton                               |
| Builder       | Course.Builder              | Fluent API for object creation                      |
| Exception Handling | ValidationException, MaxCreditLimitExceededException | Custom exceptions |
| Collections/Streams | StudentService.search() | Streams used for filtering and mapping              |
| File I/O (NIO.2) | ImportExportService      | Modern file operations                              |
| Recursion     | FileUtils.listFilesByDepth()| Directory traversal                                 |

---

## Sample Usage

### Test Data Files

Create a `test-data` folder with sample CSV files.

**students.csv**
```csv
ID,RegNo,Name,Email,GPA
S001,2024001,John Doe,john.doe@email.com,8.50
S002,2024002,Jane Smith,jane.smith@email.com,9.20
S003,2024003,Bob Wilson,bob.wilson@email.com,7.80
```

**courses.csv**
```csv
Code,Title,Credits,Department,Semester,InstructorId
CS101,Introduction to Programming,3,Computer Science,FALL,I001
CS102,Data Structures,4,Computer Science,SPRING,I001
MATH201,Calculus II,4,Mathematics,SPRING,I002
```

### Expected Program Flow

- Application starts with Java platform info
- Main menu appears with options for managing: students, courses, enrollments, grades, reports, data import/export, backup, demo features, and exit
- Navigate modules for CRUD, search, enroll, grade assign, transcript, export, backup, reporting

**Sample Output**
```
==================================================
    Campus Course & Records Manager
==================================================
1. Manage Students
2. Manage Courses
3. Manage Enrollment
4. Manage Grades
5. Generate Reports
6. Import/Export Data
7. Backup & Show Backup Size
8. Advanced Features Demo
9. Exit

Enter your choice:
```

---

## Enabling Assertions

Default: assertions are disabled.  
To enable all assertions:
```bash
java -ea -cp bin edu.ccrm.cli.CCRMApplication
```
Sample assertion in code:
```java
assert config.getMaxCreditsPerSemester() > 0 : "Max credits must be positive";
```

---

## Acknowledgments

This project is developed for educational purposes and demonstrates:

- Mastery of OOP principles
- Understanding of Java's type system and inheritance
- Proficiency with modern Java features (Streams, Lambdas, NIO.2)
- Best practices in design patterns
- Robust exception handling
- File I/O using contemporary APIs

## Made by SHILPI KUMARI
