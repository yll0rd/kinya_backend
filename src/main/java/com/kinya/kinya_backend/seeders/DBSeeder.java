package com.kinya.kinya_backend.seeders;

import com.kinya.kinya_backend.lessonCategory.entities.LessonCategory;
import com.kinya.kinya_backend.lessonCategory.entities.Phrase;
import com.kinya.kinya_backend.lessonCategory.repositories.LessonCategoryRepository;
import com.kinya.kinya_backend.lessonCategory.repositories.PhraseRepository;
import com.kinya.kinya_backend.user.User;
import com.kinya.kinya_backend.user.UserRepository;
import com.kinya.kinya_backend.user.UserRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
@Configuration
public class DBSeeder {

    @Bean
    CommandLineRunner lessonCategorySeeder(
            LessonCategoryRepository lessonCategoryRepository,
            PhraseRepository phraseRepository
    ) {
        return args -> {
            if (lessonCategoryRepository.count() == 0) {
                // ✅ Seed Lesson Categories
                LessonCategory greetings = new LessonCategory();
                greetings.setTitle("Greetings");
                greetings.setDescription("Common Kinyarwanda greetings");
                greetings.setSlug("greetings");
                greetings.set_order(1);
                greetings = lessonCategoryRepository.save(greetings);

                LessonCategory food = new LessonCategory();
                food.setTitle("Food");
                food.setDescription("Food and dining vocabulary");
                food.setSlug("food");
                food.set_order(2);
                food = lessonCategoryRepository.save(food);

                // ✅ Seed Phrases
                Phrase hello = new Phrase();
                hello.setKinyarwanda("Muraho");
                hello.setEnglish("Hello");
                hello.setIsKeyVocabulary(true);
                hello.setLessonCategory(greetings);

                Phrase thankYou = new Phrase();
                thankYou.setKinyarwanda("Urakoze");
                thankYou.setEnglish("Thank you");
                thankYou.setIsKeyVocabulary(true);
                thankYou.setLessonCategory(greetings);

                Phrase banana = new Phrase();
                banana.setKinyarwanda("Igitoki");
                banana.setEnglish("Banana");
                banana.setIsKeyVocabulary(false);
                banana.setLessonCategory(food);

                phraseRepository.saveAll(Arrays.asList(hello, thankYou, banana));
            }

        };
    }

    @Bean
    CommandLineRunner UserSeeder(UserRepository repository) {
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

            if (repository.findByEmail(leo.getEmail()) == null ) {
                repository.save(leo);
            }
            if (repository.findByEmail(alex.getEmail()) == null) {
                repository.save(alex);
            }

        };
    }

}
