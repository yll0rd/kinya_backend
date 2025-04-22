package com.kinya.kinya_backend.lessonCategory.dto;

import java.util.UUID;

public record DetailLessonCategory(UUID id, String title, String description, String slug) {
}
