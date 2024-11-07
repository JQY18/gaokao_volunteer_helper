package com.scu.gkvr_system_backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.scu.gkvr_system_backend.pojo.SchoolInfoDetail;

import java.util.Map;


public interface SchoolInfoDetailService extends IService<SchoolInfoDetail> {

    Map<String, Object> getSchoolDetail(int schoolId);
}
