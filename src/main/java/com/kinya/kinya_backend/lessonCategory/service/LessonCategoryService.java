package com.kinya.kinya_backend.lessonCategory.service;

import com.kinya.kinya_backend.exceptions.NotFoundException;
import com.kinya.kinya_backend.lessonCategory.dto.DetailLessonCategory;
import com.kinya.kinya_backend.lessonCategory.dto.DetailLessonCategorySummaryDto;
import com.kinya.kinya_backend.lessonCategory.dto.LessonCategorySummaryDTO;
import com.kinya.kinya_backend.lessonCategory.dto.PhraseDto;
import com.kinya.kinya_backend.lessonCategory.entities.Progress;
import com.kinya.kinya_backend.lessonCategory.repositories.LessonCategoryRepository;
import com.kinya.kinya_backend.lessonCategory.repositories.PhraseRepository;
import com.kinya.kinya_backend.lessonCategory.repositories.ProgressRepository;
import com.kinya.kinya_backend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LessonCategoryService {

    @Autowired
    private LessonCategoryRepository lessonCategoryRepository;

    @Autowired
    private PhraseRepository phraseRepository;

    @Autowired
    private ProgressRepository progressRepository;

    public List<LessonCategorySummaryDTO> getLessonCategories() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UUID userId = authentication == null ? null : ((User)authentication.getPrincipal()).getId();
        return lessonCategoryRepository.findAllWithCounts(userId);
    }

    public DetailLessonCategorySummaryDto getLessonCategory(String slug) throws NotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        DetailLessonCategory detailLessonCategory = lessonCategoryRepository.findBySlug(slug);
        if (detailLessonCategory == null)
            throw new NotFoundException("Lesson Category");

        List<PhraseDto> phrases = phraseRepository.findAllByLessonId(detailLessonCategory.id());

        Progress progress = progressRepository.findByUserIdAndLessonCategoryId(((User)authentication.getPrincipal()).getId(), detailLessonCategory.id());

        return new DetailLessonCategorySummaryDto(
                detailLessonCategory.id(),
                detailLessonCategory.title(),
                detailLessonCategory.description(),
                detailLessonCategory.slug(),
                progress == null ? 0 : progress.getProgress(),
                phrases
        );
    }
}
