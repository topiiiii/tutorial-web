package net.tutorial.service;

import net.tutorial.domain.dto.ArticleDTO;
import net.tutorial.domain.dto.ArticleSearchDTO;
import net.tutorial.domain.dto.FavoriteDTO;
import net.tutorial.domain.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import net.tutorial.domain.vo.ArticleByUserVO;
import net.tutorial.domain.vo.ArticleDetailVO;
import net.tutorial.domain.vo.ArticleVO;

import java.util.List;

/**
 * <p>
 * 面试经验文章表 服务类
 * </p>
 *
 * @author top
 * @since 2024-06-11
 */
public interface IArticleService extends IService<Article> {


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
     * 批量删除面试经验
     *
     * @param ids 需要删除的面试经验主键集合
     * @return 结果
     */
    public int deleteArticleByIds(Long[] ids);

    /**
     * 删除面试经验信息
     *
     * @param id 面试经验主键
     * @return 结果
     */
    public int deleteArticleById(Long id);

    //首页获取面试分享
    List<ArticleVO> getArticleList(Long typeId);
    //获取面试文章详情
    ArticleDetailVO getArticleDetail(Long articleId);

    String likeArticle(FavoriteDTO collectionDTO);

    String addArticle(ArticleDTO articleDTO);

    List<ArticleByUserVO> searchArticle(ArticleSearchDTO articleSearchDTO);

}
