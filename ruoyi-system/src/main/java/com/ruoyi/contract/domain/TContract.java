package com.ruoyi.contract.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 合同对象 t_contract
 * 
 * @author ruoyi
 * @date 2022-09-23
 */
public class TContract extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 员工的Id */
    @Excel(name = "员工的Id")
    private Long empId;

    /** 客户的Id */
    @Excel(name = "客户的Id")
    private Long customerId;

    /** 合同名称 */
    @Excel(name = "合同名称")
    private String name;

    /** add_time	datetime	0	0	0	0	0	0	0		0	添加时间				0	0 */
    @Excel(name = "add_time	datetime	0	0	0	0	0	0	0		0	添加时间				0	0")
    private String enclosure;

    /** 1:纸质合同 2：电子合同 */
    @Excel(name = "1:纸质合同 2：电子合同")
    private Long type;

    /** 状态 1：启动 0关闭 */
    @Excel(name = "状态 1：启动 0关闭")
    private Long status;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addTime;

    /** 添加人 */
    @Excel(name = "添加人")
    private Long addUserId;

    /** 修改人 */
    @Excel(name = "修改人")
    private Long updateUserId;

    /** 合同状态 1：未审核 2：审核中 3：该掌中 4：配送中 5已完成 */
    @Excel(name = "合同状态 1：未审核 2：审核中 3：该掌中 4：配送中 5已完成")
    private Long contractStatus;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
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
    public void setEnclosure(String enclosure) 
    {
        this.enclosure = enclosure;
    }

    public String getEnclosure() 
    {
        return enclosure;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
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
    public void setContractStatus(Long contractStatus) 
    {
        this.contractStatus = contractStatus;
    }

    public Long getContractStatus() 
    {
        return contractStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("empId", getEmpId())
            .append("customerId", getCustomerId())
            .append("name", getName())
            .append("enclosure", getEnclosure())
            .append("type", getType())
            .append("status", getStatus())
            .append("addTime", getAddTime())
            .append("updateTime", getUpdateTime())
            .append("addUserId", getAddUserId())
            .append("updateUserId", getUpdateUserId())
            .append("contractStatus", getContractStatus())
            .toString();
    }
}
