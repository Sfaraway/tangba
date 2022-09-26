package com.ruoyi.contract.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.contract.mapper.TCustomerMapper;
import com.ruoyi.contract.domain.TCustomer;
import com.ruoyi.contract.service.ITCustomerService;

/**
 * 客户Service业务层处理
 * 
 * @author zhou
 * @date 2022-09-25
 */
@Service
public class TCustomerServiceImpl implements ITCustomerService 
{
    @Autowired
    private TCustomerMapper tCustomerMapper;

    /**
     * 查询客户
     * 
     * @param cId 客户主键
     * @return 客户
     */
    @Override
    public TCustomer selectTCustomerByCId(Long cId)
    {
        return tCustomerMapper.selectTCustomerByCId(cId);
    }

    /**
     * 查询客户列表
     * 
     * @param tCustomer 客户
     * @return 客户
     */
    @Override
    public List<TCustomer> selectTCustomerList(TCustomer tCustomer)
    {
        return tCustomerMapper.selectTCustomerList(tCustomer);
    }

    /**
     * 新增客户
     * 
     * @param tCustomer 客户
     * @return 结果
     */
    @Override
    public int insertTCustomer(TCustomer tCustomer)
    {
        return tCustomerMapper.insertTCustomer(tCustomer);
    }

    /**
     * 修改客户
     * 
     * @param tCustomer 客户
     * @return 结果
     */
    @Override
    public int updateTCustomer(TCustomer tCustomer)
    {
        tCustomer.setUpdateTime(DateUtils.getNowDate());
        return tCustomerMapper.updateTCustomer(tCustomer);
    }

    /**
     * 批量删除客户
     * 
     * @param cIds 需要删除的客户主键
     * @return 结果
     */
    @Override
    public int deleteTCustomerByCIds(Long[] cIds)
    {
        return tCustomerMapper.deleteTCustomerByCIds(cIds);
    }

    /**
     * 删除客户信息
     * 
     * @param cId 客户主键
     * @return 结果
     */
    @Override
    public int deleteTCustomerByCId(Long cId)
    {
        return tCustomerMapper.deleteTCustomerByCId(cId);
    }

	@Override
	public int updateTCustomerStatus(TCustomer tCustomer) {
		return tCustomerMapper.updateTCustomerStatus(tCustomer);
	}
}
