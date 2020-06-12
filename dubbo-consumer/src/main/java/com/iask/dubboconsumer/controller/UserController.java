package com.iask.dubboconsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.iask.dubboapi.service.UserService;
import com.iask.dubbocommon.entity.User;
import com.iask.dubboconsumer.dubboservice.DubboService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ZY
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private DubboService dubboService;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Integer id) {
        log.info("前端请求参数：{}", id);
        return dubboService.getUserService().getUserById(id);
    }

}
