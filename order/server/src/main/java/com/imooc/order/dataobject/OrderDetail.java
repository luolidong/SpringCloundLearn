package com.imooc.order.dataobject;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by dell on 2019/1/15.
 */
@Entity
@Data
@DynamicUpdate
public class OrderDetail {
    /** 订单id. */
    @Id
    private String detailId;

    /** 商品id. */
    private String orderId;

    /** 商品名称. */
    private String productId;

    /** 商品单价. */
    private String productName;

    /** 商品单价. */
    private BigDecimal productPrice;

    /** 商品数量. */
    private Integer productQuantity;

    /** 商品小图. */
    private String productIcon;

    private Date createTime;

    private Date updateTime;
}
