package com.kinya.kinya_backend.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class HealthCheckScheduler {
    private static final Logger logger = LoggerFactory.getLogger(HealthCheckScheduler.class);

    @Value("${api.url}")
    private String apiUrl;

    private final WebClient webclient;

    public HealthCheckScheduler(WebClient.Builder webclientBuilder) {
        this.webclient = webclientBuilder.build();
    }

    @Scheduled(cron="0 */14 * * * *")
    public void checkHealth() {
        logger.info("Checking health of api at {}", apiUrl);
        webclient.get()
                .uri(apiUrl + "/api/v1/health")
                .retrieve()
                .toBodilessEntity()
                .subscribe(
                        response -> logger.info("Get request sent successfully"),
                        error -> logger.error("Error while sending request", error)
                );
    }
}
