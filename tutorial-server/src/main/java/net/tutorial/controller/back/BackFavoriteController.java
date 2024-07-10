package net.tutorial.controller.back;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import net.tutorial.annotation.Log;
import net.tutorial.controller.BaseController;
import net.tutorial.domain.entity.Favorite;
import net.tutorial.domain.page.TableDataInfo;
import net.tutorial.response.AjaxResult;
import net.tutorial.utils.enums.BusinessType;
import net.tutorial.utils.poi.ExcelUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import net.tutorial.service.IFavoriteService;

/**
 * 用户收藏Controller
 *
 * @author top
 * @date 2024-07-08
 */
@RestController
@RequestMapping("/back/favorite")
public class BackFavoriteController extends BaseController
{
    @Autowired
    private IFavoriteService favoriteService;

    /**
     * 查询用户收藏列表
     */
    @PreAuthorize("@ss.hasPermi('back:favorite:list')")
    @GetMapping("/list")
    public TableDataInfo list(Favorite favorite)
    {
        startPage();
        List<Favorite> list = favoriteService.selectFavoriteList(favorite);
        return getDataTable(list);
    }

    /**
     * 导出用户收藏列表
     */
    @PreAuthorize("@ss.hasPermi('back:favorite:export')")
    @Log(title = "用户收藏", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Favorite favorite)
    {
        List<Favorite> list = favoriteService.selectFavoriteList(favorite);
        ExcelUtil<Favorite> util = new ExcelUtil<Favorite>(Favorite.class);
        util.exportExcel(response, list, "用户收藏数据");
    }

    /**
     * 获取用户收藏详细信息
     */
    @PreAuthorize("@ss.hasPermi('back:favorite:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(favoriteService.selectFavoriteById(id));
    }

    /**
     * 新增用户收藏
     */
    @PreAuthorize("@ss.hasPermi('back:favorite:add')")
    @Log(title = "用户收藏", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Favorite favorite)
    {
        return toAjax(favoriteService.insertFavorite(favorite));
    }

    /**
     * 修改用户收藏
     */
    @PreAuthorize("@ss.hasPermi('back:favorite:edit')")
    @Log(title = "用户收藏", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Favorite favorite)
    {
        return toAjax(favoriteService.updateFavorite(favorite));
    }

    /**
     * 删除用户收藏
     */
    @PreAuthorize("@ss.hasPermi('back:favorite:remove')")
    @Log(title = "用户收藏", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(favoriteService.deleteFavoriteByIds(ids));
    }
}
