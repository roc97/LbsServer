package com.roc.service;

import com.roc.exception.LbsServerException;
import com.roc.mapper.ExperienceMapper;
import com.roc.mapper.SysUserMapper;
import com.roc.pojo.Experience;
import com.roc.pojo.SysUser;
import com.roc.utils.DateUtil;
import com.roc.utils.ResultEnum;
import com.roc.vo.ExperienceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    @Autowired
    private SysUserMapper userMapper;

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

    public List<ExperienceVo> findListByUserId(int userId)throws LbsServerException{
        SysUser user = userMapper.getPojo(userId);
        if(user==null){
            throw new LbsServerException(ResultEnum.OPERATION_FAILURE);
        }
        return experienceMapper.getListByUserId(userId);
    }

    public void deleteExperience(int experienceId) throws LbsServerException{
        int i = experienceMapper.deletePojo(experienceId);
        if (i!=1){
            throw new LbsServerException(ResultEnum.PUBLIC_FAILURE);
        }
    }

    public List<Map> countTimeSlotRecords(String startTime, String endTime){
        return experienceMapper.countTimeSlotRecords(DateUtil.stampToDate(startTime), DateUtil.stampToDate(endTime));
    }
}
