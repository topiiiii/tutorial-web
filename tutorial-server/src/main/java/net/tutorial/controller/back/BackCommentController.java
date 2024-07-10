package net.tutorial.controller.back;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import net.tutorial.annotation.Log;
import net.tutorial.controller.BaseController;
import net.tutorial.domain.entity.Comment;
import net.tutorial.domain.page.TableDataInfo;
import net.tutorial.response.AjaxResult;
import net.tutorial.utils.enums.BusinessType;
import net.tutorial.utils.poi.ExcelUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import net.tutorial.service.ICommentService;

/**
 * 评论管理Controller
 *
 * @author top
 * @date 2024-07-08
 */
@RestController
@RequestMapping("/back/comment")
@RequiredArgsConstructor
public class BackCommentController extends BaseController
{
    private final ICommentService commentService;

    /**
     * 查询评论管理列表
     */
    @PreAuthorize("@ss.hasPermi('back:comment:list')")
    @GetMapping("/list")
    public TableDataInfo list(Comment comment)
    {
        startPage();
        List<Comment> list = commentService.selectCommentList(comment);
        return getDataTable(list);
    }

    /**
     * 导出评论管理列表
     */
    @PreAuthorize("@ss.hasPermi('back:comment:export')")
    @Log(title = "评论管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Comment comment)
    {
        List<Comment> list = commentService.selectCommentList(comment);
        ExcelUtil<Comment> util = new ExcelUtil<Comment>(Comment.class);
        util.exportExcel(response, list, "评论管理数据");
    }

    /**
     * 获取评论管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('back:comment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(commentService.selectCommentById(id));
    }

    /**
     * 新增评论管理
     */
    @PreAuthorize("@ss.hasPermi('back:comment:add')")
    @Log(title = "评论管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Comment comment)
    {
        return toAjax(commentService.insertComment(comment));
    }

    /**
     * 修改评论管理
     */
    @PreAuthorize("@ss.hasPermi('back:comment:edit')")
    @Log(title = "评论管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Comment comment)
    {
        return toAjax(commentService.updateComment(comment));
    }

    /**
     * 删除评论管理
     */
    @PreAuthorize("@ss.hasPermi('back:comment:remove')")
    @Log(title = "评论管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(commentService.deleteCommentByIds(ids));
    }
}
