package com.scu.gkvr_system_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scu.gkvr_system_backend.mapper.SchoolInfoMapper;
import com.scu.gkvr_system_backend.pojo.SchoolInfo;
import com.scu.gkvr_system_backend.service.SchoolInfoService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class SchoolInfoServiceImpl extends ServiceImpl<SchoolInfoMapper, SchoolInfo>
        implements SchoolInfoService {

    private final Map<String, Object> result = new HashMap<>();  // 结果集

    @Override
    public Map<String, Object> getAllSchools(int page) {
        Page<SchoolInfo> schoolInfoPage = this.baseMapper.selectPage(new Page<>(page, 10), null);
        result.put("schools", schoolInfoPage.getRecords());
        result.put("total", schoolInfoPage.getTotal());
        return result;
    }

    @Override
    public Map<String, Object> get985Schools(int page) {
        LambdaQueryWrapper<SchoolInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SchoolInfo::getIs985, "985");
        Page<SchoolInfo> schoolInfoPage = this.baseMapper.selectPage(new Page<>(page, 10), wrapper);
        result.put("schools", schoolInfoPage.getRecords());
        result.put("total", schoolInfoPage.getTotal());
        return result;
    }

    @Override
    public Map<String, Object> get211Schools(int page) {
        LambdaQueryWrapper<SchoolInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SchoolInfo::getIs211, "211");
        Page<SchoolInfo> schoolInfoPage = this.baseMapper.selectPage(new Page<>(page, 10), wrapper);
        result.put("schools", schoolInfoPage.getRecords());
        result.put("total", schoolInfoPage.getTotal());
        return result;
    }

    @Override
    public Map<String, Object> getDoublehighSchools(int page) {
        LambdaQueryWrapper<SchoolInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SchoolInfo::getDoublehigh, "双一流");
        Page<SchoolInfo> schoolInfoPage = this.baseMapper.selectPage(new Page<>(page, 10), wrapper);
        result.put("schools", schoolInfoPage.getRecords());
        result.put("total", schoolInfoPage.getTotal());
        return result;
    }

    @Override
    public Map<String, Object> getByProvince(int page, String provinceName) {
        LambdaQueryWrapper<SchoolInfo> wrapper = new LambdaQueryWrapper<>();
        if (provinceName.equals("全部")) {
            return getAllSchools(page);
        }
        wrapper.eq(SchoolInfo::getProvinceName, provinceName);
        Page<SchoolInfo> schoolInfoPage = this.baseMapper.selectPage(new Page<>(page, 10), wrapper);
        result.put("schools", schoolInfoPage.getRecords());
        result.put("total", schoolInfoPage.getTotal());
        return result;
    }

    @Override
    public Map<String, Object> SearchByName(int page, String schoolName) {
        LambdaQueryWrapper<SchoolInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(SchoolInfo::getSchoolName, schoolName);
        Page<SchoolInfo> schoolInfoPage = this.baseMapper.selectPage(new Page<>(page, 10), wrapper);
        result.put("schools", schoolInfoPage.getRecords());
        result.put("total", schoolInfoPage.getTotal());
        return result;
    }

}




