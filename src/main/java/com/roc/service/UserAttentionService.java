package com.roc.service;

import com.roc.exception.LbsServerException;
import com.roc.mapper.SysUserMapper;
import com.roc.mapper.UserAttentionMapper;
import com.roc.pojo.SysUser;
import com.roc.pojo.UserAttention;
import com.roc.utils.ResultEnum;
import com.roc.vo.ExperienceVo;
import com.roc.vo.MarkDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Roc
 * @version 1.0
 * @date 2019/10/27 15:54
 * @description 关注功能相关的业务层
 */
@Service
public class UserAttentionService {

    @Autowired
    private UserAttentionMapper userAttentionMapper;
    @Autowired
    private SysUserMapper sysUserMapper;

    public UserAttention getUserAttention(int userId,int followId){
        return userAttentionMapper.getPojo(userId,followId);
    }

    @Transactional(rollbackFor = LbsServerException.class)
    public int addAttention(int userId,int followId){
        int i=1;
        UserAttention userAttention = getUserAttention(userId, followId);
        if(userAttention!=null){
            return i;
        }
        SysUser user = sysUserMapper.getPojo(userId);
        SysUser followedUser = sysUserMapper.getPojo(followId);
        if(user==null || followedUser==null){
            throw new LbsServerException(ResultEnum.ATTENTION_FAILURE);
        }
        userAttention=new UserAttention();
        userAttention.setUserId(userId);
        userAttention.setUserAttentionId(followId);
        i=userAttentionMapper.insertPojo(userAttention);
        return i;
    }

    /**
     * 获取关注流信息流
     * @param userId
     * @return
     */
     public Map<String,Object> getListByUserId(int userId){
         Map<String,Object> result=new HashMap<>(16);
         List<MarkDetailVo> markListByUserId = userAttentionMapper.getMarkListByUserId(userId);
         List<ExperienceVo> commListByUserId = userAttentionMapper.getCommListByUserId(userId);
         result.put("markList",markListByUserId);
         result.put("commList",commListByUserId);
         result.put("count",markListByUserId.size()+commListByUserId.size());
         return result;
     }

     public List<UserAttention> getListByAttentionId(int userAttentionId){
        return userAttentionMapper.getListByAttentionId(userAttentionId);
     }

     public int cancelAttention(int userId,int followId){
        return userAttentionMapper.deletePojo(userId,followId);
     }
}
