package com.ruoyi.contract.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.contract.domain.TLogistics;
import com.ruoyi.contract.mapper.TLogisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.contract.service.ITLogisticsService;

/**
 * 物流Service业务层处理
 * 
 * @author Mr.Zeng
 * @date 2022-09-24
 */
@Service
public class TLogisticsServiceImpl implements ITLogisticsService 
{
    @Autowired
    private TLogisticsMapper tLogisticsMapper;

    /**
     * 查询物流
     * 
     * @param id 物流主键
     * @return 物流
     */
    @Override
    public TLogistics selectTLogisticsById(Long id)
    {
        return tLogisticsMapper.selectTLogisticsById(id);
    }

    /**
     * 查询物流列表
     * 
     * @param tLogistics 物流
     * @return 物流
     */
    @Override
    public List<TLogistics> selectTLogisticsList(TLogistics tLogistics)
    {
        return tLogisticsMapper.selectTLogisticsList(tLogistics);
    }

    /**
     * 新增物流
     * 
     * @param tLogistics 物流
     * @return 结果
     */
    @Override
    public int insertTLogistics(TLogistics tLogistics)
    {
        return tLogisticsMapper.insertTLogistics(tLogistics);
    }

    /**
     * 修改物流
     * 
     * @param tLogistics 物流
     * @return 结果
     */
    @Override
    public int updateTLogistics(TLogistics tLogistics)
    {
        tLogistics.setUpdateTime(DateUtils.getNowDate());
        return tLogisticsMapper.updateTLogistics(tLogistics);
    }

    /**
     * 批量删除物流
     * 
     * @param ids 需要删除的物流主键
     * @return 结果
     */
    @Override
    public int deleteTLogisticsByIds(Long[] ids)
    {
        return tLogisticsMapper.deleteTLogisticsByIds(ids);
    }

    /**
     * 删除物流信息
     * 
     * @param id 物流主键
     * @return 结果
     */
    @Override
    public int deleteTLogisticsById(Long id)
    {
        return tLogisticsMapper.deleteTLogisticsById(id);
    }
}
