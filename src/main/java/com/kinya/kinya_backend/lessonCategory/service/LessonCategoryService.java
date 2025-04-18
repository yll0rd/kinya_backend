package com.kinya.kinya_backend.lessonCategory.service;

import com.kinya.kinya_backend.lessonCategory.dto.LessonCategorySummaryDTO;
import com.kinya.kinya_backend.lessonCategory.repositories.LessonCategoryRepository;
import com.kinya.kinya_backend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonCategoryService {

    @Autowired
    private LessonCategoryRepository lessonCategoryRepository;

    public List<LessonCategorySummaryDTO> getLessonCategories() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

//        System.out.println("anonymousUser".equals(authentication.getPrincipal()));
        return lessonCategoryRepository.findAllWithPhraseCount("anonymousUser".equals(authentication.getPrincipal()) ? null : ((User)authentication.getPrincipal()).getId());
    }
}
