package net.tutorial.service;

import net.tutorial.domain.entity.Tutor;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 辅导班表 服务类
 * </p>
 *
 * @author top
 * @since 2024-07-05
 */
public interface ITutorService extends IService<Tutor> {

    /**
     * 查询辅导班
     *
     * @param id 辅导班主键
     * @return 辅导班
     */
    public Tutor selectTutorById(Long id);

    /**
     * 查询辅导班列表
     *
     * @param tutor 辅导班
     * @return 辅导班集合
     */
    public List<Tutor> selectTutorList(Tutor tutor);

    /**
     * 新增辅导班
     *
     * @param tutor 辅导班
     * @return 结果
     */
    public int insertTutor(Tutor tutor);

    /**
     * 修改辅导班
     *
     * @param tutor 辅导班
     * @return 结果
     */
    public int updateTutor(Tutor tutor);

    /**
     * 批量删除辅导班
     *
     * @param ids 需要删除的辅导班主键集合
     * @return 结果
     */
    public int deleteTutorByIds(Long[] ids);

    /**
     * 删除辅导班信息
     *
     * @param id 辅导班主键
     * @return 结果
     */
    public int deleteTutorById(Long id);
    List<Tutor> getTutorList();
}
