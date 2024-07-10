package net.tutorial.service.impl;

import lombok.RequiredArgsConstructor;
import net.tutorial.domain.entity.Tutor;
import net.tutorial.mapper.TutorMapper;
import net.tutorial.service.ITutorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 辅导班表 服务实现类
 * </p>
 *
 * @author top
 * @since 2024-07-05
 */
@Service
@RequiredArgsConstructor
public class TutorServiceImpl extends ServiceImpl<TutorMapper, Tutor> implements ITutorService {

    private final TutorMapper tutorMapper;

    /**
     * 查询辅导班
     *
     * @param id 辅导班主键
     * @return 辅导班
     */
    @Override
    public Tutor selectTutorById(Long id)
    {
        return tutorMapper.selectTutorById(id);
    }

    /**
     * 查询辅导班列表
     *
     * @param tutor 辅导班
     * @return 辅导班
     */
    @Override
    public List<Tutor> selectTutorList(Tutor tutor)
    {
        return tutorMapper.selectTutorList(tutor);
    }

    /**
     * 新增辅导班
     *
     * @param tutor 辅导班
     * @return 结果
     */
    @Override
    public int insertTutor(Tutor tutor)
    {
        return tutorMapper.insertTutor(tutor);
    }

    /**
     * 修改辅导班
     *
     * @param tutor 辅导班
     * @return 结果
     */
    @Override
    public int updateTutor(Tutor tutor)
    {
        return tutorMapper.updateTutor(tutor);
    }

    /**
     * 批量删除辅导班
     *
     * @param ids 需要删除的辅导班主键
     * @return 结果
     */
    @Override
    public int deleteTutorByIds(Long[] ids)
    {
        return tutorMapper.deleteTutorByIds(ids);
    }

    /**
     * 删除辅导班信息
     *
     * @param id 辅导班主键
     * @return 结果
     */
    @Override
    public int deleteTutorById(Long id)
    {
        return tutorMapper.deleteTutorById(id);
    }


    /**
     * 获取辅导班列表
     * @return
     */
    @Override
    public List<Tutor> getTutorList() {
        return lambdaQuery().list();
    }
}
