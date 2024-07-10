package net.tutorial.service;

import net.tutorial.domain.entity.Type;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 类型表 服务类
 * </p>
 *
 * @author top
 * @since 2024-06-10
 */
public interface ITypeService extends IService<Type> {

    /**
     * 查询类型管理
     *
     * @param id 类型管理主键
     * @return 类型管理
     */
    public Type selectTypeById(Long id);

    /**
     * 查询类型管理列表
     *
     * @param type 类型管理
     * @return 类型管理集合
     */
    public List<Type> selectTypeList(Type type);

    /**
     * 新增类型管理
     *
     * @param type 类型管理
     * @return 结果
     */
    public int insertType(Type type);

    /**
     * 修改类型管理
     *
     * @param type 类型管理
     * @return 结果
     */
    public int updateType(Type type);

    /**
     * 批量删除类型管理
     *
     * @param ids 需要删除的类型管理主键集合
     * @return 结果
     */
    public int deleteTypeByIds(Long[] ids);

    /**
     * 删除类型管理信息
     *
     * @param id 类型管理主键
     * @return 结果
     */
    public int deleteTypeById(Long id);
    List<Type> getTypeList();
}
