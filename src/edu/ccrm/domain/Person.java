package edu.ccrm.domain;

import java.time.LocalDate;

public abstract class Person {
    protected String id;
    protected Name name;
    protected String email;
    protected LocalDate dateOfBirth;
    protected boolean isActive;
    
    public Person(String id, Name name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.isActive = true;
        this.dateOfBirth = LocalDate.now();
    }
    
    // Abstract method - must be implemented by subclasses
    public abstract String getRole();
    public abstract void displayProfile();
    
    // Getters and setters
    public String getId() { return id; }
    public Name getName() { return name; }
    public String getEmail() { return email; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public boolean isActive() { return isActive; }
    
    public void setEmail(String email) { this.email = email; }
    public void setActive(boolean active) { this.isActive = active; }
    
    @Override
    public String toString() {
        return String.format("%s: %s (%s)", getRole(), name.getFullName(), email);
    }
}