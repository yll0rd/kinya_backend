package com.kinya.kinya_backend.lessonCategory.repositories;

import com.kinya.kinya_backend.lessonCategory.dto.DetailLessonCategory;
import com.kinya.kinya_backend.lessonCategory.dto.LessonCategorySummaryDTO;
import com.kinya.kinya_backend.lessonCategory.entities.LessonCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LessonCategoryRepository extends JpaRepository<LessonCategory, UUID> {

    @Query("""
    SELECT new com.kinya.kinya_backend.lessonCategory.dto.LessonCategorySummaryDTO(
        lc.id,
                        lc.title,
                        lc.description,
                        lc.slug,
                        CAST(COUNT(DISTINCT p.id) AS int),
                        CAST(SUM(CASE WHEN ?1 IS NOT NULL AND prog.user.id = ?1 THEN 1 ELSE 0 END) AS int)
                    )
                    FROM LessonCategory lc
                    LEFT JOIN lc.phraseList p
                    LEFT JOIN lc.progressList prog
                    GROUP BY lc.id, lc.title, lc.description, lc.slug
""")
    List<LessonCategorySummaryDTO> findAllWithCounts(UUID userId);


    @Query("""
            SELECT new com.kinya.kinya_backend.lessonCategory.dto.DetailLessonCategory(
                lc.id,
                lc.title,
                lc.description,
                lc.slug
            )
            FROM LessonCategory lc
            WHERE lc.slug = ?1
        """)
    DetailLessonCategory findBySlug(String slug);
}
