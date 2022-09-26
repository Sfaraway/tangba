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
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.contract.domain.TEmp;
import com.ruoyi.contract.service.ITEmpService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 员工Controller
 * 
 * @author yixin
 * @date 2022-09-25
 */
@RestController
@RequestMapping("/tangba/emp")
public class TEmpController extends BaseController
{
    @Autowired
    private ITEmpService tEmpService;

    /**
     * 查询员工列表
     */
    @PreAuthorize("@ss.hasPermi('tangba:emp:list')")
    @GetMapping("/list")
    public TableDataInfo list(TEmp tEmp)
    {
        startPage();
        List<TEmp> list = tEmpService.selectTEmpList(tEmp);
        return getDataTable(list);
    }

    /**
     * 导出员工列表
     */
    @PreAuthorize("@ss.hasPermi('tangba:emp:export')")
    @Log(title = "员工", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TEmp tEmp)
    {
        List<TEmp> list = tEmpService.selectTEmpList(tEmp);
        ExcelUtil<TEmp> util = new ExcelUtil<TEmp>(TEmp.class);
        util.exportExcel(response, list, "员工数据");
    }

    /**
     * 获取员工详细信息
     */
    @PreAuthorize("@ss.hasPermi('tangba:emp:query')")
    @GetMapping(value = "/{eId}")
    public AjaxResult getInfo(@PathVariable("eId") Long eId)
    {
        return AjaxResult.success(tEmpService.selectTEmpByEId(eId));
    }

    /**
     * 新增员工
     */
    @PreAuthorize("@ss.hasPermi('tangba:emp:add')")
    @Log(title = "员工", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody TEmp tEmp)
    {
        return toAjax(tEmpService.insertTEmp(tEmp));
    }

    /**
     * 修改员工
     */
    @PreAuthorize("@ss.hasPermi('tangba:emp:edit')")
    @Log(title = "员工", businessType = BusinessType.UPDATE)
    @PutMapping("/update")
    public AjaxResult edit(@RequestBody TEmp tEmp)
    {
        return toAjax(tEmpService.updateTEmp(tEmp));
    }

    /**
     * 删除员工
     */
    @PreAuthorize("@ss.hasPermi('tangba:emp:remove')")
    @Log(title = "员工", businessType = BusinessType.DELETE)
	@DeleteMapping("/{eIds}")
    public AjaxResult remove(@PathVariable Long[] eIds)
    {
        return toAjax(tEmpService.deleteTEmpByEIds(eIds));
    }
    
    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('tangba:emp:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody TEmp tEmp)
    {
//        userService.checkUserAllowed(user);
//        userService.checkUserDataScope(user.getUserId());
//        user.setUpdateBy(getUsername());
        return toAjax(tEmpService.updateTEmpStatus(tEmp));
    }
}
