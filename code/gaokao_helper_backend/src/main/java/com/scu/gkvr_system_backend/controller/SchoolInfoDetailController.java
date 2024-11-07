package com.scu.gkvr_system_backend.controller;

import com.scu.gkvr_system_backend.service.SchoolInfoDetailService;
import com.scu.gkvr_system_backend.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/schoolInfoDetail")
@CrossOrigin
public class SchoolInfoDetailController {

    @Autowired
    SchoolInfoDetailService schoolInfoDetailService;

    @GetMapping
    public Result<Map<String, Object>> getSchoolDetail(@RequestParam int schoolId) {
        Map<String, Object> data = schoolInfoDetailService.getSchoolDetail(schoolId);
        if (data != null) {
            return Result.success(data);
        }
        return Result.fail("查询失败");
    }
}
