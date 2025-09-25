Campus Course & Records Manager (CCRM)
A comprehensive Java SE console application for managing students, courses, enrollments, and academic records.
Table of Contents

Project Overview
How to Run
Java Platform Information
Setup Instructions
Project Structure
Features Demonstration Map
Sample Usage
Acknowledgments

Project Overview
CCRM is a console-based Java application that demonstrates core Java SE concepts including:

Object-Oriented Programming (Encapsulation, Inheritance, Abstraction, Polymorphism)
Modern Java features (Streams, Lambda expressions, Date/Time API)
Design patterns (Singleton, Builder)
File I/O using NIO.2
Exception handling
Collections framework

Key Features:

Student management (CRUD operations)
Course management with search and filtering
Enrollment system with business rules
Grade recording and GPA calculation
Transcript generation
Data import/export (CSV)
Automated backup with timestamp
Comprehensive reporting using Stream API

How to Run
Prerequisites:

JDK Version: Java 11 or higher
IDE: Eclipse, IntelliJ IDEA, or VS Code (optional)

Compilation and Execution Commands:
bash# Navigate to project root directory
cd ccrm-project

# Compile all Java files
javac -d bin src/edu/ccrm/*/*.java

# Run the application
java -cp bin edu.ccrm.cli.CCRMApplication

# Run with assertions enabled (recommended)
java -ea -cp bin edu.ccrm.cli.CCRMApplication
Eclipse Setup:

Open Eclipse IDE
File → New → Java Project
Project name: "CCRM"
Create packages following the structure below
Copy source files to respective packages
Right-click on CCRMApplication.java → Run As → Java Application

Java Platform Information
Evolution of Java

1995: Java 1.0 - Initial release with basic OOP features
1998: Java 1.2 - Collections framework, Swing GUI
2004: Java 5.0 - Generics, annotations, autoboxing, enums
2011: Java 7 - Try-with-resources, diamond operator, NIO.2
2014: Java 8 - Lambda expressions, Stream API, functional interfaces
2017: Java 9 - Module system, improved Stream API
2018: Java 10 - Local variable type inference (var)
2018: Java 11 - HTTP client, string methods (LTS)
2021: Java 17 - Pattern matching, sealed classes (LTS)
2024: Java 21 - Virtual threads, pattern matching for switch (LTS)

Java Platform Comparison
PlatformFull NameTarget DevicesKey FeaturesJava SEStandard EditionDesktop apps, serversCore APIs, JVM, development toolsJava EEEnterprise EditionWeb apps, distributed systemsServlets, JSP, EJB, JPA, web servicesJava MEMicro EditionMobile devices, embedded systemsLightweight APIs, limited memory footprint
Java Architecture: JDK, JRE, JVM
JVM (Java Virtual Machine):

Runtime environment that executes Java bytecode
Platform-specific implementation
Provides memory management, garbage collection, security

JRE (Java Runtime Environment):

JVM + Core libraries + Supporting files
Required to run Java applications
End-users need JRE to execute Java programs

JDK (Java Development Kit):

JRE + Development tools (javac, jar, javadoc, etc.)
Required for Java development
Contains everything needed to develop, compile, and run Java applications

┌─────────────────────────────┐
│           JDK               │
│  ┌─────────────────────────┐│
│  │         JRE             ││
│  │  ┌─────────────────────┐││
│  │  │       JVM           │││
│  │  │                     │││
│  │  └─────────────────────┘││
│  │  + Core Libraries       ││
│  └─────────────────────────┘│
│  + Development Tools        │
│    (javac, jar, javadoc)    │
└─────────────────────────────┘
Setup Instructions
Windows Installation Steps

Download JDK:

Go to Oracle JDK or OpenJDK
Download JDK 11 or higher for Windows x64


Install JDK:

Run the downloaded installer (.exe file)
Follow installation wizard (typically installs to C:\Program Files\Java\jdk-XX)
Note the installation path


Set Environment Variables:

   JAVA_HOME = C:\Program Files\Java\jdk-21
   PATH = %PATH%;%JAVA_HOME%\bin

Verify Installation:

bash   java -version
   javac -version

Expected Output:

   java version "21.0.1" 2023-10-17 LTS
   Java(TM) SE Runtime Environment (build 21.0.1+12-LTS-29)
   Java HotSpot(TM) 64-Bit Server VM (build 21.0.1+12-LTS-29, mixed mode)
Eclipse IDE Setup

Download Eclipse:

Go to Eclipse Downloads
Download "Eclipse IDE for Java Developers"


Install Eclipse:

Extract downloaded file
Run eclipse.exe


Create New Project:

File → New → Java Project
Project name: "CCRM"
Use default JRE or configure specific JDK


Configure Run Configuration:

Right-click project → Properties → Run/Debug Settings
New → Java Application
Main class: edu.ccrm.cli.CCRMApplication
Arguments tab → VM arguments: -ea (to enable assertions)



Project Structure
src/
├── edu/ccrm/
│   ├── cli/                    # Command Line Interface
│   │   └── CCRMApplication.java
│   ├── domain/                 # Domain Models
│   │   ├── Person.java         # Abstract base class
│   │   ├── Student.java        # Student entity
│   │   ├── Instructor.java     # Instructor entity  
│   │   ├── Course.java         # Course entity (Builder pattern)
│   │   ├── Enrollment.java     # Enrollment relationship
│   │   ├── Name.java           # Immutable value class
│   │   ├── Grade.java          # Enum with constructor
│   │   └── Semester.java       # Enum with fields
│   ├── service/                # Business Logic Services
│   │   ├── Persistable.java    # Generic interface
│   │   ├── Searchable.java     # Generic interface with default methods
│   │   ├── StudentService.java # Student operations
│   │   └── CourseService.java  # Course operations
│   ├── io/                     # File I/O Operations
│   │   └── ImportExportService.java # CSV import/export, backup
│   ├── util/                   # Utility Classes
│   │   ├── ValidationException.java        # Custom exception
│   │   ├── MaxCreditLimitExceededException.java # Custom checked exception
│   │   └── FileUtils.java      # Recursive file utilities
│   └── config/                 # Configuration
│       └── AppConfig.java      # Singleton pattern
Features Demonstration Map
ConceptFile/Class/MethodDescriptionOOP PillarsEncapsulationStudent.javaPrivate fields with getters/settersInheritancePerson.java → Student.java, Instructor.javaAbstract class inheritanceAbstractionPerson.getRole(), Person.displayProfile()Abstract methodsPolymorphismCCRMApplication.viewStudentProfile()Virtual method callsAccess ModifiersPrivatePerson.id, Course.codePrivate fieldsProtectedPerson.name, Person.emailProtected for inheritancePublicAll getter methodsPublic interfaceDefaultPackage-level classesDefault accessAdvanced OOPStatic Nested ClassImportExportService.CSVHandlerUtility class inside serviceInner ClassStudent.AcademicRecordNon-static inner classAnonymous ClassCCRMApplication.demonstrateAdvancedFeatures()Runnable implementationInterfacesInterface DefinitionPersistable.java, Searchable.javaGeneric interfacesDefault MethodsPersistable.backup()Default implementationDiamond ProblemInterface multiple inheritanceResolved with explicit overrideFunctional InterfaceLambda expressions in searchPredicate, Comparator usageDesign PatternsSingletonAppConfig.javaThread-safe singletonBuilderCourse.BuilderFluent API for object creationException HandlingCustom ExceptionsValidationException, MaxCreditLimitExceededExceptionDomain-specific errorsTry-catch-finallyThroughout service classesProper exception handlingMulti-catchCCRMApplication.run()Multiple exception typesThrows declarationService method signaturesChecked exceptionsCollections & StreamsListsStudentService.findAll()ArrayList usageSetsStudent.enrolledCoursesHashSet for uniquenessMapsStudent.courseGradesHashMap for key-value pairsStream APIStudentService.search()Filter, map, collect operationsLambda expressionsCourse sorting, student filteringFunctional programmingMethod referencesresults.forEach(System.out::println)Concise syntaxFile I/O (NIO.2)Path APIImportExportService.createBackup()Modern path handlingFiles operationsCopy, move, exists, createDirectoriesFile system operationsStream processingFileUtils.calculateDirectorySize()Files.walk() with streamsDate/Time APILocalDateStudent.enrollmentDateDate without timeLocalDateTimeBackup timestamp generationDate with timeFormattingDateTimeFormatter.ofPattern()Custom date formatsLanguage FeaturesEnhanced for-eachCCRMApplication.listAllStudents()Collection iterationTraditional loopsVarious methodsfor, while, do-whileBreak/ContinueLoop control examplesJump statementsLabeled breakCCRMApplication.run()Breaking from nested loopsSwitch statementsMenu handling throughout CLIClassic and enhanced switchArraysArray operationsCCRMApplication.demonstrateAdvancedFeatures()Arrays.sort(), toString()Enhanced forArray iterationfor-each syntaxString OperationsString methodsAdvanced features demosubstring, trim, split methodsStringBuilderEfficient string buildingWhen needed for performancePrimitives & OperatorsArithmeticGPA calculationBasic math operationsLogicalConditional checks&&,BitwisePermission checking demo&,RelationalComparisons throughout==, !=, <, >, etc.Type SystemAutoboxingCollections with primitivesAutomatic conversionInstanceofType checking before castSafe downcastingCastingUpcast/downcast examplesType conversionEnumsBasic enumSemester.javaConstants with methodsEnum with constructorGrade.javaEnum with fields and behaviorImmutabilityImmutable className.javaFinal fields, no settersDefensive copyingGetters return copiesProtect internal stateRecursionRecursive methodFileUtils.listFilesByDepth()Directory traversalBase caseDepth limit checkTermination conditionAssertionsAssert statementsCCRMApplication.demonstrateAdvancedFeatures()Runtime checksInvariant checkingCredit limits, null checksPrecondition validation
Sample Usage
Sample Commands (with assertions enabled):
bash# Compile the project
javac -d bin -cp src src/edu/ccrm/cli/CCRMApplication.java src/edu/ccrm/*/*.java

# Run with assertions enabled
java -ea -cp bin edu.ccrm.cli.CCRMApplication
Test Data Files
Create a test-data folder with sample CSV files:
students.csv:
csvID,RegNo,Name,Email,GPA
S001,2024001,John Doe,john.doe@email.com,8.50
S002,2024002,Jane Smith,jane.smith@email.com,9.20
S003,2024003,Bob Wilson,bob.wilson@email.com,7.80
courses.csv:
csvCode,Title,Credits,Department,Semester,InstructorId
CS101,Introduction to Programming,3,Computer Science,FALL,I001
CS102,Data Structures,4,Computer Science,SPRING,I001  
MATH201,Calculus II,4,Mathematics,SPRING,I002
Expected Program Flow:

Application starts with Java platform information
Main menu appears with 8 options
Navigate through different modules:

Add/view students and courses
Enroll students with credit limit validation
Assign grades and generate transcripts
Export data to CSV files
Create timestamped backups
View reports using Stream API
See advanced Java features demo



Sample Output:
Java Platform Information:
Java SE: Standard Edition - Desktop applications, core Java APIs
Java EE: Enterprise Edition - Web applications, distributed computing
Java ME: Micro Edition - Embedded systems, mobile devices

==================================================
Campus Course & Records Manager
==================================================

==============================
MAIN MENU
==============================
1. Manage Students
2. Manage Courses
3. Manage Enrollment
4. Manage Grades
5. Generate Reports
6. Import/Export Data
7. Backup & Show Backup Size
8. Advanced Features Demo
0. Exit
Enter your choice:
Enabling Assertions
Assertions are disabled by default in Java. To enable them:
bash# Enable all assertions
java -ea CCRMApplication

# Enable assertions for specific package
java -ea:edu.ccrm... CCRMApplication

# Disable system assertions (but enable user assertions)
java -ea -dsa CCRMApplication
Sample assertion in code:
javaassert config.getMaxCreditsPerSemester() > 0 : "Max credits must be positive";
Acknowledgments
This project demonstrates core Java SE concepts as required by the Programming in Java course syllabus. All code is original work created for educational purposes.
Key Learning Outcomes:

Mastery of OOP principles and their practical application
Understanding of Java's type system and inheritance model
Proficiency with modern Java features (Streams, Lambda, NIO.2)
Experience with design patterns and best practices
Comprehensive exception handling strategies
File I/O operations using contemporary APIs