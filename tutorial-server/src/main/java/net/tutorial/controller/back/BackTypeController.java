package net.tutorial.controller.back;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import net.tutorial.domain.entity.Type;
import net.tutorial.domain.page.TableDataInfo;
import net.tutorial.utils.poi.ExcelUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import net.tutorial.annotation.Log;
import net.tutorial.controller.BaseController;
import net.tutorial.response.AjaxResult;
import net.tutorial.utils.enums.BusinessType;
import net.tutorial.service.ITypeService;

/**
 * 类型管理Controller
 *
 * @author top
 * @date 2024-07-08
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/back/type")
public class BackTypeController extends BaseController
{

    private final ITypeService typeService;

    /**
     * 查询类型管理列表
     */
    @PreAuthorize("@ss.hasPermi('back:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(Type type)
    {
        startPage();
        List<Type> list = typeService.selectTypeList(type);
        return getDataTable(list);
    }

    /**
     * 导出类型管理列表
     */
    @PreAuthorize("@ss.hasPermi('back:type:export')")
    @Log(title = "类型管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Type type)
    {
        List<Type> list = typeService.selectTypeList(type);
        ExcelUtil<Type> util = new ExcelUtil<Type>(Type.class);
        util.exportExcel(response, list, "类型管理数据");
    }

    /**
     * 获取类型管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('back:type:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(typeService.selectTypeById(id));
    }

    /**
     * 新增类型管理
     */
    @PreAuthorize("@ss.hasPermi('back:type:add')")
    @Log(title = "类型管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Type type)
    {
        return toAjax(typeService.insertType(type));
    }

    /**
     * 修改类型管理
     */
    @PreAuthorize("@ss.hasPermi('back:type:edit')")
    @Log(title = "类型管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Type type)
    {
        return toAjax(typeService.updateType(type));
    }

    /**
     * 删除类型管理
     */
    @PreAuthorize("@ss.hasPermi('back:type:remove')")
    @Log(title = "类型管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(typeService.deleteTypeByIds(ids));
    }
}
