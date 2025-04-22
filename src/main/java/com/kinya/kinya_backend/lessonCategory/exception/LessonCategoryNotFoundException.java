package com.kinya.kinya_backend.lessonCategory.exception;

public class LessonCategoryNotFoundException extends RuntimeException  {
    public LessonCategoryNotFoundException() {
        super("Lesson Category Not Found");
    }
}
