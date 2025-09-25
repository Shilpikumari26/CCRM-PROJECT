package edu.ccrm.io;

import edu.ccrm.domain.*;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class ImportExportService {
    private static final String DATA_DIR = "data";
    private static final String BACKUP_DIR = "backups";
    
    // Static nested class for CSV operations
    public static class CSVHandler {
        public static void exportStudents(List<Student> students, Path filePath) throws IOException {
            List<String> lines = students.stream()
                .map(s -> String.format("%s,%s,%s,%s,%.2f", 
                    s.getId(), s.getRegNo(), s.getName().getFullName(), 
                    s.getEmail(), s.calculateGPA()))
                .collect(Collectors.toList());
            
            lines.add(0, "ID,RegNo,Name,Email,GPA"); // Header
            Files.write(filePath, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }
        
        public static void exportCourses(List<Course> courses, Path filePath) throws IOException {
            List<String> lines = courses.stream()
                .map(c -> String.format("%s,%s,%d,%s,%s,%s", 
                    c.getCode(), c.getTitle(), c.getCredits(), 
                    c.getDepartment(), c.getSemester(), c.getInstructorId()))
                .collect(Collectors.toList());
            
            lines.add(0, "Code,Title,Credits,Department,Semester,InstructorId"); // Header
            Files.write(filePath, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }
    }
    
    public void exportData(List<Student> students, List<Course> courses) throws IOException {
        Path dataDir = Paths.get(DATA_DIR);
        if (!Files.exists(dataDir)) {
            Files.createDirectories(dataDir);
        }
        
        CSVHandler.exportStudents(students, dataDir.resolve("students.csv"));
        CSVHandler.exportCourses(courses, dataDir.resolve("courses.csv"));
        
        System.out.println("Data exported to " + dataDir.toAbsolutePath());
    }
    
    public Path createBackup() throws IOException {
        Path dataDir = Paths.get(DATA_DIR);
        Path backupDir = Paths.get(BACKUP_DIR);
        
        if (!Files.exists(backupDir)) {
            Files.createDirectories(backupDir);
        }
        
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        Path timestampedBackup = backupDir.resolve("backup_" + timestamp);
        
        if (Files.exists(dataDir)) {
            copyDirectory(dataDir, timestampedBackup);
        }
        
        return timestampedBackup;
    }
    
    private void copyDirectory(Path source, Path target) throws IOException {
        Files.walk(source).forEach(sourcePath -> {
            try {
                Path targetPath = target.resolve(source.relativize(sourcePath));
                if (Files.isDirectory(sourcePath)) {
                    Files.createDirectories(targetPath);
                } else {
                    Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}