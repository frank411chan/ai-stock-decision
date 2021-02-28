package com.ymchen.aistockdecision.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ymchen.aistockdecision.entity.Order;
import com.ymchen.aistockdecision.mapper.OrderMapper;
import com.ymchen.aistockdecision.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
