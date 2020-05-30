package com.atguigu.springcloud.servece;

import org.springframework.stereotype.Component;

@Component
public class PaymentfallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "fallback ok";
    }

    @Override
    public String paymentInfo_TIMEOUT(Integer id) {
        return "fallback timeout";
    }
}
