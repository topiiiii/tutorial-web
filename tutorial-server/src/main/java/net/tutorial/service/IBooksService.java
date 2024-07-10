package net.tutorial.service;

import net.tutorial.domain.entity.Books;
import com.baomidou.mybatisplus.extension.service.IService;
import net.tutorial.domain.vo.BooksVO;

import java.util.List;

/**
 * <p>
 * 图书表 服务类
 * </p>
 *
 * @author top
 * @since 2024-06-10
 */
public interface IBooksService extends IService<Books> {
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
     * 批量删除图书管理
     *
     * @param ids 需要删除的图书管理主键集合
     * @return 结果
     */
    public int deleteBooksByIds(Long[] ids);

    /**
     * 删除图书管理信息
     *
     * @param id 图书管理主键
     * @return 结果
     */
    public int deleteBooksById(Long id);

    /**
     * 首页获取图书教程
     * @param typeId
     * @return
     */
    List<BooksVO> getBooksListByType(Long typeId,String category);

    /**
     * 随机推荐图书
     * @return
     */
    List<BooksVO> randomRecommendation();

}
