package com.kinya.kinya_backend.lessonCategory.dto;

import java.util.UUID;

public record LessonCategorySummaryDTO(UUID id, String title, String description, String slug, Integer phrases, Integer progress) {
}
