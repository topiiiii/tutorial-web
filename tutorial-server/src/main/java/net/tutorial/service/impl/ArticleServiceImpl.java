package net.tutorial.service.impl;

import com.baomidou.mybatisplus.extension.toolkit.Db;
import lombok.RequiredArgsConstructor;
import net.tutorial.domain.User;
import net.tutorial.domain.dto.ArticleDTO;
import net.tutorial.domain.dto.ArticleSearchDTO;
import net.tutorial.domain.dto.FavoriteDTO;
import net.tutorial.domain.entity.Article;
import net.tutorial.domain.entity.Chapters;
import net.tutorial.domain.entity.Favorite;
import net.tutorial.domain.entity.Comment;
import net.tutorial.domain.vo.ArticleByUserVO;
import net.tutorial.domain.vo.ArticleDetailVO;
import net.tutorial.domain.vo.ArticleVO;
import net.tutorial.exception.ServiceException;
import net.tutorial.mapper.ArticleMapper;
import net.tutorial.mapper.ChaptersMapper;
import net.tutorial.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.tutorial.utils.DateUtils;
import net.tutorial.utils.SecurityUtils;
import net.tutorial.utils.bean.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 面试经验文章表 服务实现类
 * </p>
 *
 * @author top
 * @since 2024-06-11
 */
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    private final ArticleMapper articleMapper;

    private final ChaptersMapper chaptersMapper;

    /**
     * 查询面试经验
     *
     * @param id 面试经验主键
     * @return 面试经验
     */
    @Override
    public Article selectArticleById(Long id)
    {
        return articleMapper.selectArticleById(id);
    }

    /**
     * 查询面试经验列表
     *
     * @param article 面试经验
     * @return 面试经验
     */
    @Override
    public List<Article> selectArticleList(Article article)
    {
        return articleMapper.selectArticleList(article);
    }

    /**
     * 新增面试经验
     *
     * @param article 面试经验
     * @return 结果
     */
    @Override
    public int insertArticle(Article article)
    {
        article.setCreateTime(LocalDateTime.now());
        return articleMapper.insertArticle(article);
    }

    /**
     * 修改面试经验
     *
     * @param article 面试经验
     * @return 结果
     */
    @Override
    public int updateArticle(Article article)
    {
        article.setUpdateTime(LocalDateTime.now());
        return articleMapper.updateArticle(article);
    }

    /**
     * 批量删除面试经验
     *
     * @param ids 需要删除的面试经验主键
     * @return 结果
     */
    @Override
    public int deleteArticleByIds(Long[] ids)
    {
        return articleMapper.deleteArticleByIds(ids);
    }

    /**
     * 删除面试经验信息
     *
     * @param id 面试经验主键
     * @return 结果
     */
    @Override
    public int deleteArticleById(Long id)
    {
        return articleMapper.deleteArticleById(id);
    }



    /**
     * 首页获取面试分享
     * @param typeId
     * @return
     */
    @Override
    public List<ArticleVO> getArticleList(Long typeId) {
        //1.根据typeId查询文章
        List<Article> articleList = lambdaQuery().eq(Article::getTypeId, typeId).list();

        //2.补充作者信息
        List<ArticleVO> articleVOList = new ArrayList<>();
        articleList.forEach(article -> {

            ArticleVO articleVO = BeanUtils.copyBean(article, ArticleVO.class);

            User user = Db.getById(article.getUserId(), User.class);
            articleVO.setNickname(user.getNickname());
            articleVO.setUserAvatar(user.getAvatar());

            articleVOList.add(articleVO);
        });

        //3.封装vo返回
        return articleVOList;
    }

    /**
     * 获取面试文章详情
     * @param articleId
     * @return
     */
    @Override
    public ArticleDetailVO getArticleDetail(Long articleId) {
        Article article = getById(articleId);

        if (article==null) {
            throw new ServiceException("文章不存在");
        }

        ArticleDetailVO articleDetailVO = BeanUtils.copyBean(article, ArticleDetailVO.class);

        User user = Db.getById(articleDetailVO.getUserId(), User.class);

        articleDetailVO.setUserAvatar(user.getAvatar());
        articleDetailVO.setNickname(user.getNickname());

        article.setView(article.getView()+1);
        save(article);

        return articleDetailVO;
    }

    /**
     * 点赞
     * @param collectionDTO
     * @return
     */
    @Override
    public String likeArticle(FavoriteDTO collectionDTO) {
        //0.检测用户是否登录
        User user = SecurityUtils.getLoginUserByApp().getUser();

        //1.面试文章点赞
        if (collectionDTO.getType().equals(Favorite.ARTICLE)) {
            //1.1给文章加点赞数量
            Article article = Db.getById(collectionDTO.getArticleId(), Article.class);
            if (article==null) {
                throw new ServiceException("不存在文章");
            }
            article.setLikeNum(article.getLikeNum()+1);
            articleMapper.updateById(article);
        }

        //2.书内容点赞
        if (collectionDTO.getType().equals(Favorite.BOOK)) {
            //2.1给书加点赞数量
            Chapters chapters = Db.lambdaQuery(Chapters.class).eq(Chapters::getId, collectionDTO.getArticleId()).one();
            if (chapters==null) {
                throw new ServiceException("不存在文章");
            }
            chapters.setLikeNum(chapters.getLikeNum()+1);
            chaptersMapper.updateById(chapters);
        }
        return "点赞成功";
    }

    /**
     * 用户添加文章
     * @param articleDTO
     * @return
     */
    @Override
    public String addArticle(ArticleDTO articleDTO) {
        //1.获取当前用户
        User user = SecurityUtils.getLoginUserByApp().getUser();

        Article article = BeanUtils.copyBean(articleDTO, Article.class);

        article.setUserId(user.getId());
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        save(article);

        return "添加成功";
    }

    /**
     * 用户搜索文章
     * @param articleSearchDTO
     * @return
     */
    @Override
    public List<ArticleByUserVO> searchArticle(ArticleSearchDTO articleSearchDTO) {
        //1.获取当前用户
        User user = SecurityUtils.getLoginUserByApp().getUser();

        //2.根据条件查询
        articleSearchDTO.setUserId(user.getId());
        articleSearchDTO.setState(articleSearchDTO.getState().equals("3") ? null : articleSearchDTO.getState());
        List<Article> articleList = articleMapper.searchArticle(articleSearchDTO);



        List<ArticleByUserVO> articleByUserVOList = BeanUtils.copyList(articleList, ArticleByUserVO.class);
        for (ArticleByUserVO articleByUserVO : articleByUserVOList) {
            List<Comment> commentList = Db.lambdaQuery(Comment.class)
                    .eq(Comment::getArticleId, articleByUserVO.getId())
                    .eq(Comment::getCommentType, "0")
                    .list();
            articleByUserVO.setCommentNum(commentList.size());
        }

        return articleByUserVOList;
    }
}
