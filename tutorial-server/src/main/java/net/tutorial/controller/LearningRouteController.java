package net.tutorial.controller;


import lombok.RequiredArgsConstructor;
import net.tutorial.response.AjaxResult;
import net.tutorial.service.ILearningRouteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import static net.tutorial.response.AjaxResult.success;

/**
 * <p>
 * 学习路线表 前端控制器
 * </p>
 *
 * @author top
 * @since 2024-06-10
 */
@RestController
@RequestMapping("/front/learning-route")
@RequiredArgsConstructor
public class LearningRouteController {

    private final ILearningRouteService learningRouteService;

    /**
     * 首页获取学习路线列表
     * @param typeId
     * @return
     */
    @GetMapping("/list/{typeId}")
    public AjaxResult getLearningRouteList(@PathVariable("typeId") Long typeId){
        return success(learningRouteService.getLearningRouteList(typeId));
    }
}
