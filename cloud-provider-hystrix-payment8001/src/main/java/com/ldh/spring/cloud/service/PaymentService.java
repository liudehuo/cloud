package com.ldh.spring.cloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    /**
     * 正常不出错的情况
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id) {
        return "线程池："+Thread.currentThread().getName()+"paymentInfo_OK,id:"+id+"\t"+"hhh";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentInfo_Timeout(Integer id) {
//        int timeNumber = 5;
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "线程池："+Thread.currentThread().getName()+"paymentInfo_Timeout,id:"+id+"\t"+"hhh";
    }

    public String paymentInfo_TimeoutHandler(Integer id) {
        return "线程池："+Thread.currentThread().getName()+"系统繁忙，请稍后重试,id:"+id+"\t"+"o(╥﹏╥)o";
    }

    // 服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties ={
            @HystrixProperty(name="circuitBreaker.enabled",value="true"), //是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="10"),// 请求次数
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="10000"),//时间窗口期
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="60")//失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("*******id不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调试成功，流水号："+serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id不能为负数，请稍后再试========id:"+id;
    }
}
