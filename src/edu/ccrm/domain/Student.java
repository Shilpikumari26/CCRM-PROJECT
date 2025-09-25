package edu.ccrm.domain;

import java.time.LocalDate;
import java.util.*;

public class Student extends Person {
    private String regNo;
    private Set<String> enrolledCourses;
    private Map<String, Grade> courseGrades;
    private LocalDate enrollmentDate;
    
    public Student(String id, String regNo, Name name, String email) {
        super(id, name, email);
        this.regNo = regNo;
        this.enrolledCourses = new HashSet<>();
        this.courseGrades = new HashMap<>();
        this.enrollmentDate = LocalDate.now();
    }
    
    @Override
    public String getRole() {
        return "Student";
    }
    
    @Override
    public void displayProfile() {
        System.out.println("=== Student Profile ===");
        System.out.println("ID: " + id);
        System.out.println("Registration No: " + regNo);
        System.out.println("Name: " + name.getFullName());
        System.out.println("Email: " + email);
        System.out.println("Enrollment Date: " + enrollmentDate);
        System.out.println("Enrolled Courses: " + enrolledCourses.size());
        System.out.println("GPA: " + String.format("%.2f", calculateGPA()));
    }
    
    public void enrollInCourse(String courseCode) {
        enrolledCourses.add(courseCode);
    }
    
    public void unenrollFromCourse(String courseCode) {
        enrolledCourses.remove(courseCode);
        courseGrades.remove(courseCode);
    }
    
    public void assignGrade(String courseCode, Grade grade) {
        if (enrolledCourses.contains(courseCode)) {
            courseGrades.put(courseCode, grade);
        }
    }
    
    public double calculateGPA() {
        if (courseGrades.isEmpty()) return 0.0;
        
        double totalPoints = courseGrades.values().stream()
            .mapToDouble(Grade::getGradePoint)
            .sum();
        
        return totalPoints / courseGrades.size();
    }
    
    // Nested inner class for academic record
    public class AcademicRecord {
        private final String semester;
        private final Map<String, Grade> semesterGrades;
        
        public AcademicRecord(String semester) {
            this.semester = semester;
            this.semesterGrades = new HashMap<>(courseGrades);
        }
        
        public void printRecord() {
            System.out.println("Academic Record for " + semester);
            semesterGrades.forEach((course, grade) -> 
                System.out.println(course + ": " + grade));
        }
    }
    
    // Getters
    public String getRegNo() { return regNo; }
    public Set<String> getEnrolledCourses() { return new HashSet<>(enrolledCourses); }
    public Map<String, Grade> getCourseGrades() { return new HashMap<>(courseGrades); }
    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    
    @Override
    public String toString() {
        return String.format("Student[%s]: %s (RegNo: %s, GPA: %.2f)", 
            id, name.getFullName(), regNo, calculateGPA());
    }
