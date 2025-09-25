package edu.ccrm.service;

import edu.ccrm.domain.*;
import edu.ccrm.util.ValidationException;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StudentService implements Persistable<Student>, Searchable<Student> {
    private Map<String, Student> students;
    
    public StudentService() {
        this.students = new HashMap<>();
    }
    
    @Override
    public void save(Student student) throws IOException {
        if (student == null || student.getId() == null) {
            throw new ValidationException("Student and ID cannot be null");
        }
        students.put(student.getId(), student);
    }
    
    @Override
    public Student findById(String id) throws IOException {
        return students.get(id);
    }
    
    @Override
    public List<Student> findAll() throws IOException {
        return new ArrayList<>(students.values());
    }
    
    @Override
    public void delete(String id) throws IOException {
        Student student = students.get(id);
        if (student != null) {
            student.setActive(false);
        }
    }
    
    @Override
    public List<Student> search(Predicate<Student> criteria) {
        return students.values().stream()
            .filter(criteria)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<Student> filterBy(String field, String value) {
        switch (field.toLowerCase()) {
            case "name":
                return search(s -> s.getName().getFullName().toLowerCase().contains(value.toLowerCase()));
            case "email":
                return search(s -> s.getEmail().toLowerCase().contains(value.toLowerCase()));
            default:
                return new ArrayList<>();
        }
    }
    
    public void enrollStudent(String studentId, String courseCode) {
        Student student = students.get(studentId);
        if (student != null) {
            student.enrollInCourse(courseCode);
        }
    }
    
    public void unenrollStudent(String studentId, String courseCode) {
        Student student = students.get(studentId);
        if (student != null) {
            student.unenrollFromCourse(courseCode);
        }
    }
    
    public List<Student> getTopStudents(int count) {
        return students.values().stream()
            .sorted((s1, s2) -> Double.compare(s2.calculateGPA(), s1.calculateGPA()))
            .limit(count)
            .collect(Collectors.toList());
    }
}