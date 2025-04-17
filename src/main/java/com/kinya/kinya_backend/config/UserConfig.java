package com.kinya.kinya_backend.config;

import com.kinya.kinya_backend.user.User;
import com.kinya.kinya_backend.user.UserRepository;
import com.kinya.kinya_backend.user.UserRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//import java.util.List;


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

            if (repository.findByEmail("l.youmbi@irembo.com") == null ) {
                repository.save(leo);
            }
            if (repository.findByEmail("alex@irembo.com") == null) {
                repository.save(alex);
            }

        };
    }
}
