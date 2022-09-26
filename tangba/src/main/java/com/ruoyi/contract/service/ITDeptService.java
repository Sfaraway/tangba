package com.ruoyi.contract.service;

import java.util.List;
import com.ruoyi.contract.domain.TDept;

/**
 * 部门Service接口
 * 
 * @author yixin
 * @date 2022-09-25
 */
public interface ITDeptService 
{
    /**
     * 查询部门
     * 
     * @param deptId 部门主键
     * @return 部门
     */
    public TDept selectTDeptByDeptId(Long deptId);

    /**
     * 查询部门列表
     * 
     * @param tDept 部门
     * @return 部门集合
     */
    public List<TDept> selectTDeptList(TDept tDept);

    /**
     * 新增部门
     * 
     * @param tDept 部门
     * @return 结果
     */
    public int insertTDept(TDept tDept);

    /**
     * 修改部门
     * 
     * @param tDept 部门
     * @return 结果
     */
    public int updateTDept(TDept tDept);

    /**
     * 批量删除部门
     * 
     * @param deptIds 需要删除的部门主键集合
     * @return 结果
     */
    public int deleteTDeptByDeptIds(Long[] deptIds);

    /**
     * 删除部门信息
     * 
     * @param deptId 部门主键
     * @return 结果
     */
    public int deleteTDeptByDeptId(Long deptId);
}
