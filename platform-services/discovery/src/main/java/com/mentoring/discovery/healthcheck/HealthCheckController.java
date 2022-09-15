package com.mentoring.discovery.healthcheck;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HealthCheckController {
    private static final Logger log = LoggerFactory.getLogger(HealthCheckController.class);

    @GetMapping("/health-check")
    public String healthCheck() {
        log.info("Health-check endpoint has requested");
        return String.format("Server time is: %s", LocalDateTime.now());
    }

}