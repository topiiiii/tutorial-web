package net.tutorial.mapper;

import net.tutorial.domain.entity.PointsLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 积分日志表 Mapper 接口
 * </p>
 *
 * @author top
 * @since 2024-07-05
 */
public interface PointsLogMapper extends BaseMapper<PointsLog> {
    /**
     * 查询积分日志
     *
     * @param id 积分日志主键
     * @return 积分日志
     */
    public PointsLog selectPointsLogById(Long id);

    /**
     * 查询积分日志列表
     *
     * @param pointsLog 积分日志
     * @return 积分日志集合
     */
    public List<PointsLog> selectPointsLogList(PointsLog pointsLog);

    /**
     * 新增积分日志
     *
     * @param pointsLog 积分日志
     * @return 结果
     */
    public int insertPointsLog(PointsLog pointsLog);

    /**
     * 修改积分日志
     *
     * @param pointsLog 积分日志
     * @return 结果
     */
    public int updatePointsLog(PointsLog pointsLog);

    /**
     * 删除积分日志
     *
     * @param id 积分日志主键
     * @return 结果
     */
    public int deletePointsLogById(Long id);

    /**
     * 批量删除积分日志
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePointsLogByIds(Long[] ids);
}
