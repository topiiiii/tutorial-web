package net.tutorial.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.tutorial.domain.entity.Rotation;

import java.util.List;

/**
 * @author guansiyu
 * @date 2024/9/12
 */
public interface RotationMapper extends BaseMapper<Rotation> {

    public List<Rotation> selectRotation();
}
