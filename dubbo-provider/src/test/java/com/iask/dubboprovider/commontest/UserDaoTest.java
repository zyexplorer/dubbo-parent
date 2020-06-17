package com.iask.dubboprovider.commontest;

import com.iask.dubboapi.dao.mysql.UserDao;
import com.iask.dubbocommon.entity.mysql.User;
import com.iask.dubboprovider.DubboProviderApplicationTests;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

public class UserDaoTest extends DubboProviderApplicationTests {

    @Resource
    UserDao UserDao;

    @Test
    public void testGetUserById() {
        User user = UserDao.getUserById(23);
        System.out.println(user);
    }
}
