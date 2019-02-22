package com.imooc.controller;

import com.imooc.VO.ProductVO;
import com.imooc.VO.ResultVO;
import com.imooc.dataobject.ProductInfo;
import com.imooc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by dell on 2019/2/21.
 */
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private

    @PostMapping("/list")
    public ResultVO<ProductVO> list() {
        List<ProductInfo> productInfoList =
    }
}
