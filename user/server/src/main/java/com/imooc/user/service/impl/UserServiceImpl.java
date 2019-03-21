package com.imooc.user.service.impl;

import com.imooc.user.dataobject.UserInfo;
import com.imooc.user.repository.UserInfoRepostory;
import com.imooc.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dell on 2019/3/19.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoRepostory repostory;

    @Override
    public UserInfo findByOpenid(String openid) {
        return repostory.findByOpenid(openid);
    }
}
