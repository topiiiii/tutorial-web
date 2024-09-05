package net.tutorial.service;

import net.tutorial.domain.entity.Tutor;

import java.util.List;

public interface ITutorCategoryService {
    List<Tutor> getTutorByCategory(Long categoryId);
}
