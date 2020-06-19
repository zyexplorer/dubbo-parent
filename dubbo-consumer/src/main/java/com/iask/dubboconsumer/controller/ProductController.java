package com.iask.dubboconsumer.controller;

import com.iask.dubbocommon.entity.mysql.ProductDict;
import com.iask.dubboconsumer.dubboservice.DubboService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: dubbo-parent
 * @className: ProductController.java
 * @description:
 * @author: ZY
 * @create: 2020年06月19日 10:58
 **/

@Slf4j
@RestController
@RequestMapping("/prod")
public class ProductController {

    @Resource
    DubboService dubboService;

    @GetMapping("/{id}")
    public ProductDict getProductInfo(@PathVariable("id") Integer id) {
        return dubboService.getProductDictService().queryById(id);
    }


}
