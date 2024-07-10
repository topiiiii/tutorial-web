package net.tutorial.service;

import net.tutorial.domain.entity.Chapters;
import com.baomidou.mybatisplus.extension.service.IService;
import net.tutorial.domain.vo.ChaptersVO;
import net.tutorial.domain.vo.ChildChaptersVO;

import java.util.List;

/**
 * <p>
 * 章节表 服务类
 * </p>
 *
 * @author top
 * @since 2024-06-12
 */
public interface IChaptersService extends IService<Chapters> {

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
     * 批量删除章节管理
     *
     * @param ids 需要删除的章节管理主键集合
     * @return 结果
     */
    public int deleteChaptersByIds(Long[] ids);

    /**
     * 删除章节管理信息
     *
     * @param id 章节管理主键
     * @return 结果
     */
    public int deleteChaptersById(Long id);

    //获取书章节详情
    ChaptersVO getChaptersListByBooksId(Long booksId);

    //根据父章节id，查询子章节列表
    List<ChildChaptersVO> getChildChaptersListByParentId(Long parentId);
}
