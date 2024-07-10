package net.tutorial.controller;


import lombok.RequiredArgsConstructor;
import net.tutorial.response.AjaxResult;
import net.tutorial.service.ITypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import static net.tutorial.response.AjaxResult.success;

/**
 * <p>
 * 类型表 前端控制器
 * </p>
 *
 * @author top
 * @since 2024-06-10
 */
@RestController
@RequestMapping("/front/type")
@RequiredArgsConstructor
public class TypeController {

    private final ITypeService typeService;

    /**
     * 首页获取类型
     * @param
     * @return
     */
    @GetMapping("/list")
    public AjaxResult getTypeList(){
        return success(typeService.getTypeList());
    }
}
