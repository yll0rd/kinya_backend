package com.kinya.kinya_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableScheduling
//@PropertySource("file:${user.dir}/.env")
public class KinyaBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(KinyaBackendApplication.class, args);
    }

}
