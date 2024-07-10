package net.tutorial.controller;


import lombok.RequiredArgsConstructor;
import net.tutorial.domain.page.TableDataInfo;
import net.tutorial.response.AjaxResult;
import net.tutorial.service.IVideoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import static net.tutorial.response.AjaxResult.success;
import static net.tutorial.utils.PageUtils.startPage;

/**
 * <p>
 * 视频表 前端控制器
 * </p>
 *
 * @author top
 * @since 2024-06-16
 */
@RestController
@RequestMapping("/front/video")
@RequiredArgsConstructor
public class VideoController extends BaseController{

    private final IVideoService videoService;

    /**
     * 获取视频详情
     * @param chapterId
     * @return
     */
    @GetMapping("/{chapterId}")
    public AjaxResult getVideoDetail(@PathVariable("chapterId") Long chapterId){
        return success(videoService.getVideoDetail(chapterId));
    }

    /**
     * 相关课程推荐
     * @return
     */
    @GetMapping("/recommend/{chapterId}")
    public TableDataInfo getCourseRecommendation(@PathVariable("chapterId") Long chapterId){
        startPage();
        return getDataTable(videoService.getCourseRecommendation(chapterId));
    }
}
