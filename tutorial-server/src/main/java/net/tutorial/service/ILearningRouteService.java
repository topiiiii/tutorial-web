package net.tutorial.service;

import net.tutorial.domain.entity.LearningRoute;
import com.baomidou.mybatisplus.extension.service.IService;
import net.tutorial.domain.vo.LearningRouteVO;

import java.util.List;

/**
 * <p>
 * 学习路线表 服务类
 * </p>
 *
 * @author top
 * @since 2024-06-10
 */
public interface ILearningRouteService extends IService<LearningRoute> {

    /**
     * 查询学习路线
     *
     * @param id 学习路线主键
     * @return 学习路线
     */
    public LearningRoute selectLearningRouteById(Long id);

    /**
     * 查询学习路线列表
     *
     * @param learningRoute 学习路线
     * @return 学习路线集合
     */
    public List<LearningRoute> selectLearningRouteList(LearningRoute learningRoute);

    /**
     * 新增学习路线
     *
     * @param learningRoute 学习路线
     * @return 结果
     */
    public int insertLearningRoute(LearningRoute learningRoute);

    /**
     * 修改学习路线
     *
     * @param learningRoute 学习路线
     * @return 结果
     */
    public int updateLearningRoute(LearningRoute learningRoute);

    /**
     * 批量删除学习路线
     *
     * @param ids 需要删除的学习路线主键集合
     * @return 结果
     */
    public int deleteLearningRouteByIds(Long[] ids);

    /**
     * 删除学习路线信息
     *
     * @param id 学习路线主键
     * @return 结果
     */
    public int deleteLearningRouteById(Long id);
    List<LearningRouteVO> getLearningRouteList(Long typeId);
}
