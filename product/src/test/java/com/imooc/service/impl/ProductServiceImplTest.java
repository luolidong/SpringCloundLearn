package com.imooc.service.impl;

import com.imooc.dto.CartDTO;
import com.imooc.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * Created by dell on 2019/3/4.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceImplTest {
    @Autowired
    private ProductService productService;

    @Test
    public void decreaseStock() throws Exception {
        productService.decreaseStock(Arrays.asList(new CartDTO("123456", 10)));
    }

}