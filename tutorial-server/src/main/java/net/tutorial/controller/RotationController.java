package net.tutorial.controller;

import lombok.RequiredArgsConstructor;
import net.tutorial.response.AjaxResult;
import net.tutorial.service.IRotationService;
import org.apache.ibatis.annotations.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static net.tutorial.response.AjaxResult.success;

/**
 * 轮播图
 * @author guansiyu
 * @date 2024/9/13
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/front/rotation")
public class RotationController {

    private final IRotationService rotationService;
    @GetMapping
    public AjaxResult getRotations() {
        return success(rotationService.listRotations());
    }
}
