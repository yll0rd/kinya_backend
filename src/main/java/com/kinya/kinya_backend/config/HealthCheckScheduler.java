package com.kinya.kinya_backend.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;

@Component
public class HealthCheckScheduler {
    private static final Logger logger = LoggerFactory.getLogger(HealthCheckScheduler.class);

    @Value("${api.url}")
    private String apiUrl;

    private final WebClient webclient;

    public HealthCheckScheduler(WebClient.Builder webclientBuilder) {
        ConnectionProvider connectionProvider = ConnectionProvider.builder("health-check")
                .maxConnections(10)
                .maxIdleTime(Duration.ofSeconds(30))
                .maxLifeTime(Duration.ofSeconds(5))
                .pendingAcquireTimeout(Duration.ofSeconds(10))
                .build();

        HttpClient httpClient = HttpClient.create(connectionProvider)
                .responseTimeout(Duration.ofSeconds(10));

        this.webclient = webclientBuilder
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    @Scheduled(cron="0 */14 * * * *")
    public void checkHealth() {
        logger.info("Checking health of api at {}", apiUrl);
        webclient.get()
                .uri(apiUrl + "/api/v1/health")
                .retrieve()
                .toBodilessEntity()
                .timeout(Duration.ofSeconds(10))
                .subscribe(
                        response -> logger.info("Get request sent successfully"),
                        error -> logger.error("Error while sending request", error)
                );
    }
}
