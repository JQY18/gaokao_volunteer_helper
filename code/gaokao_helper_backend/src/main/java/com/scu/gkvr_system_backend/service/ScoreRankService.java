package com.scu.gkvr_system_backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.scu.gkvr_system_backend.pojo.ScoreRank;

import java.util.Map;


public interface ScoreRankService extends IService<ScoreRank> {

    Map<String, Object> getRank(int score);

    Map<String, Object> getReco(int page, int score, String risk);
}
