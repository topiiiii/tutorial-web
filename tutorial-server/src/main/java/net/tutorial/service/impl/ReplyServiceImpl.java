package net.tutorial.service.impl;

import com.baomidou.mybatisplus.extension.toolkit.Db;
import lombok.RequiredArgsConstructor;
import net.tutorial.annotation.IsLogin;
import net.tutorial.domain.User;
import net.tutorial.domain.dto.ReplyDTO;
import net.tutorial.domain.entity.Comment;
import net.tutorial.domain.entity.Reply;
import net.tutorial.mapper.ReplyMapper;
import net.tutorial.service.IReplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.tutorial.utils.DateUtils;
import net.tutorial.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 回复表 服务实现类
 * </p>
 *
 * @author top
 * @since 2024-06-21
 */
@Service
@RequiredArgsConstructor
public class ReplyServiceImpl extends ServiceImpl<ReplyMapper, Reply> implements IReplyService {

    private final ReplyMapper replyMapper;

    /**
     * 查询回复
     *
     * @param id 回复主键
     * @return 回复
     */
    @Override
    public Reply selectReplyById(Long id)
    {
        return replyMapper.selectReplyById(id);
    }

    /**
     * 查询回复列表
     *
     * @param reply 回复
     * @return 回复
     */
    @Override
    public List<Reply> selectReplyList(Reply reply)
    {
        return replyMapper.selectReplyList(reply);
    }

    /**
     * 新增回复
     *
     * @param reply 回复
     * @return 结果
     */
    @Override
    public int insertReply(Reply reply)
    {
        reply.setCreateTime(LocalDateTime.now());
        return replyMapper.insertReply(reply);
    }

    /**
     * 修改回复
     *
     * @param reply 回复
     * @return 结果
     */
    @Override
    public int updateReply(Reply reply)
    {
        reply.setUpdateTime(LocalDateTime.now());
        return replyMapper.updateReply(reply);
    }

    /**
     * 批量删除回复
     *
     * @param ids 需要删除的回复主键
     * @return 结果
     */
    @Override
    public int deleteReplyByIds(Long[] ids)
    {
        return replyMapper.deleteReplyByIds(ids);
    }

    /**
     * 删除回复信息
     *
     * @param id 回复主键
     * @return 结果
     */
    @Override
    public int deleteReplyById(Long id)
    {
        return replyMapper.deleteReplyById(id);
    }

    /**
     * 回复评论
     * @param replyDTO
     * @return
     */
    @Override
    @IsLogin
    public String reply(ReplyDTO replyDTO) {
        User user = SecurityUtils.getLoginUserByApp().getUser();
        Comment comment = Db.getById(replyDTO.getCommentId(), Comment.class);

        Reply reply = Reply.builder()
                .content(replyDTO.getContent())
                .commentId(replyDTO.getCommentId())
                .flag(replyDTO.getFlag())
                .fromUid(user.getId())
                .toUid(comment.getFromUid())
                .fromAvatar(user.getAvatar())
                .toAvatar(comment.getFromAvatar())
                .fromName(user.getNickname())
                .toName(comment.getFromName())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();

        save(reply);

        return "回复成功";
    }
}
