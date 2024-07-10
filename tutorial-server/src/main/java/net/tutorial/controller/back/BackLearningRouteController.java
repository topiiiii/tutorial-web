package net.tutorial.controller.back;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import net.tutorial.annotation.Log;
import net.tutorial.controller.BaseController;
import net.tutorial.domain.entity.LearningRoute;
import net.tutorial.domain.page.TableDataInfo;
import net.tutorial.response.AjaxResult;
import net.tutorial.utils.enums.BusinessType;
import net.tutorial.utils.poi.ExcelUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import net.tutorial.service.ILearningRouteService;

/**
 * 学习路线Controller
 *
 * @author top
 * @date 2024-07-08
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/back/route")
public class BackLearningRouteController extends BaseController
{
    private final ILearningRouteService learningRouteService;

    /**
     * 查询学习路线列表
     */
    @PreAuthorize("@ss.hasPermi('back:route:list')")
    @GetMapping("/list")
    public TableDataInfo list(LearningRoute learningRoute)
    {
        startPage();
        List<LearningRoute> list = learningRouteService.selectLearningRouteList(learningRoute);
        return getDataTable(list);
    }

    /**
     * 导出学习路线列表
     */
    @PreAuthorize("@ss.hasPermi('back:route:export')")
    @Log(title = "学习路线", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LearningRoute learningRoute)
    {
        List<LearningRoute> list = learningRouteService.selectLearningRouteList(learningRoute);
        ExcelUtil<LearningRoute> util = new ExcelUtil<LearningRoute>(LearningRoute.class);
        util.exportExcel(response, list, "学习路线数据");
    }

    /**
     * 获取学习路线详细信息
     */
    @PreAuthorize("@ss.hasPermi('back:route:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(learningRouteService.selectLearningRouteById(id));
    }

    /**
     * 新增学习路线
     */
    @PreAuthorize("@ss.hasPermi('back:route:add')")
    @Log(title = "学习路线", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LearningRoute learningRoute)
    {
        return toAjax(learningRouteService.insertLearningRoute(learningRoute));
    }

    /**
     * 修改学习路线
     */
    @PreAuthorize("@ss.hasPermi('back:route:edit')")
    @Log(title = "学习路线", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LearningRoute learningRoute)
    {
        return toAjax(learningRouteService.updateLearningRoute(learningRoute));
    }

    /**
     * 删除学习路线
     */
    @PreAuthorize("@ss.hasPermi('back:route:remove')")
    @Log(title = "学习路线", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(learningRouteService.deleteLearningRouteByIds(ids));
    }
}
