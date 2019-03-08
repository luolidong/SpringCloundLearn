package com.imooc.order.service.impl;


import com.imooc.order.dataobject.OrderDetail;
import com.imooc.order.dataobject.OrderMaster;
import com.imooc.order.dto.OrderDTO;
import com.imooc.order.enums.OrderStatusEnum;
import com.imooc.order.enums.PayStatusEnum;
import com.imooc.order.repository.OrderDetailRepository;
import com.imooc.order.repository.OrderMasterRepository;
import com.imooc.order.service.OrderService;
import com.imooc.order.utils.KeyUtil;
import com.imooc.product.client.ProductClient;
import com.imooc.product.common.DecreaseStockInput;
import com.imooc.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dell on 2019/1/15.
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private ProductClient productClient;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();

        //1. 查询商品（数量，价格）
        List<String> productIdList = orderDTO.getOrderDetailList().stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());

        List<ProductInfoOutput> productInfoList = productClient.listForOrder(productIdList);

        BigDecimal orderAmout = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            for (ProductInfoOutput productInfo : productInfoList) {
                if (productInfo.getProductId().equals(orderDetail.getProductId())) {
                    orderAmout = productInfo.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmout);

                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());

                    //订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }

        //4. 扣库存
        List<DecreaseStockInput> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new DecreaseStockInput(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        productClient.decreaseStock(cartDTOList);

        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(KeyUtil.genUniqueKey());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmout);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMasterRepository.save(orderMaster);

        return orderDTO;
    }

//    @Override
//    public OrderDTO findOne(String orderId) {
//        OrderMaster om = new OrderMaster();
//        om.setOrderId(orderId);
//
//        OrderMaster orderMaster = orderMasterRepository.findOne(Example.of(om)).orElse(null);
//        if (orderMaster == null) {
//            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
//        }
//
//        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
//        if (CollectionUtils.isEmpty(orderDetailList)) {
//            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
//        }
//
//        OrderDTO orderDTO = new OrderDTO();
//        BeanUtils.copyProperties(orderMaster,orderDTO);
//        orderDTO.setOrderDetailList(orderDetailList);
//        return orderDTO;
//    }

//    @Override
//    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
//        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
//
//        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());
//
//        return new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements());
//    }

//    @Override
//    @Transactional
//    public OrderDTO cancle(OrderDTO orderDTO) {
//
//        //判断订单状态
//        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
//            log.error("[取消订单]订单状态不正确，orderId = {}, orderStatus = {}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
//            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
//        }
//
//        //修改订单状态
//        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
//        OrderMaster orderMaster = new OrderMaster();
//        BeanUtils.copyProperties(orderDTO,orderMaster);
//        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
//        if (updateResult == null) {
//            log.error("【取消订单】更新失败，orderMaster = {}",orderMaster);
//            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
//        }
//
//        //返回库存
//        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
//            log.error("[取消订单] 订单中无商品详情 orderDTO = {}", orderDTO);
//            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
//        }
//        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
//                .map(e->new CartDTO(e.getProductId(),e.getProductQuantity()))
//                .collect(Collectors.toList());
//        productService.increaseStock(cartDTOList);
//
//        //如果已支付，需要退款
//        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
//            //TODO
//        }
//
//        return orderDTO;
//    }

//    @Override
//    public OrderDTO finish(OrderDTO orderDTO) {
//        //判断订单状态
//        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
//            log.error("[完结订单]订单状态不正确，orderId = {}, orderStatus = {}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
//            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
//        }
//
//        //修改订单状态
//        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
//        OrderMaster orderMaster = new OrderMaster();
//        BeanUtils.copyProperties(orderDTO,orderMaster);
//        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
//        if (updateResult == null) {
//            log.error("【完结订单】更新失败，orderMaster = {}",orderMaster);
//            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
//        }
//        return orderDTO;
//    }
//
//    @Override
//    @Transactional
//    public OrderDTO paid(OrderDTO orderDTO) {
//        //判断订单状态
//        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
//            log.error("[支付订单]订单状态不正确，orderId = {}, orderStatus = {}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
//            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
//        }
//
//        //判断支付状态
//        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
//            log.error("[支付订单]订单支付状态不正确，orderId = {}, payStatus = {}", orderDTO.getOrderId(), orderDTO.getPayStatus());
//            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
//        }
//
//
//        //修改支付状态
//        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
//        OrderMaster orderMaster = new OrderMaster();
//        BeanUtils.copyProperties(orderDTO,orderMaster);
//        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
//        if (updateResult == null) {
//            log.error("【支付订单】更新失败，orderMaster = {}",orderMaster);
//            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
//        }
//        return orderDTO;
//    }
}
