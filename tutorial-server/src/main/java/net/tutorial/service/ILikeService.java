package net.tutorial.service;

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

}

