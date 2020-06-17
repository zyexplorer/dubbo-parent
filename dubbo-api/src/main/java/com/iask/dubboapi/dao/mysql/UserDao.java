package com.iask.dubboapi.dao.mysql;

import com.iask.dubbocommon.entity.mysql.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    User getUserById(Integer id);

}
