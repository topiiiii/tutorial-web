package net.tutorial.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
public class ArticleDetailVO implements Serializable {

    /**
     * ID
     */
    private Long id;



    /**
     * 用户id
     */
    private Long userId;

    private String userAvatar;

    private String nickname;

    /**
     * 标题
     */
    private String title;

    /**
     * 封面
     */
    private String cover;

    /**
     * 文章类型（1原创，2转载，3 翻译)
     */
    private String articleType;

    /**
     * 内容
     */
    private String content;

    /**
     * 浏览量
     */
    private Integer view;

    /**
     * 点赞量
     */
    private Integer likeNum;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
