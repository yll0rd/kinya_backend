package com.kinya.kinya_backend.lessonCategory.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "lesson_category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LessonCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(unique = true)
    private String slug;

    @Column(unique = true)
    private Integer _order;

    @OneToMany(mappedBy = "lessonCategory")
    private List<Progress> progressList;

    @OneToMany(mappedBy = "lessonCategory")
    private List<Phrase> phraseList;

    @CreationTimestamp
    @Column(updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;
}
