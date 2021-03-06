package com.xxt.springcloud.service;

import com.xxt.springcloud.entities.Payment;

public interface PaymentService {
    int create(Payment payment);

    Payment getPaymentById(Long id);

    String ping();

    String longTimeJob();

    String paymentCitcuitBreaker(Integer id);
}
