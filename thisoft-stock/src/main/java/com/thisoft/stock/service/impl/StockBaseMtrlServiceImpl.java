package com.thisoft.stock.service.impl;

import java.util.List;
import com.thisoft.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.thisoft.stock.mapper.StockBaseMtrlMapper;
import com.thisoft.stock.domain.StockBaseMtrl;
import com.thisoft.stock.service.IStockBaseMtrlService;

/**
 * 料号基本资料档Service业务层处理
 * 
 * @author wq
 * @date 2021-05-25
 */
@Service
public class StockBaseMtrlServiceImpl implements IStockBaseMtrlService 
{
    @Autowired
    private StockBaseMtrlMapper stockBaseMtrlMapper;

    /**
     * 查询料号基本资料档
     * 
     * @param mtrlNo 料号基本资料档ID
     * @return 料号基本资料档
     */
    @Override
    public StockBaseMtrl selectStockBaseMtrlById(Long mtrlNo)
    {
        return stockBaseMtrlMapper.selectStockBaseMtrlById(mtrlNo);
    }

    /**
     * 查询料号基本资料档列表
     * 
     * @param stockBaseMtrl 料号基本资料档
     * @return 料号基本资料档
     */
    @Override
    public List<StockBaseMtrl> selectStockBaseMtrlList(StockBaseMtrl stockBaseMtrl)
    {
        return stockBaseMtrlMapper.selectStockBaseMtrlList(stockBaseMtrl);
    }

    /**
     * 新增料号基本资料档
     * 
     * @param stockBaseMtrl 料号基本资料档
     * @return 结果
     */
    @Override
    public int insertStockBaseMtrl(StockBaseMtrl stockBaseMtrl)
    {
        stockBaseMtrl.setCreateTime(DateUtils.getNowDate());
        return stockBaseMtrlMapper.insertStockBaseMtrl(stockBaseMtrl);
    }

    /**
     * 修改料号基本资料档
     * 
     * @param stockBaseMtrl 料号基本资料档
     * @return 结果
     */
    @Override
    public int updateStockBaseMtrl(StockBaseMtrl stockBaseMtrl)
    {
        stockBaseMtrl.setUpdateTime(DateUtils.getNowDate());
        return stockBaseMtrlMapper.updateStockBaseMtrl(stockBaseMtrl);
    }

    /**
     * 批量删除料号基本资料档
     * 
     * @param mtrlNos 需要删除的料号基本资料档ID
     * @return 结果
     */
    @Override
    public int deleteStockBaseMtrlByIds(Long[] mtrlNos)
    {
        return stockBaseMtrlMapper.deleteStockBaseMtrlByIds(mtrlNos);
    }

    /**
     * 删除料号基本资料档信息
     * 
     * @param mtrlNo 料号基本资料档ID
     * @return 结果
     */
    @Override
    public int deleteStockBaseMtrlById(Long mtrlNo)
    {
        return stockBaseMtrlMapper.deleteStockBaseMtrlById(mtrlNo);
    }
}
