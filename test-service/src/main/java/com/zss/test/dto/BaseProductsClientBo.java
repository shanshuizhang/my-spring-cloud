package com.zss.test.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class BaseProductsClientBo {
    @Data
    @AllArgsConstructor
    public static class GetCompanyInfoParamBo {
        @JsonProperty("codes")
        private List<String> codes;
    }

    @Data
    public static class CompanyInfoBo {
        @JsonProperty("regist_address")
        private String address;

        /**
         * 主营业务
         */
        @JsonProperty("company_business")
        private String business;

        @JsonProperty("company_code")
        private String code;

        /**
         * 公司成立时间
         */
        @JsonProperty("establish_time")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date establishTime;

        @JsonProperty("id_card_no")
        private String legalPersonIdCard;

        @JsonProperty("legal_person")
        private String legalPersonName;

        @JsonProperty("legal_tel")
        private String legalPersonPhone;

        @JsonProperty("company_name")
        private String name;

        @JsonProperty("company_phone")
        private String phone;

        /**
         * 注册资金
         */
        @JsonProperty("regist_capital")
        private BigDecimal registeredCapital;
    }

    @Data
    @Builder
    public static class EnterpriseMatchResaleOrderParamBo {

        @JsonProperty("goods_cycle")
        private Integer goodsCycle;

        @JsonProperty("goods_denomination_price")
        private BigDecimal goodsDenominationPrice;

        @JsonProperty("goods_number")
        private Integer goodsNumber;

        @JsonProperty("purchase_order_id")
        private String purchaseOrderId;

        @JsonProperty("base_order_id")
        private Long resaleOrderId;

        @JsonProperty("user_id")
        private Long userId;

        @JsonProperty("user_name")
        private String userName;
    }

    @Data
    @AllArgsConstructor
    public static class GetGoodsByPurchaseOrderParamBo {

        @JsonProperty("form_flow_no")
        private String purchaseOrderId;
    }

    @Data
    public static class GoodsBo {

        @JsonProperty("goods_cycle")
        private Integer goodsCycle;

        @JsonProperty("goods_denomination_price")
        private BigDecimal goodsDenominationPrice;

        @JsonProperty("goods_price")
        private BigDecimal goodsPrice;

        @JsonProperty("goods_profit_rate")
        private BigDecimal goodsProfitRate;

        @JsonProperty("goods_sku_id")
        private Integer goodsSkuId;

        @JsonProperty("goods_spu_id")
        private Integer goodsSpuId;
    }

    @Data
    @Builder
    @AllArgsConstructor
    public static class RevocationEnterpriseMatchResaleOrderParamBo {

        /**
         * 采购单匹配记录 ID
         */
        @JsonProperty("purchase_match_id")
        private Integer purchaseResaleMatchId;
    }

    @Data
    @AllArgsConstructor
    public static class GetEnterpriseUserParamBo {

        @JsonProperty("bank_account_id")
        private Long enterpriseUserId;
    }

    @Data
    public static class EnterpriseUser {

        @JsonProperty("company_bank")
        private String bank;

        @JsonProperty("company_account")
        private String bankCard;

        @JsonProperty("credit_code")
        private String code;

        @JsonProperty("id")
        private Long id;

        @JsonProperty("company_name")
        private String name;

        @JsonProperty("company_phone")
        private String phone;
    }
}
