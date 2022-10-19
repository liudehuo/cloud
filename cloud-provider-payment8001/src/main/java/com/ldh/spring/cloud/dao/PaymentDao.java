package com.ldh.spring.cloud.dao;

import com.ldh.spring.cloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author liudehuo
 * @since 1.0
 * @version 1.0
 */
@Mapper
public interface PaymentDao {

    int insert(Payment payment);

    Payment selectById(@Param("id") Long id);

}
