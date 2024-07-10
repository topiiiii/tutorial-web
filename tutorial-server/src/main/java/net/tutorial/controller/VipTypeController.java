package net.tutorial.controller;


import lombok.RequiredArgsConstructor;
import net.tutorial.response.AjaxResult;
import net.tutorial.service.IVipTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import static net.tutorial.response.AjaxResult.success;

/**
 * <p>
 * 会员类型表 前端控制器
 * </p>
 *
 * @author top
 * @since 2024-06-20
 */
@RestController
@RequestMapping("/front/vip-type")
@RequiredArgsConstructor
public class VipTypeController {

    private final IVipTypeService vipTypeService;

    /**
     * 获取vip类型列表
     * @return
     */
    @GetMapping("/list")
    public AjaxResult getVipTypeList(){
        return success(vipTypeService.getVipTypeList());
    }

}
