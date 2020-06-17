package com.iask.dubboconsumer.dubbotest;

import com.alibaba.dubbo.config.annotation.Reference;
import com.iask.dubboconsumer.DubboConsumerApplicationTests;
import com.wenwo.platform.flow.IFlowDoctorBlackService;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class FlowDoctorBlackServiceTest extends DubboConsumerApplicationTests {

    @Reference
    IFlowDoctorBlackService flowDoctorBlackService;

    @Test
    public void testDubboFinddisabledDocIds() {
        Query blackQuery = new Query();
        blackQuery.addCriteria(Criteria.where("publishDocId").is("1110"));
        try {
            List<String> ids = flowDoctorBlackService.finddisabledDocIds(blackQuery);
            System.out.println(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
