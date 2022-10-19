package com.ldh.spring.cloud.service;

import com.ldh.spring.cloud.entities.Payment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * @author liudehuo
 * @since 1.0
 * @version 1.0
 */
@Service
public interface PaymentService {

    int create(Payment payment);

    Payment queryById(@Param("id") Long id);
}
