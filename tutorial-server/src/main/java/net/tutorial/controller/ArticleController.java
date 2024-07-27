package net.tutorial.controller;


import lombok.RequiredArgsConstructor;
import net.tutorial.domain.dto.ArticleDTO;
import net.tutorial.domain.dto.ArticleSearchDTO;
import net.tutorial.domain.dto.FavoriteDTO;
import net.tutorial.domain.page.TableDataInfo;
import net.tutorial.response.AjaxResult;
import net.tutorial.service.IArticleService;
import net.tutorial.service.ILikeService;
import org.springframework.web.bind.annotation.*;

import static net.tutorial.response.AjaxResult.success;

/**
 * <p>
 * 面试经验文章表 前端控制器
 * </p>
 *
 * @author top
 * @since 2024-06-11
 */
@RestController
@RequestMapping("/front/article")
@RequiredArgsConstructor
public class ArticleController extends BaseController{

    private final IArticleService articleService;

    private final ILikeService likeService;

    /**
     * 首页获取面试分享
     * @param typeId
     * @return
     */
    @GetMapping("/list/{typeId}")
    public TableDataInfo getArticleList(@PathVariable("typeId") Long typeId){
        startPage();
        return getDataTable(articleService.getArticleList(typeId));
    }

    /**
     * 获取面试文章详情
     * @param articleId
     * @return
     */
    @GetMapping("/{articleId}")
    public AjaxResult getArticleDetail(@PathVariable("articleId") Long articleId){
        return success(articleService.getArticleDetail(articleId));
    }

    /**
     * 点赞
     * @param collectionDTO
     * @return
     */
    @GetMapping("/like")
    public AjaxResult likeArticle(FavoriteDTO collectionDTO){
        return success(articleService.likeArticle(collectionDTO));
    }

    /**
     * 取消点赞
     *
     * @param favoriteDTO
     * @return
     */
    @DeleteMapping("/like")
    public AjaxResult cancelLikeArticle(FavoriteDTO favoriteDTO) {
        return success(
                likeService.cancelLikeObject(
                        Integer.parseInt(favoriteDTO.getType()),
                        favoriteDTO.getArticleId()
                )
        );
    }

    /**
     * 用户添加文章
     * @param articleDTO
     * @return
     */
    @PostMapping("/add")
    public AjaxResult addArticle(@RequestBody ArticleDTO articleDTO){
        return success(articleService.addArticle(articleDTO));
    }

    /**
     * 搜搜文章
     * @param articleSearchDTO
     * @return
     */
    @GetMapping("/search")
    public TableDataInfo searchArticle(ArticleSearchDTO articleSearchDTO){
        startPage();
        return getDataTable(articleService.searchArticle(articleSearchDTO));
    }
}
