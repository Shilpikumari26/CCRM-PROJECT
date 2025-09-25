package edu.ccrm.service;

import edu.ccrm.domain.*;
import edu.ccrm.util.ValidationException;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CourseService implements Persistable<Course>, Searchable<Course> {
    private Map<String, Course> courses;
    
    public CourseService() {
        this.courses = new HashMap<>();
    }
    
    @Override
    public void save(Course course) throws IOException {
        if (course == null || course.getCode() == null) {
            throw new ValidationException("Course and code cannot be null");
        }
        courses.put(course.getCode(), course);
    }
    
    @Override
    public Course findById(String code) throws IOException {
        return courses.get(code);
    }
    
    @Override
    public List<Course> findAll() throws IOException {
        return new ArrayList<>(courses.values());
    }
    
    @Override
    public void delete(String code) throws IOException {
        Course course = courses.get(code);
        if (course != null) {
            course.setActive(false);
        }
    }
    
    @Override
    public List<Course> search(Predicate<Course> criteria) {
        return courses.values().stream()
            .filter(criteria)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<Course> filterBy(String field, String value) {
        switch (field.toLowerCase()) {
            case "department":
                return search(c -> c.getDepartment().toLowerCase().contains(value.toLowerCase()));
            case "instructor":
                return search(c -> c.getInstructorId().equals(value));
            case "semester":
                return search(c -> c.getSemester().name().equalsIgnoreCase(value));
            default:
                return new ArrayList<>();
        }
    }
    
    public Map<String, Long> getCoursesByDepartment() {
        return courses.values().stream()
            .collect(Collectors.groupingBy(Course::getDepartment, Collectors.counting()));
    }
}