package net.tutorial.controller;


import lombok.RequiredArgsConstructor;
import net.tutorial.response.AjaxResult;
import net.tutorial.service.IContentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import static net.tutorial.response.AjaxResult.success;

/**
 * <p>
 * 内容表 前端控制器
 * </p>
 *
 * @author top
 * @since 2024-06-12
 */
@RestController
@RequestMapping("/front/content")
@RequiredArgsConstructor
public class ContentController {

    private final IContentService contentService;

    /**
     * 根据章节id查询文章内容
     * @param chapterId
     * @return
     */
    @GetMapping("/{chapterId}")
    public AjaxResult getContentByChapterId(@PathVariable("chapterId") Long chapterId){
        return success(contentService.getContentByChapterId(chapterId));
    }
}
