package edu.ccrm.service;

import java.io.IOException;
import java.util.List;

public interface Persistable<T> {
    void save(T entity) throws IOException;
    T findById(String id) throws IOException;
    List<T> findAll() throws IOException;
    void delete(String id) throws IOException;
    
    // Default method to demonstrate interface evolution
    default void backup() throws IOException {
        System.out.println("Creating backup for " + getClass().getSimpleName());
    }
}