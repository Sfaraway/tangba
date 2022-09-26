package com.ruoyi.contract.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.contract.mapper.TContractMapper;
import com.ruoyi.contract.domain.TContract;
import com.ruoyi.contract.service.ITContractService;

/**
 * 合同Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-09-23
 */
@Service
public class TContractServiceImpl implements ITContractService 
{
    @Autowired
    private TContractMapper tContractMapper;

    /**
     * 查询合同
     * 
     * @param id 合同主键
     * @return 合同
     */
    @Override
    public TContract selectTContractById(Long id)
    {
        return tContractMapper.selectTContractById(id);
    }

    /**
     * 查询合同列表
     * 
     * @param tContract 合同
     * @return 合同
     */
    @Override
    public List<TContract> selectTContractList(TContract tContract)
    {
        return tContractMapper.selectTContractList(tContract);
    }
    



    /**
     * 新增合同
     * 
     * @param tContract 合同
     * @return 结果
     */
    @Override
    public int insertTContract(TContract tContract)
    {
        return tContractMapper.insertTContract(tContract);
    }

    
    /**
     * 修改合同
     * 
     * @param tContract 合同
     * @return 结果
     */
    @Override
    public int updateTContract(TContract tContract)
    {
        tContract.setUpdateTime(DateUtils.getNowDate());
        return tContractMapper.updateTContract(tContract);
    }
    

    /**
     * 修改合同状态
     * 
     * @param id 合同主键
     * @return 结果
     */
    @Override
    public int updateStatus(TContract tContract)
    {
        return tContractMapper.updateTContract(tContract);
    }

    /**
     * 批量删除合同
     * 
     * @param ids 需要删除的合同主键
     * @return 结果
     */
    @Override
    public int deleteTContractByIds(Long[] ids)
    {
        return tContractMapper.deleteTContractByIds(ids);
    }

    /**
     * 删除合同信息
     * 
     * @param id 合同主键
     * @return 结果
     */
    @Override
    public int deleteTContractById(Long id)
    {
        return tContractMapper.deleteTContractById(id);
    }

    
	
}
