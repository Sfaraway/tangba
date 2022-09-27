package com.ruoyi.contract.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 印章对象 t_seal
 * 
 * @author ch
 * @date 2022-09-27
 */
public class TSeal extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 印章ID */
    @Excel(name = "印章ID")
    private Integer id;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String name;

    /** seal */
    @Excel(name = "seal")
    private String sealUrl;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addTime;

    /** 添加人 */
    @Excel(name = "添加人")
    private Integer addUserId;

    /** 修改人 */
    @Excel(name = "修改人")
    private Integer updateUserId;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setSealUrl(String sealUrl) 
    {
        this.sealUrl = sealUrl;
    }

    public String getSealUrl() 
    {
        return sealUrl;
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
    public void setAddUserId(Integer addUserId) 
    {
        this.addUserId = addUserId;
    }

    public Integer getAddUserId() 
    {
        return addUserId;
    }
    public void setUpdateUserId(Integer updateUserId) 
    {
        this.updateUserId = updateUserId;
    }

    public Integer getUpdateUserId() 
    {
        return updateUserId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("sealUrl", getSealUrl())
            .append("status", getStatus())
            .append("addTime", getAddTime())
            .append("updateTime", getUpdateTime())
            .append("addUserId", getAddUserId())
            .append("updateUserId", getUpdateUserId())
            .toString();
    }
}
