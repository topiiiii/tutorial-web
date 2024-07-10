package net.tutorial.controller;


import lombok.RequiredArgsConstructor;
import net.tutorial.domain.page.TableDataInfo;
import net.tutorial.service.IPointsLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 积分日志表 前端控制器
 * </p>
 *
 * @author top
 * @since 2024-07-05
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/front/points-log")
public class PointsLogController extends BaseController{

    private final IPointsLogService pointsLogService;

    /**
     * 用户获取积分变动日志
     * @return
     */
    @GetMapping("/list")
    public TableDataInfo getPointsLogList(){
        startPage();
        return getDataTable(pointsLogService.getPointsLogList());
    }
}
