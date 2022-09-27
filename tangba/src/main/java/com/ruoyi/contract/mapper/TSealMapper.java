package com.ruoyi.contract.mapper;

import java.util.List;
import com.ruoyi.contract.domain.TSeal;
import org.springframework.stereotype.Repository;

/**
 * 印章Mapper接口
 * 
 * @author ch
 * @date 2022-09-27
 */
@Repository
public interface TSealMapper 
{
    /**
     * 查询印章
     * 
     * @param id 印章主键
     * @return 印章
     */
    public TSeal selectTSealById(Integer id);

    /**
     * 查询印章列表
     * 
     * @param tSeal 印章
     * @return 印章集合
     */
    public List<TSeal> selectTSealList(TSeal tSeal);

    /**
     * 新增印章
     * 
     * @param tSeal 印章
     * @return 结果
     */
    public int insertTSeal(TSeal tSeal);

    /**
     * 修改印章
     * 
     * @param tSeal 印章
     * @return 结果
     */
    public int updateTSeal(TSeal tSeal);

    /**
     * 删除印章
     * 
     * @param id 印章主键
     * @return 结果
     */
    public int deleteTSealById(Integer id);

    /**
     * 批量删除印章
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTSealByIds(Integer[] ids);
}
