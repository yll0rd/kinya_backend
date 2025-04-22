package com.kinya.kinya_backend.lessonCategory.repositories;

import com.kinya.kinya_backend.lessonCategory.entities.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ProgressRepository extends JpaRepository<Progress, UUID> {

    @Query("""
        SELECT p FROM Progress p WHERE p.user.id = :userId AND p.lessonCategory.id = :lessonCategoryId
""")
    Progress findByUserIdAndLessonCategoryId(UUID userId, UUID lessonCategoryId);
}
