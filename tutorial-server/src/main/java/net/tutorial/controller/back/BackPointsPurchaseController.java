package net.tutorial.controller.back;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import net.tutorial.annotation.Log;
import net.tutorial.controller.BaseController;
import net.tutorial.domain.entity.PointsPurchase;
import net.tutorial.domain.page.TableDataInfo;
import net.tutorial.response.AjaxResult;
import net.tutorial.utils.enums.BusinessType;
import net.tutorial.utils.poi.ExcelUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import net.tutorial.service.IPointsPurchaseService;

/**
 * 积分购买记录Controller
 *
 * @author top
 * @date 2024-07-08
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/back/pointsPurchase")
public class BackPointsPurchaseController extends BaseController
{
    private final IPointsPurchaseService pointsPurchaseService;

    /**
     * 查询积分购买记录列表
     */
    @PreAuthorize("@ss.hasPermi('back:pointsPurchase:list')")
    @GetMapping("/list")
    public TableDataInfo list(PointsPurchase pointsPurchase)
    {
        startPage();
        List<PointsPurchase> list = pointsPurchaseService.selectPointsPurchaseList(pointsPurchase);
        return getDataTable(list);
    }

    /**
     * 导出积分购买记录列表
     */
    @PreAuthorize("@ss.hasPermi('back:pointsPurchase:export')")
    @Log(title = "积分购买记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PointsPurchase pointsPurchase)
    {
        List<PointsPurchase> list = pointsPurchaseService.selectPointsPurchaseList(pointsPurchase);
        ExcelUtil<PointsPurchase> util = new ExcelUtil<PointsPurchase>(PointsPurchase.class);
        util.exportExcel(response, list, "积分购买记录数据");
    }

    /**
     * 获取积分购买记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('back:pointsPurchase:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pointsPurchaseService.selectPointsPurchaseById(id));
    }

    /**
     * 新增积分购买记录
     */
    @PreAuthorize("@ss.hasPermi('back:pointsPurchase:add')")
    @Log(title = "积分购买记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PointsPurchase pointsPurchase)
    {
        return toAjax(pointsPurchaseService.insertPointsPurchase(pointsPurchase));
    }

    /**
     * 修改积分购买记录
     */
    @PreAuthorize("@ss.hasPermi('back:pointsPurchase:edit')")
    @Log(title = "积分购买记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PointsPurchase pointsPurchase)
    {
        return toAjax(pointsPurchaseService.updatePointsPurchase(pointsPurchase));
    }

    /**
     * 删除积分购买记录
     */
    @PreAuthorize("@ss.hasPermi('back:pointsPurchase:remove')")
    @Log(title = "积分购买记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pointsPurchaseService.deletePointsPurchaseByIds(ids));
    }
}
