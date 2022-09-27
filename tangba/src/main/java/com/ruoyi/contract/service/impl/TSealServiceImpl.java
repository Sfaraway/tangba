package com.ruoyi.contract.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.contract.mapper.TSealMapper;
import com.ruoyi.contract.domain.TSeal;
import com.ruoyi.contract.service.ITSealService;

/**
 * 印章Service业务层处理
 * 
 * @author ch
 * @date 2022-09-27
 */
@Service
public class TSealServiceImpl implements ITSealService 
{
    @Autowired
    private TSealMapper tSealMapper;

    /**
     * 查询印章
     * 
     * @param id 印章主键
     * @return 印章
     */
    @Override
    public TSeal selectTSealById(Integer id)
    {
        return tSealMapper.selectTSealById(id);
    }

    /**
     * 查询印章列表
     * 
     * @param tSeal 印章
     * @return 印章
     */
    @Override
    public List<TSeal> selectTSealList(TSeal tSeal)
    {
        return tSealMapper.selectTSealList(tSeal);
    }

    /**
     * 新增印章
     * 
     * @param tSeal 印章
     * @return 结果
     */
    @Override
    public int insertTSeal(TSeal tSeal)
    {
        return tSealMapper.insertTSeal(tSeal);
    }

    /**
     * 修改印章
     * 
     * @param tSeal 印章
     * @return 结果
     */
    @Override
    public int updateTSeal(TSeal tSeal)
    {
        tSeal.setUpdateTime(DateUtils.getNowDate());
        return tSealMapper.updateTSeal(tSeal);
    }

    /**
     * 批量删除印章
     * 
     * @param ids 需要删除的印章主键
     * @return 结果
     */
    @Override
    public int deleteTSealByIds(Integer[] ids)
    {
        return tSealMapper.deleteTSealByIds(ids);
    }

    /**
     * 删除印章信息
     * 
     * @param id 印章主键
     * @return 结果
     */
    @Override
    public int deleteTSealById(Integer id)
    {
        return tSealMapper.deleteTSealById(id);
    }

    @Override
    public int updateSealStatus(TSeal tSeal) {
        return tSealMapper.updateTSeal(tSeal);
    }
}
