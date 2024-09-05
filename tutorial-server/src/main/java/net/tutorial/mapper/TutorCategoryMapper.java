package net.tutorial.mapper;

import net.tutorial.domain.entity.Tutor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TutorCategoryMapper {
    List<Tutor> findByCategory(Long categoryId);
}
