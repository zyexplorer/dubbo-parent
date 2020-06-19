package com.iask.dubboconsumer.dubbotest;

import com.alibaba.dubbo.config.annotation.Reference;
import com.iask.dubboapi.service.mongo.FlowDoctorBlackService;
import com.iask.dubboconsumer.DubboConsumerApplicationTests;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FlowDoctorBlackServiceTest extends DubboConsumerApplicationTests {

    @Reference
    FlowDoctorBlackService flowDoctorBlackService;

    @Test
    public void testDubboFinddisabledDocIds() {
        try {
            List<String> ids = flowDoctorBlackService.finddisabledDocIds("1110");
            System.out.println(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
