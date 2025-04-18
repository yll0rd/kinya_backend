package com.kinya.kinya_backend.lessonCategory.repositories;

import com.kinya.kinya_backend.lessonCategory.entities.Phrase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PhraseRepository extends JpaRepository<Phrase, UUID> { }

