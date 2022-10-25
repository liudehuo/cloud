package com.ldh.spring.cloud.service;

import com.ldh.spring.cloud.entities.CommonResult;
import com.ldh.spring.cloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE") // 微服务名称
public interface PaymentFeignService {

    /**
     * 调用微服务下的"/payment/get/{id}"地址
     * @param id
     * @return
     */
    @GetMapping(value = "/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/feign/timeout")
    String paymentFeignTimeout();
}
