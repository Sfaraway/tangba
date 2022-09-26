package com.ruoyi.contract.mapper;

import java.util.List;
import com.ruoyi.contract.domain.TJob;
import com.ruoyi.contract.domain.TEmp;
import org.apache.ibatis.annotations.Mapper;

/**
 * 职位Mapper接口
 * 
 * @author yixin
 * @date 2022-09-25
 */
@Mapper
public interface TJobMapper 
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
     * 删除职位
     * 
     * @param jobId 职位主键
     * @return 结果
     */
    public int deleteTJobByJobId(Long jobId);

    /**
     * 批量删除职位
     * 
     * @param jobIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTJobByJobIds(Long[] jobIds);

    /**
     * 批量删除员工
     * 
     * @param jobIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTEmpByEIds(Long[] jobIds);
    
    /**
     * 批量新增员工
     * 
     * @param tEmpList 员工列表
     * @return 结果
     */
    public int batchTEmp(List<TEmp> tEmpList);
    

    /**
     * 通过职位主键删除员工信息
     * 
     * @param jobId 职位ID
     * @return 结果
     */
    public int deleteTEmpByEId(Long jobId);
}
