package com.ldh.spring.cloud.service;

public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "paymentInfo_OK";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "";
    }
}
