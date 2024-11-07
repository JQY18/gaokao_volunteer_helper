package com.scu.gkvr_system_backend.controller;

import com.scu.gkvr_system_backend.service.SchoolInfoService;
import com.scu.gkvr_system_backend.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/schoolInfo")
@CrossOrigin
public class SchoolInfoController {

    @Autowired
    private SchoolInfoService schoolInfoService;

    @GetMapping("/all")
    public Result<Map<String, Object>> getAllSchools(@RequestParam int page) {
        Map<String, Object> data = schoolInfoService.getAllSchools(page);
        if (data != null) {
            return Result.success(data);
        }
        return Result.fail("查询失败");
    }

    @GetMapping("/985")
    public Result<Map<String, Object>> get985Schools(@RequestParam int page) {
        Map<String, Object> data = schoolInfoService.get985Schools(page);
        if (data != null) {
            return Result.success(data);
        }
        return Result.fail("查询失败");
    }

    @GetMapping("/211")
    public Result<Map<String, Object>> get211Schools(@RequestParam int page) {
        Map<String, Object> data = schoolInfoService.get211Schools(page);
        if (data != null) {
            return Result.success(data);
        }
        return Result.fail("查询失败");
    }

    @GetMapping("/doublehigh")
    public Result<Map<String, Object>> getDoublehighSchools(@RequestParam int page) {
        Map<String, Object> data = schoolInfoService.getDoublehighSchools(page);
        if (data != null) {
            return Result.success(data);
        }
        return Result.fail("查询失败");
    }

    @GetMapping("/getByProvince")
    public Result<Map<String, Object>> searchSchools(@RequestParam int page,
                                                     @RequestParam String provinceName) {
        Map<String, Object> data = schoolInfoService.getByProvince(page, provinceName);
        if (data != null) {
            return Result.success(data);
        }
        return Result.fail("查询失败");
    }

    @GetMapping("/searchByName")
    public Result<Map<String, Object>> searchByName(@RequestParam int page,
                                                    @RequestParam String schoolName) {
        Map<String, Object> data = schoolInfoService.SearchByName(page, schoolName);
        if (data != null) {
            return Result.success(data);
        }
        return Result.fail("查询失败");
    }


}
