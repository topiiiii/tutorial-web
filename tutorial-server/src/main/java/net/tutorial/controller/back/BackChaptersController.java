package net.tutorial.controller.back;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import net.tutorial.annotation.Log;
import net.tutorial.controller.BaseController;
import net.tutorial.domain.entity.Chapters;
import net.tutorial.domain.page.TableDataInfo;
import net.tutorial.response.AjaxResult;
import net.tutorial.utils.enums.BusinessType;
import net.tutorial.utils.poi.ExcelUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import net.tutorial.service.IChaptersService;

/**
 * 章节管理Controller
 *
 * @author top
 * @date 2024-07-08
 */
@RestController
@RequestMapping("/back/chapters")
@RequiredArgsConstructor
public class BackChaptersController extends BaseController
{
    private final IChaptersService chaptersService;

    /**
     * 查询章节管理列表
     */
    @PreAuthorize("@ss.hasPermi('back:chapters:list')")
    @GetMapping("/list")
    public TableDataInfo list(Chapters chapters)
    {
        startPage();
        List<Chapters> list = chaptersService.selectChaptersList(chapters);
        return getDataTable(list);
    }

    /**
     * 导出章节管理列表
     */
    @PreAuthorize("@ss.hasPermi('back:chapters:export')")
    @Log(title = "章节管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Chapters chapters)
    {
        List<Chapters> list = chaptersService.selectChaptersList(chapters);
        ExcelUtil<Chapters> util = new ExcelUtil<Chapters>(Chapters.class);
        util.exportExcel(response, list, "章节管理数据");
    }

    /**
     * 获取章节管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('back:chapters:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(chaptersService.selectChaptersById(id));
    }

    /**
     * 新增章节管理
     */
    @PreAuthorize("@ss.hasPermi('back:chapters:add')")
    @Log(title = "章节管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Chapters chapters)
    {
        return toAjax(chaptersService.insertChapters(chapters));
    }

    /**
     * 修改章节管理
     */
    @PreAuthorize("@ss.hasPermi('back:chapters:edit')")
    @Log(title = "章节管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Chapters chapters)
    {
        return toAjax(chaptersService.updateChapters(chapters));
    }

    /**
     * 删除章节管理
     */
    @PreAuthorize("@ss.hasPermi('back:chapters:remove')")
    @Log(title = "章节管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(chaptersService.deleteChaptersByIds(ids));
    }
}
