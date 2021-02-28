package com.ymchen.aistockdecision.service;

import com.ymchen.aistockdecision.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;


    @Test
    public void testSave(){
        for (int i = 0 ; i< 100 ; i++){
            Order order = new Order();
            order.setName("电脑"+i);
            order.setType("办公");
            orderService.save(order);
        }
    }

    @Test
    public void testGetById(){
        long id = 572809708746833920L;
        Order order  = orderService.getById(id);
        System.out.println(order.toString());
    }
}
