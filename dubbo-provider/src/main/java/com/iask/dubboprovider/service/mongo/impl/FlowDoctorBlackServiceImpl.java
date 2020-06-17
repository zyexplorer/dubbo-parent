package com.iask.dubboprovider.service.mongo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.iask.dubboapi.dao.mongo.FlowDoctorBlackMongoDao;
import com.iask.dubboapi.service.mongo.FlowDoctorBlackService;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZY
 */
@Service
public class FlowDoctorBlackServiceImpl implements FlowDoctorBlackService {

    @Resource
    private FlowDoctorBlackMongoDao flowDoctorBlackDao;

    /**
     * @param publishDocId
     * @return
     * @描述: 根据条件查询被禁用医生id集合
     * @作者: xiongwei
     * @创建日期: 2017年10月19日 下午5:50:04
     */
    @Override
    public List<String> finddisabledDocIds(String publishDocId) {
        Query blackQuery = new Query();
        blackQuery.addCriteria(Criteria.where("publishDocId").is(publishDocId));
        return flowDoctorBlackDao.findField(blackQuery, "disabledDocId");
    }

    @Override
    public String commonMethod() {
        return "普通方法返回";
    }


}
