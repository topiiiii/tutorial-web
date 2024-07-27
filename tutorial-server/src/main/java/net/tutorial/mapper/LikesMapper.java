package net.tutorial.mapper;

import net.tutorial.domain.entity.Like;

import java.util.List;

/**
 * @author guansiyu
 * @date 2024/7/14
 */
public interface LikesMapper {
    /**
     * 查询点赞记录
     * @param like
     * @return
     */
    public List<Like> selectLikes(Like like);

    /**
     * 插入点赞记录
     * @param like
     * @return
     */
    public int insertLike(Like like);

    public int deleteLike(Like like);
}
