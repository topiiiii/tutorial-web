package net.tutorial.controller.back;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import net.tutorial.annotation.Log;
import net.tutorial.controller.BaseController;
import net.tutorial.domain.entity.Content;
import net.tutorial.domain.page.TableDataInfo;
import net.tutorial.response.AjaxResult;
import net.tutorial.utils.enums.BusinessType;
import net.tutorial.utils.poi.ExcelUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import net.tutorial.service.IContentService;

/**
 * 内容管理Controller
 *
 * @author top
 * @date 2024-07-08
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/back/content")
public class BackContentController extends BaseController
{
    private final IContentService contentService;

    /**
     * 查询内容管理列表
     */
    @PreAuthorize("@ss.hasPermi('back:content:list')")
    @GetMapping("/list")
    public TableDataInfo list(Content content)
    {
        startPage();
        List<Content> list = contentService.selectContentList(content);
        return getDataTable(list);
    }

    /**
     * 导出内容管理列表
     */
    @PreAuthorize("@ss.hasPermi('back:content:export')")
    @Log(title = "内容管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Content content)
    {
        List<Content> list = contentService.selectContentList(content);
        ExcelUtil<Content> util = new ExcelUtil<Content>(Content.class);
        util.exportExcel(response, list, "内容管理数据");
    }

    /**
     * 获取内容管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('back:content:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(contentService.selectContentById(id));
    }

    /**
     * 新增内容管理
     */
    @PreAuthorize("@ss.hasPermi('back:content:add')")
    @Log(title = "内容管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Content content)
    {
        return toAjax(contentService.insertContent(content));
    }

    /**
     * 修改内容管理
     */
    @PreAuthorize("@ss.hasPermi('back:content:edit')")
    @Log(title = "内容管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Content content)
    {
        return toAjax(contentService.updateContent(content));
    }

    /**
     * 删除内容管理
     */
    @PreAuthorize("@ss.hasPermi('back:content:remove')")
    @Log(title = "内容管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(contentService.deleteContentByIds(ids));
    }
}
