package net.tutorial.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 相关课程推荐VO
 *
 * @author top
 * @date 2024/6/16 21:15
 */
@Data
public class recommendVideoVO implements Serializable {

    //书名
    private Long id;


    /**
     * 书名
     */
    private String name;

    /**
     * 封面
     */
    private String cover;

    /**
     * 章节id
     */
    private Long chapterId;

    /**
     * 浏览量
     */
    private Integer views;

}
