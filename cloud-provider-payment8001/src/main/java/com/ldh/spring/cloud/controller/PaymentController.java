package com.ldh.spring.cloud.controller;

import com.ldh.spring.cloud.entities.CommonResult;
import com.ldh.spring.cloud.entities.Payment;
import com.ldh.spring.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author liudehuo
 * @since 1.0
 * @version 1.0
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("添加成功:"+result);
        if (result > 0)
            return new CommonResult(200,"插入数据库成功",result);
        else
            return new CommonResult(444,"插入数据库失败",null);
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.queryById(id);
        log.info("查询成功:"+payment);
        if (payment != null)
            return new CommonResult(200,"查询成功",payment);
        else
            return new CommonResult(444,id+" : 查询失败",null);
    }
}
