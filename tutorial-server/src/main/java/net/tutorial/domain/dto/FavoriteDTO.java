package net.tutorial.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * <p>
 * 用户收藏表DTO
 * </p>
 *
 * @author top
 * @since 2024-07-02
 */
@Data
public class FavoriteDTO implements Serializable {


    /**
     * 0文章or1面试经验
     */
    @NotEmpty
    private String type;

    /**
     * 收藏的id
     */
    @NotEmpty
    private Long articleId;



}
