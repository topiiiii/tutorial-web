package net.tutorial.controller.back;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import net.tutorial.domain.entity.VipUser;
import net.tutorial.domain.page.TableDataInfo;
import net.tutorial.utils.poi.ExcelUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import net.tutorial.annotation.Log;
import net.tutorial.controller.BaseController;
import net.tutorial.response.AjaxResult;
import net.tutorial.utils.enums.BusinessType;
import net.tutorial.service.IVipUserService;

/**
 * 用户会员Controller
 *
 * @author top
 * @date 2024-07-08
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/back/vipUser")
public class BackVipUserController extends BaseController
{
    private final IVipUserService vipUserService;

    /**
     * 查询用户会员列表
     */
    @PreAuthorize("@ss.hasPermi('back:vipUser:list')")
    @GetMapping("/list")
    public TableDataInfo list(VipUser vipUser)
    {
        startPage();
        List<VipUser> list = vipUserService.selectVipUserList(vipUser);
        return getDataTable(list);
    }

    /**
     * 导出用户会员列表
     */
    @PreAuthorize("@ss.hasPermi('back:vipUser:export')")
    @Log(title = "用户会员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VipUser vipUser)
    {
        List<VipUser> list = vipUserService.selectVipUserList(vipUser);
        ExcelUtil<VipUser> util = new ExcelUtil<VipUser>(VipUser.class);
        util.exportExcel(response, list, "用户会员数据");
    }

    /**
     * 获取用户会员详细信息
     */
    @PreAuthorize("@ss.hasPermi('back:vipUser:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(vipUserService.selectVipUserById(id));
    }

    /**
     * 新增用户会员
     */
    @PreAuthorize("@ss.hasPermi('back:vipUser:add')")
    @Log(title = "用户会员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VipUser vipUser)
    {
        return toAjax(vipUserService.insertVipUser(vipUser));
    }

    /**
     * 修改用户会员
     */
    @PreAuthorize("@ss.hasPermi('back:vipUser:edit')")
    @Log(title = "用户会员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VipUser vipUser)
    {
        return toAjax(vipUserService.updateVipUser(vipUser));
    }

    /**
     * 删除用户会员
     */
    @PreAuthorize("@ss.hasPermi('back:vipUser:remove')")
    @Log(title = "用户会员", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(vipUserService.deleteVipUserByIds(ids));
    }
}
