package net.tutorial.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import lombok.RequiredArgsConstructor;
import net.tutorial.domain.entity.LearningRoute;
import net.tutorial.domain.entity.TotalRoute;
import net.tutorial.domain.vo.LearningRouteVO;
import net.tutorial.domain.vo.TotalRouteVO;
import net.tutorial.mapper.TotalRouteMapper;
import net.tutorial.service.ITotalRouteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.tutorial.utils.bean.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 学习路线总体表 服务实现类
 * </p>
 *
 * @author top
 * @since 2024-06-11
 */
@Service
@RequiredArgsConstructor
public class TotalRouteServiceImpl extends ServiceImpl<TotalRouteMapper, TotalRoute> implements ITotalRouteService {

    private final TotalRouteMapper totalRouteMapper;


    /**
     * 查询学习路线总体
     *
     * @param id 学习路线总体主键
     * @return 学习路线总体
     */
    @Override
    public TotalRoute selectTotalRouteById(Long id)
    {
        return totalRouteMapper.selectTotalRouteById(id);
    }

    /**
     * 查询学习路线总体列表
     *
     * @param totalRoute 学习路线总体
     * @return 学习路线总体
     */
    @Override
    public List<TotalRoute> selectTotalRouteList(TotalRoute totalRoute)
    {
        return totalRouteMapper.selectTotalRouteList(totalRoute);
    }

    /**
     * 新增学习路线总体
     *
     * @param totalRoute 学习路线总体
     * @return 结果
     */
    @Override
    public int insertTotalRoute(TotalRoute totalRoute)
    {
        return totalRouteMapper.insertTotalRoute(totalRoute);
    }

    /**
     * 修改学习路线总体
     *
     * @param totalRoute 学习路线总体
     * @return 结果
     */
    @Override
    public int updateTotalRoute(TotalRoute totalRoute)
    {
        return totalRouteMapper.updateTotalRoute(totalRoute);
    }

    /**
     * 批量删除学习路线总体
     *
     * @param ids 需要删除的学习路线总体主键
     * @return 结果
     */
    @Override
    public int deleteTotalRouteByIds(Long[] ids)
    {
        return totalRouteMapper.deleteTotalRouteByIds(ids);
    }

    /**
     * 删除学习路线总体信息
     *
     * @param id 学习路线总体主键
     * @return 结果
     */
    @Override
    public int deleteTotalRouteById(Long id)
    {
        return totalRouteMapper.deleteTotalRouteById(id);
    }



    /**
     * 根据类型获取总体路线图
     * @param typeId
     * @return
     */
    @Override
    public List<TotalRouteVO> getTotalRoute(Long typeId) {
        //1.查询总体路线表
        QueryWrapper<TotalRoute> totalRouteQueryWrapper = new QueryWrapper<>();
        totalRouteQueryWrapper
                .lambda()
                .select(TotalRoute::getModuleName,TotalRoute::getGain,TotalRoute::getRouteId)
                .eq(TotalRoute::getTypeId,typeId);
        List<TotalRoute> totalRouteList = totalRouteMapper.selectList(totalRouteQueryWrapper);

        //2.根据路线id，查询路线
        List<TotalRouteVO> totalRouteVOList = new ArrayList<>();
        totalRouteList.forEach(totalRoute -> {
            //2.1分割路线id为list，查询路线
            List<Long> routeIdList = Arrays.stream(totalRoute.getRouteId().split(","))
                    .map(Long::valueOf)
                    .collect(Collectors.toList());
            List<LearningRoute> learningRouteList = Db.listByIds(routeIdList, LearningRoute.class);
            //2.2将路线转为VO
            List<LearningRouteVO> learningRouteVOList = LearningRouteServiceImpl.getLearningRouteVOList(learningRouteList);

            TotalRouteVO totalRouteVO = BeanUtils.copyBean(totalRoute, TotalRouteVO.class);
            totalRouteVO.setLearningRouteVOList(learningRouteVOList);
            totalRouteVOList.add(totalRouteVO);

        });
        //4.封装VO
        return totalRouteVOList;
    }
}
