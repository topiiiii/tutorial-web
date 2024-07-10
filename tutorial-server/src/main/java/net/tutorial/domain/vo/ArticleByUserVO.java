package net.tutorial.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
public class ArticleByUserVO implements Serializable {

    /**
     * ID
     */
    private Long id;


    /**
     * 标题
     */
    private String title;

    /**
     * 封面
     */
    private String cover;

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

    /**
     * 评论数量
     */
    private Integer commentNum;

}
