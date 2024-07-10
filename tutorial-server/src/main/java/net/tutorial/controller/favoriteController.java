package net.tutorial.controller;


import lombok.RequiredArgsConstructor;
import net.tutorial.domain.dto.FavoriteDTO;
import net.tutorial.response.AjaxResult;
import net.tutorial.service.IFavoriteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import static net.tutorial.response.AjaxResult.success;

/**
 * <p>
 * 用户收藏表 前端控制器
 * </p>
 *
 * @author top
 * @since 2024-07-02
 */
@RestController
@RequestMapping("/front/favorite")
@RequiredArgsConstructor
public class favoriteController {

    private final IFavoriteService collectionService;

    /**
     * 用户收藏
     * @param favoriteDTO
     * @return
     */
    @GetMapping()
    public AjaxResult Favorite(FavoriteDTO favoriteDTO){
        return success(collectionService.favorite(favoriteDTO));
    }

}
