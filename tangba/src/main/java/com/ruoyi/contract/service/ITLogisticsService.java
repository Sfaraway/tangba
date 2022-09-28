package com.ruoyi.contract.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.contract.domain.TLogistics;

/**
 * 物流Service接口
 * 
 * @author Mr.Zeng
 * @date 2022-09-24
 */
public interface ITLogisticsService 
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

    //ITService
    /**
     * 修改配送状态
     *
     * @param logistic 物流主键
     * @return 结果
     */
    public int updateStatus(TLogistics logistic);

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
     * 批量删除物流
     * 
     * @param ids 需要删除的物流主键集合
     * @return 结果
     */
    public int deleteTLogisticsByIds(Long[] ids);

    /**
     * 删除物流信息
     * 
     * @param id 物流主键
     * @return 结果
     */
    public int deleteTLogisticsById(Long id);

    public Map<String,Object> insert2(TLogistics logistics);

    public List<Map<String,Object>> selectCE();

}
