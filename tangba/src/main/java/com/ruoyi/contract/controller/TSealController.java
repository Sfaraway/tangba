package com.ruoyi.contract.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.SysUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.contract.domain.TSeal;
import com.ruoyi.contract.service.ITSealService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 印章Controller
 * 
 * @author ch
 * @date 2022-09-27
 */
@RestController
@RequestMapping("/eleseal/seal")
public class TSealController extends BaseController
{
    @Autowired
    private ITSealService tSealService;

    /**
     * 查询印章列表
     */
    @PreAuthorize("@ss.hasPermi('eleseal:seal:list')")
    @GetMapping("/list")
    public TableDataInfo list(TSeal tSeal)
    {
        startPage();
        List<TSeal> list = tSealService.selectTSealList(tSeal);
        return getDataTable(list);
    }

    /**
     * 导出印章列表
     */
    @PreAuthorize("@ss.hasPermi('eleseal:seal:export')")
    @Log(title = "印章", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TSeal tSeal)
    {
        List<TSeal> list = tSealService.selectTSealList(tSeal);
        ExcelUtil<TSeal> util = new ExcelUtil<TSeal>(TSeal.class);
        util.exportExcel(response, list, "印章数据");
    }

    /**
     * 获取印章详细信息
     */
    @PreAuthorize("@ss.hasPermi('eleseal:seal:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(tSealService.selectTSealById(id));
    }

    /**
     * 新增印章
     */
    @PreAuthorize("@ss.hasPermi('eleseal:seal:add')")
    @Log(title = "印章", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TSeal tSeal)
    {
       /* tSeal.setAddTime();*/
        return toAjax(tSealService.insertTSeal(tSeal));
    }

    /**
     * 修改印章
     */
    @PreAuthorize("@ss.hasPermi('eleseal:seal:edit')")
    @Log(title = "印章", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TSeal tSeal)
    {
        return toAjax(tSealService.updateTSeal(tSeal));
    }

    /**
     * 删除印章
     */
    @PreAuthorize("@ss.hasPermi('eleseal:seal:remove')")
    @Log(title = "印章", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(tSealService.deleteTSealByIds(ids));
    }

    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('eleseal:seal:edit')")
    @Log(title = "印章", businessType = BusinessType.UPDATE)
    @PutMapping("/changeSealStatus")
    public AjaxResult changeSealStatus(@RequestBody TSeal tSeal)
    {
      /*  userService.checkUserAllowed(user);
        userService.checkUserDataScope(user.getUserId());*/
        tSeal.setUpdateBy(getUsername());
        return toAjax(tSealService.updateSealStatus(tSeal));
    }
}
