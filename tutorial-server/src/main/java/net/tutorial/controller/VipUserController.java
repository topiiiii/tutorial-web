package net.tutorial.controller;


import lombok.RequiredArgsConstructor;
import net.tutorial.service.IVipUserService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户会员表 前端控制器
 * </p>
 *
 * @author top
 * @since 2024-07-02
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/front/vip")
public class VipUserController {

    private final IVipUserService userService;
}
