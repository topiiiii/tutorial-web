package net.tutorial.service.impl;

import lombok.AllArgsConstructor;
import net.tutorial.domain.entity.Tutor;
import net.tutorial.mapper.TutorCategoryMapper;
import net.tutorial.service.ITutorCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class TutorCategoryServiceImpl implements ITutorCategoryService {
    private TutorCategoryMapper tutorCategoryMapper;
    @Override
    public List<Tutor> getTutorByCategory(Long categoryId) {
        return tutorCategoryMapper.findByCategory(categoryId);
    }
}
