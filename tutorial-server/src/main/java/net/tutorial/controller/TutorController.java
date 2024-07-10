package net.tutorial.controller;


import lombok.RequiredArgsConstructor;
import net.tutorial.domain.page.TableDataInfo;
import net.tutorial.response.AjaxResult;
import net.tutorial.service.ITutorService;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import static net.tutorial.response.AjaxResult.success;

/**
 * <p>
 * 辅导班表 前端控制器
 * </p>
 *
 * @author top
 * @since 2024-07-05
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/front/tutor")
public class TutorController extends BaseController{

    private final ITutorService tutorService;

    /**
     * 获取辅导班列表
     * @return
     */
    @GetMapping("/list")
    public TableDataInfo getTutorList(){
        startPage();
        return getDataTable(tutorService.getTutorList());
    }
}
