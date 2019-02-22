package com.imooc.service.impl;

import com.imooc.dataobject.ProductInfo;
import com.imooc.enums.ProductStatusEnum;
import com.imooc.repository.ProductInfoRepository;
import com.imooc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dell on 2019/1/14.
 */

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        return repository.findByProductId(productId);
    }

    @Override
    public List<ProductInfo> findUpALl() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return null;
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

//    @Override
//    @Transactional
//    public void decreaseStock(List<CartDTO> cartDTOList) {
//        for (CartDTO cartDTO: cartDTOList) {
//            ProductInfo productInfo = repository.findByProductId(cartDTO.getProductId());
//            if (productInfo == null) {
//                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
//            }
//
//            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
//            if (result < 0) {
//                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
//            }
//
//            productInfo.setProductStock(result);
//
//            repository.save(productInfo);
//        }
//    }


}
