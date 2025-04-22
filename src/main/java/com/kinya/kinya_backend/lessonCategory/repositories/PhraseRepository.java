package com.kinya.kinya_backend.lessonCategory.repositories;

import com.kinya.kinya_backend.lessonCategory.dto.PhraseDto;
import com.kinya.kinya_backend.lessonCategory.entities.Phrase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PhraseRepository extends JpaRepository<Phrase, UUID> {

    @Query("""
        SELECT new com.kinya.kinya_backend.lessonCategory.dto.PhraseDto(p.id, p.kinyarwanda, p.english, p.audioUrl)
        FROM Phrase p WHERE p.lessonCategory.id = :lessonId
""")
    List<PhraseDto> findAllByLessonId(UUID lessonId);
}

