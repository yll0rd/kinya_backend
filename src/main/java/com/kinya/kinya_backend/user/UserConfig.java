package com.kinya.kinya_backend.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;


@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository) {
        return args -> {
            User leo = new User(
                    "Leo",
                    "l.youmbi@irembo.com",
                    new BCryptPasswordEncoder().encode("hashed_pw"),
                    UserRole.ADMIN
            );

            User alex = new User(
                    "alex",
                    "alex@irembo.com",
                    new BCryptPasswordEncoder().encode("hashed_pw")
            );

            repository.saveAll(List.of(leo, alex));
        };
    }
}
