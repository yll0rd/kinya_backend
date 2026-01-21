package com.kinya.kinya_backend.lessonCategory.controller;

import com.kinya.kinya_backend.lessonCategory.dto.DetailLessonCategorySummaryDto;
import com.kinya.kinya_backend.lessonCategory.dto.LessonCategorySummaryDTO;
import com.kinya.kinya_backend.lessonCategory.service.LessonCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/lesson-category")
public class LessonCategoryController {

    @Autowired
    private LessonCategoryService lessonCategoryService;

    @GetMapping
    public ResponseEntity<List<LessonCategorySummaryDTO>> getLessonCategories() {
        return ResponseEntity.ok(this.lessonCategoryService.getLessonCategories());
    }

    @GetMapping(path = "{slug}")
    public ResponseEntity<DetailLessonCategorySummaryDto> getLessonCategory(@PathVariable String slug) {
        return ResponseEntity.ok(this.lessonCategoryService.getLessonCategory(slug));
    }
}
