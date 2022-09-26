package com.ruoyi.contract.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.contract.mapper.TEmpMapper;
import com.ruoyi.contract.domain.TEmp;
import com.ruoyi.contract.service.ITEmpService;

/**
 * 员工Service业务层处理
 * 
 * @author yixin
 * @date 2022-09-25
 */
@Service
public class TEmpServiceImpl implements ITEmpService 
{
    @Autowired
    private TEmpMapper tEmpMapper;

    /**
     * 查询员工
     * 
     * @param eId 员工主键
     * @return 员工
     */
    @Override
    public TEmp selectTEmpByEId(Long eId)
    {
        return tEmpMapper.selectTEmpByEId(eId);
    }

    /**
     * 查询员工列表
     * 
     * @param tEmp 员工
     * @return 员工
     */
    @Override
    public List<TEmp> selectTEmpList(TEmp tEmp)
    {
        return tEmpMapper.selectTEmpList(tEmp);
    }

    /**
     * 新增员工
     * 
     * @param tEmp 员工
     * @return 结果
     */
    @Override
    public int insertTEmp(TEmp tEmp)
    {
        return tEmpMapper.insertTEmp(tEmp);
    }

    /**
     * 修改员工
     * 
     * @param tEmp 员工
     * @return 结果
     */
    @Override
    public int updateTEmp(TEmp tEmp)
    {
        tEmp.setUpdateTime(DateUtils.getNowDate());
        return tEmpMapper.updateTEmp(tEmp);
    }

    /**
     * 批量删除员工
     * 
     * @param eIds 需要删除的员工主键
     * @return 结果
     */
    @Override
    public int deleteTEmpByEIds(Long[] eIds)
    {
        return tEmpMapper.deleteTEmpByEIds(eIds);
    }

    /**
     * 删除员工信息
     * 
     * @param eId 员工主键
     * @return 结果
     */
    @Override
    public int deleteTEmpByEId(Long eId)
    {
        return tEmpMapper.deleteTEmpByEId(eId);
    }
    
    /**
     * 修改用户状态
     * 
     * @param tEmp 用户信息
     * @return 结果
     */
    @Override
    public int updateTEmpStatus(TEmp tEmp)
    {
        return tEmpMapper.updateTEmpStatus(tEmp);
    }
}
