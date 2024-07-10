package net.tutorial.mapper;

import net.tutorial.domain.entity.Chapters;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 章节表 Mapper 接口
 * </p>
 *
 * @author top
 * @since 2024-06-12
 */
public interface ChaptersMapper extends BaseMapper<Chapters> {
    /**
     * 查询章节管理
     *
     * @param id 章节管理主键
     * @return 章节管理
     */
    public Chapters selectChaptersById(Long id);

    /**
     * 查询章节管理列表
     *
     * @param chapters 章节管理
     * @return 章节管理集合
     */
    public List<Chapters> selectChaptersList(Chapters chapters);

    /**
     * 新增章节管理
     *
     * @param chapters 章节管理
     * @return 结果
     */
    public int insertChapters(Chapters chapters);

    /**
     * 修改章节管理
     *
     * @param chapters 章节管理
     * @return 结果
     */
    public int updateChapters(Chapters chapters);

    /**
     * 删除章节管理
     *
     * @param id 章节管理主键
     * @return 结果
     */
    public int deleteChaptersById(Long id);

    /**
     * 批量删除章节管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteChaptersByIds(Long[] ids);

    //查询上一节
    Long selectLastContent(Long chapterId,Long booksId);

    //查询下一节
    Long selectNextContent(Long chapterId,Long booksId);

}
