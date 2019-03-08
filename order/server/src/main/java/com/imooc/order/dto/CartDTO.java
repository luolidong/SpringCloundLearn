package com.imooc.order.dto;

import lombok.Data;

/**
 * 购物车
 * Created by dell on 2019/1/15.
 */

@Data
public class CartDTO {
    /** 商品ID */
    private String productId;

    /** 数量 */
    private Integer productQuantity;

    public CartDTO() {}

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
