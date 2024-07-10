package net.tutorial.service;

import net.tutorial.domain.entity.Books;
import net.tutorial.domain.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;
import net.tutorial.domain.vo.VideoVO;
import net.tutorial.domain.vo.recommendVideoVO;

import java.util.List;

/**
 * <p>
 * 视频表 服务类
 * </p>
 *
 * @author top
 * @since 2024-06-16
 */
public interface IVideoService extends IService<Video> {

    /**
     * 查询视频管理
     *
     * @param id 视频管理主键
     * @return 视频管理
     */
    public Video selectVideoById(Long id);

    /**
     * 查询视频管理列表
     *
     * @param video 视频管理
     * @return 视频管理集合
     */
    public List<Video> selectVideoList(Video video);

    /**
     * 新增视频管理
     *
     * @param video 视频管理
     * @return 结果
     */
    public int insertVideo(Video video);

    /**
     * 修改视频管理
     *
     * @param video 视频管理
     * @return 结果
     */
    public int updateVideo(Video video);

    /**
     * 批量删除视频管理
     *
     * @param ids 需要删除的视频管理主键集合
     * @return 结果
     */
    public int deleteVideoByIds(Long[] ids);

    /**
     * 删除视频管理信息
     *
     * @param id 视频管理主键
     * @return 结果
     */
    public int deleteVideoById(Long id);
    VideoVO getVideoDetail(Long chapterId);
    //相关课程推荐
    List<recommendVideoVO> getCourseRecommendation(Long chapterId);

}
