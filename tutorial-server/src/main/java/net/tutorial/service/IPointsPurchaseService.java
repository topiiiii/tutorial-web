package net.tutorial.service;

import net.tutorial.domain.entity.PointsPurchase;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 积分购买记录表 服务类
 * </p>
 *
 * @author top
 * @since 2024-07-06
 */
public interface IPointsPurchaseService extends IService<PointsPurchase> {

    /**
     * 查询积分购买记录
     *
     * @param id 积分购买记录主键
     * @return 积分购买记录
     */
    public PointsPurchase selectPointsPurchaseById(Long id);

    /**
     * 查询积分购买记录列表
     *
     * @param pointsPurchase 积分购买记录
     * @return 积分购买记录集合
     */
    public List<PointsPurchase> selectPointsPurchaseList(PointsPurchase pointsPurchase);

    /**
     * 新增积分购买记录
     *
     * @param pointsPurchase 积分购买记录
     * @return 结果
     */
    public int insertPointsPurchase(PointsPurchase pointsPurchase);

    /**
     * 修改积分购买记录
     *
     * @param pointsPurchase 积分购买记录
     * @return 结果
     */
    public int updatePointsPurchase(PointsPurchase pointsPurchase);

    /**
     * 批量删除积分购买记录
     *
     * @param ids 需要删除的积分购买记录主键集合
     * @return 结果
     */
    public int deletePointsPurchaseByIds(Long[] ids);

    /**
     * 删除积分购买记录信息
     *
     * @param id 积分购买记录主键
     * @return 结果
     */
    public int deletePointsPurchaseById(Long id);
}
