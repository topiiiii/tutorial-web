package net.tutorial.mapper;

import net.tutorial.domain.entity.Content;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 内容表 Mapper 接口
 * </p>
 *
 * @author top
 * @since 2024-06-12
 */
public interface ContentMapper extends BaseMapper<Content> {
    /**
     * 查询内容管理
     *
     * @param id 内容管理主键
     * @return 内容管理
     */
    public Content selectContentById(Long id);

    /**
     * 查询内容管理列表
     *
     * @param content 内容管理
     * @return 内容管理集合
     */
    public List<Content> selectContentList(Content content);

    /**
     * 新增内容管理
     *
     * @param content 内容管理
     * @return 结果
     */
    public int insertContent(Content content);

    /**
     * 修改内容管理
     *
     * @param content 内容管理
     * @return 结果
     */
    public int updateContent(Content content);

    /**
     * 删除内容管理
     *
     * @param id 内容管理主键
     * @return 结果
     */
    public int deleteContentById(Long id);

    /**
     * 批量删除内容管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteContentByIds(Long[] ids);
}
