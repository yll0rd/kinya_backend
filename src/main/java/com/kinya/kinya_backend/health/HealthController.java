
package com.kinya.kinya_backend.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> getHealth() {
        return checkHealth();
    }

    private ResponseEntity<Map<String, Object>> checkHealth() {
        Map<String, Object> healthStatus = new HashMap<>();
        Map<String, String> checks = new HashMap<>();

        // Application status
        healthStatus.put("status", "UP");
        healthStatus.put("timestamp", System.currentTimeMillis());

        // Database connectivity check
        try (Connection connection = dataSource.getConnection()) {
            if (connection.isValid(5)) {
                checks.put("database", "UP");
            } else {
                checks.put("database", "DOWN");
                healthStatus.put("status", "DOWN");
            }
        } catch (Exception e) {
            checks.put("database", "DOWN - " + e.getMessage());
            healthStatus.put("status", "DOWN");
        }

        healthStatus.put("checks", checks);

        // Return appropriate HTTP status
        if ("UP".equals(healthStatus.get("status"))) {
            return ResponseEntity.ok(healthStatus);
        } else {
            return ResponseEntity.status(503).body(healthStatus);
        }
    }
}