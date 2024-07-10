package net.tutorial.service;

import net.tutorial.domain.entity.VipType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 会员类型表 服务类
 * </p>
 *
 * @author top
 * @since 2024-06-20
 */
public interface IVipTypeService extends IService<VipType> {

    /**
     * 查询会员类型
     *
     * @param id 会员类型主键
     * @return 会员类型
     */
    public VipType selectVipTypeById(Long id);

    /**
     * 查询会员类型列表
     *
     * @param vipType 会员类型
     * @return 会员类型集合
     */
    public List<VipType> selectVipTypeList(VipType vipType);

    /**
     * 新增会员类型
     *
     * @param vipType 会员类型
     * @return 结果
     */
    public int insertVipType(VipType vipType);

    /**
     * 修改会员类型
     *
     * @param vipType 会员类型
     * @return 结果
     */
    public int updateVipType(VipType vipType);

    /**
     * 批量删除会员类型
     *
     * @param ids 需要删除的会员类型主键集合
     * @return 结果
     */
    public int deleteVipTypeByIds(Long[] ids);

    /**
     * 删除会员类型信息
     *
     * @param id 会员类型主键
     * @return 结果
     */
    public int deleteVipTypeById(Long id);
    List<VipType> getVipTypeList();

}
