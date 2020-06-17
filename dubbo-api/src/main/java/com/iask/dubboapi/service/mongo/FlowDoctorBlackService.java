package com.iask.dubboapi.service.mongo;

import java.util.List;
import org.springframework.data.mongodb.core.query.Query;

/**
 * @author ZY
 */
public interface FlowDoctorBlackService {

    /**
     * @描述: 根据条件查询被禁用医生id集合
     * @param publishDocId
     * @return
     * @作者: xiongwei
     * @创建日期: 2017年10月19日 下午5:50:04
     */
    List<String> finddisabledDocIds(String publishDocId);

    String commonMethod();

}
