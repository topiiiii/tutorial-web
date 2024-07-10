package net.tutorial.controller.back;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import net.tutorial.domain.entity.Article;
import net.tutorial.domain.page.TableDataInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import net.tutorial.annotation.Log;
import net.tutorial.controller.BaseController;
import net.tutorial.response.AjaxResult;
import net.tutorial.utils.enums.BusinessType;
import net.tutorial.utils.poi.ExcelUtil;
import net.tutorial.service.IArticleService;

/**
 * 面试经验Controller
 *
 * @author top
 * @date 2024-07-08
 */
@RestController
@RequestMapping("/back/article")
@RequiredArgsConstructor
public class BackArticleController extends BaseController
{
    private final IArticleService articleService;

    /**
     * 查询面试经验列表
     */
    @PreAuthorize("@ss.hasPermi('back:article:list')")
    @GetMapping("/list")
    public TableDataInfo list(Article article)
    {
        startPage();
        List<Article> list = articleService.selectArticleList(article);
        return getDataTable(list);
    }

    /**
     * 导出面试经验列表
     */
    @PreAuthorize("@ss.hasPermi('back:article:export')")
    @Log(title = "面试经验", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Article article)
    {
        List<Article> list = articleService.selectArticleList(article);
        ExcelUtil<Article> util = new ExcelUtil<Article>(Article.class);
        util.exportExcel(response, list, "面试经验数据");
    }

    /**
     * 获取面试经验详细信息
     */
    @PreAuthorize("@ss.hasPermi('back:article:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(articleService.selectArticleById(id));
    }

    /**
     * 新增面试经验
     */
    @PreAuthorize("@ss.hasPermi('back:article:add')")
    @Log(title = "面试经验", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Article article)
    {
        return toAjax(articleService.insertArticle(article));
    }

    /**
     * 修改面试经验
     */
    @PreAuthorize("@ss.hasPermi('back:article:edit')")
    @Log(title = "面试经验", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Article article)
    {
        return toAjax(articleService.updateArticle(article));
    }

    /**
     * 删除面试经验
     */
    @PreAuthorize("@ss.hasPermi('back:article:remove')")
    @Log(title = "面试经验", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(articleService.deleteArticleByIds(ids));
    }
}
