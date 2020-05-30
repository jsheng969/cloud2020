package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfo_OK(Integer id){
        return "线程池:" + Thread.currentThread().getName() + "PaymentInfo_OK,id:" + id;
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TIMEOUTHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentInfo_TIMEOUT(Integer id){
        try {
            TimeUnit.SECONDS.sleep(5);
        }catch (InterruptedException e ){
            e.printStackTrace();
        }
        return "线程池:" + Thread.currentThread().getName() + "PaymentInfo_TIMEOUT,id:" + id;
    }

    public String paymentInfo_TIMEOUTHandler(Integer id){
        return "调用8001支付接口超时或异常，线程池:"+Thread.currentThread().getName();
    }
}
