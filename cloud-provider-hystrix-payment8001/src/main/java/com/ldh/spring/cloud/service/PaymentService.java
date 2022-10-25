package com.ldh.spring.cloud.service;

import org.springframework.stereotype.Service;

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

    public String paymentInfo_Timeout(Integer id) {
        int timeNumber = 3;
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "线程池："+Thread.currentThread().getName()+"paymentInfo_Timeout,id:"+id+"\t"+"hhh"+"耗时"+timeNumber+"秒钟";
    }
}
