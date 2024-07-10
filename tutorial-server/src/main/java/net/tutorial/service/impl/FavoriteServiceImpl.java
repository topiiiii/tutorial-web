package net.tutorial.service.impl;

import lombok.RequiredArgsConstructor;
import net.tutorial.domain.User;
import net.tutorial.domain.dto.FavoriteDTO;
import net.tutorial.domain.entity.Favorite;
import net.tutorial.mapper.FavoriteMapper;
import net.tutorial.service.IFavoriteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.tutorial.utils.DateUtils;
import net.tutorial.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 用户收藏表 服务实现类
 * </p>
 *
 * @author top
 * @since 2024-07-02
 */
@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl extends ServiceImpl<FavoriteMapper, Favorite> implements IFavoriteService {

    private final FavoriteMapper favoriteMapper;

    /**
     * 查询用户收藏
     *
     * @param id 用户收藏主键
     * @return 用户收藏
     */
    @Override
    public Favorite selectFavoriteById(Long id)
    {
        return favoriteMapper.selectFavoriteById(id);
    }

    /**
     * 查询用户收藏列表
     *
     * @param favorite 用户收藏
     * @return 用户收藏
     */
    @Override
    public List<Favorite> selectFavoriteList(Favorite favorite)
    {
        return favoriteMapper.selectFavoriteList(favorite);
    }

    /**
     * 新增用户收藏
     *
     * @param favorite 用户收藏
     * @return 结果
     */
    @Override
    public int insertFavorite(Favorite favorite)
    {
        favorite.setCreateTime(LocalDateTime.now());
        return favoriteMapper.insertFavorite(favorite);
    }

    /**
     * 修改用户收藏
     *
     * @param favorite 用户收藏
     * @return 结果
     */
    @Override
    public int updateFavorite(Favorite favorite)
    {
        favorite.setUpdateTime(LocalDateTime.now());
        return favoriteMapper.updateFavorite(favorite);
    }

    /**
     * 批量删除用户收藏
     *
     * @param ids 需要删除的用户收藏主键
     * @return 结果
     */
    @Override
    public int deleteFavoriteByIds(Long[] ids)
    {
        return favoriteMapper.deleteFavoriteByIds(ids);
    }

    /**
     * 删除用户收藏信息
     *
     * @param id 用户收藏主键
     * @return 结果
     */
    @Override
    public int deleteFavoriteById(Long id)
    {
        return favoriteMapper.deleteFavoriteById(id);
    }


    /**
     * 用户收藏文章
     * @param favoriteDTO
     * @return
     */
    @Override
    public String favorite(FavoriteDTO favoriteDTO) {
        User user = SecurityUtils.getLoginUserByApp().getUser();

        Favorite favorite = Favorite.builder()
                .userId(user.getId())
                .articleId(favoriteDTO.getArticleId())
                .type(favoriteDTO.getType())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();

        save(favorite);

        return "收藏成功";
    }
}
