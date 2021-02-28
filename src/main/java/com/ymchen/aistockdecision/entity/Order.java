package com.ymchen.aistockdecision.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author chenym
 */
@Data
@TableName("t_order")
public class Order {
    @TableId("order_id")
    private Long orderId;

    private String name;

    private String type;
    @TableField("gmt_create")
    private Date gmtCreate;
}
