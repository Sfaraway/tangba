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
import com.ruoyi.contract.domain.TCustomer;
import com.ruoyi.contract.domain.TEmp;
import com.ruoyi.contract.service.ITCustomerService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 客户Controller
 * 
 * @author zhou
 * @date 2022-09-25
 */
@RestController
@RequestMapping("/tangba/customer")
public class TCustomerController extends BaseController
{
    @Autowired
    private ITCustomerService tCustomerService;

    /**
     * 查询客户列表
     */
    @PreAuthorize("@ss.hasPermi('tangba:customer:list')")
    @GetMapping("/list")
    public TableDataInfo list(TCustomer tCustomer)
    {
        startPage();
        List<TCustomer> list = tCustomerService.selectTCustomerList(tCustomer);
        return getDataTable(list);
    }

    /**
     * 导出客户列表
     */
    @PreAuthorize("@ss.hasPermi('tangba:customer:export')")
    @Log(title = "客户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TCustomer tCustomer)
    {
        List<TCustomer> list = tCustomerService.selectTCustomerList(tCustomer);
        ExcelUtil<TCustomer> util = new ExcelUtil<TCustomer>(TCustomer.class);
        util.exportExcel(response, list, "客户数据");
    }

    /**
     * 获取客户详细信息
     */
    @PreAuthorize("@ss.hasPermi('tangba:customer:query')")
    @GetMapping(value = "/{cId}")
    public AjaxResult getInfo(@PathVariable("cId") Long cId)
    {
        return AjaxResult.success(tCustomerService.selectTCustomerByCId(cId));
    }

    /**
     * 新增客户
     */
    @PreAuthorize("@ss.hasPermi('tangba:customer:add')")
    @Log(title = "客户", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody TCustomer tCustomer)
    {
        return toAjax(tCustomerService.insertTCustomer(tCustomer));
    }

    /**
     * 修改客户
     */
    @PreAuthorize("@ss.hasPermi('tangba:customer:edit')")
    @Log(title = "客户", businessType = BusinessType.UPDATE)
    @PutMapping("/update")
    public AjaxResult edit(@RequestBody TCustomer tCustomer)
    {
        return toAjax(tCustomerService.updateTCustomer(tCustomer));
    }

    /**
     * 删除客户
     */
    @PreAuthorize("@ss.hasPermi('tangba:customer:remove')")
    @Log(title = "客户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{cIds}")
    public AjaxResult remove(@PathVariable Long[] cIds)
    {
        return toAjax(tCustomerService.deleteTCustomerByCIds(cIds));
    }
    
    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('tangba:customer:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody TCustomer tCustomer)
    {
//        userService.checkUserAllowed(user);
//        userService.checkUserDataScope(user.getUserId());
//        user.setUpdateBy(getUsername());
        return toAjax(tCustomerService.updateTCustomerStatus(tCustomer));
    }
}
