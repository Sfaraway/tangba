package com.ruoyi.contract.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.contract.domain.TContract;
import org.springframework.stereotype.Repository;

/**
 * 合同Mapper接口
 * 
 * @author ruoyi
 * @date 2022-09-28
 */
@Repository
public interface TContractMapper 
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
     * 删除合同
     * 
     * @param id 合同主键
     * @return 结果
     */
    public int deleteTContractById(Long id);

    /**
     * 批量删除合同
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTContractByIds(Long[] ids);

    public List<Map<String, Object>> selectContractCustomerEmpAll(TContract contract);

    public List<Map<String, Object>> selectStaffPosition(TContract contract);
}
