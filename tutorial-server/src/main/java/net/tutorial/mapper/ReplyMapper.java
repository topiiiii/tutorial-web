package net.tutorial.mapper;

import net.tutorial.domain.entity.Reply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 回复表 Mapper 接口
 * </p>
 *
 * @author top
 * @since 2024-06-21
 */
public interface ReplyMapper extends BaseMapper<Reply> {
    /**
     * 查询回复
     *
     * @param id 回复主键
     * @return 回复
     */
    public Reply selectReplyById(Long id);

    /**
     * 查询回复列表
     *
     * @param reply 回复
     * @return 回复集合
     */
    public List<Reply> selectReplyList(Reply reply);

    /**
     * 新增回复
     *
     * @param reply 回复
     * @return 结果
     */
    public int insertReply(Reply reply);

    /**
     * 修改回复
     *
     * @param reply 回复
     * @return 结果
     */
    public int updateReply(Reply reply);

    /**
     * 删除回复
     *
     * @param id 回复主键
     * @return 结果
     */
    public int deleteReplyById(Long id);

    /**
     * 批量删除回复
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteReplyByIds(Long[] ids);
}
