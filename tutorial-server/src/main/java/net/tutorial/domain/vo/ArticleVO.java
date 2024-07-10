package net.tutorial.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
public class ArticleVO implements Serializable {

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
