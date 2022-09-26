package com.ruoyi.contract.mapper;

import java.util.List;

import com.ruoyi.contract.domain.TLogistics;

/**
 * 物流Mapper接口
 * 
 * @author Mr.Zeng
 * @date 2022-09-24
 */
public interface TLogisticsMapper 
{
    /**
     * 查询物流
     * 
     * @param id 物流主键
     * @return 物流
     */
    public TLogistics selectTLogisticsById(Long id);

    /**
     * 查询物流列表
     * 
     * @param tLogistics 物流
     * @return 物流集合
     */
    public List<TLogistics> selectTLogisticsList(TLogistics tLogistics);

    /**
     * 新增物流
     * 
     * @param tLogistics 物流
     * @return 结果
     */
    public int insertTLogistics(TLogistics tLogistics);

    /**
     * 修改物流
     * 
     * @param tLogistics 物流
     * @return 结果
     */
    public int updateTLogistics(TLogistics tLogistics);

    /**
     * 删除物流
     * 
     * @param id 物流主键
     * @return 结果
     */
    public int deleteTLogisticsById(Long id);

    /**
     * 批量删除物流
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTLogisticsByIds(Long[] ids);
}
