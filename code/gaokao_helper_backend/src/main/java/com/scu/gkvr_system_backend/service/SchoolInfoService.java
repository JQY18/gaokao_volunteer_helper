package com.scu.gkvr_system_backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.scu.gkvr_system_backend.pojo.SchoolInfo;

import java.util.Map;


public interface SchoolInfoService extends IService<SchoolInfo> {

    Map<String, Object> getAllSchools(int page);

    Map<String, Object> get985Schools(int page);

    Map<String, Object> get211Schools(int page);

    Map<String, Object> getDoublehighSchools(int page);

    Map<String, Object> getByProvince(int page, String provinceName);

    Map<String, Object> SearchByName(int page, String schoolName);
}
