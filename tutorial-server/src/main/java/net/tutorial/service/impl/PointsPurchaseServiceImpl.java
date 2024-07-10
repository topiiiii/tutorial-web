package net.tutorial.service.impl;

import lombok.RequiredArgsConstructor;
import net.tutorial.domain.entity.PointsPurchase;
import net.tutorial.mapper.PointsPurchaseMapper;
import net.tutorial.service.IPointsPurchaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.tutorial.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 积分购买记录表 服务实现类
 * </p>
 *
 * @author top
 * @since 2024-07-06
 */
@Service
@RequiredArgsConstructor
public class PointsPurchaseServiceImpl extends ServiceImpl<PointsPurchaseMapper, PointsPurchase> implements IPointsPurchaseService {

    private final PointsPurchaseMapper pointsPurchaseMapper;

    /**
     * 查询积分购买记录
     *
     * @param id 积分购买记录主键
     * @return 积分购买记录
     */
    @Override
    public PointsPurchase selectPointsPurchaseById(Long id)
    {
        return pointsPurchaseMapper.selectPointsPurchaseById(id);
    }

    /**
     * 查询积分购买记录列表
     *
     * @param pointsPurchase 积分购买记录
     * @return 积分购买记录
     */
    @Override
    public List<PointsPurchase> selectPointsPurchaseList(PointsPurchase pointsPurchase)
    {
        return pointsPurchaseMapper.selectPointsPurchaseList(pointsPurchase);
    }

    /**
     * 新增积分购买记录
     *
     * @param pointsPurchase 积分购买记录
     * @return 结果
     */
    @Override
    public int insertPointsPurchase(PointsPurchase pointsPurchase)
    {
        pointsPurchase.setCreateTime(LocalDateTime.now());
        return pointsPurchaseMapper.insertPointsPurchase(pointsPurchase);
    }

    /**
     * 修改积分购买记录
     *
     * @param pointsPurchase 积分购买记录
     * @return 结果
     */
    @Override
    public int updatePointsPurchase(PointsPurchase pointsPurchase)
    {
        return pointsPurchaseMapper.updatePointsPurchase(pointsPurchase);
    }

    /**
     * 批量删除积分购买记录
     *
     * @param ids 需要删除的积分购买记录主键
     * @return 结果
     */
    @Override
    public int deletePointsPurchaseByIds(Long[] ids)
    {
        return pointsPurchaseMapper.deletePointsPurchaseByIds(ids);
    }

    /**
     * 删除积分购买记录信息
     *
     * @param id 积分购买记录主键
     * @return 结果
     */
    @Override
    public int deletePointsPurchaseById(Long id)
    {
        return pointsPurchaseMapper.deletePointsPurchaseById(id);
    }
}
