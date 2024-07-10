package net.tutorial.controller;


import lombok.RequiredArgsConstructor;
import net.tutorial.response.AjaxResult;
import net.tutorial.service.IChaptersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import static net.tutorial.response.AjaxResult.success;

/**
 * <p>
 * 章节表 前端控制器
 * </p>
 *
 * @author top
 * @since 2024-06-12
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/front/chapters")
public class ChaptersController {

    private final IChaptersService chaptersService;

    /**
     * 获取书章节详情
     * @param booksId
     * @return
     */
    @GetMapping("/{booksId}")
    public AjaxResult getChaptersListByBooksId(@PathVariable("booksId") Long booksId){
        return success(chaptersService.getChaptersListByBooksId(booksId));
    }

    /**
     * 根据父章节id，查询子章节列表
     * @param parentId
     * @return
     */
    @GetMapping("/list/child/{parentId}")
    public AjaxResult getChildChaptersListByParentId(@PathVariable("parentId") Long parentId){
        return success(chaptersService.getChildChaptersListByParentId(parentId));
    }
}
