package com.ymchen.aistockdecision.feign.fallback;

import com.ymchen.aistockdecision.feign.EastmoneyFeign;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@Component
public class EastmoneyFeignFallBack implements EastmoneyFeign {
    @Override
    public String getStockList() {
        return "hystrix--服务器忙，请稍后再试!";
    }
}
