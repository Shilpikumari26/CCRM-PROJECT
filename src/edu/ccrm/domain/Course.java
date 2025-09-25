package edu.ccrm.domain;

import java.time.LocalDate;

public class Course {
    private String code;
    private String title;
    private int credits;
    private String instructorId;
    private Semester semester;
    private String department;
    private boolean isActive;
    private LocalDate createdDate;
    
    // Private constructor for Builder pattern
    private Course(Builder builder) {
        this.code = builder.code;
        this.title = builder.title;
        this.credits = builder.credits;
        this.instructorId = builder.instructorId;
        this.semester = builder.semester;
        this.department = builder.department;
        this.isActive = true;
        this.createdDate = LocalDate.now();
    }
    
    // Builder pattern implementation
    public static class Builder {
        private String code;
        private String title;
        private int credits;
        private String instructorId;
        private Semester semester;
        private String department;
        
        public Builder(String code, String title) {
            this.code = code;
            this.title = title;
        }
        
        public Builder credits(int credits) {
            this.credits = credits;
            return this;
        }
        
        public Builder instructor(String instructorId) {
            this.instructorId = instructorId;
            return this;
        }
        
        public Builder semester(Semester semester) {
            this.semester = semester;
            return this;
        }
        
        public Builder department(String department) {
            this.department = department;
            return this;
        }
        
        public Course build() {
            return new Course(this);
        }
    }
    
    // Getters
    public String getCode() { return code; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }
    public String getInstructorId() { return instructorId; }
    public Semester getSemester() { return semester; }
    public String getDepartment() { return department; }
    public boolean isActive() { return isActive; }
    public LocalDate getCreatedDate() { return createdDate; }
    
    // Setters
    public void setTitle(String title) { this.title = title; }
    public void setCredits(int credits) { this.credits = credits; }
    public void setInstructorId(String instructorId) { this.instructorId = instructorId; }
    public void setSemester(Semester semester) { this.semester = semester; }
    public void setDepartment(String department) { this.department = department; }
    public void setActive(boolean active) { this.isActive = active; }
    
    @Override
    public String toString() {
        return String.format("Course[%s]: %s (%d credits, %s, %s)", 
            code, title, credits, semester.getDisplayName(), department);
    }
}
