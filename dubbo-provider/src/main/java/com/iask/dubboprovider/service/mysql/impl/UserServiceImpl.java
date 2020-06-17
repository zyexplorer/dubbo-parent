package com.iask.dubboprovider.service.mysql.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.iask.dubboapi.dao.mysql.UserDao;
import com.iask.dubboapi.service.mysql.UserService;
import com.iask.dubbocommon.entity.mysql.User;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * @author ZY
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User getUserById(Integer id) {
        log.info("远程调用，入参：{}", id);
        //return User.builder().id(id).name("张三").age(18).job("搬砖").build();
        return userDao.getUserById(id);
    }
}
