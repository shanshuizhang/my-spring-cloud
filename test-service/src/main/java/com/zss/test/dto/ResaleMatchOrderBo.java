package com.zss.test.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResaleMatchOrderBo {

    /**
     * 商品面额总额
     */
    @JsonProperty("face_amount")
    @JsonAlias("goods_total_denomination_price")
    private BigDecimal goodsTotalDenominationAmount;

    /**
     * 采购方 ID
     */
    @JsonProperty("company_code")
    private String procurementId;

    /**
     * 采购方名称
     */
    @JsonProperty("company_name")
    private String procurementName;

    /**
     * 采购方电话
     */
    @JsonProperty("company_phone")
    private String procurementPhone;

    /**
     * 采购方类型（采购来源平台）
     */
    @JsonProperty("form_id")
    private Integer procurementType;

    /**
     * 采购单号
     */
    @JsonProperty("form_flow_no")
    private String purchaseOrderId;

    /**
     * 采购单匹配记录 ID
     */
    @JsonProperty("id")
    @JsonAlias("purchase_match_id")
    private Integer purchaseResaleMatchId;
}
