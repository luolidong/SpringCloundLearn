package com.imooc.user.enums;

import lombok.Getter;

/**
 * Created by dell on 2019/1/15.
 */

@Getter
public enum ResultEnum {
    SUCCESS(0, "成功"),

    LOGIN_FAIL(1, "登录失败"),

    ROLE_ERROR(2, "角色权限失误"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
