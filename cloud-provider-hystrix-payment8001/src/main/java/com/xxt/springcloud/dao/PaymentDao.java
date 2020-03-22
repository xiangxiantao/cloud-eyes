package com.xxt.springcloud.dao;

import com.xxt.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentDao {

    /**
     * 能否返回主键
     * @param payment
     * @return
     */
    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);

}
