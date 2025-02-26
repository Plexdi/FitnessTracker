package com.plexdi.fitnesstrackerbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

import java.nio.file.Paths;

@SpringBootApplication
public class FitnessTrackerBackendApplication {

    public static void main(String[] args) {
        // Load .env from the resources folder
        Dotenv dotenv = Dotenv.configure()
        .directory("C:\\Users\\Thanh\\OneDrive\\Documents\\Coding\\FitnessTracker\\backend\\fitness-tracker-backend\\fitness-tracker-backend\\src\\main\\resources\\.env")  // 👈 Specify root directory
        .ignoreIfMalformed()
        .ignoreIfMissing()
        .load();


        // Set environment variables
        String dbUrl = dotenv.get("DATABASE_URL");
        String dbUsername = dotenv.get("DATABASE_USERNAME");
        String dbPassword = dotenv.get("DATABASE_PASSWORD");

        if (dbUrl == null || dbUsername == null || dbPassword == null) {
            System.err.println("❌ ERROR: Missing database environment variables. Check .env file.");
            return; // Stop execution
        }

        System.setProperty("DATABASE_URL", dbUrl);
        System.setProperty("DATABASE_USERNAME", dbUsername);
        System.setProperty("DATABASE_PASSWORD", dbPassword);

        // Run the Spring Boot application
        SpringApplication.run(FitnessTrackerBackendApplication.class, args);
    }
}
