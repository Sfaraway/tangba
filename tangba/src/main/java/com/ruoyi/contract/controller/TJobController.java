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
import com.ruoyi.contract.domain.TJob;
import com.ruoyi.contract.service.ITJobService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 职位Controller
 * 
 * @author yixin
 * @date 2022-09-25
 */
@RestController
@RequestMapping("/tangba/job")
public class TJobController extends BaseController
{
    @Autowired
    private ITJobService tJobService;

    /**
     * 查询职位列表
     */
    @PreAuthorize("@ss.hasPermi('tangba:job:list')")
    @GetMapping("/list")
    public TableDataInfo list(TJob tJob)
    {
        startPage();
        List<TJob> list = tJobService.selectTJobList(tJob);
        return getDataTable(list);
    }

    /**
     * 导出职位列表
     */
    @PreAuthorize("@ss.hasPermi('tangba:job:export')")
    @Log(title = "职位", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TJob tJob)
    {
        List<TJob> list = tJobService.selectTJobList(tJob);
        ExcelUtil<TJob> util = new ExcelUtil<TJob>(TJob.class);
        util.exportExcel(response, list, "职位数据");
    }

    /**
     * 获取职位详细信息
     */
    @PreAuthorize("@ss.hasPermi('tangba:job:query')")
    @GetMapping(value = "/{jobId}")
    public AjaxResult getInfo(@PathVariable("jobId") Long jobId)
    {
        return AjaxResult.success(tJobService.selectTJobByJobId(jobId));
    }

    /**
     * 新增职位
     */
    @PreAuthorize("@ss.hasPermi('tangba:job:add')")
    @Log(title = "职位", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TJob tJob)
    {
        return toAjax(tJobService.insertTJob(tJob));
    }

    /**
     * 修改职位
     */
    @PreAuthorize("@ss.hasPermi('tangba:job:edit')")
    @Log(title = "职位", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TJob tJob)
    {
        return toAjax(tJobService.updateTJob(tJob));
    }

    /**
     * 删除职位
     */
    @PreAuthorize("@ss.hasPermi('tangba:job:remove')")
    @Log(title = "职位", businessType = BusinessType.DELETE)
	@DeleteMapping("/{jobIds}")
    public AjaxResult remove(@PathVariable Long[] jobIds)
    {
        return toAjax(tJobService.deleteTJobByJobIds(jobIds));
    }
}
