package com.thisoft.stock.mapper;

import java.util.List;
import com.thisoft.stock.domain.StockBaseMtrl;

/**
 * 料号基本资料档Mapper接口
 * 
 * @author wq
 * @date 2021-05-25
 */
public interface StockBaseMtrlMapper 
{
    /**
     * 查询料号基本资料档
     * 
     * @param mtrlNo 料号基本资料档ID
     * @return 料号基本资料档
     */
    public StockBaseMtrl selectStockBaseMtrlById(Long mtrlNo);

    /**
     * 查询料号基本资料档列表
     * 
     * @param stockBaseMtrl 料号基本资料档
     * @return 料号基本资料档集合
     */
    public List<StockBaseMtrl> selectStockBaseMtrlList(StockBaseMtrl stockBaseMtrl);

    /**
     * 新增料号基本资料档
     * 
     * @param stockBaseMtrl 料号基本资料档
     * @return 结果
     */
    public int insertStockBaseMtrl(StockBaseMtrl stockBaseMtrl);

    /**
     * 修改料号基本资料档
     * 
     * @param stockBaseMtrl 料号基本资料档
     * @return 结果
     */
    public int updateStockBaseMtrl(StockBaseMtrl stockBaseMtrl);

    /**
     * 删除料号基本资料档
     * 
     * @param mtrlNo 料号基本资料档ID
     * @return 结果
     */
    public int deleteStockBaseMtrlById(Long mtrlNo);

    /**
     * 批量删除料号基本资料档
     * 
     * @param mtrlNos 需要删除的数据ID
     * @return 结果
     */
    public int deleteStockBaseMtrlByIds(Long[] mtrlNos);
}
