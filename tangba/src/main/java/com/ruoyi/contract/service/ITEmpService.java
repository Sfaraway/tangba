package com.ruoyi.contract.service;

import java.util.List;
import com.ruoyi.contract.domain.TEmp;
import org.springframework.stereotype.Service;

/**
 * 员工Service接口
 * 
 * @author yixin
 * @date 2022-09-25
 */

public interface ITEmpService 
{
    /**
     * 查询员工
     * 
     * @param eId 员工主键
     * @return 员工
     */
    public TEmp selectTEmpByEId(Long eId);

    /**
     * 查询员工列表
     * 
     * @param tEmp 员工
     * @return 员工集合
     */
    public List<TEmp> selectTEmpList(TEmp tEmp);

    /**
     * 新增员工
     * 
     * @param tEmp 员工
     * @return 结果
     */
    public int insertTEmp(TEmp tEmp);

    /**
     * 修改员工
     * 
     * @param tEmp 员工
     * @return 结果
     */
    public int updateTEmp(TEmp tEmp);

    /**
     * 批量删除员工
     * 
     * @param eIds 需要删除的员工主键集合
     * @return 结果
     */
    public int deleteTEmpByEIds(Long[] eIds);

    /**
     * 删除员工信息
     * 
     * @param eId 员工主键
     * @return 结果
     */
    public int deleteTEmpByEId(Long eId);

    /**
     * 修改用户状态
     * 
     * @param user 用户信息
     * @return 结果
     */
	public int updateTEmpStatus(TEmp tEmp);
}
