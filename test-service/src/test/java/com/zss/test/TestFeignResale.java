package com.zss.test;

import com.alibaba.fastjson.JSON;
import com.zss.springcloud.common.feign.ClientResultBo;
import com.zss.test.dto.BaseProductsClientBo;
import com.zss.test.dto.ResaleMatchOrderBo;
import com.zss.test.service.FeignService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @author fuguozhang
 * @email fuguozhang@jyblife.com
 * @date 2020/1/8 11:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TestFeignResale {

    @Autowired
    private FeignService feignService;

    public void testResaleMatch(int resaleOrderId){
        log.info("testResaleMatch测试执行了。。。当前线程：{}",Thread.currentThread().getName());
        long startTime = System.currentTimeMillis();
        BaseProductsClientBo.EnterpriseMatchResaleOrderParamBo paramBo = BaseProductsClientBo.EnterpriseMatchResaleOrderParamBo
                .builder()
                .goodsCycle(60)
                .goodsDenominationPrice(new BigDecimal(10000))
                .goodsNumber(1)
                .purchaseOrderId("100000007")
                .resaleOrderId(20200110L + resaleOrderId)
                .userId(123456L)
                .userName("张三")
                .build();
        ClientResultBo<ResaleMatchOrderBo> clientResultBo = feignService.resaleMatch(paramBo);
        long endTime = System.currentTimeMillis();
        log.info("调用resaleMatch方法耗时：{}",endTime - startTime);
        log.info("返回结果信息：{}",JSON.toJSONString(clientResultBo));
    }

    public void testResaleRevoke(){
        log.info("testResaleRevoke测试执行了。。。当前线程：{}",Thread.currentThread().getName());
        long startTime = System.currentTimeMillis();
        BaseProductsClientBo.RevocationEnterpriseMatchResaleOrderParamBo paramBo = BaseProductsClientBo.RevocationEnterpriseMatchResaleOrderParamBo
                .builder()
                .purchaseResaleMatchId(127)
                .build();
        ClientResultBo clientResultBo = feignService.resaleRevoke(paramBo);
        long endTime = System.currentTimeMillis();
        log.info("调用resaleRevoke方法耗时：{}",endTime - startTime);
        log.info("返回结果信息：{}",JSON.toJSONString(clientResultBo));
    }

    @Test
    public void testMultipleThreads() throws InterruptedException{
        for(int i = 251; i <252;i++){
            int resaleOrderId = i;
            new Thread(()->{
                //testResaleRevoke();
                testResaleMatch(resaleOrderId);
            }).start();
        }
        TimeUnit.SECONDS.sleep(10);
    }
}
