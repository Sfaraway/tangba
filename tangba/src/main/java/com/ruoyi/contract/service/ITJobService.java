package com.ruoyi.contract.service;

import java.util.List;
import com.ruoyi.contract.domain.TJob;
import org.springframework.stereotype.Service;

/**
 * 职位Service接口
 * 
 * @author yixin
 * @date 2022-09-25
 */

public interface ITJobService 
{
    /**
     * 查询职位
     * 
     * @param jobId 职位主键
     * @return 职位
     */
    public TJob selectTJobByJobId(Long jobId);

    /**
     * 查询职位列表
     * 
     * @param tJob 职位
     * @return 职位集合
     */
    public List<TJob> selectTJobList(TJob tJob);

    /**
     * 新增职位
     * 
     * @param tJob 职位
     * @return 结果
     */
    public int insertTJob(TJob tJob);

    /**
     * 修改职位
     * 
     * @param tJob 职位
     * @return 结果
     */
    public int updateTJob(TJob tJob);

    /**
     * 批量删除职位
     * 
     * @param jobIds 需要删除的职位主键集合
     * @return 结果
     */
    public int deleteTJobByJobIds(Long[] jobIds);

    /**
     * 删除职位信息
     * 
     * @param jobId 职位主键
     * @return 结果
     */
    public int deleteTJobByJobId(Long jobId);
}
