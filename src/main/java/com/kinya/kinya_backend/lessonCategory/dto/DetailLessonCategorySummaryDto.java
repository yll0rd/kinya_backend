package com.kinya.kinya_backend.lessonCategory.dto;

import java.util.List;
import java.util.UUID;

public record DetailLessonCategorySummaryDto(UUID id, String title, String description, String slug, Integer progress, List<?> phrases) {
}
