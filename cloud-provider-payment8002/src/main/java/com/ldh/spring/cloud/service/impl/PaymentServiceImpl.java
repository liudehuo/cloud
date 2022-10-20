package com.ldh.spring.cloud.service.impl;

import com.ldh.spring.cloud.dao.PaymentDao;
import com.ldh.spring.cloud.entities.Payment;
import com.ldh.spring.cloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liudehuo
 * @since 1.0
 * @version 1.0
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.insert(payment);
    }

    @Override
    public Payment queryById(Long id) {
        return paymentDao.selectById(id);
    }
}
