package com.roc.service;

import com.roc.mapper.ExperienceMapper;
import com.roc.pojo.Experience;
import com.roc.vo.ExperienceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Roc
 * @version 1.0
 * @date 2019/10/30 22:32
 * @description 心得交流业务
 */
@Service
public class ExperienceService {

    @Autowired
    private ExperienceMapper experienceMapper;

    public List<ExperienceVo> findAllList(){
        return experienceMapper.getAllList();
    }

    public int postExperience(Experience experience){
        return experienceMapper.insertPojo(experience);
    }

    public int updateLikeNumAndUnlikeNum(Experience experience){
        return experienceMapper.updatePojo(experience);
    }

    public Experience findExperience(int commId){
        return experienceMapper.getPojo(commId);
    }
}
