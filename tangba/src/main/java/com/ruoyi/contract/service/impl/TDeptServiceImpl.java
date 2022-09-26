package com.ruoyi.contract.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.contract.domain.TEmp;
import com.ruoyi.contract.mapper.TDeptMapper;
import com.ruoyi.contract.domain.TDept;
import com.ruoyi.contract.service.ITDeptService;

/**
 * 部门Service业务层处理
 * 
 * @author yixin
 * @date 2022-09-25
 */
@Service
public class TDeptServiceImpl implements ITDeptService {
    @Autowired
    private TDeptMapper tDeptMapper;

    /**
     * 查询部门
     * 
     * @param deptId 部门主键
     * @return 部门
     */
    @Override
    public TDept selectTDeptByDeptId(Long deptId)
    {
        return tDeptMapper.selectTDeptByDeptId(deptId);
    }

    /**
     * 查询部门列表
     * 
     * @param tDept 部门
     * @return 部门
     */
    @Override
    public List<TDept> selectTDeptList(TDept tDept)
    {
        return tDeptMapper.selectTDeptList(tDept);
    }

    /**
     * 新增部门
     * 
     * @param tDept 部门
     * @return 结果
     */
    @Transactional
    @Override
    public int insertTDept(TDept tDept)
    {
        int rows = tDeptMapper.insertTDept(tDept);
        insertTEmp(tDept);
        return rows;
    }

    /**
     * 修改部门
     * 
     * @param tDept 部门
     * @return 结果
     */
    @Transactional
    @Override
    public int updateTDept(TDept tDept)
    {
        tDept.setUpdateTime(DateUtils.getNowDate());
        tDeptMapper.deleteTEmpByEId(tDept.getDeptId());
        insertTEmp(tDept);
        return tDeptMapper.updateTDept(tDept);
    }

    /**
     * 批量删除部门
     * 
     * @param deptIds 需要删除的部门主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteTDeptByDeptIds(Long[] deptIds)
    {
        tDeptMapper.deleteTEmpByEIds(deptIds);
        return tDeptMapper.deleteTDeptByDeptIds(deptIds);
    }

    /**
     * 删除部门信息
     * 
     * @param deptId 部门主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteTDeptByDeptId(Long deptId)
    {
        tDeptMapper.deleteTEmpByEId(deptId);
        return tDeptMapper.deleteTDeptByDeptId(deptId);
    }

    /**
     * 新增员工信息
     * 
     * @param tDept 部门对象
     */
    public void insertTEmp(TDept tDept)
    {
        List<TEmp> tEmpList = tDept.getTEmpList();
        Long deptId = tDept.getDeptId();
        if (StringUtils.isNotNull(tEmpList))
        {
            List<TEmp> list = new ArrayList<TEmp>();
            for (TEmp tEmp : tEmpList)
            {
                tEmp.seteId(deptId);
                list.add(tEmp);
            }
            if (list.size() > 0)
            {
                tDeptMapper.batchTEmp(list);
            }
        }
    }
}
