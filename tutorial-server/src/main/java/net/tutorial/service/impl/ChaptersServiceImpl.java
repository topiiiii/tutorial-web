package net.tutorial.service.impl;

import com.baomidou.mybatisplus.extension.toolkit.Db;
import lombok.RequiredArgsConstructor;
import net.tutorial.domain.entity.Books;
import net.tutorial.domain.entity.Chapters;
import net.tutorial.domain.vo.ChaptersVO;
import net.tutorial.domain.vo.ChildChaptersVO;
import net.tutorial.mapper.ChaptersMapper;
import net.tutorial.service.IChaptersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.tutorial.utils.bean.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static net.tutorial.constant.Constants.PARENT_ID;

/**
 * <p>
 * 章节表 服务实现类
 * </p>
 *
 * @author top
 * @since 2024-06-12
 */
@Service
@RequiredArgsConstructor
public class ChaptersServiceImpl extends ServiceImpl<ChaptersMapper, Chapters> implements IChaptersService {

    private final ChaptersMapper chaptersMapper;


    /**
     * 查询章节管理
     *
     * @param id 章节管理主键
     * @return 章节管理
     */
    @Override
    public Chapters selectChaptersById(Long id)
    {
        return chaptersMapper.selectChaptersById(id);
    }

    /**
     * 查询章节管理列表
     *
     * @param chapters 章节管理
     * @return 章节管理
     */
    @Override
    public List<Chapters> selectChaptersList(Chapters chapters)
    {
        return chaptersMapper.selectChaptersList(chapters);
    }

    /**
     * 新增章节管理
     *
     * @param chapters 章节管理
     * @return 结果
     */
    @Override
    public int insertChapters(Chapters chapters)
    {
        return chaptersMapper.insertChapters(chapters);
    }

    /**
     * 修改章节管理
     *
     * @param chapters 章节管理
     * @return 结果
     */
    @Override
    public int updateChapters(Chapters chapters)
    {
        return chaptersMapper.updateChapters(chapters);
    }

    /**
     * 批量删除章节管理
     *
     * @param ids 需要删除的章节管理主键
     * @return 结果
     */
    @Override
    public int deleteChaptersByIds(Long[] ids)
    {
        return chaptersMapper.deleteChaptersByIds(ids);
    }

    /**
     * 删除章节管理信息
     *
     * @param id 章节管理主键
     * @return 结果
     */
    @Override
    public int deleteChaptersById(Long id)
    {
        return chaptersMapper.deleteChaptersById(id);
    }



    /**
     * 获取书章节详情
     * @param booksId
     * @return
     */
    @Override
    public ChaptersVO getChaptersListByBooksId(Long booksId) {
        ChaptersVO chaptersVO = new ChaptersVO();

        //1.根据图书id查询所有的父章节
        List<ChildChaptersVO> parentChaptersVOList = getChapters(booksId,PARENT_ID);
        chaptersVO.setChapters(parentChaptersVOList);

        //2.根据父章节查询子章节
        parentChaptersVOList.forEach(parentChaptersVO ->
        {
            List<ChildChaptersVO> childChaptersVOList = getChapters(booksId, parentChaptersVO.getId());
            parentChaptersVO.setChildChaptersVOList(childChaptersVOList);
        });

        //3.查询图书简介
        Books books = Db.getById(booksId, Books.class);

        //4.组装VO
        chaptersVO.setBookId(booksId);
        chaptersVO.setBookName(books.getName());
        chaptersVO.setDescription(books.getDescription());

        return chaptersVO;
    }

    /**
     * 根据父章节id，查询子章节列表
     * @param parentId
     * @return
     */
    @Override
    public List<ChildChaptersVO> getChildChaptersListByParentId(Long parentId) {
        List<Chapters> list = Db.lambdaQuery(Chapters.class).select(Chapters::getId, Chapters::getName, Chapters::getSequence,
                        Chapters::getUnlockPoints)
                .eq(Chapters::getParentId, parentId)
                .orderByAsc(Chapters::getSequence).list();

        return  BeanUtils.copyList(list, ChildChaptersVO.class);
    }

    /**
     * 获取章节列表
     *
     * @param booksId
     * @param parentId
     * @return
     */
    private List<ChildChaptersVO> getChapters(Long booksId, Long parentId){

        List<Chapters> list = Db.lambdaQuery(Chapters.class).select(Chapters::getId, Chapters::getName, Chapters::getSequence,
                        Chapters::getUnlockPoints)
                .eq(Chapters::getBookId, booksId)
                .eq(Chapters::getParentId, parentId)
                .orderByAsc(Chapters::getSequence).list();
        return BeanUtils.copyList(list, ChildChaptersVO.class);
    }
}
