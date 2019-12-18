package com.roc.service;

import com.roc.exception.LbsServerException;
import com.roc.mapper.ExperienceMapper;
import com.roc.mapper.PraiseOrDisagreeMapper;
import com.roc.mapper.ReplyMapper;
import com.roc.mapper.SysUserMapper;
import com.roc.pojo.Experience;
import com.roc.pojo.PraiseOrDisagree;
import com.roc.pojo.SysUser;
import com.roc.utils.DateUtil;
import com.roc.utils.ResultEnum;
import com.roc.vo.ExperienceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private PraiseOrDisagreeMapper praiseOrDisagreeMapper;
    @Autowired
    private ReplyMapper replyMapper;

    public List<ExperienceVo> findAllList(int userId){
        return experienceMapper.getAllList(userId);
    }

    public int postExperience(Experience experience){
        return experienceMapper.insertPojo(experience);
    }

    public Experience findExperience(int operateObject,int commId){
        return experienceMapper.getPojo(operateObject,commId);
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

    @Transactional(readOnly = false)
    public void likeOrHate(int userId,int targetId,int targetType,int operateType)throws LbsServerException{
        PraiseOrDisagree pojo = praiseOrDisagreeMapper.getPojo(userId, targetId, targetType);
        if(pojo!=null && operateType!=-1){
            throw new LbsServerException(ResultEnum.OPERATION_FAILURE);
        }
        pojo=new PraiseOrDisagree();
        pojo.setOperateObject(userId);
        pojo.setOperateTarget(targetId);
        pojo.setTargetType(targetType);
        pojo.setOperateType(operateType);
        int i =0;
        if (operateType==-1){
            i=praiseOrDisagreeMapper.deletePojo(pojo);
        }else {
            i=praiseOrDisagreeMapper.insertPojo(pojo);
        }
        if (i==0){
            throw new LbsServerException(ResultEnum.OPERATION_FAILURE);
        }
        int total= praiseOrDisagreeMapper.countLikeNumORHateNum(targetId, targetType, operateType);
        int j=0;
        if (targetType==0){
            j= experienceMapper.updateLikeNumOrHateNum(targetId, operateType, total);
        }else if(targetType==1){
            j= replyMapper.updateLikeNumAndUnlikeNum(targetId,operateType,total);
        }
        if(j==0){
            throw new LbsServerException(ResultEnum.OPERATION_FAILURE);
        }
    }
}
