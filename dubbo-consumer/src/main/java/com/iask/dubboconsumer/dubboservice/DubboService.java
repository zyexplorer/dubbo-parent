package com.iask.dubboconsumer.dubboservice;

import com.alibaba.dubbo.config.annotation.Reference;
import com.iask.dubboapi.service.mongo.FlowDoctorBlackService;
import com.iask.dubboapi.service.mysql.ProductDictService;
import com.iask.dubboapi.service.mysql.UserService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Service
public class DubboService {

    @Reference
    UserService userService;

    @Reference
    FlowDoctorBlackService flowDoctorBlackService;

    @Reference
    ProductDictService productDictService;
}
