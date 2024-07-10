package net.tutorial.controller;


import lombok.RequiredArgsConstructor;
import net.tutorial.response.AjaxResult;
import net.tutorial.service.IBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static net.tutorial.response.AjaxResult.success;

/**
 * <p>
 * 图书表 前端控制器
 * </p>
 *
 * @author top
 * @since 2024-06-10
 */
@RestController
@RequestMapping("/front/books")
@RequiredArgsConstructor
public class BooksController {

    private final IBooksService booksService;

    /**
     * 首页获取图书教程
     * @param typeId
     * @return
     */
    @GetMapping("/list/{typeId}/{category}")
    public AjaxResult getBooksListByType(@PathVariable("typeId") Long typeId,
                                         @PathVariable("category") String category){
        return success(booksService.getBooksListByType(typeId,category));
    }

    /**
     * 随机推荐图书
     * @return
     */
    @GetMapping("/recommend")
    public AjaxResult randomRecommendation(){
        return success(booksService.randomRecommendation());
    }

}
