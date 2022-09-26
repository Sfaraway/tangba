package com.ruoyi.contract.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.contract.domain.TDept;
import com.ruoyi.contract.service.ITDeptService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 部门Controller
 * 
 * @author yixin
 * @date 2022-09-25
 */
@RestController
@RequestMapping("/tangba/dept")
public class TDeptController extends BaseController
{
    @Autowired
    private ITDeptService tDeptService;

    /**
     * 查询部门列表
     */
    @PreAuthorize("@ss.hasPermi('tangba:dept:list')")
    @GetMapping("/list")
    public TableDataInfo list(TDept tDept)
    {
        startPage();
        List<TDept> list = tDeptService.selectTDeptList(tDept);
        return getDataTable(list);
    }

    /**
     * 导出部门列表
     */
    @PreAuthorize("@ss.hasPermi('tangba:dept:export')")
    @Log(title = "部门", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TDept tDept)
    {
        List<TDept> list = tDeptService.selectTDeptList(tDept);
        ExcelUtil<TDept> util = new ExcelUtil<TDept>(TDept.class);
        util.exportExcel(response, list, "部门数据");
    }

    /**
     * 获取部门详细信息
     */
    @PreAuthorize("@ss.hasPermi('tangba:dept:query')")
    @GetMapping(value = "/{deptId}")
    public AjaxResult getInfo(@PathVariable("deptId") Long deptId)
    {
        return AjaxResult.success(tDeptService.selectTDeptByDeptId(deptId));
    }

    /**
     * 新增部门
     */
    @PreAuthorize("@ss.hasPermi('tangba:dept:add')")
    @Log(title = "部门", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TDept tDept)
    {
        return toAjax(tDeptService.insertTDept(tDept));
    }

    /**
     * 修改部门
     */
    @PreAuthorize("@ss.hasPermi('tangba:dept:edit')")
    @Log(title = "部门", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TDept tDept)
    {
        return toAjax(tDeptService.updateTDept(tDept));
    }

    /**
     * 删除部门
     */
    @PreAuthorize("@ss.hasPermi('tangba:dept:remove')")
    @Log(title = "部门", businessType = BusinessType.DELETE)
	@DeleteMapping("/{deptIds}")
    public AjaxResult remove(@PathVariable Long[] deptIds)
    {
        return toAjax(tDeptService.deleteTDeptByDeptIds(deptIds));
    }
}
