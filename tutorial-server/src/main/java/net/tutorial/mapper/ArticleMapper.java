package net.tutorial.mapper;

import net.tutorial.domain.dto.ArticleSearchDTO;
import net.tutorial.domain.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 面试经验文章表 Mapper 接口
 * </p>
 *
 * @author top
 * @since 2024-06-11
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    /**
     * 查询面试经验
     *
     * @param id 面试经验主键
     * @return 面试经验
     */
    public Article selectArticleById(Long id);

    /**
     * 查询面试经验列表
     *
     * @param article 面试经验
     * @return 面试经验集合
     */
    public List<Article> selectArticleList(Article article);

    /**
     * 新增面试经验
     *
     * @param article 面试经验
     * @return 结果
     */
    public int insertArticle(Article article);

    /**
     * 修改面试经验
     *
     * @param article 面试经验
     * @return 结果
     */
    public int updateArticle(Article article);

    /**
     * 删除面试经验
     *
     * @param id 面试经验主键
     * @return 结果
     */
    public int deleteArticleById(Long id);

    /**
     * 批量删除面试经验
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteArticleByIds(Long[] ids);

    List<Article> searchArticle(ArticleSearchDTO articleSearchDTO);

}
