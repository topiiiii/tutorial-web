package net.tutorial.service;

import net.tutorial.domain.dto.CommentDTO;
import net.tutorial.domain.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import net.tutorial.domain.vo.CommentVO;

import java.util.List;

/**
 * <p>
 * 评论表 服务类
 * </p>
 *
 * @author top
 * @since 2024-06-20
 */
public interface ICommentService extends IService<Comment> {

    /**
     * 查询评论管理
     *
     * @param id 评论管理主键
     * @return 评论管理
     */
    public Comment selectCommentById(Long id);

    /**
     * 查询评论管理列表
     *
     * @param comment 评论管理
     * @return 评论管理集合
     */
    public List<Comment> selectCommentList(Comment comment);

    /**
     * 新增评论管理
     *
     * @param comment 评论管理
     * @return 结果
     */
    public int insertComment(Comment comment);

    /**
     * 修改评论管理
     *
     * @param comment 评论管理
     * @return 结果
     */
    public int updateComment(Comment comment);

    /**
     * 批量删除评论管理
     *
     * @param ids 需要删除的评论管理主键集合
     * @return 结果
     */
    public int deleteCommentByIds(Long[] ids);

    /**
     * 删除评论管理信息
     *
     * @param id 评论管理主键
     * @return 结果
     */
    public int deleteCommentById(Long id);

    String comment(CommentDTO commentDTO);
    //获取评论
    List<CommentVO> getCommentContent(String commentType, Long articleId);
}
