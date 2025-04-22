package com.kinya.kinya_backend.lessonCategory.dto;

import java.util.UUID;

public record PhraseDto(UUID id, String kinyarwanda, String english, String audioUrl, Boolean isKeyVocabulary) {
}
