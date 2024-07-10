package net.tutorial.controller;


import lombok.RequiredArgsConstructor;
import net.tutorial.response.AjaxResult;
import net.tutorial.service.ITotalRouteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import static net.tutorial.response.AjaxResult.success;

/**
 * <p>
 * 学习路线总体表 前端控制器
 * </p>
 *
 * @author top
 * @since 2024-06-11
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/front/total-route")
public class TotalRouteController {

    private final ITotalRouteService totalRouteService;

    /**
     * 根据类型获取总体路线图
     * @param typeId
     * @return
     */
    @GetMapping("/{typeId}")
    public AjaxResult getTotalRoute(@PathVariable("typeId") Long typeId){
        return success(totalRouteService.getTotalRoute(typeId));
    }
}
