package net.tutorial.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import net.tutorial.domain.entity.Books;
import net.tutorial.domain.vo.BooksVO;
import net.tutorial.mapper.BooksMapper;
import net.tutorial.service.IBooksService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.tutorial.utils.bean.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;


/**
 * <p>
 * 图书表 服务实现类
 * </p>
 *
 * @author top
 * @since 2024-06-10
 */
@Service
@RequiredArgsConstructor
public class BooksServiceImpl extends ServiceImpl<BooksMapper, Books> implements IBooksService {

    private final BooksMapper booksMapper;


    /**
     * 查询图书管理
     *
     * @param id 图书管理主键
     * @return 图书管理
     */
    @Override
    public Books selectBooksById(Long id)
    {
        return booksMapper.selectBooksById(id);
    }

    /**
     * 查询图书管理列表
     *
     * @param books 图书管理
     * @return 图书管理
     */
    @Override
    public List<Books> selectBooksList(Books books)
    {
        return booksMapper.selectBooksList(books);
    }

    /**
     * 新增图书管理
     *
     * @param books 图书管理
     * @return 结果
     */
    @Override
    public int insertBooks(Books books)
    {
        return booksMapper.insertBooks(books);
    }

    /**
     * 修改图书管理
     *
     * @param books 图书管理
     * @return 结果
     */
    @Override
    public int updateBooks(Books books)
    {
        return booksMapper.updateBooks(books);
    }

    /**
     * 批量删除图书管理
     *
     * @param ids 需要删除的图书管理主键
     * @return 结果
     */
    @Override
    public int deleteBooksByIds(Long[] ids)
    {
        return booksMapper.deleteBooksByIds(ids);
    }

    /**
     * 删除图书管理信息
     *
     * @param id 图书管理主键
     * @return 结果
     */
    @Override
    public int deleteBooksById(Long id)
    {
        return booksMapper.deleteBooksById(id);
    }


    /**
     * 首页获取图书教程
     * @param typeId
     * @return
     */
    @Override
    public List<BooksVO> getBooksListByType(Long typeId,String category) {
        List<Books> booksList = lambdaQuery().eq(Books::getTypeId, typeId)
                .eq(Books::getBooksType, category).list();
        return BeanUtils.copyList(booksList,BooksVO.class);
    }

    /**
     * 随机推荐图书
     * @return
     */
    @Override
    public List<BooksVO> randomRecommendation() {
        List<Books> books = booksMapper.randomRecommendation();
        return BeanUtils.copyList(books, BooksVO.class);
    }
}
