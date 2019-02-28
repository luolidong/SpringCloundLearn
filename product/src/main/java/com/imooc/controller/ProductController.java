package com.imooc.controller;

import com.imooc.VO.ProductInfoVO;
import com.imooc.VO.ProductVO;
import com.imooc.VO.ResultVO;
import com.imooc.dataobject.ProductCategory;
import com.imooc.dataobject.ProductInfo;
import com.imooc.service.CategoryService;
import com.imooc.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dell on 2019/2/21.
 */
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO<ProductVO> list() {
        List<ProductInfo> productInfoList = productService.findUpALl();
        List<Integer> categoryTypeList = productInfoList.stream().map(ProductInfo::getProductType).collect(Collectors.toList());
        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : categoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo: productInfoList) {
                if (productInfo.getProductType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }

            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        ResultVO resultVO = new ResultVO();
        resultVO.setData(productVOList);
        resultVO.setCode(0);
        resultVO.setMsg("success");
        return resultVO;
    }
}
