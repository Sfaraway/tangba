package com.ruoyi.contract.mapper;

import java.util.List;
import com.ruoyi.contract.domain.TDept;
import com.ruoyi.contract.domain.TEmp;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 部门Mapper接口
 * 
 * @author yixin
 * @date 2022-09-25
 */
@Mapper
public interface TDeptMapper 
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
     * 删除部门
     * 
     * @param deptId 部门主键
     * @return 结果
     */
    public int deleteTDeptByDeptId(Long deptId);

    /**
     * 批量删除部门
     * 
     * @param deptIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTDeptByDeptIds(Long[] deptIds);

    /**
     * 批量删除员工
     * 
     * @param deptIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTEmpByEIds(Long[] deptIds);
    
    /**
     * 批量新增员工
     * 
     * @param tEmpList 员工列表
     * @return 结果
     */
    public int batchTEmp(List<TEmp> tEmpList);
    

    /**
     * 通过部门主键删除员工信息
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    public int deleteTEmpByEId(Long deptId);
}
