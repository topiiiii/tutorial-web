package net.tutorial.controller;

import lombok.RequiredArgsConstructor;
import net.tutorial.domain.entity.Tutor;
import net.tutorial.domain.entity.TutorCategory;
import net.tutorial.service.ITutorCategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/front/tutor")
public class TutorCategoryController {
    private final ITutorCategoryService tutorCategoryService;
    @GetMapping("/category")
    public List<Tutor> getTutorByCategory(@RequestParam Long categoryId){
        return tutorCategoryService.getTutorByCategory(categoryId);
    }
}
