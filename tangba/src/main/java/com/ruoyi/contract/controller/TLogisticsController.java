package com.ruoyi.contract.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.contract.domain.TLogistics;
import com.ruoyi.contract.service.ITLogisticsService;
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
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 物流Controller
 * 
 * @author Mr.Zeng
 * @date 2022-09-24
 */
@RestController
@RequestMapping("/logistic/logistics")
public class TLogisticsController extends BaseController
{
    @Autowired
    private ITLogisticsService tLogisticsService;

    /**
     * 查询物流列表
     */
    @PreAuthorize("@ss.hasPermi('logistic:logistics:list')")
    @GetMapping("/list")
    public TableDataInfo list(TLogistics tLogistics)
    {
        startPage();
        List<TLogistics> list = tLogisticsService.selectTLogisticsList(tLogistics);
        return getDataTable(list);
    }

    /**
     * 导出物流列表
     */
    @PreAuthorize("@ss.hasPermi('logistic:logistics:export')")
    @Log(title = "物流", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TLogistics tLogistics)
    {
        List<TLogistics> list = tLogisticsService.selectTLogisticsList(tLogistics);
        ExcelUtil<TLogistics> util = new ExcelUtil<TLogistics>(TLogistics.class);
        util.exportExcel(response, list, "物流数据");
    }

    /**
     * 获取物流详细信息
     */
    @PreAuthorize("@ss.hasPermi('logistic:logistics:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tLogisticsService.selectTLogisticsById(id));
    }

    /**
     * 新增物流
     */
    @PreAuthorize("@ss.hasPermi('logistic:logistics:add')")
    @Log(title = "物流", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TLogistics tLogistics)
    {
        return toAjax(tLogisticsService.insertTLogistics(tLogistics));
    }

    /**
     * 修改物流
     */
    @PreAuthorize("@ss.hasPermi('logistic:logistics:edit')")
    @Log(title = "物流", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TLogistics tLogistics)
    {
        return toAjax(tLogisticsService.updateTLogistics(tLogistics));
    }

    /**
     * 删除物流
     */
    @PreAuthorize("@ss.hasPermi('logistic:logistics:remove')")
    @Log(title = "物流", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tLogisticsService.deleteTLogisticsByIds(ids));
    }
}
