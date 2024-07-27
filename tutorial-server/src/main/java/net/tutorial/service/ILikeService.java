package net.tutorial.service;

import java.util.Set;

/**
 * 点赞服务
 * @author guansiyu
 * @date 2024/7/26
 */
public interface ILikeService {
    /**
     * 点赞
     * 0 chapter
     * 1 article
     * @return
     */
    public int likeObject(Long userId, int type, Long objectId);

    public int cancelLikeObject(int type, Long objectId);

    /**
     * 获取用户某一类对象的点赞集合
     *
     * @param type
     * @return
     */
    public Set<Long> getSetUserLikesByType(int type);
}

