package com.ruoyi.contract.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.contract.domain.TEmp;
import com.ruoyi.contract.mapper.TJobMapper;
import com.ruoyi.contract.domain.TJob;
import com.ruoyi.contract.service.ITJobService;

/**
 * 职位Service业务层处理
 * 
 * @author yixin
 * @date 2022-09-25
 */
@Service
public class TJobServiceImpl implements ITJobService 
{
    @Autowired
    private TJobMapper tJobMapper;

    /**
     * 查询职位
     * 
     * @param jobId 职位主键
     * @return 职位
     */
    @Override
    public TJob selectTJobByJobId(Long jobId)
    {
        return tJobMapper.selectTJobByJobId(jobId);
    }

    /**
     * 查询职位列表
     * 
     * @param tJob 职位
     * @return 职位
     */
    @Override
    public List<TJob> selectTJobList(TJob tJob)
    {
        return tJobMapper.selectTJobList(tJob);
    }

    /**
     * 新增职位
     * 
     * @param tJob 职位
     * @return 结果
     */
    @Transactional
    @Override
    public int insertTJob(TJob tJob)
    {
        int rows = tJobMapper.insertTJob(tJob);
        insertTEmp(tJob);
        return rows;
    }

    /**
     * 修改职位
     * 
     * @param tJob 职位
     * @return 结果
     */
    @Transactional
    @Override
    public int updateTJob(TJob tJob)
    {
        tJob.setUpdateTime(DateUtils.getNowDate());
        tJobMapper.deleteTEmpByEId(tJob.getJobId());
        insertTEmp(tJob);
        return tJobMapper.updateTJob(tJob);
    }

    /**
     * 批量删除职位
     * 
     * @param jobIds 需要删除的职位主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteTJobByJobIds(Long[] jobIds)
    {
        tJobMapper.deleteTEmpByEIds(jobIds);
        return tJobMapper.deleteTJobByJobIds(jobIds);
    }

    /**
     * 删除职位信息
     * 
     * @param jobId 职位主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteTJobByJobId(Long jobId)
    {
        tJobMapper.deleteTEmpByEId(jobId);
        return tJobMapper.deleteTJobByJobId(jobId);
    }

    /**
     * 新增员工信息
     * 
     * @param tJob 职位对象
     */
    public void insertTEmp(TJob tJob)
    {
        List<TEmp> tEmpList = tJob.getTEmpList();
        Long jobId = tJob.getJobId();
        if (StringUtils.isNotNull(tEmpList))
        {
            List<TEmp> list = new ArrayList<TEmp>();
            for (TEmp tEmp : tEmpList)
            {
                tEmp.seteId(jobId);
                list.add(tEmp);
            }
            if (list.size() > 0)
            {
                tJobMapper.batchTEmp(list);
            }
        }
    }
}
