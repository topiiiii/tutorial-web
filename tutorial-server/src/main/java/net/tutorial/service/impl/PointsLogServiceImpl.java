package net.tutorial.service.impl;

import lombok.RequiredArgsConstructor;
import net.tutorial.domain.User;
import net.tutorial.domain.entity.PointsLog;
import net.tutorial.domain.vo.PointsLogVO;
import net.tutorial.mapper.PointsLogMapper;
import net.tutorial.service.IPointsLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.tutorial.utils.DateUtils;
import net.tutorial.utils.SecurityUtils;
import net.tutorial.utils.bean.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 积分日志表 服务实现类
 * </p>
 *
 * @author top
 * @since 2024-07-05
 */
@Service
@RequiredArgsConstructor
public class PointsLogServiceImpl extends ServiceImpl<PointsLogMapper, PointsLog> implements IPointsLogService {

    private final PointsLogMapper pointsLogMapper;

    /**
     * 查询积分日志
     *
     * @param id 积分日志主键
     * @return 积分日志
     */
    @Override
    public PointsLog selectPointsLogById(Long id)
    {
        return pointsLogMapper.selectPointsLogById(id);
    }

    /**
     * 查询积分日志列表
     *
     * @param pointsLog 积分日志
     * @return 积分日志
     */
    @Override
    public List<PointsLog> selectPointsLogList(PointsLog pointsLog)
    {
        return pointsLogMapper.selectPointsLogList(pointsLog);
    }

    /**
     * 新增积分日志
     *
     * @param pointsLog 积分日志
     * @return 结果
     */
    @Override
    public int insertPointsLog(PointsLog pointsLog)
    {
        pointsLog.setCreateTime(LocalDateTime.now());
        return pointsLogMapper.insertPointsLog(pointsLog);
    }

    /**
     * 修改积分日志
     *
     * @param pointsLog 积分日志
     * @return 结果
     */
    @Override
    public int updatePointsLog(PointsLog pointsLog)
    {
        return pointsLogMapper.updatePointsLog(pointsLog);
    }

    /**
     * 批量删除积分日志
     *
     * @param ids 需要删除的积分日志主键
     * @return 结果
     */
    @Override
    public int deletePointsLogByIds(Long[] ids)
    {
        return pointsLogMapper.deletePointsLogByIds(ids);
    }

    /**
     * 删除积分日志信息
     *
     * @param id 积分日志主键
     * @return 结果
     */
    @Override
    public int deletePointsLogById(Long id)
    {
        return pointsLogMapper.deletePointsLogById(id);
    }


    /**
     * 用户获取积分变动日志
     * @return
     */
    @Override
    public List<PointsLogVO> getPointsLogList() {
        //1.获取当前用户
        User user = SecurityUtils.getLoginUserByApp().getUser();

        List<PointsLog> pointsLogList = lambdaQuery().eq(PointsLog::getUserId, user.getId()).list();



        return BeanUtils.copyList(pointsLogList, PointsLogVO.class);
    }
}
