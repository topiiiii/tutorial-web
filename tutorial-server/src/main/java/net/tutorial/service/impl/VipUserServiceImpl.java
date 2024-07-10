package net.tutorial.service.impl;

import lombok.RequiredArgsConstructor;
import net.tutorial.domain.entity.VipUser;
import net.tutorial.mapper.VipUserMapper;
import net.tutorial.service.IVipUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户会员表 服务实现类
 * </p>
 *
 * @author top
 * @since 2024-07-02
 */
@Service
@RequiredArgsConstructor
public class VipUserServiceImpl extends ServiceImpl<VipUserMapper, VipUser> implements IVipUserService {

    private final VipUserMapper vipUserMapper;

    /**
     * 查询用户会员
     *
     * @param id 用户会员主键
     * @return 用户会员
     */
    @Override
    public VipUser selectVipUserById(Long id)
    {
        return vipUserMapper.selectVipUserById(id);
    }

    /**
     * 查询用户会员列表
     *
     * @param vipUser 用户会员
     * @return 用户会员
     */
    @Override
    public List<VipUser> selectVipUserList(VipUser vipUser)
    {
        return vipUserMapper.selectVipUserList(vipUser);
    }

    /**
     * 新增用户会员
     *
     * @param vipUser 用户会员
     * @return 结果
     */
    @Override
    public int insertVipUser(VipUser vipUser)
    {
        return vipUserMapper.insertVipUser(vipUser);
    }

    /**
     * 修改用户会员
     *
     * @param vipUser 用户会员
     * @return 结果
     */
    @Override
    public int updateVipUser(VipUser vipUser)
    {
        return vipUserMapper.updateVipUser(vipUser);
    }

    /**
     * 批量删除用户会员
     *
     * @param ids 需要删除的用户会员主键
     * @return 结果
     */
    @Override
    public int deleteVipUserByIds(Long[] ids)
    {
        return vipUserMapper.deleteVipUserByIds(ids);
    }

    /**
     * 删除用户会员信息
     *
     * @param id 用户会员主键
     * @return 结果
     */
    @Override
    public int deleteVipUserById(Long id)
    {
        return vipUserMapper.deleteVipUserById(id);
    }
}
