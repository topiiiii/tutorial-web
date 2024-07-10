package net.tutorial.mapper;

import net.tutorial.domain.entity.TotalRoute;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 学习路线总体表 Mapper 接口
 * </p>
 *
 * @author top
 * @since 2024-06-11
 */
public interface TotalRouteMapper extends BaseMapper<TotalRoute> {
    /**
     * 查询学习路线总体
     *
     * @param id 学习路线总体主键
     * @return 学习路线总体
     */
    public TotalRoute selectTotalRouteById(Long id);

    /**
     * 查询学习路线总体列表
     *
     * @param totalRoute 学习路线总体
     * @return 学习路线总体集合
     */
    public List<TotalRoute> selectTotalRouteList(TotalRoute totalRoute);

    /**
     * 新增学习路线总体
     *
     * @param totalRoute 学习路线总体
     * @return 结果
     */
    public int insertTotalRoute(TotalRoute totalRoute);

    /**
     * 修改学习路线总体
     *
     * @param totalRoute 学习路线总体
     * @return 结果
     */
    public int updateTotalRoute(TotalRoute totalRoute);

    /**
     * 删除学习路线总体
     *
     * @param id 学习路线总体主键
     * @return 结果
     */
    public int deleteTotalRouteById(Long id);

    /**
     * 批量删除学习路线总体
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTotalRouteByIds(Long[] ids);
}
