package com.iask.dubboprovider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.iask.dubboapi.service.UserService;
import com.iask.dubbocommon.entity.User;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ZY
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Override
    public User getUserById(Integer id) {
        log.info("远程调用，入参：{}", id);
        return User.builder().id(id).name("张三").age(18).job("搬砖").build();
    }
}
