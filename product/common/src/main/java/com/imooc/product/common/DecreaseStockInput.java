package com.imooc.product.common;

import lombok.Data;

/**
 * Created by dell on 2019/3/7.
 */
@Data
public class DecreaseStockInput {
    /** 商品ID */
    private String productId;

    /** 数量 */
    private Integer productQuantity;

    public DecreaseStockInput() {}

    public DecreaseStockInput(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
