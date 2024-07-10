package net.tutorial.mapper;

import net.tutorial.domain.entity.Type;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 类型表 Mapper 接口
 * </p>
 *
 * @author top
 * @since 2024-06-10
 */
public interface TypeMapper extends BaseMapper<Type> {

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
     * 删除类型管理
     *
     * @param id 类型管理主键
     * @return 结果
     */
    public int deleteTypeById(Long id);

    /**
     * 批量删除类型管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTypeByIds(Long[] ids);
}
