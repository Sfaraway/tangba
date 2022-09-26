package com.ruoyi.contract.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 客户对象 t_customer
 * 
 * @author zhou
 * @date 2022-09-25
 */
public class TCustomer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long cId;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String cname;

    /** 客户账号 */
    @Excel(name = "客户账号")
    private String cAccount;

    /** 电话 */
    @Excel(name = "电话")
    private String phone;

    /** 状态(0:启用,1:停用) */
    @Excel(name = "状态(0:启用,1:停用)")
    private String status;

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

    public void setcId(Long cId) 
    {
        this.cId = cId;
    }

    public Long getcId() 
    {
        return cId;
    }
    public void setCname(String cname) 
    {
        this.cname = cname;
    }

    public String getCname() 
    {
        return cname;
    }
    public void setcAccount(String cAccount) 
    {
        this.cAccount = cAccount;
    }

    public String getcAccount() 
    {
        return cAccount;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
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
            .append("cId", getcId())
            .append("cname", getCname())
            .append("cAccount", getcAccount())
            .append("phone", getPhone())
            .append("status", getStatus())
            .append("addTime", getAddTime())
            .append("updateTime", getUpdateTime())
            .append("addUserId", getAddUserId())
            .append("updateUserId", getUpdateUserId())
            .toString();
    }
}
