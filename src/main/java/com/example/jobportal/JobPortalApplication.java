package com.example.jobportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JobPortalApplication {

    public static void main(String[] args) {
        // Creates the uploads directory if it doesn't exist
        java.io.File dir = new java.io.File("uploads");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        SpringApplication.run(JobPortalApplication.class, args);
    }
}
