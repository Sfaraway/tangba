package com.ruoyi.contract.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
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
import com.ruoyi.contract.domain.TContract;
import com.ruoyi.contract.service.ITContractService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 合同Controller
 * 
 * @author ruoyi
 * @date 2022-09-23
 */
@RestController
@RequestMapping("/tcon/contract")
public class TContractController extends BaseController
{
    @Autowired
    private ITContractService tContractService;

    /**
     * 查询合同列表
     */
    @PreAuthorize("@ss.hasPermi('tcon:contract:list')")
    @GetMapping("/list")
    public TableDataInfo list(TContract tContract)
    {
        startPage();
        List<TContract> list = tContractService.selectTContractList(tContract);
        return getDataTable(list);
    }



    /**
     * 导出合同列表
     */
    @PreAuthorize("@ss.hasPermi('tcon:contract:export')")
    @Log(title = "合同", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TContract tContract)
    {
        List<TContract> list = tContractService.selectTContractList(tContract);
        ExcelUtil<TContract> util = new ExcelUtil<TContract>(TContract.class);
        util.exportExcel(response, list, "合同数据");
    }
    


    /**
     * 获取合同详细信息
     */
    @PreAuthorize("@ss.hasPermi('tcon:contract:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tContractService.selectTContractById(id));
    }
    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('tcon:contract:edit')")
    @Log(title = "合同", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody TContract tContract)
    {
        tContract.setUpdateBy(getUsername());
        return toAjax(tContractService.updateStatus(tContract));
    }

    /**
     * 审批状态修改
     */
    @PreAuthorize("@ss.hasPermi('tcon:contract:edit')")
    @Log(title = "合同", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatusTwo")
    public AjaxResult changeStatusTwo(@RequestBody TContract tContract)
    {

        tContract.setUpdateBy(getUsername());
        return toAjax(tContractService.updateStatusTwo(tContract));
    }
    /**
     * 新增合同
     */
    @PreAuthorize("@ss.hasPermi('tcon:contract:add')")
    @Log(title = "合同", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TContract tContract)
    {
        return toAjax(tContractService.insertTContract(tContract));
    }

    /**
     * 修改合同
     */
    @PreAuthorize("@ss.hasPermi('tcon:contract:edit')")
    @Log(title = "合同", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TContract tContract)
    {
        return toAjax(tContractService.updateTContract(tContract));
    }

    /**
     * 删除合同
     */
    @PreAuthorize("@ss.hasPermi('tcon:contract:remove')")
    @Log(title = "合同", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tContractService.deleteTContractByIds(ids));
    }
}

