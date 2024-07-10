package net.tutorial.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import lombok.RequiredArgsConstructor;
import net.tutorial.domain.User;
import net.tutorial.domain.entity.*;
import net.tutorial.domain.vo.ChaptersVO;
import net.tutorial.domain.vo.ContentVO;
import net.tutorial.exception.ServiceException;
import net.tutorial.mapper.ChaptersMapper;
import net.tutorial.mapper.ContentMapper;
import net.tutorial.service.IContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.tutorial.utils.SecurityUtils;
import net.tutorial.utils.bean.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

import static net.tutorial.constant.Constants.PARENT_ID;

/**
 * <p>
 * 内容表 服务实现类
 * </p>
 *
 * @author top
 * @since 2024-06-12
 */
@Service
@RequiredArgsConstructor
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements IContentService {

    private final ChaptersMapper chaptersMapper;

    private final ContentMapper contentMapper;

    /**
     * 查询内容管理
     *
     * @param id 内容管理主键
     * @return 内容管理
     */
    @Override
    public Content selectContentById(Long id)
    {
        return contentMapper.selectContentById(id);
    }

    /**
     * 查询内容管理列表
     *
     * @param content 内容管理
     * @return 内容管理
     */
    @Override
    public List<Content> selectContentList(Content content)
    {
        return contentMapper.selectContentList(content);
    }

    /**
     * 新增内容管理
     *
     * @param content 内容管理
     * @return 结果
     */
    @Override
    public int insertContent(Content content)
    {
        return contentMapper.insertContent(content);
    }

    /**
     * 修改内容管理
     *
     * @param content 内容管理
     * @return 结果
     */
    @Override
    public int updateContent(Content content)
    {
        return contentMapper.updateContent(content);
    }

    /**
     * 批量删除内容管理
     *
     * @param ids 需要删除的内容管理主键
     * @return 结果
     */
    @Override
    public int deleteContentByIds(Long[] ids)
    {
        return contentMapper.deleteContentByIds(ids);
    }

    /**
     * 删除内容管理信息
     *
     * @param id 内容管理主键
     * @return 结果
     */
    @Override
    public int deleteContentById(Long id)
    {
        return contentMapper.deleteContentById(id);
    }


    /**
     * 根据章节id查询文章内容
     *
     * @param chapterId
     * @return
     */
    @Override
    public ContentVO getContentByChapterId(Long chapterId) {
        //0.检测是否为高级内容，检测用户身份
        //0.1是否为高级内容
        Chapters chapters = Db.getById(chapterId,Chapters.class);
        if (chapters.getUnlockPoints()!=0) {
            //0.2获取当前用户,检测是否为会员
            User user = SecurityUtils.getLoginUserByApp().getUser();
            VipUser vipUser = Db.lambdaQuery(VipUser.class).eq(VipUser::getUserId, user.getId()).one();
            //如果会员天数为0则为普通用户
            // 计算两个日期之间的天数差
            long daysBetween = ChronoUnit.DAYS.between(vipUser.getStartDate(), vipUser.getEndDate());
            if (daysBetween==0){
                PointsPurchase pointsPurchase = Db.lambdaQuery(PointsPurchase.class)
                        .eq(PointsPurchase::getUserId, user.getId())
                        .eq(PointsPurchase::getChaptersId, chapters.getId()).one();
                if (pointsPurchase==null) {
                    throw new ServiceException("请充值");
                }
            }
        }


        //1.查询文章内容跟
        Content content = lambdaQuery().eq(Content::getChapterId, chapterId).one();

        //2.组装VO
        ContentVO contentVO = BeanUtils.copyBean(content, ContentVO.class);

        //2.1查询章节名
        contentVO.setName(chapters.getName());
        //2.2查询图书名
        Books books = Db.getById(chapters.getBookId(), Books.class);
        contentVO.setBookName(books.getName());

        //2.3查询上下节
        Long lastId = chaptersMapper.selectLastContent(chapterId, chapters.getBookId());
        Long nextId = chaptersMapper.selectNextContent(chapterId, chapters.getBookId());
        contentVO.setLastId(lastId==null?-1L:lastId);
        contentVO.setNextId(nextId==null?-1L:nextId);

        //2.3查询章节目录
        List<Chapters> list = Db.lambdaQuery(Chapters.class)
                .eq(Chapters::getParentId,PARENT_ID)
                .eq(Chapters::getBookId,chapters.getBookId())
                .orderByAsc(Chapters::getSequence).list();
        contentVO.setChaptersList(list);

        return contentVO;
    }
}
