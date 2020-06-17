package com.iask.dubboprovider.commontest;

import com.iask.dubboapi.dao.mongo.FlowDoctorBlackMongoDao;
import com.iask.dubboprovider.DubboProviderApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.annotation.Resource;
import java.util.List;

public class FlowDoctorBlackDaoTest extends DubboProviderApplicationTests {

    @Resource
    FlowDoctorBlackMongoDao flowDoctorDao;

    @Test
    public void test() {
        Query blackQuery = new Query();
        blackQuery.addCriteria(Criteria.where("publishDocId").is("59f0586184ae501908792561"));
        List<String> disabledDocId = flowDoctorDao.findField(blackQuery, "disabledDocId");
        System.out.println(disabledDocId);
    }
}
