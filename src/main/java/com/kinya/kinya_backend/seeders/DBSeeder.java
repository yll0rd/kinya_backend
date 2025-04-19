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

import java.util.List;

@Configuration
public class DBSeeder {

    @Bean
    CommandLineRunner lessonCategorySeeder(
            LessonCategoryRepository lessonCategoryRepository,
            PhraseRepository phraseRepository
    ) {
        return args -> {
            if (lessonCategoryRepository.count() > 0) return; // Avoid reseeding

            LessonCategory greetings = createCategory("Greetings", "Learn common greetings and introductions", 1);
            LessonCategory food = createCategory("Food & Drinks", "Vocabulary for ordering and discussing meals", 2);
            LessonCategory travel = createCategory("Travel", "Essential phrases for getting around", 3);
            LessonCategory family = createCategory("Family", "Terms for family members and relationships", 4);
            LessonCategory numbers = createCategory("Numbers", "Counting and basic mathematics", 5);
            LessonCategory time = createCategory("Time & Date", "Expressing time and scheduling", 6);

            lessonCategoryRepository.saveAll(List.of(greetings, food, travel, family, numbers, time));

            phraseRepository.saveAll(List.of(
                    // GREETINGS
                    phrase("Muraho", "Hello", true, greetings),
                    phrase("Amakuru?", "How are you?", true, greetings),
                    phrase("Nitwa John", "My name is John", true, greetings),
                    phrase("Wiriwe", "Good afternoon", false, greetings),
                    phrase("Ijoro ryiza", "Good night", false, greetings),
                    phrase("Murakoze", "Thank you", false, greetings),

                    // FOOD & DRINKS
                    phrase("Ndashaka amazi", "I want water", true, food),
                    phrase("Ifunguro riryoshye", "Delicious meal", true, food),
                    phrase("Ufite icyayi?", "Do you have tea?", false, food),
                    phrase("Ndashonje", "I am hungry", true, food),
                    phrase("Inka yokeje", "Grilled beef", false, food),
                    phrase("Igikombe cy'ikawa", "Cup of coffee", false, food),

                    // TRAVEL
                    phrase("Ndi mu rugendo", "I am traveling", true, travel),
                    phrase("Gari ya moshi iri hehe?", "Where is the train?", true, travel),
                    phrase("Ndashaka taxi", "I need a taxi", true, travel),
                    phrase("Ikarita y'ubwikorezi", "Transport card", false, travel),
                    phrase("Ujya he?", "Where are you going?", false, travel),

                    // FAMILY
                    phrase("Papa", "Father", true, family),
                    phrase("Mama", "Mother", true, family),
                    phrase("Umwana", "Child", false, family),
                    phrase("Umuvandimwe", "Sibling", false, family),
                    phrase("Nyogokuru", "Grandmother", false, family),

                    // NUMBERS
                    phrase("Rimwe", "One", true, numbers),
                    phrase("Kabiri", "Two", true, numbers),
                    phrase("Gatatu", "Three", true, numbers),
                    phrase("Kane", "Four", false, numbers),
                    phrase("Tanu", "Five", false, numbers),

                    // TIME & DATE
                    phrase("Isaha", "Hour", true, time),
                    phrase("Umunsi", "Day", true, time),
                    phrase("Amasaha angahe?", "What time is it?", true, time),
                    phrase("Tariki ya none", "Today's date", false, time),
                    phrase("Ejo hazaza", "Tomorrow", false, time)
            ));

        };
    }

    private LessonCategory createCategory(String title, String description, int order) {
        LessonCategory lc = new LessonCategory();
        lc.setTitle(title);
        lc.setDescription(description);
        lc.setSlug(title.toLowerCase().replaceAll("[^a-z0-9]+", "-"));
        lc.set_order(order);
        return lc;
    }

    private Phrase phrase(String kinya, String eng, boolean isKey, LessonCategory category) {
        Phrase p = new Phrase();
        p.setKinyarwanda(kinya);
        p.setEnglish(eng);
        p.setIsKeyVocabulary(isKey);
        p.setLessonCategory(category);
        return p;
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
