package com.zss.test.service;

import com.zss.springcloud.common.feign.ClientResultBo;
import com.zss.test.dto.BaseProductsClientBo;
import com.zss.test.dto.ResaleMatchOrderBo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author fuguozhang
 * @email fuguozhang@jyblife.com
 * @date 2020/1/8 14:02
 */
@FeignClient(name = "BASE-PRODUCTS-APP-REST")
public interface FeignService {

    /**
     * 企业匹配转售订单
     *
     * @param paramBo
     * @return
     */
    @PostMapping(path = "/resale/refillPurchase/v1/resaleMatch", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ClientResultBo<ResaleMatchOrderBo> resaleMatch(@RequestBody BaseProductsClientBo.EnterpriseMatchResaleOrderParamBo paramBo);

    /**
     * 撤销企业匹配转售订单
     *
     * @param paramBo
     * @return
     */
    @PostMapping(path = "/resale/refillPurchase/v1/resaleRevoke", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ClientResultBo resaleRevoke(@RequestBody BaseProductsClientBo.RevocationEnterpriseMatchResaleOrderParamBo paramBo);
}
