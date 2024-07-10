package net.tutorial.controller.back;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import net.tutorial.annotation.Log;
import net.tutorial.controller.BaseController;
import net.tutorial.domain.entity.Books;
import net.tutorial.domain.page.TableDataInfo;
import net.tutorial.response.AjaxResult;
import net.tutorial.utils.enums.BusinessType;
import net.tutorial.utils.poi.ExcelUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import net.tutorial.service.IBooksService;

/**
 * 图书管理Controller
 *
 * @author top
 * @date 2024-07-08
 */
@RestController
@RequestMapping("/back/books")
@RequiredArgsConstructor
public class BackBooksController extends BaseController
{
    private final IBooksService booksService;

    /**
     * 查询图书管理列表
     */
    @PreAuthorize("@ss.hasPermi('back:books:list')")
    @GetMapping("/list")
    public TableDataInfo list(Books books)
    {
        startPage();
        List<Books> list = booksService.selectBooksList(books);
        return getDataTable(list);
    }

    /**
     * 导出图书管理列表
     */
    @PreAuthorize("@ss.hasPermi('back:books:export')")
    @Log(title = "图书管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Books books)
    {
        List<Books> list = booksService.selectBooksList(books);
        ExcelUtil<Books> util = new ExcelUtil<Books>(Books.class);
        util.exportExcel(response, list, "图书管理数据");
    }

    /**
     * 获取图书管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('back:books:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(booksService.selectBooksById(id));
    }

    /**
     * 新增图书管理
     */
    @PreAuthorize("@ss.hasPermi('back:books:add')")
    @Log(title = "图书管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Books books)
    {
        return toAjax(booksService.insertBooks(books));
    }

    /**
     * 修改图书管理
     */
    @PreAuthorize("@ss.hasPermi('back:books:edit')")
    @Log(title = "图书管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Books books)
    {
        return toAjax(booksService.updateBooks(books));
    }

    /**
     * 删除图书管理
     */
    @PreAuthorize("@ss.hasPermi('back:books:remove')")
    @Log(title = "图书管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(booksService.deleteBooksByIds(ids));
    }
}
