package com.iask.dubboconsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.iask.dubboapi.service.mongo.FlowDoctorBlackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/flow")
public class FlowDoctorBlackController {

    @Reference
    FlowDoctorBlackService flowDoctorBlackService;

    @GetMapping("/finddisabledDocIds")
    public List<String> testFinddisabledDocIds(String publishDocId) {
        log.info("查询mongo库入参：{}", publishDocId);
        return flowDoctorBlackService.finddisabledDocIds(publishDocId);
    }

}
