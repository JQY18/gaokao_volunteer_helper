package com.scu.gkvr_system_backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.scu.gkvr_system_backend.pojo.MajorInfo;

import java.util.Map;


public interface MajorInfoService extends IService<MajorInfo> {

    Map<String, Object> getSortedMajors(int page, String type);

    Map<String, Object> SearchByName(int page, String majorName);
}
