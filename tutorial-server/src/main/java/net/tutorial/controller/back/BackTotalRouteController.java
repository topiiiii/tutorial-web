package net.tutorial.controller.back;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import net.tutorial.domain.entity.TotalRoute;
import net.tutorial.domain.page.TableDataInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import net.tutorial.annotation.Log;
import net.tutorial.controller.BaseController;
import net.tutorial.response.AjaxResult;
import net.tutorial.utils.enums.BusinessType;
import net.tutorial.utils.poi.ExcelUtil;
import net.tutorial.service.ITotalRouteService;

/**
 * 学习路线总体Controller
 *
 * @author top
 * @date 2024-07-08
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/back/totalRoute")
public class BackTotalRouteController extends BaseController
{
    private final ITotalRouteService totalRouteService;

    /**
     * 查询学习路线总体列表
     */
    @PreAuthorize("@ss.hasPermi('back:totalRoute:list')")
    @GetMapping("/list")
    public TableDataInfo list(TotalRoute totalRoute)
    {
        startPage();
        List<TotalRoute> list = totalRouteService.selectTotalRouteList(totalRoute);
        return getDataTable(list);
    }

    /**
     * 导出学习路线总体列表
     */
    @PreAuthorize("@ss.hasPermi('back:totalRoute:export')")
    @Log(title = "学习路线总体", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TotalRoute totalRoute)
    {
        List<TotalRoute> list = totalRouteService.selectTotalRouteList(totalRoute);
        ExcelUtil<TotalRoute> util = new ExcelUtil<TotalRoute>(TotalRoute.class);
        util.exportExcel(response, list, "学习路线总体数据");
    }

    /**
     * 获取学习路线总体详细信息
     */
    @PreAuthorize("@ss.hasPermi('back:totalRoute:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(totalRouteService.selectTotalRouteById(id));
    }

    /**
     * 新增学习路线总体
     */
    @PreAuthorize("@ss.hasPermi('back:totalRoute:add')")
    @Log(title = "学习路线总体", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TotalRoute totalRoute)
    {
        return toAjax(totalRouteService.insertTotalRoute(totalRoute));
    }

    /**
     * 修改学习路线总体
     */
    @PreAuthorize("@ss.hasPermi('back:totalRoute:edit')")
    @Log(title = "学习路线总体", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TotalRoute totalRoute)
    {
        return toAjax(totalRouteService.updateTotalRoute(totalRoute));
    }

    /**
     * 删除学习路线总体
     */
    @PreAuthorize("@ss.hasPermi('back:totalRoute:remove')")
    @Log(title = "学习路线总体", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(totalRouteService.deleteTotalRouteByIds(ids));
    }
}
