package com.imooc.service;

import com.imooc.dataobject.ProductInfo;
import com.imooc.dto.CartDTO;

import java.util.List;

/**
 * Created by dell on 2019/1/14.
 */
public interface ProductService {

    ProductInfo findOne(String productId);

    List<ProductInfo> findUpALl();

    List<ProductInfo> findList(List<String> productIdList);

    ProductInfo save(ProductInfo productInfo);

//    // 加库存
//    void increaseStock(List<CartDTO> cartDTOList);
//
    // 减库存
    void decreaseStock(List<CartDTO> cartDTOList);
}
