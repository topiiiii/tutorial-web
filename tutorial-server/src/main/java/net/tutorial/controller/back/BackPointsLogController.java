package net.tutorial.controller.back;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import net.tutorial.annotation.Log;
import net.tutorial.controller.BaseController;
import net.tutorial.domain.entity.PointsLog;
import net.tutorial.domain.page.TableDataInfo;
import net.tutorial.response.AjaxResult;
import net.tutorial.utils.enums.BusinessType;
import net.tutorial.utils.poi.ExcelUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import net.tutorial.service.IPointsLogService;

/**
 * 积分日志Controller
 *
 * @author top
 * @date 2024-07-08
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/back/pointsLog")
public class BackPointsLogController extends BaseController
{

    private final IPointsLogService pointsLogService;

    /**
     * 查询积分日志列表
     */
    @PreAuthorize("@ss.hasPermi('back:pointsLog:list')")
    @GetMapping("/list")
    public TableDataInfo list(PointsLog pointsLog)
    {
        startPage();
        List<PointsLog> list = pointsLogService.selectPointsLogList(pointsLog);
        return getDataTable(list);
    }

    /**
     * 导出积分日志列表
     */
    @PreAuthorize("@ss.hasPermi('back:pointsLog:export')")
    @Log(title = "积分日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PointsLog pointsLog)
    {
        List<PointsLog> list = pointsLogService.selectPointsLogList(pointsLog);
        ExcelUtil<PointsLog> util = new ExcelUtil<PointsLog>(PointsLog.class);
        util.exportExcel(response, list, "积分日志数据");
    }

    /**
     * 获取积分日志详细信息
     */
    @PreAuthorize("@ss.hasPermi('back:pointsLog:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pointsLogService.selectPointsLogById(id));
    }

    /**
     * 新增积分日志
     */
    @PreAuthorize("@ss.hasPermi('back:pointsLog:add')")
    @Log(title = "积分日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PointsLog pointsLog)
    {
        return toAjax(pointsLogService.insertPointsLog(pointsLog));
    }

    /**
     * 修改积分日志
     */
    @PreAuthorize("@ss.hasPermi('back:pointsLog:edit')")
    @Log(title = "积分日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PointsLog pointsLog)
    {
        return toAjax(pointsLogService.updatePointsLog(pointsLog));
    }

    /**
     * 删除积分日志
     */
    @PreAuthorize("@ss.hasPermi('back:pointsLog:remove')")
    @Log(title = "积分日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pointsLogService.deletePointsLogByIds(ids));
    }
}
