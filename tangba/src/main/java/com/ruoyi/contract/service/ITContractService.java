package com.ruoyi.contract.service;

import java.util.List;
import com.ruoyi.contract.domain.TContract;

/**
 * 合同Service接口
 * 
 * @author ruoyi
 * @date 2022-09-28
 */
public interface ITContractService 
{
    /**
     * 查询合同
     * 
     * @param id 合同主键
     * @return 合同
     */
    public TContract selectTContractById(Long id);

    /**
     * 查询合同列表
     * 
     * @param tContract 合同
     * @return 合同集合
     */
    public List<TContract> selectTContractList(TContract tContract);

    /**
     * 新增合同
     * 
     * @param tContract 合同
     * @return 结果
     */
    public int insertTContract(TContract tContract);

    /**
     * 修改合同
     * 
     * @param tContract 合同
     * @return 结果
     */
    public int updateTContract(TContract tContract);

    /**
     * 批量删除合同
     * 
     * @param ids 需要删除的合同主键集合
     * @return 结果
     */
    public int deleteTContractByIds(Long[] ids);

    /**
     * 删除合同信息
     * 
     * @param id 合同主键
     * @return 结果
     */
    public int deleteTContractById(Long id);
}
