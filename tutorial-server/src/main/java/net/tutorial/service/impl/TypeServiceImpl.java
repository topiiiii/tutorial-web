package net.tutorial.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import net.tutorial.domain.entity.Type;
import net.tutorial.mapper.TypeMapper;
import net.tutorial.service.ITypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 类型表 服务实现类
 * </p>
 *
 * @author top
 * @since 2024-06-10
 */
@Service
@RequiredArgsConstructor
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements ITypeService {

    private final TypeMapper typeMapper;


    /**
     * 查询类型管理
     *
     * @param id 类型管理主键
     * @return 类型管理
     */
    @Override
    public Type selectTypeById(Long id)
    {
        return typeMapper.selectTypeById(id);
    }

    /**
     * 查询类型管理列表
     *
     * @param type 类型管理
     * @return 类型管理
     */
    @Override
    public List<Type> selectTypeList(Type type)
    {
        return typeMapper.selectTypeList(type);
    }

    /**
     * 新增类型管理
     *
     * @param type 类型管理
     * @return 结果
     */
    @Override
    public int insertType(Type type)
    {
        return typeMapper.insertType(type);
    }

    /**
     * 修改类型管理
     *
     * @param type 类型管理
     * @return 结果
     */
    @Override
    public int updateType(Type type)
    {
        return typeMapper.updateType(type);
    }

    /**
     * 批量删除类型管理
     *
     * @param ids 需要删除的类型管理主键
     * @return 结果
     */
    @Override
    public int deleteTypeByIds(Long[] ids)
    {
        return typeMapper.deleteTypeByIds(ids);
    }

    /**
     * 删除类型管理信息
     *
     * @param id 类型管理主键
     * @return 结果
     */
    @Override
    public int deleteTypeById(Long id)
    {
        return typeMapper.deleteTypeById(id);
    }



    @Override
    public List<Type> getTypeList() {
        QueryWrapper<Type> wrapper = new QueryWrapper<>();
        wrapper.lambda().select(Type::getId,Type::getName);
        return typeMapper.selectList(wrapper);
    }
}
