package com.imooc.repository;

import com.imooc.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by dell on 2019/1/14.
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {
    ProductInfo findByProductId(String productId);

    List<ProductInfo> findByProductStatus(int productState);

    List<ProductInfo> findByProductIdIn(List<String> productIdList);
}
