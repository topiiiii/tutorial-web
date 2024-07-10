package net.tutorial.mapper;

import net.tutorial.domain.entity.Books;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 图书表 Mapper 接口
 * </p>
 *
 * @author top
 * @since 2024-06-10
 */
public interface BooksMapper extends BaseMapper<Books> {
    /**
     * 查询图书管理
     *
     * @param id 图书管理主键
     * @return 图书管理
     */
    public Books selectBooksById(Long id);

    /**
     * 查询图书管理列表
     *
     * @param books 图书管理
     * @return 图书管理集合
     */
    public List<Books> selectBooksList(Books books);

    /**
     * 新增图书管理
     *
     * @param books 图书管理
     * @return 结果
     */
    public int insertBooks(Books books);

    /**
     * 修改图书管理
     *
     * @param books 图书管理
     * @return 结果
     */
    public int updateBooks(Books books);

    /**
     * 删除图书管理
     *
     * @param id 图书管理主键
     * @return 结果
     */
    public int deleteBooksById(Long id);

    /**
     * 批量删除图书管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBooksByIds(Long[] ids);

    List<Books> randomRecommendation();

    Long getFirstChapter(Long Id);
}
