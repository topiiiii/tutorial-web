package net.tutorial.service;

import net.tutorial.domain.entity.Rotation;

import java.util.List;

/**
 * 轮播图服务
 * @author guansiyu
 * @date 2024/9/13
 */
public interface IRotationService {
    /**
     * 获取轮播图
     * @param rotation
     * @return
     */
    public List<Rotation> listRotations();
}
