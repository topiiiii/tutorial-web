package net.tutorial.service.impl;

import lombok.RequiredArgsConstructor;
import net.tutorial.domain.entity.Rotation;
import net.tutorial.mapper.RotationMapper;
import net.tutorial.service.IRotationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author guansiyu
 * @date 2024/9/13
 */
@Service
@RequiredArgsConstructor
public class RotationServiceImpl implements IRotationService {

    private final RotationMapper rotationMapper;

    @Override
    public List<Rotation> listRotations() {
        return rotationMapper.selectRotation();
    }
}
