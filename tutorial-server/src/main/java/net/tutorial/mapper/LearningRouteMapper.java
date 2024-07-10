package net.tutorial.mapper;

import net.tutorial.domain.entity.LearningRoute;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 学习路线表 Mapper 接口
 * </p>
 *
 * @author top
 * @since 2024-06-10
 */
public interface LearningRouteMapper extends BaseMapper<LearningRoute> {
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
     * 删除学习路线
     *
     * @param id 学习路线主键
     * @return 结果
     */
    public int deleteLearningRouteById(Long id);

    /**
     * 批量删除学习路线
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLearningRouteByIds(Long[] ids);
}
