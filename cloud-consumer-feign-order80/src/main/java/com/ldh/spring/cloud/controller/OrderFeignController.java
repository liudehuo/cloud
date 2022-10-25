package com.ldh.spring.cloud.controller;

import com.ldh.spring.cloud.entities.CommonResult;
import com.ldh.spring.cloud.entities.Payment;
import com.ldh.spring.cloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderFeignController {

    private static Logger log = LoggerFactory.getLogger(OrderFeignController.class);

    @Resource
    public PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        log.info("请求id:"+id);
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout() {
        /*
            openfeign-ribbon，客户端一般默认等待1秒，超时将报错。
            如需避免此类情况，需要设置Feign客户端超时控制
         */
        return paymentFeignService.paymentFeignTimeout();
    }
}
