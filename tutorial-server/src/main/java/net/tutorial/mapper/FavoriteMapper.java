package net.tutorial.mapper;

import net.tutorial.domain.entity.Favorite;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户收藏表 Mapper 接口
 * </p>
 *
 * @author top
 * @since 2024-07-02
 */
public interface FavoriteMapper extends BaseMapper<Favorite> {
    /**
     * 查询用户收藏
     *
     * @param id 用户收藏主键
     * @return 用户收藏
     */
    public Favorite selectFavoriteById(Long id);

    /**
     * 查询用户收藏列表
     *
     * @param favorite 用户收藏
     * @return 用户收藏集合
     */
    public List<Favorite> selectFavoriteList(Favorite favorite);

    /**
     * 新增用户收藏
     *
     * @param favorite 用户收藏
     * @return 结果
     */
    public int insertFavorite(Favorite favorite);

    /**
     * 修改用户收藏
     *
     * @param favorite 用户收藏
     * @return 结果
     */
    public int updateFavorite(Favorite favorite);

    /**
     * 删除用户收藏
     *
     * @param id 用户收藏主键
     * @return 结果
     */
    public int deleteFavoriteById(Long id);

    /**
     * 批量删除用户收藏
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFavoriteByIds(Long[] ids);
}
