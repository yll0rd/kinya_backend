package com.kinya.kinya_backend.lessonCategory.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Phrase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String kinyarwanda;

    @Column(nullable = false)
    private String english;

    @Column()
    private String audioUrl;

    @Column(nullable = false)
    private Boolean isKeyVocabulary = false;

    @ManyToOne
    @JoinColumn(name = "lesson_category_id")
    private LessonCategory lessonCategory;
}
