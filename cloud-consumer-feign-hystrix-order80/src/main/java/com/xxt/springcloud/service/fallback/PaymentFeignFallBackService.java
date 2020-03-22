package com.xxt.springcloud.service.fallback;

import com.xxt.springcloud.entities.CommonResult;
import com.xxt.springcloud.entities.Payment;
import com.xxt.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PaymentFeignFallBackService implements PaymentFeignService {
    @Override
    public CommonResult<Payment> getPaymentById(Long id) {
        log.info("getPaymentById fallback");
        return null;
    }

    @Override
    public String longTimeJob() {
        return "longTimeJob fallback";
    }

    @Override
    public String ping() {
        return "ping fallback";
    }
}
