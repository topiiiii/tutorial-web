package net.tutorial.service.impl;

import com.baomidou.mybatisplus.extension.toolkit.Db;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.tutorial.annotation.IsLogin;
import net.tutorial.domain.User;
import net.tutorial.domain.dto.CommentDTO;
import net.tutorial.domain.entity.Comment;
import net.tutorial.domain.entity.Reply;
import net.tutorial.domain.vo.CommentVO;
import net.tutorial.domain.vo.ReplyVO;
import net.tutorial.mapper.CommentMapper;
import net.tutorial.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.tutorial.utils.DateUtils;
import net.tutorial.utils.SecurityUtils;
import net.tutorial.utils.bean.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author top
 * @since 2024-06-20
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    private final CommentMapper commentMapper;

    /**
     * 查询评论管理
     *
     * @param id 评论管理主键
     * @return 评论管理
     */
    @Override
    public Comment selectCommentById(Long id)
    {
        return commentMapper.selectCommentById(id);
    }

    /**
     * 查询评论管理列表
     *
     * @param comment 评论管理
     * @return 评论管理
     */
    @Override
    public List<Comment> selectCommentList(Comment comment)
    {
        return commentMapper.selectCommentList(comment);
    }

    /**
     * 新增评论管理
     *
     * @param comment 评论管理
     * @return 结果
     */
    @Override
    public int insertComment(Comment comment)
    {
        comment.setCreateTime(LocalDateTime.now());
        return commentMapper.insertComment(comment);
    }

    /**
     * 修改评论管理
     *
     * @param comment 评论管理
     * @return 结果
     */
    @Override
    public int updateComment(Comment comment)
    {
        comment.setUpdateTime(LocalDateTime.now());
        return commentMapper.updateComment(comment);
    }

    /**
     * 批量删除评论管理
     *
     * @param ids 需要删除的评论管理主键
     * @return 结果
     */
    @Override
    public int deleteCommentByIds(Long[] ids)
    {
        return commentMapper.deleteCommentByIds(ids);
    }

    /**
     * 删除评论管理信息
     *
     * @param id 评论管理主键
     * @return 结果
     */
    @Override
    public int deleteCommentById(Long id)
    {
        return commentMapper.deleteCommentById(id);
    }

    /**
     * 评论
     * @param commentDTO
     * @return
     */
    @IsLogin
    public String comment(CommentDTO commentDTO) {
        User user = SecurityUtils.getLoginUserByApp().getUser();

        Comment comment = Comment.builder()
                .articleId(commentDTO.getArticleId())
                .commentType(commentDTO.getCommentType())
                .content(commentDTO.getContent())
                .fromUid(user.getId())
                .fromAvatar(user.getAvatar())
                .fromName(user.getNickname())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        save(comment);

        return "评论成功";
    }

    /**
     * 获取评论
     * 获取所有评论，再将所有回复挂在评论下面
     *
     * @param commentType
     * @param articleId
     * @return
     */
    @Override
    public List<CommentVO> getCommentContent(String commentType, Long articleId) {
        //1.获取所有评论
        List<Comment> commentList = lambdaQuery().eq(Comment::getCommentType, commentType).eq(Comment::getArticleId, articleId).list();

        //2.1添加VO
        List<CommentVO> commentVOList = BeanUtils.copyList(commentList, CommentVO.class);
        //2.2根据评论id,查询评论回复
        for (CommentVO commentVO : commentVOList) {
            List<Reply> replyListTOComment = Db.lambdaQuery(Reply.class)
                    .eq(Reply::getFlag, Reply.TO_COMMENTS)
                    .eq(Reply::getCommentId, commentVO.getId())
                    .list();

            //2.3查询回复的回复
            List<Reply> replyListToReply = new ArrayList<>();
            for (Reply reply : replyListTOComment) {
                replyListToReply = Db.lambdaQuery(Reply.class)
                        .eq(Reply::getFlag, Reply.TO_REPLY)
                        .eq(Reply::getCommentId, reply.getId())
                        .list();
            }

            //2.4 合并起来转为VO
            replyListTOComment.addAll(replyListToReply);
            commentVO.setReplyVOList(BeanUtils.copyList(replyListTOComment, ReplyVO.class));
        }

        return commentVOList;
    }







}
