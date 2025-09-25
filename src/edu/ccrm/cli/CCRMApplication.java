package edu.ccrm.cli;

import edu.ccrm.config.AppConfig;
import edu.ccrm.domain.*;
import edu.ccrm.service.*;
import edu.ccrm.io.ImportExportService;
import edu.ccrm.util.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.Comparator;

public class CCRMApplication {
    private final Scanner scanner;
    private final StudentService studentService;
    private final CourseService courseService;
    private final ImportExportService ioService;
    private final AppConfig config;
    
    public CCRMApplication() {
        this.scanner = new Scanner(System.in);
        this.studentService = new StudentService();
        this.courseService = new CourseService();
        this.ioService = new ImportExportService();
        this.config = AppConfig.getInstance();
        
        initializeTestData();
    }
    
    public static void main(String[] args) {
        System.out.println("Java Platform Information:");
        System.out.println("Java SE: Standard Edition - Desktop applications, core Java APIs");
        System.out.println("Java EE: Enterprise Edition - Web applications, distributed computing");  
        System.out.println("Java ME: Micro Edition - Embedded systems, mobile devices");
        System.out.println();
        
        CCRMApplication app = new CCRMApplication();
        app.run();
    }
    
    private void initializeTestData() {
        try {
            // Create sample students
            Student s1 = new Student("S001", "2024001", new Name("John", "Doe"), "john.doe@email.com");
            Student s2 = new Student("S002", "2024002", new Name("Jane", "Smith"), "jane.smith@email.com");
            
            studentService.save(s1);
            studentService.save(s2);
            
            // Create sample instructors
            Instructor i1 = new Instructor("I001", new Name("Dr. Alice", "Johnson"), "alice.j@email.com", "Computer Science");
            
            // Create sample courses using Builder pattern
            Course c1 = new Course.Builder("CS101", "Introduction to Programming")
                .credits(3)
                .department("Computer Science")
                .semester(Semester.FALL)
                .instructor("I001")
                .build();
            
            Course c2 = new Course.Builder("CS102", "Data Structures")
                .credits(4)
                .department("Computer Science")
                .semester(Semester.SPRING)
                .instructor("I001")
                .build();
            
            courseService.save(c1);
            courseService.save(c2);
            
            // Enroll students and assign grades
            studentService.enrollStudent("S001", "CS101");
            studentService.enrollStudent("S001", "CS102");
            s1.assignGrade("CS101", Grade.A);
            s1.assignGrade("CS102", Grade.B);
            
            studentService.enrollStudent("S002", "CS101");
            s2.assignGrade("CS101", Grade.S);
            
        } catch (IOException e) {
            System.err.println("Error initializing test data: " + e.getMessage());
        }
    }
    
    public void run() {
        System.out.println("=".repeat(50));
        System.out.println(config.getApplicationName());
        System.out.println("=".repeat(50));
        
        boolean running = true;
        
        mainLoop: while (running) {
            displayMainMenu();
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        manageStudents();
                        break;
                    case 2:
                        manageCourses();
                        break;
                    case 3:
                        manageEnrollment();
                        break;
                    case 4:
                        manageGrades();
                        break;
                    case 5:
                        generateReports();
                        break;
                    case 6:
                        importExportData();
                        break;
                    case 7:
                        backupAndShowSize();
                        break;
                    case 8:
                        demonstrateAdvancedFeatures();
                        break;
                    case 0:
                        running = false;
                        break mainLoop; // Labeled break demonstration
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (Exception e) {
                System.err.println("An error occurred: " + e.getMessage());
            }
            
            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        
        System.out.println("Thank you for using " + config.getApplicationName());
        scanner.close();
    }
    
    private void displayMainMenu() {
        System.out.println("\n" + "=".repeat(30));
        System.out.println("MAIN MENU");
        System.out.println("=".repeat(30));
        System.out.println("1. Manage Students");
        System.out.println("2. Manage Courses");
        System.out.println("3. Manage Enrollment");
        System.out.println("4. Manage Grades");
        System.out.println("5. Generate Reports");
        System.out.println("6. Import/Export Data");
        System.out.println("7. Backup & Show Backup Size");
        System.out.println("8. Advanced Features Demo");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }
    
    private void manageStudents() {
        System.out.println("\n=== STUDENT MANAGEMENT ===");
        System.out.println("1. Add Student");
        System.out.println("2. List All Students");
        System.out.println("3. Search Students");
        System.out.println("4. View Student Profile");
        System.out.println("5. Update Student");
        System.out.print("Enter choice: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    listAllStudents();
                    break;
                case 3:
                    searchStudents();
                    break;
                case 4:
                    viewStudentProfile();
                    break;
                case 5:
                    updateStudent();
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }
    
    private void addStudent() {
        try {
            System.out.print("Enter Student ID: ");
            String id = scanner.nextLine();
            
            System.out.print("Enter Registration Number: ");
            String regNo = scanner.nextLine();
            
            System.out.print("Enter First Name: ");
            String firstName = scanner.nextLine();
            
            System.out.print("Enter Last Name: ");
            String lastName = scanner.nextLine();
            
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();
            
            Student student = new Student(id, regNo, new Name(firstName, lastName), email);
            studentService.save(student);
            
            System.out.println("Student added successfully!");
            
        } catch (IOException e) {
            System.err.println("Error adding student: " + e.getMessage());
        }
    }
    
    private void listAllStudents() {
        try {
            List<Student> students = studentService.findAll();
            
            if (students.isEmpty()) {
                System.out.println("No students found.");
                return;
            }
            
            System.out.println("\n=== ALL STUDENTS ===");
            
            // Demonstrate enhanced for loop
            for (Student student : students) {
                System.out.println(student);
            }
            
            // Demonstrate traditional for loop with continue
            System.out.println("\nActive Students Only:");
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                if (!student.isActive()) {
                    continue; // Skip inactive students
                }
                System.out.println((i + 1) + ". " + student);
            }
            
        } catch (IOException e) {
            System.err.println("Error retrieving students: " + e.getMessage());
        }
    }
    
    private void searchStudents() {
        System.out.print("Enter search term (name/email): ");
        String searchTerm = scanner.nextLine();
        
        // Using lambda expressions for search
        List<Student> results = studentService.search(student -> 
            student.getName().getFullName().toLowerCase().contains(searchTerm.toLowerCase()) ||
            student.getEmail().toLowerCase().contains(searchTerm.toLowerCase())
        );
        
        if (results.isEmpty()) {
            System.out.println("No students found matching: " + searchTerm);
        } else {
            System.out.println("\nSearch Results:");
            results.forEach(System.out::println); // Method reference
        }
    }
    
    private void viewStudentProfile() {
        try {
            System.out.print("Enter Student ID: ");
            String id = scanner.nextLine();
            
            Student student = studentService.findById(id);
            if (student != null) {
                student.displayProfile(); // Polymorphism
                
                // Create and display academic record using inner class
                Student.AcademicRecord record = student.new AcademicRecord("Current Semester");
                record.printRecord();
            } else {
                System.out.println("Student not found.");
            }
        } catch (IOException e) {
            System.err.println("Error retrieving student: " + e.getMessage());
        }
    }
    
    private void updateStudent() {
        try {
            System.out.print("Enter Student ID: ");
            String id = scanner.nextLine();
            
            Student student = studentService.findById(id);
            if (student != null) {
                System.out.print("Enter new email (current: " + student.getEmail() + "): ");
                String newEmail = scanner.nextLine();
                
                if (!newEmail.trim().isEmpty()) {
                    student.setEmail(newEmail);
                    studentService.save(student);
                    System.out.println("Student updated successfully!");
                }
            } else {
                System.out.println("Student not found.");
            }
        } catch (IOException e) {
            System.err.println("Error updating student: " + e.getMessage());
        }
    }
    
    private void manageCourses() {
        System.out.println("\n=== COURSE MANAGEMENT ===");
        System.out.println("1. Add Course");
        System.out.println("2. List All Courses");
        System.out.println("3. Search Courses");
        System.out.println("4. Update Course");
        System.out.print("Enter choice: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    listAllCourses();
                    break;
                case 3:
                    searchCourses();
                    break;
                case 4:
                    updateCourse();
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }
    
    private void addCourse() {
        try {
            System.out.print("Enter Course Code: ");
            String code = scanner.nextLine();
            
            System.out.print("Enter Course Title: ");
            String title = scanner.nextLine();
            
            System.out.print("Enter Credits: ");
            int credits = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Enter Department: ");
            String department = scanner.nextLine();
            
            System.out.print("Enter Instructor ID: ");
            String instructorId = scanner.nextLine();
            
            System.out.println("Select Semester:");
            Semester[] semesters = Semester.values();
            for (int i = 0; i < semesters.length; i++) {
                System.out.println((i + 1) + ". " + semesters[i].getDisplayName());
            }
            
            int semesterChoice = Integer.parseInt(scanner.nextLine()) - 1;
            Semester semester = semesters[semesterChoice];
            
            // Using Builder pattern
            Course course = new Course.Builder(code, title)
                .credits(credits)
                .department(department)
                .instructor(instructorId)
                .semester(semester)
                .build();
            
            courseService.save(course);
            System.out.println("Course added successfully!");
            
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error adding course: " + e.getMessage());
        }
    }
    
    private void listAllCourses() {
        try {
            List<Course> courses = courseService.findAll();
            
            if (courses.isEmpty()) {
                System.out.println("No courses found.");
                return;
            }
            
            System.out.println("\n=== ALL COURSES ===");
            
            // Sort courses using lambda comparator
            courses.sort(Comparator.comparing(Course::getCode));
            
            courses.forEach(System.out::println);
            
        } catch (IOException e) {
            System.err.println("Error retrieving courses: " + e.getMessage());
        }
    }
    
    private void searchCourses() {
        System.out.println("Search by:");
        System.out.println("1. Department");
        System.out.println("2. Semester");
        System.out.println("3. Instructor");
        System.out.print("Enter choice: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter search value: ");
            String searchValue = scanner.nextLine();
            
            List<Course> results;
            
            switch (choice) {
                case 1:
                    results = courseService.filterBy("department", searchValue);
                    break;
                case 2:
                    results = courseService.filterBy("semester", searchValue);
                    break;
                case 3:
                    results = courseService.filterBy("instructor", searchValue);
                    break;
                default:
                    System.out.println("Invalid choice.");
                    return;
            }
            
            if (results.isEmpty()) {
                System.out.println("No courses found.");
            } else {
                System.out.println("\nSearch Results:");
                results.forEach(System.out::println);
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }
    
    private void updateCourse() {
        try {
            System.out.print("Enter Course Code: ");
            String code = scanner.nextLine();
            
            Course course = courseService.findById(code);
            if (course != null) {
                System.out.print("Enter new title (current: " + course.getTitle() + "): ");
                String newTitle = scanner.nextLine();
                
                if (!newTitle.trim().isEmpty()) {
                    course.setTitle(newTitle);
                    courseService.save(course);
                    System.out.println("Course updated successfully!");
                }
            } else {
                System.out.println("Course not found.");
            }
        } catch (IOException e) {
            System.err.println("Error updating course: " + e.getMessage());
        }
    }
    
    private void manageEnrollment() {
        System.out.println("\n=== ENROLLMENT MANAGEMENT ===");
        System.out.println("1. Enroll Student in Course");
        System.out.println("2. Unenroll Student from Course");
        System.out.println("3. View Student Enrollments");
        System.out.print("Enter choice: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) {
                case 1:
                    enrollStudent();
                    break;
                case 2:
                    unenrollStudent();
                    break;
                case 3:
                    viewStudentEnrollments();
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }
    
    private void enrollStudent() {
        try {
            System.out.print("Enter Student ID: ");
            String studentId = scanner.nextLine();
            
            System.out.print("Enter Course Code: ");
            String courseCode = scanner.nextLine();
            
            Student student = studentService.findById(studentId);
            Course course = courseService.findById(courseCode);
            
            if (student == null) {
                System.out.println("Student not found.");
                return;
            }
            
            if (course == null) {
                System.out.println("Course not found.");
                return;
            }
            
            // Check credit limit using custom exception
            int currentCredits = student.getEnrolledCourses().size() * 3; // Assume 3 credits per course
            int newCredits = currentCredits + course.getCredits();
            
            try {
                if (newCredits > config.getMaxCreditsPerSemester()) {
                    throw new MaxCreditLimitExceededException(newCredits, config.getMaxCreditsPerSemester());
                }
                
                studentService.enrollStudent(studentId, courseCode);
                System.out.println("Student enrolled successfully!");
                
            } catch (MaxCreditLimitExceededException e) {
                System.err.println("Enrollment failed: " + e.getMessage());
            }
            
        } catch (IOException e) {
            System.err.println("Error during enrollment: " + e.getMessage());
        }
    }
    
    private void unenrollStudent() {
        try {
            System.out.print("Enter Student ID: ");
            String studentId = scanner.nextLine();
            
            System.out.print("Enter Course Code: ");
            String courseCode = scanner.nextLine();
            
            studentService.unenrollStudent(studentId, courseCode);
            System.out.println("Student unenrolled successfully!");
            
        } catch (Exception e) {
            System.err.println("Error during unenrollment: " + e.getMessage());
        }
    }
    
    private void viewStudentEnrollments() {
        try {
            System.out.print("Enter Student ID: ");
            String studentId = scanner.nextLine();
            
            Student student = studentService.findById(studentId);
            if (student != null) {
                System.out.println("\nEnrolled Courses for " + student.getName().getFullName() + ":");
                student.getEnrolledCourses().forEach(System.out::println);
            } else {
                System.out.println("Student not found.");
            }
        } catch (IOException e) {
            System.err.println("Error retrieving enrollments: " + e.getMessage());
        }
    }
    
    private void manageGrades() {
        System.out.println("\n=== GRADE MANAGEMENT ===");
        System.out.println("1. Assign Grade");
        System.out.println("2. View Student Grades");
        System.out.println("3. Generate Transcript");
        System.out.print("Enter choice: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) {
                case 1:
                    assignGrade();
                    break;
                case 2:
                    viewStudentGrades();
                    break;
                case 3:
                    generateTranscript();
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }
    
    private void assignGrade() {
        try {
            System.out.print("Enter Student ID: ");
            String studentId = scanner.nextLine();
            
            System.out.print("Enter Course Code: ");
            String courseCode = scanner.nextLine();
            
            System.out.print("Enter marks (0-100): ");
            double marks = Double.parseDouble(scanner.nextLine());
            
            Student student = studentService.findById(studentId);
            if (student != null && student.getEnrolledCourses().contains(courseCode)) {
                Grade grade = Grade.fromMarks(marks);
                student.assignGrade(courseCode, grade);
                
                System.out.println("Grade assigned: " + grade + " (" + marks + " marks)");
            } else {
                System.out.println("Student not found or not enrolled in course.");
            }
            
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error assigning grade: " + e.getMessage());
        }
    }
    
    private void viewStudentGrades() {
        try {
            System.out.print("Enter Student ID: ");
            String studentId = scanner.nextLine();
            
            Student student = studentService.findById(studentId);
            if (student != null) {
                System.out.println("\nGrades for " + student.getName().getFullName() + ":");
                student.getCourseGrades().forEach((course, grade) -> 
                    System.out.println(course + ": " + grade + " (" + grade.getGradePoint() + " points)"));
                
                System.out.println("Overall GPA: " + String.format("%.2f", student.calculateGPA()));
            } else {
                System.out.println("Student not found.");
            }
        } catch (IOException e) {
            System.err.println("Error retrieving grades: " + e.getMessage());
        }
    }
    
    private void generateTranscript() {
        try {
            System.out.print("Enter Student ID: ");
            String studentId = scanner.nextLine();
            
            Student student = studentService.findById(studentId);
            if (student != null) {
                System.out.println("\n" + "=".repeat(50));
                System.out.println("OFFICIAL TRANSCRIPT");
                System.out.println("=".repeat(50));
                student.displayProfile(); // Uses polymorphism
                
                System.out.println("\nCourse Grades:");
                student.getCourseGrades().forEach((course, grade) -> {
                    try {
                        Course courseObj = courseService.findById(course);
                        if (courseObj != null) {
                            System.out.printf("%-10s %-30s %d credits Grade: %s (%.1f)%n",
                                course, courseObj.getTitle(), courseObj.getCredits(), 
                                grade, grade.getGradePoint());
                        }
                    } catch (IOException e) {
                        System.out.println(course + ": " + grade);
                    }
                });
                
                System.out.println("\nOverall GPA: " + String.format("%.2f", student.calculateGPA()));
                System.out.println("=".repeat(50));
            } else {
                System.out.println("Student not found.");
            }
        } catch (IOException e) {
            System.err.println("Error generating transcript: " + e.getMessage());
        }
    }
    
    private void generateReports() {
        System.out.println("\n=== REPORTS ===");
        System.out.println("1. Top Students by GPA");
        System.out.println("2. Courses by Department");
        System.out.println("3. GPA Distribution");
        System.out.print("Enter choice: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) {
                case 1:
                    showTopStudents();
                    break;
                case 2:
                    showCoursesByDepartment();
                    break;
                case 3:
                    showGPADistribution();
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }
    
    private void showTopStudents() {
        System.out.print("Enter number of top students to show: ");
        try {
            int count = Integer.parseInt(scanner.nextLine());
            List<Student> topStudents = studentService.getTopStudents(count);
            
            System.out.println("\nTop " + count + " Students by GPA:");
            for (int i = 0; i < topStudents.size(); i++) {
                Student student = topStudents.get(i);
                System.out.printf("%d. %s - GPA: %.2f%n", 
                    i + 1, student.getName().getFullName(), student.calculateGPA());
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }
    
    private void showCoursesByDepartment() {
        var coursesByDept = courseService.getCoursesByDepartment();
        
        System.out.println("\nCourses by Department:");
        coursesByDept.forEach((dept, count) -> 
            System.out.println(dept + ": " + count + " courses"));
    }
    
    private void showGPADistribution() {
        try {
            List<Student> students = studentService.findAll();
            
            // Using Stream API for GPA distribution
            long excellent = students.stream()
                .mapToDouble(Student::calculateGPA)
                .filter(gpa -> gpa >= 9.0)
                .count();
            
            long good = students.stream()
                .mapToDouble(Student::calculateGPA)
                .filter(gpa -> gpa >= 7.0 && gpa < 9.0)
                .count();
            
            long average = students.stream()
                .mapToDouble(Student::calculateGPA)
                .filter(gpa -> gpa >= 5.0 && gpa < 7.0)
                .count();
            
            long poor = students.stream()
                .mapToDouble(Student::calculateGPA)
                .filter(gpa -> gpa < 5.0)
                .count();
            
            System.out.println("\nGPA Distribution:");
            System.out.println("Excellent (9.0+): " + excellent + " students");
            System.out.println("Good (7.0-8.9): " + good + " students");
            System.out.println("Average (5.0-6.9): " + average + " students");
            System.out.println("Poor (<5.0): " + poor + " students");
            
        } catch (IOException e) {
            System.err.println("Error generating GPA distribution: " + e.getMessage());
        }
    }
    
    private void importExportData() {
        System.out.println("\n=== IMPORT/EXPORT DATA ===");
        System.out.println("1. Export Data to CSV");
        System.out.println("2. Import Data from CSV (Not implemented in demo)");
        System.out.print("Enter choice: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            
            if (choice == 1) {
                List<Student> students = studentService.findAll();
                List<Course> courses = courseService.findAll();
                
                ioService.exportData(students, courses);
                System.out.println("Data exported successfully!");
            } else {
                System.out.println("Import feature not implemented in this demo.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        } catch (IOException e) {
            System.err.println("Error during import/export: " + e.getMessage());
        }
    }
    
    private void backupAndShowSize() {
        try {
            Path backupPath = ioService.createBackup();
            System.out.println("Backup created at: " + backupPath.toAbsolutePath());
            
            // Demonstrate recursive utility
            long size = FileUtils.calculateDirectorySize(backupPath);
            System.out.println("Backup size: " + size + " bytes");
            
            System.out.println("\nBackup directory structure:");
            FileUtils.listFilesByDepth(backupPath, 2);
            
        } catch (IOException e) {
            System.err.println("Error creating backup: " + e.getMessage());
        }
    }
    
    private void demonstrateAdvancedFeatures() {
        System.out.println("\n=== ADVANCED FEATURES DEMO ===");
        
        // Demonstrate Arrays utility
        String[] courseCodes = {"CS101", "CS102", "CS201", "CS301"};
        Arrays.sort(courseCodes);
        System.out.println("Sorted course codes: " + Arrays.toString(courseCodes));
        
        // Demonstrate String methods
        String sampleText = "  Campus Course Records Manager  ";
        System.out.println("Original: '" + sampleText + "'");
        System.out.println("Trimmed: '" + sampleText.trim() + "'");
        System.out.println("Substring: '" + sampleText.substring(2, 8) + "'");
        
        // Demonstrate bitwise operators
        int permissions = 7; // 111 in binary (read, write, execute)
        System.out.println("Permissions: " + permissions);
        System.out.println("Has read permission: " + ((permissions & 4) != 0));
        System.out.println("Has write permission: " + ((permissions & 2) != 0));
        
        // Demonstrate instanceof and casting
        try {
            List<Student> students = studentService.findAll();
            if (!students.isEmpty()) {
                Person person = students.get(0); // Upcast
                
                if (person instanceof Student) { // instanceof check
                    Student student = (Student) person; // Downcast
                    System.out.println("Downcasted student: " + student.getRegNo());
                }
            }
        } catch (IOException e) {
            System.err.println("Error in advanced features demo: " + e.getMessage());
        }
        
        // Demonstrate anonymous inner class
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous inner class executed!");
            }
        };
        task.run();
        
        // Demonstrate assertions (must be enabled with -ea flag)
        try {
            assert config.getMaxCreditsPerSemester() > 0 : "Max credits must be positive";
            System.out.println("Assertion passed: Max credits = " + config.getMaxCreditsPerSemester());
        } catch (AssertionError e) {
            System.err.println("Assertion failed: " + e.getMessage());
        }
        
        // Demonstrate do-while loop
        int counter = 0;
        do {
            System.out.println("Do-while iteration: " + counter);
            counter++;
        } while (counter < 2);
        
        // Demonstrate while loop with break
        int whileCounter = 0;
        while (true) {
            if (whileCounter >= 2) {
                break; // Demonstrate break
            }
            System.out.println("While iteration: " + whileCounter);
            whileCounter++;
        }
    }
}