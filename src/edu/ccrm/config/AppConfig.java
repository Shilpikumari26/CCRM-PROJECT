package edu.ccrm.config;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AppConfig {
    private static AppConfig instance;
    private static final Object lock = new Object();
    
    private final String dataDirectory;
    private final int maxCreditsPerSemester;
    private final String applicationName;
    
    private AppConfig() {
        this.dataDirectory = "data";
        this.maxCreditsPerSemester = 20;
        this.applicationName = "Campus Course & Records Manager";
    }
    
    public static AppConfig getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new AppConfig();
                }
            }
        }
        return instance;
    }
    
    public String getDataDirectory() { return dataDirectory; }
    public int getMaxCreditsPerSemester() { return maxCreditsPerSemester; }
    public String getApplicationName() { return applicationName; }
    public Path getDataPath() { return Paths.get(dataDirectory); }
}