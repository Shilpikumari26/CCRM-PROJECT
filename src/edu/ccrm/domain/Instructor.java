package edu.ccrm.domain;

import java.util.*;

public class Instructor extends Person {
    private String department;
    private Set<String> assignedCourses;
    
    public Instructor(String id, Name name, String email, String department) {
        super(id, name, email);
        this.department = department;
        this.assignedCourses = new HashSet<>();
    }
    
    @Override
    public String getRole() {
        return "Instructor";
    }
    
    @Override
    public void displayProfile() {
        System.out.println("=== Instructor Profile ===");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name.getFullName());
        System.out.println("Email: " + email);
        System.out.println("Department: " + department);
        System.out.println("Assigned Courses: " + assignedCourses.size());
    }
    
    public void assignCourse(String courseCode) {
        assignedCourses.add(courseCode);
    }
    
    public void unassignCourse(String courseCode) {
        assignedCourses.remove(courseCode);
    }
    
    // Getters
    public String getDepartment() { return department; }
    public Set<String> getAssignedCourses() { return new HashSet<>(assignedCourses); }
    
    public void setDepartment(String department) { this.department = department; }
    
    @Override
    public String toString() {
        return String.format("Instructor[%s]: %s (%s Dept.)", 
            id, name.getFullName(), department);
    }
}