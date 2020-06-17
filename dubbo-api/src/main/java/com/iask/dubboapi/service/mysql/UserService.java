package com.iask.dubboapi.service.mysql;

import com.iask.dubbocommon.entity.mysql.User;

public interface UserService {

    User getUserById(Integer id);

}
