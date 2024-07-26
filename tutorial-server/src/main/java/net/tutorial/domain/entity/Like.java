package net.tutorial.domain.entity;

/**
 * 点赞关系
 * @author guansiyu
 * @date 2024/7/14
 */
public class Like {
    private Long likeId;
    private Long userId;
    private int type;
    private Long objectId;

    public Like(Long userId, int type, Long objectId) {
        this.userId = userId;
        this.type = type;
        this.objectId = objectId;
    }
}
