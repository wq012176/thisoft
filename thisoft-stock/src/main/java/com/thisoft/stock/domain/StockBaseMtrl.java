package com.thisoft.stock.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.thisoft.common.annotation.Excel;
import com.thisoft.common.core.domain.BaseEntity;

/**
 * 料号基本资料档对象 stock_base_mtrl
 * 
 * @author wq
 * @date 2021-05-25
 */
public class StockBaseMtrl extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 料号 */
    private Long mtrlNo;

    /** 料名 */
    @Excel(name = "料名")
    private String mtrlName;

    /** 物料大类编码 */
    @Excel(name = "物料大类编码")
    private String mtrlNoMain;

    /** nc物料编码 */
    private String mtrlNoNc;

    /** nc物料名称 */
    private String mtrlNameNc;

    /** 物料属性(M材料P备件) */
    private String mtrlType;

    /** 计量单位 */
    @Excel(name = "计量单位")
    private String unit;

    /** 品牌 */
    @Excel(name = "品牌")
    private String brand;

    /** 规格型号 */
    @Excel(name = "规格型号")
    private String mtrlSpec;

    /** 账套 */
    private String companyId;

    public void setMtrlNo(Long mtrlNo) 
    {
        this.mtrlNo = mtrlNo;
    }

    public Long getMtrlNo() 
    {
        return mtrlNo;
    }
    public void setMtrlName(String mtrlName) 
    {
        this.mtrlName = mtrlName;
    }

    public String getMtrlName() 
    {
        return mtrlName;
    }
    public void setMtrlNoMain(String mtrlNoMain) 
    {
        this.mtrlNoMain = mtrlNoMain;
    }

    public String getMtrlNoMain() 
    {
        return mtrlNoMain;
    }
    public void setMtrlNoNc(String mtrlNoNc) 
    {
        this.mtrlNoNc = mtrlNoNc;
    }

    public String getMtrlNoNc() 
    {
        return mtrlNoNc;
    }
    public void setMtrlNameNc(String mtrlNameNc) 
    {
        this.mtrlNameNc = mtrlNameNc;
    }

    public String getMtrlNameNc() 
    {
        return mtrlNameNc;
    }
    public void setMtrlType(String mtrlType) 
    {
        this.mtrlType = mtrlType;
    }

    public String getMtrlType() 
    {
        return mtrlType;
    }
    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }
    public void setBrand(String brand) 
    {
        this.brand = brand;
    }

    public String getBrand() 
    {
        return brand;
    }
    public void setMtrlSpec(String mtrlSpec) 
    {
        this.mtrlSpec = mtrlSpec;
    }

    public String getMtrlSpec() 
    {
        return mtrlSpec;
    }
    public void setCompanyId(String companyId) 
    {
        this.companyId = companyId;
    }

    public String getCompanyId() 
    {
        return companyId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("mtrlNo", getMtrlNo())
            .append("mtrlName", getMtrlName())
            .append("mtrlNoMain", getMtrlNoMain())
            .append("mtrlNoNc", getMtrlNoNc())
            .append("mtrlNameNc", getMtrlNameNc())
            .append("mtrlType", getMtrlType())
            .append("unit", getUnit())
            .append("brand", getBrand())
            .append("mtrlSpec", getMtrlSpec())
            .append("companyId", getCompanyId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
