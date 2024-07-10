package net.tutorial.controller.back;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import net.tutorial.domain.entity.Reply;
import net.tutorial.domain.page.TableDataInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import net.tutorial.annotation.Log;
import net.tutorial.controller.BaseController;
import net.tutorial.response.AjaxResult;
import net.tutorial.utils.enums.BusinessType;
import net.tutorial.utils.poi.ExcelUtil;
import net.tutorial.service.IReplyService;

/**
 * 回复Controller
 *
 * @author top
 * @date 2024-07-08
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/back/reply")
public class BackReplyController extends BaseController
{
    private final IReplyService replyService;

    /**
     * 查询回复列表
     */
    @PreAuthorize("@ss.hasPermi('back:reply:list')")
    @GetMapping("/list")
    public TableDataInfo list(Reply reply)
    {
        startPage();
        List<Reply> list = replyService.selectReplyList(reply);
        return getDataTable(list);
    }

    /**
     * 导出回复列表
     */
    @PreAuthorize("@ss.hasPermi('back:reply:export')")
    @Log(title = "回复", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Reply reply)
    {
        List<Reply> list = replyService.selectReplyList(reply);
        ExcelUtil<Reply> util = new ExcelUtil<Reply>(Reply.class);
        util.exportExcel(response, list, "回复数据");
    }

    /**
     * 获取回复详细信息
     */
    @PreAuthorize("@ss.hasPermi('back:reply:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(replyService.selectReplyById(id));
    }

    /**
     * 新增回复
     */
    @PreAuthorize("@ss.hasPermi('back:reply:add')")
    @Log(title = "回复", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Reply reply)
    {
        return toAjax(replyService.insertReply(reply));
    }

    /**
     * 修改回复
     */
    @PreAuthorize("@ss.hasPermi('back:reply:edit')")
    @Log(title = "回复", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Reply reply)
    {
        return toAjax(replyService.updateReply(reply));
    }

    /**
     * 删除回复
     */
    @PreAuthorize("@ss.hasPermi('back:reply:remove')")
    @Log(title = "回复", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(replyService.deleteReplyByIds(ids));
    }
}
