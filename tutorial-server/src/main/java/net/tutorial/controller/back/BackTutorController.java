package net.tutorial.controller.back;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import net.tutorial.domain.entity.Tutor;
import net.tutorial.domain.page.TableDataInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import net.tutorial.annotation.Log;
import net.tutorial.controller.BaseController;
import net.tutorial.response.AjaxResult;
import net.tutorial.utils.enums.BusinessType;
import net.tutorial.utils.poi.ExcelUtil;
import net.tutorial.service.ITutorService;

/**
 * 辅导班Controller
 *
 * @author top
 * @date 2024-07-08
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/back/tutor")
public class BackTutorController extends BaseController
{
    private final ITutorService tutorService;

    /**
     * 查询辅导班列表
     */
    @PreAuthorize("@ss.hasPermi('back:tutor:list')")
    @GetMapping("/list")
    public TableDataInfo list(Tutor tutor)
    {
        startPage();
        List<Tutor> list = tutorService.selectTutorList(tutor);
        return getDataTable(list);
    }

    /**
     * 导出辅导班列表
     */
    @PreAuthorize("@ss.hasPermi('back:tutor:export')")
    @Log(title = "辅导班", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Tutor tutor)
    {
        List<Tutor> list = tutorService.selectTutorList(tutor);
        ExcelUtil<Tutor> util = new ExcelUtil<Tutor>(Tutor.class);
        util.exportExcel(response, list, "辅导班数据");
    }

    /**
     * 获取辅导班详细信息
     */
    @PreAuthorize("@ss.hasPermi('back:tutor:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tutorService.selectTutorById(id));
    }

    /**
     * 新增辅导班
     */
    @PreAuthorize("@ss.hasPermi('back:tutor:add')")
    @Log(title = "辅导班", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Tutor tutor)
    {
        return toAjax(tutorService.insertTutor(tutor));
    }

    /**
     * 修改辅导班
     */
    @PreAuthorize("@ss.hasPermi('back:tutor:edit')")
    @Log(title = "辅导班", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Tutor tutor)
    {
        return toAjax(tutorService.updateTutor(tutor));
    }

    /**
     * 删除辅导班
     */
    @PreAuthorize("@ss.hasPermi('back:tutor:remove')")
    @Log(title = "辅导班", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tutorService.deleteTutorByIds(ids));
    }
}
