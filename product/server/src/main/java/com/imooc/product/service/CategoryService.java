package com.imooc.product.service;


import com.imooc.product.dataobject.ProductCategory;

import java.util.List;

/**
 * Created by dell on 2019/1/11.
 */
public interface CategoryService {
    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
