package com.imooc.order.exception;


import com.imooc.order.enums.ResultEnum;

/**
 * Created by dell on 2019/1/15.
 */
public class OrderException extends  RuntimeException{

    private Integer code;

    public OrderException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public OrderException(Integer code, String message) {
        super(message);

        this.code = code;
    }
}
