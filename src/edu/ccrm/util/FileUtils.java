package edu.ccrm.util;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

public class FileUtils {
    
    // Recursive method to calculate directory size
    public static long calculateDirectorySize(Path directory) throws IOException {
        if (!Files.exists(directory)) return 0;
        
        try (Stream<Path> paths = Files.walk(directory)) {
            return paths
                .filter(Files::isRegularFile)
                .mapToLong(path -> {
                    try {
                        return Files.size(path);
                    } catch (IOException e) {
                        return 0;
                    }
                })
                .sum();
        }
    }
    
    // Recursive method to list files by depth
    public static void listFilesByDepth(Path directory, int maxDepth) throws IOException {
        listFilesByDepth(directory, 0, maxDepth);
    }
    
    private static void listFilesByDepth(Path directory, int currentDepth, int maxDepth) throws IOException {
        if (currentDepth > maxDepth || !Files.exists(directory)) return;
        
        String indent = "  ".repeat(currentDepth);
        System.out.println(indent + directory.getFileName());
        
        if (Files.isDirectory(directory)) {
            try (Stream<Path> paths = Files.list(directory)) {
                paths.forEach(path -> {
                    try {
                        listFilesByDepth(path, currentDepth + 1, maxDepth);
                    } catch (IOException e) {
                        System.err.println("Error listing: " + e.getMessage());
                    }
                });
            }
        }
    }
}