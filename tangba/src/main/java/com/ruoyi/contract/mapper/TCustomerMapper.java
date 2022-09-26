package com.ruoyi.contract.mapper;

import java.util.List;
import com.ruoyi.contract.domain.TCustomer;
import org.apache.ibatis.annotations.Mapper;

/**
 * 客户Mapper接口
 * 
 * @author zhou
 * @date 2022-09-25
 */
@Mapper
public interface TCustomerMapper 
{
    /**
     * 查询客户
     * 
     * @param cId 客户主键
     * @return 客户
     */
    public TCustomer selectTCustomerByCId(Long cId);

    /**
     * 查询客户列表
     * 
     * @param tCustomer 客户
     * @return 客户集合
     */
    public List<TCustomer> selectTCustomerList(TCustomer tCustomer);

    /**
     * 新增客户
     * 
     * @param tCustomer 客户
     * @return 结果
     */
    public int insertTCustomer(TCustomer tCustomer);

    /**
     * 修改客户
     * 
     * @param tCustomer 客户
     * @return 结果
     */
    public int updateTCustomer(TCustomer tCustomer);

    /**
     * 删除客户
     * 
     * @param cId 客户主键
     * @return 结果
     */
    public int deleteTCustomerByCId(Long cId);

    /**
     * 批量删除客户
     * 
     * @param cIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTCustomerByCIds(Long[] cIds);

	public int updateTCustomerStatus(TCustomer tCustomer);
}
