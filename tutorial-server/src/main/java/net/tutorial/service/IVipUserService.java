package net.tutorial.service;

import net.tutorial.domain.entity.VipUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户会员表 服务类
 * </p>
 *
 * @author top
 * @since 2024-07-02
 */
public interface IVipUserService extends IService<VipUser> {
    /**
     * 查询用户会员
     *
     * @param id 用户会员主键
     * @return 用户会员
     */
    public VipUser selectVipUserById(Long id);

    /**
     * 查询用户会员列表
     *
     * @param vipUser 用户会员
     * @return 用户会员集合
     */
    public List<VipUser> selectVipUserList(VipUser vipUser);

    /**
     * 新增用户会员
     *
     * @param vipUser 用户会员
     * @return 结果
     */
    public int insertVipUser(VipUser vipUser);

    /**
     * 修改用户会员
     *
     * @param vipUser 用户会员
     * @return 结果
     */
    public int updateVipUser(VipUser vipUser);

    /**
     * 批量删除用户会员
     *
     * @param ids 需要删除的用户会员主键集合
     * @return 结果
     */
    public int deleteVipUserByIds(Long[] ids);

    /**
     * 删除用户会员信息
     *
     * @param id 用户会员主键
     * @return 结果
     */
    public int deleteVipUserById(Long id);
}
