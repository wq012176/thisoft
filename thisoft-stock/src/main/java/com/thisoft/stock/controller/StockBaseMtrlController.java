package com.thisoft.stock.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.thisoft.common.annotation.Log;
import com.thisoft.common.core.controller.BaseController;
import com.thisoft.common.core.domain.AjaxResult;
import com.thisoft.common.enums.BusinessType;
import com.thisoft.stock.domain.StockBaseMtrl;
import com.thisoft.stock.service.IStockBaseMtrlService;
import com.thisoft.common.utils.poi.ExcelUtil;
import com.thisoft.common.core.page.TableDataInfo;

/**
 * 料号基本资料档Controller
 * 
 * @author wq
 * @date 2021-05-25
 */
@RestController
@RequestMapping("/stock/mtrl")
public class StockBaseMtrlController extends BaseController
{
    @Autowired
    private IStockBaseMtrlService stockBaseMtrlService;

    /**
     * 查询料号基本资料档列表
     */
    @PreAuthorize("@ss.hasPermi('stock:mtrl:list')")
    @GetMapping("/list")
    public TableDataInfo list(StockBaseMtrl stockBaseMtrl)
    {
        startPage();
        List<StockBaseMtrl> list = stockBaseMtrlService.selectStockBaseMtrlList(stockBaseMtrl);
        return getDataTable(list);
    }

    /**
     * 导出料号基本资料档列表
     */
    @PreAuthorize("@ss.hasPermi('stock:mtrl:export')")
    @Log(title = "料号基本资料档", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(StockBaseMtrl stockBaseMtrl)
    {
        List<StockBaseMtrl> list = stockBaseMtrlService.selectStockBaseMtrlList(stockBaseMtrl);
        ExcelUtil<StockBaseMtrl> util = new ExcelUtil<StockBaseMtrl>(StockBaseMtrl.class);
        String a = "1";
        return util.exportExcel(list, "mtrl");
    }

    /**
     * 获取料号基本资料档详细信息
     */
    @PreAuthorize("@ss.hasPermi('stock:mtrl:query')")
    @GetMapping(value = "/{mtrlNo}")
    public AjaxResult getInfo(@PathVariable("mtrlNo") Long mtrlNo)
    {
        return AjaxResult.success(stockBaseMtrlService.selectStockBaseMtrlById(mtrlNo));
    }

    /**
     * 新增料号基本资料档
     */
    @PreAuthorize("@ss.hasPermi('stock:mtrl:add')")
    @Log(title = "料号基本资料档", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StockBaseMtrl stockBaseMtrl)
    {
        return toAjax(stockBaseMtrlService.insertStockBaseMtrl(stockBaseMtrl));
    }

    /**
     * 修改料号基本资料档
     */
    @PreAuthorize("@ss.hasPermi('stock:mtrl:edit')")
    @Log(title = "料号基本资料档", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StockBaseMtrl stockBaseMtrl)
    {
        return toAjax(stockBaseMtrlService.updateStockBaseMtrl(stockBaseMtrl));
    }

    /**
     * 删除料号基本资料档
     */
    @PreAuthorize("@ss.hasPermi('stock:mtrl:remove')")
    @Log(title = "料号基本资料档", businessType = BusinessType.DELETE)
	@DeleteMapping("/{mtrlNos}")
    public AjaxResult remove(@PathVariable Long[] mtrlNos)
    {
        return toAjax(stockBaseMtrlService.deleteStockBaseMtrlByIds(mtrlNos));
    }
}
