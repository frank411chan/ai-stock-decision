package com.ymchen.aistockdecision.controller;

import com.ymchen.aistockdecision.feign.EastmoneyFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/data/collect/api")
public class DataCollectorController {
    @Autowired
    EastmoneyFeign eastmoneyFeign;

    @GetMapping (value = "/eastmoney")
    @ResponseBody
    public String testFeign()  {
        String s = eastmoneyFeign.getStockList();
        System.out.printf(s);
        return "调用结果:" + s;
    }

}
