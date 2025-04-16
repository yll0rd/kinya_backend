package com.kinya.kinya_backend.lesson.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/lesson")
public class LessonController {

    @GetMapping
    public String getLessons() {
        System.out.println("I am in the lesson controller");
        return "Okayy";
    }
}
