package com.roc.utils;

import com.google.gson.JsonObject;
import com.roc.exception.LbsServerException;
import com.roc.pojo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author Roc
 * @version 1.0
 * @date 2019/10/22 22:19
 */
@Component
public class UserUtil {

    @Autowired
    private RedisUtil redisUtil;

    public static SysUser getCurrentUser(){
        return (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public void checkToken(int userId,String token){
        Object redisValue= redisUtil.get(token);
        if (redisValue==null){
            //该用户未登录
            throw new LbsServerException(ResultEnum.LOGIN_EXPIRE);
        }
        JsonObject jo=JsonUtil.parse(redisValue.toString());
        int redisUserId=Integer.valueOf(jo.get("userId").getAsString());
        if(userId!=redisUserId){
            //前端传过来的用户id与token里存储的不同
            throw new LbsServerException(ResultEnum.LOGIN_NO_AUTHORIZATION);
        }
        long day=60*60*24;
        long expireTime=60*60+redisUtil.getExpire(token);
        if(expireTime>day){
            redisUtil.expire(token, day);
        }else {
            redisUtil.expire(token, expireTime);
        }
    }
}
