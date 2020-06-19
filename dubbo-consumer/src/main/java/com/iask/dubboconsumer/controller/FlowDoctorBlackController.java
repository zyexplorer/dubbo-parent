package com.iask.dubboconsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.iask.dubboapi.service.mongo.FlowDoctorBlackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 
 * @Param: 
 * @Return: 
 * @Author: ZY
 * @Date: 2020/6/17 0017-20:57
 **/
@Slf4j
@RestController
@RequestMapping("/flow")
public class FlowDoctorBlackController {

    @Reference
    FlowDoctorBlackService flowDoctorBlackService;

    @GetMapping("/findDisabledDocIds")
    public List<String> testFindDisabledDocIds(String publishDocId) {
        log.info("查询mongo库入参：{}", publishDocId);
        return flowDoctorBlackService.finddisabledDocIds(publishDocId);
    }

}
