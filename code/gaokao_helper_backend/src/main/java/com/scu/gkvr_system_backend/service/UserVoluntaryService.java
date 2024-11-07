package com.scu.gkvr_system_backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.scu.gkvr_system_backend.pojo.UserVoluntary;

import java.util.Map;


public interface UserVoluntaryService extends IService<UserVoluntary> {

    Map<String, Object> getVoluntary(String username);

    Boolean addVoluntary(UserVoluntary userVoluntary);

    Boolean deleteVoluntary(UserVoluntary userVoluntary);
}
