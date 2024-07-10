package net.tutorial.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import lombok.RequiredArgsConstructor;
import net.tutorial.domain.entity.Books;
import net.tutorial.domain.entity.Chapters;
import net.tutorial.domain.entity.Video;
import net.tutorial.domain.vo.VideoVO;
import net.tutorial.domain.vo.recommendVideoVO;
import net.tutorial.mapper.BooksMapper;
import net.tutorial.mapper.VideoMapper;
import net.tutorial.service.IVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.tutorial.utils.DateUtils;
import net.tutorial.utils.bean.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static net.tutorial.constant.Constants.PARENT_ID;

/**
 * <p>
 * 视频表 服务实现类
 * </p>
 *
 * @author top
 * @since 2024-06-16
 */
@Service
@RequiredArgsConstructor
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements IVideoService {

    private final BooksMapper booksMapper;

    private final VideoMapper videoMapper;

    /**
     * 查询视频管理
     *
     * @param id 视频管理主键
     * @return 视频管理
     */
    @Override
    public Video selectVideoById(Long id)
    {
        return videoMapper.selectVideoById(id);
    }

    /**
     * 查询视频管理列表
     *
     * @param video 视频管理
     * @return 视频管理
     */
    @Override
    public List<Video> selectVideoList(Video video)
    {
        return videoMapper.selectVideoList(video);
    }

    /**
     * 新增视频管理
     *
     * @param video 视频管理
     * @return 结果
     */
    @Override
    public int insertVideo(Video video)
    {
        video.setCreateTime(LocalDateTime.now());
        return videoMapper.insertVideo(video);
    }

    /**
     * 修改视频管理
     *
     * @param video 视频管理
     * @return 结果
     */
    @Override
    public int updateVideo(Video video)
    {
        video.setUpdateTime(LocalDateTime.now());
        return videoMapper.updateVideo(video);
    }

    /**
     * 批量删除视频管理
     *
     * @param ids 需要删除的视频管理主键
     * @return 结果
     */
    @Override
    public int deleteVideoByIds(Long[] ids)
    {
        return videoMapper.deleteVideoByIds(ids);
    }

    /**
     * 删除视频管理信息
     *
     * @param id 视频管理主键
     * @return 结果
     */
    @Override
    public int deleteVideoById(Long id)
    {
        return videoMapper.deleteVideoById(id);
    }



    /**
     * 获取视频详情
     * @param chapterId
     * @return
     */
    @Override
    public VideoVO getVideoDetail(Long chapterId) {
        //1.查询视频地址
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper
                .lambda()
                .select(Video::getId,Video::getVideo,Video::getChapterId,Video::getCreateTime)
                .eq(Video::getChapterId,chapterId);
        Video video = getOne(videoQueryWrapper);

        //2.组装VO
        VideoVO videoVO = BeanUtils.copyBean(video, VideoVO.class);

        //2.1查询章节名
        Chapters chapters = Db.getById(video.getChapterId(), Chapters.class);
        videoVO.setName(chapters.getName());

        //2.2查询简介
        Books books = Db.getById(chapters.getBookId(), Books.class);
        videoVO.setDescription(books.getDescription());

        //2.3查询章节目录
        List<Chapters> list = Db.lambdaQuery(Chapters.class).select(Chapters::getId, Chapters::getName, Chapters::getSequence,
                        Chapters::getUnlockPoints)
                .eq(Chapters::getParentId,PARENT_ID)
                .eq(Chapters::getBookId,chapters.getBookId())
                .orderByAsc(Chapters::getSequence).list();
        videoVO.setChaptersList(list);

        return videoVO;
    }

    /**
     * 相关课程推荐
     * @return
     */
    @Override
    public List<recommendVideoVO> getCourseRecommendation(Long chapterId) {
        //1.查询属于那个课程
        Chapters chapters = Db.getById(chapterId, Chapters.class);
        Books books = Db.getById(chapters.getBookId(), Books.class);

        //2.同类目下查询其他课程
        List<Books> booksList = Db.lambdaQuery(Books.class)
                .eq(Books::getTypeId, books.getTypeId())
                .list();

        //3.组装VO
        List<recommendVideoVO> recommendVideoVOList = BeanUtils.copyList(booksList, recommendVideoVO.class);

        //3.1查询每本书第一个章节
        recommendVideoVOList.forEach(recommendVideoVO ->
            recommendVideoVO.setChapterId(booksMapper.getFirstChapter(recommendVideoVO.getId()))
        );

        return recommendVideoVOList;
    }
}
