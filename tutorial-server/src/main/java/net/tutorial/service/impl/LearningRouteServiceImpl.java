package net.tutorial.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import lombok.RequiredArgsConstructor;
import net.tutorial.domain.entity.Books;
import net.tutorial.domain.entity.ConnectRoute;
import net.tutorial.domain.entity.LearningRoute;
import net.tutorial.domain.vo.BooksVO;
import net.tutorial.domain.vo.LearningRouteVO;
import net.tutorial.mapper.ConnectRouteMapper;
import net.tutorial.mapper.LearningRouteMapper;
import net.tutorial.service.ILearningRouteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.tutorial.utils.bean.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;


/**
 * <p>
 * 学习路线表 服务实现类
 * </p>
 *
 * @author top
 * @since 2024-06-10
 */
@Service
@RequiredArgsConstructor
public class LearningRouteServiceImpl extends ServiceImpl<LearningRouteMapper, LearningRoute> implements ILearningRouteService {

    private final LearningRouteMapper learningRouteMapper;
    private final ConnectRouteMapper connectRouteMapper;


    /**
     * 查询学习路线
     *
     * @param id 学习路线主键
     * @return 学习路线
     */
    @Override
    public LearningRoute selectLearningRouteById(Long id)
    {
        return learningRouteMapper.selectLearningRouteById(id);
    }

    /**
     * 查询学习路线列表
     *
     * @param learningRoute 学习路线
     * @return 学习路线
     */
    @Override
    public List<LearningRoute> selectLearningRouteList(LearningRoute learningRoute)
    {
        return learningRouteMapper.selectLearningRouteList(learningRoute);
    }

    /**
     * 新增学习路线
     *
     * @param learningRoute 学习路线
     * @return 结果
     */
    @Override
    public int insertLearningRoute(LearningRoute learningRoute)
    {
        return learningRouteMapper.insertLearningRoute(learningRoute);
    }

    /**
     * 修改学习路线
     *
     * @param learningRoute 学习路线
     * @return 结果
     */
    @Override
    public int updateLearningRoute(LearningRoute learningRoute)
    {
        return learningRouteMapper.updateLearningRoute(learningRoute);
    }

    /**
     * 批量删除学习路线
     *
     * @param ids 需要删除的学习路线主键
     * @return 结果
     */
    @Override
    public int deleteLearningRouteByIds(Long[] ids)
    {
        return learningRouteMapper.deleteLearningRouteByIds(ids);
    }

    /**
     * 删除学习路线信息
     *
     * @param id 学习路线主键
     * @return 结果
     */
    @Override
    public int deleteLearningRouteById(Long id)
    {
        return learningRouteMapper.deleteLearningRouteById(id);
    }


    /**
     * 首页获取学习路线列表
     * @param typeId
     * @return
     */
    @Override
    public List<LearningRouteVO> getLearningRouteList(Long typeId) {
        //1.查询该分类下的所有路线
        QueryWrapper<LearningRoute> learningRouteQueryWrapper = new QueryWrapper<>();
        learningRouteQueryWrapper.lambda()
                .select(LearningRoute::getId,LearningRoute::getName,LearningRoute::getCover)
                .eq(LearningRoute::getTypeId,typeId);
        List<LearningRoute> learningRouteList = learningRouteMapper.selectList(learningRouteQueryWrapper);

        //2.查询该路线对应有哪些书
        //2.1封装路线vo
        List<LearningRouteVO> voList = getLearningRouteVOList(learningRouteList);
        return voList;
    }

    static List<LearningRouteVO> getLearningRouteVOList(List<LearningRoute> learningRouteList) {
        List<LearningRouteVO> voList = BeanUtils.copyList(learningRouteList, LearningRouteVO.class);

        QueryWrapper<ConnectRoute> connectRouteQueryWrapper = new QueryWrapper<>();
        voList.forEach(learningRouteVO -> {

            //2.2根据路线id查询关联表里有哪些书

            connectRouteQueryWrapper
                        .lambda()
                        .select(ConnectRoute::getBooksId)
                        .eq(ConnectRoute::getRouteId,learningRouteVO.getId());

            List<ConnectRoute> connectBooksIdList = Db.lambdaQuery(ConnectRoute.class)
                            .eq(ConnectRoute::getRouteId, learningRouteVO.getId())
                            .list();
//            List<ConnectRoute> connectBooksIdList = connectRouteMapper.selectList(connectRouteQueryWrapper);

                    if (connectBooksIdList != null && connectBooksIdList.size() > 0) {
                        //2.3根据书id查询书信息
                        List<Long> booksIds = connectBooksIdList.stream()
                                .map(ConnectRoute::getBooksId)
                                .collect(Collectors.toList());
                        List<Books> booksList = Db.listByIds(booksIds, Books.class);

                        //2.4封装进vo
                        List<BooksVO> booksVOList = BeanUtils.copyList(booksList, BooksVO.class);
                        learningRouteVO.setBooksVOList(booksVOList);
                    }

                }
        );
        return voList;
    }
}
