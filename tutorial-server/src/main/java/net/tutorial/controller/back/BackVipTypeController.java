package net.tutorial.controller.back;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import net.tutorial.domain.entity.VipType;
import net.tutorial.domain.page.TableDataInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import net.tutorial.annotation.Log;
import net.tutorial.controller.BaseController;
import net.tutorial.response.AjaxResult;
import net.tutorial.utils.enums.BusinessType;
import net.tutorial.utils.poi.ExcelUtil;
import net.tutorial.service.IVipTypeService;

/**
 * 会员类型Controller
 *
 * @author top
 * @date 2024-07-08
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/back/vipType")
public class BackVipTypeController extends BaseController
{
    private final IVipTypeService vipTypeService;

    /**
     * 查询会员类型列表
     */
    @PreAuthorize("@ss.hasPermi('back:vipType:list')")
    @GetMapping("/list")
    public TableDataInfo list(VipType vipType)
    {
        startPage();
        List<VipType> list = vipTypeService.selectVipTypeList(vipType);
        return getDataTable(list);
    }

    /**
     * 导出会员类型列表
     */
    @PreAuthorize("@ss.hasPermi('back:vipType:export')")
    @Log(title = "会员类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VipType vipType)
    {
        List<VipType> list = vipTypeService.selectVipTypeList(vipType);
        ExcelUtil<VipType> util = new ExcelUtil<VipType>(VipType.class);
        util.exportExcel(response, list, "会员类型数据");
    }

    /**
     * 获取会员类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('back:vipType:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(vipTypeService.selectVipTypeById(id));
    }

    /**
     * 新增会员类型
     */
    @PreAuthorize("@ss.hasPermi('back:vipType:add')")
    @Log(title = "会员类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VipType vipType)
    {
        return toAjax(vipTypeService.insertVipType(vipType));
    }

    /**
     * 修改会员类型
     */
    @PreAuthorize("@ss.hasPermi('back:vipType:edit')")
    @Log(title = "会员类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VipType vipType)
    {
        return toAjax(vipTypeService.updateVipType(vipType));
    }

    /**
     * 删除会员类型
     */
    @PreAuthorize("@ss.hasPermi('back:vipType:remove')")
    @Log(title = "会员类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(vipTypeService.deleteVipTypeByIds(ids));
    }
}
