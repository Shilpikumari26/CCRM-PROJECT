package edu.ccrm.domain;

import java.time.LocalDate;

public class Enrollment {
    private String studentId;
    private String courseCode;
    private LocalDate enrollmentDate;
    private Grade grade;
    private boolean isActive;
    
    public Enrollment(String studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.enrollmentDate = LocalDate.now();
        this.isActive = true;
    }
    
    // Getters and setters
    public String getStudentId() { return studentId; }
    public String getCourseCode() { return courseCode; }
    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public Grade getGrade() { return grade; }
    public boolean isActive() { return isActive; }
    
    public void setGrade(Grade grade) { this.grade = grade; }
    public void setActive(boolean active) { this.isActive = active; }
    
    @Override
    public String toString() {
        return String.format("Enrollment[%s -> %s]: %s (Grade: %s)", 
            studentId, courseCode, enrollmentDate, grade != null ? grade : "Not Graded");
    }
}