package net.tutorial.service;

import net.tutorial.domain.entity.TotalRoute;
import com.baomidou.mybatisplus.extension.service.IService;
import net.tutorial.domain.vo.TotalRouteVO;

import java.util.List;

/**
 * <p>
 * 学习路线总体表 服务类
 * </p>
 *
 * @author top
 * @since 2024-06-11
 */
public interface ITotalRouteService extends IService<TotalRoute> {

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
     * 批量删除学习路线总体
     *
     * @param ids 需要删除的学习路线总体主键集合
     * @return 结果
     */
    public int deleteTotalRouteByIds(Long[] ids);

    /**
     * 删除学习路线总体信息
     *
     * @param id 学习路线总体主键
     * @return 结果
     */
    public int deleteTotalRouteById(Long id);
    //根据类型获取总体路线图
    List<TotalRouteVO> getTotalRoute(Long typeId);

}
