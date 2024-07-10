package net.tutorial.service;

import net.tutorial.domain.entity.Content;
import com.baomidou.mybatisplus.extension.service.IService;
import net.tutorial.domain.vo.ContentVO;

import java.util.List;

/**
 * <p>
 * 内容表 服务类
 * </p>
 *
 * @author top
 * @since 2024-06-12
 */
public interface IContentService extends IService<Content> {
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
     * 批量删除内容管理
     *
     * @param ids 需要删除的内容管理主键集合
     * @return 结果
     */
    public int deleteContentByIds(Long[] ids);

    /**
     * 删除内容管理信息
     *
     * @param id 内容管理主键
     * @return 结果
     */
    public int deleteContentById(Long id);

    //根据章节id查询文章内容
    ContentVO getContentByChapterId(Long chapterId);
}
