package net.tutorial.controller.back;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import net.tutorial.domain.entity.Video;
import net.tutorial.domain.page.TableDataInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import net.tutorial.annotation.Log;
import net.tutorial.controller.BaseController;
import net.tutorial.response.AjaxResult;
import net.tutorial.utils.enums.BusinessType;
import net.tutorial.utils.poi.ExcelUtil;
import net.tutorial.service.IVideoService;

/**
 * 视频管理Controller
 *
 * @author top
 * @date 2024-07-08
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/back/video")
public class BackVideoController extends BaseController
{
    private final IVideoService videoService;

    /**
     * 查询视频管理列表
     */
    @PreAuthorize("@ss.hasPermi('back:video:list')")
    @GetMapping("/list")
    public TableDataInfo list(Video video)
    {
        startPage();
        List<Video> list = videoService.selectVideoList(video);
        return getDataTable(list);
    }

    /**
     * 导出视频管理列表
     */
    @PreAuthorize("@ss.hasPermi('back:video:export')")
    @Log(title = "视频管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Video video)
    {
        List<Video> list = videoService.selectVideoList(video);
        ExcelUtil<Video> util = new ExcelUtil<Video>(Video.class);
        util.exportExcel(response, list, "视频管理数据");
    }

    /**
     * 获取视频管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('back:video:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(videoService.selectVideoById(id));
    }

    /**
     * 新增视频管理
     */
    @PreAuthorize("@ss.hasPermi('back:video:add')")
    @Log(title = "视频管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Video video)
    {
        return toAjax(videoService.insertVideo(video));
    }

    /**
     * 修改视频管理
     */
    @PreAuthorize("@ss.hasPermi('back:video:edit')")
    @Log(title = "视频管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Video video)
    {
        return toAjax(videoService.updateVideo(video));
    }

    /**
     * 删除视频管理
     */
    @PreAuthorize("@ss.hasPermi('back:video:remove')")
    @Log(title = "视频管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(videoService.deleteVideoByIds(ids));
    }
}
