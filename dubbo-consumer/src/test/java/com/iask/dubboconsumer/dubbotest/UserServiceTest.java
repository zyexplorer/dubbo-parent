package com.iask.dubboconsumer.dubbotest;

import com.alibaba.dubbo.config.annotation.Reference;
import com.iask.dubboapi.service.UserService;
import com.iask.dubbocommon.entity.User;
import com.iask.dubboconsumer.DubboConsumerApplicationTests;
import org.junit.jupiter.api.Test;

public class UserServiceTest extends DubboConsumerApplicationTests {

    @Reference
    UserService userService;

    @Test
    public void testUserService() {
        User user = userService.getUserById(12);
        System.out.println(user);
    }

}
