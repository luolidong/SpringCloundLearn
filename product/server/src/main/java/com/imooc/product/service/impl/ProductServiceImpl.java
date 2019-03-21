package com.imooc.product.service.impl;

import com.imooc.product.common.DecreaseStockInput;
import com.imooc.product.common.ProductInfoOutput;
import com.imooc.product.dataobject.ProductInfo;
import com.imooc.product.enums.ProductStatusEnum;
import com.imooc.product.enums.ResultEnum;
import com.imooc.product.exception.SellException;
import com.imooc.product.repository.ProductInfoRepository;
import com.imooc.product.service.ProductService;
import com.imooc.product.utils.JsonUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dell on 2019/1/14.
 */

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository repository;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public ProductInfo findOne(String productId) {
        return repository.findByProductId(productId);
    }

    @Override
    public List<ProductInfo> findUpALl() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> findList(List<String> productIdList) {
        return repository.findByProductIdIn(productIdList);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

//    @Override
//    @Transactional
//    public void increaseStock(List<CartDTO> cartDTOList) {
//        for (CartDTO cartDTO: cartDTOList) {
//            ProductInfo productInfo = repository.findByProductId(cartDTO.getProductId());
//            if (productInfo == null) {
//                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
//            }
//
//            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
//
//            productInfo.setProductStock(result);
//
//            repository.save(productInfo);
//        }
//    }

    @Override
    public void decreaseStock(List<DecreaseStockInput> cartDTOList) {
        List<ProductInfo> productInfoList = decreaseStockProcess(cartDTOList);
        List<ProductInfoOutput> ProductInfoOutputList = productInfoList.stream().map(e -> {
            ProductInfoOutput productInfoOutput = new ProductInfoOutput();
            BeanUtils.copyProperties(e, productInfoOutput);
            return productInfoOutput;
        }).collect(Collectors.toList());

        //发送mq 消息
        amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(ProductInfoOutputList));

    }

    @Transactional
    public List<ProductInfo> decreaseStockProcess(List<DecreaseStockInput> cartDTOList) {
        List<ProductInfo> productInfoList = new ArrayList<>();

        for (DecreaseStockInput cartDTO: cartDTOList) {
            ProductInfo productInfo = repository.findByProductId(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);

            repository.save(productInfo);
            productInfoList.add(productInfo);
        }

        return productInfoList;
    }
}
