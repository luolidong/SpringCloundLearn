package com.imooc.user.service;

import com.imooc.user.dataobject.UserInfo;

/**
 * Created by dell on 2019/3/19.
 */
public interface UserService {
    UserInfo findByOpenid(String openid);
}
