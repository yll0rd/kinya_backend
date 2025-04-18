package com.kinya.kinya_backend.lessonCategory.entities;

import com.kinya.kinya_backend.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "progress")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "lesson_category_id")
    private LessonCategory lessonCategory;

    @Column(nullable = false)
    @Min(0)
    @Max(100)
    private Integer progress;

}
