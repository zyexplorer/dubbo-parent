package com.iask.dubboconsumer.dubboservice;

import com.alibaba.dubbo.config.annotation.Reference;
import com.iask.dubboapi.service.UserService;
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

}
