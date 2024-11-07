package com.scu.gkvr_system_backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.scu.gkvr_system_backend.pojo.User;


public interface UserService extends IService<User> {

    Boolean login(User user);

    Boolean register(User user);
}
