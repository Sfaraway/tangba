package com.ruoyi.contract.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 物流对象 t_logistics
 * 
 * @author Mr.Zeng
 * @date 2022-09-24
 */
public class TLogistics extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 合同id */
    @Excel(name = "合同id")
    private Long id;

    /** 快递单号 */
    @Excel(name = "快递单号")
    private String orderNumber;

    /** 员工Id */
    @Excel(name = "员工Id")
    private Long empId;

    /** 客户Id */
    @Excel(name = "客户Id")
    private Long customerId;


    /** 合同名称 */
    @Excel(name = "合同名称")
    private String name;

    /** 物流公司 */
    @Excel(name = "物流公司")
    private String company;

    /** 配送状态 */
    @Excel(name = "配送状态")
    private String status;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addTime;

    /** 添加人id */
    @Excel(name = "添加人id")
    private Long addUserId;

    /** 修改人id */
    @Excel(name = "修改人id")
    private Long updateUserId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOrderNumber(String orderNumber) 
    {
        this.orderNumber = orderNumber;
    }

    public String getOrderNumber() 
    {
        return orderNumber;
    }
    public void setEmpId(Long empId) 
    {
        this.empId = empId;
    }

    public Long getEmpId() 
    {
        return empId;
    }
    public void setCustomerId(Long customerId) 
    {
        this.customerId = customerId;
    }

    public Long getCustomerId() 
    {
        return customerId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setCompany(String company) 
    {
        this.company = company;
    }

    public String getCompany() 
    {
        return company;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setAddTime(Date addTime) 
    {
        this.addTime = addTime;
    }

    public Date getAddTime() 
    {
        return addTime;
    }
    public void setAddUserId(Long addUserId) 
    {
        this.addUserId = addUserId;
    }

    public Long getAddUserId() 
    {
        return addUserId;
    }
    public void setUpdateUserId(Long updateUserId) 
    {
        this.updateUserId = updateUserId;
    }

    public Long getUpdateUserId() 
    {
        return updateUserId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderNumber", getOrderNumber())
            .append("empId", getEmpId())
            .append("customerId", getCustomerId())
            .append("name", getName())
            .append("company", getCompany())
            .append("status", getStatus())
            .append("addTime", getAddTime())
            .append("updateTime", getUpdateTime())
            .append("addUserId", getAddUserId())
            .append("updateUserId", getUpdateUserId())
            .toString();
    }
}
