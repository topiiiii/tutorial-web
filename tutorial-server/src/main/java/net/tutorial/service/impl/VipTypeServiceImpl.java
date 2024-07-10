package net.tutorial.service.impl;

import lombok.RequiredArgsConstructor;
import net.tutorial.domain.entity.VipType;
import net.tutorial.mapper.VipTypeMapper;
import net.tutorial.service.IVipTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 会员类型表 服务实现类
 * </p>
 *
 * @author top
 * @since 2024-06-20
 */
@Service
@RequiredArgsConstructor
public class VipTypeServiceImpl extends ServiceImpl<VipTypeMapper, VipType> implements IVipTypeService {

    private final VipTypeMapper vipTypeMapper;

    /**
     * 查询会员类型
     *
     * @param id 会员类型主键
     * @return 会员类型
     */
    @Override
    public VipType selectVipTypeById(Long id)
    {
        return vipTypeMapper.selectVipTypeById(id);
    }

    /**
     * 查询会员类型列表
     *
     * @param vipType 会员类型
     * @return 会员类型
     */
    @Override
    public List<VipType> selectVipTypeList(VipType vipType)
    {
        return vipTypeMapper.selectVipTypeList(vipType);
    }

    /**
     * 新增会员类型
     *
     * @param vipType 会员类型
     * @return 结果
     */
    @Override
    public int insertVipType(VipType vipType)
    {
        return vipTypeMapper.insertVipType(vipType);
    }

    /**
     * 修改会员类型
     *
     * @param vipType 会员类型
     * @return 结果
     */
    @Override
    public int updateVipType(VipType vipType)
    {
        return vipTypeMapper.updateVipType(vipType);
    }

    /**
     * 批量删除会员类型
     *
     * @param ids 需要删除的会员类型主键
     * @return 结果
     */
    @Override
    public int deleteVipTypeByIds(Long[] ids)
    {
        return vipTypeMapper.deleteVipTypeByIds(ids);
    }

    /**
     * 删除会员类型信息
     *
     * @param id 会员类型主键
     * @return 结果
     */
    @Override
    public int deleteVipTypeById(Long id)
    {
        return vipTypeMapper.deleteVipTypeById(id);
    }


    /**
     * 获取vip类型列表
     * @return
     */
    @Override
    public List<VipType> getVipTypeList() {
        return lambdaQuery().list();
    }
}
