package net.tutorial.mapper;

import net.tutorial.domain.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 评论表 Mapper 接口
 * </p>
 *
 * @author top
 * @since 2024-06-20
 */
public interface CommentMapper extends BaseMapper<Comment> {
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
     * 删除评论管理
     *
     * @param id 评论管理主键
     * @return 结果
     */
    public int deleteCommentById(Long id);

    /**
     * 批量删除评论管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCommentByIds(Long[] ids);

}
