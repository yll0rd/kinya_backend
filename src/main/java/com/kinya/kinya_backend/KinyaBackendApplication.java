package com.kinya.kinya_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@PropertySource("file:${user.dir}/.env")
public class KinyaBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(KinyaBackendApplication.class, args);
    }

}
