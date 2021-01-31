package com.ymchen.aistockdecision;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Administrator
 */
@SpringBootApplication
@EnableFeignClients
@EnableHystrix
public class AiStockDecisionApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiStockDecisionApplication.class, args);
    }

}
