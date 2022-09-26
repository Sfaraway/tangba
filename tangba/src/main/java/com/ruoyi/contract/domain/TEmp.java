package com.ruoyi.contract.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 员工对象 t_emp
 * 
 * @author yixin
 * @date 2022-09-25
 */
public class TEmp extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long eId;

    /** 账号 */
    @Excel(name = "账号")
    private String eAcount;

    /** 姓名 */
    @Excel(name = "姓名")
    private String ename;

    /** 电话 */
    @Excel(name = "电话")
    private String tel;

    /** 职务ID */
    @Excel(name = "职务ID")
    private Long jobId;

    /** 部门ID */
    @Excel(name = "部门ID")
    private Long deptId;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
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

    /** 部门 */
    @Excel(name = "部门")
    private String dname;

    /** 职位 */
    @Excel(name = "职位")
    private String job;

    public void seteId(Long eId) 
    {
        this.eId = eId;
    }

    public Long geteId() 
    {
        return eId;
    }
    public void seteAcount(String eAcount) 
    {
        this.eAcount = eAcount;
    }

    public String geteAcount() 
    {
        return eAcount;
    }
    public void setEname(String ename) 
    {
        this.ename = ename;
    }

    public String getEname() 
    {
        return ename;
    }
    public void setTel(String tel) 
    {
        this.tel = tel;
    }

    public String getTel() 
    {
        return tel;
    }
    public void setJobId(Long jobId) 
    {
        this.jobId = jobId;
    }

    public Long getJobId() 
    {
        return jobId;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
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
    public void setDname(String dname) 
    {
        this.dname = dname;
    }

    public String getDname() 
    {
        return dname;
    }
    public void setJob(String job) 
    {
        this.job = job;
    }

    public String getJob() 
    {
        return job;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("eId", geteId())
            .append("eAcount", geteAcount())
            .append("ename", getEname())
            .append("tel", getTel())
            .append("jobId", getJobId())
            .append("deptId", getDeptId())
            .append("status", getStatus())
            .append("addTime", getAddTime())
            .append("updateTime", getUpdateTime())
            .append("addUserId", getAddUserId())
            .append("updateUserId", getUpdateUserId())
            .append("dname", getDname())
            .append("job", getJob())
            .toString();
    }
}
