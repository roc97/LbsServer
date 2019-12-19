package com.roc.service;

import com.roc.exception.LbsServerException;
import com.roc.mapper.ExperienceMapper;
import com.roc.mapper.PraiseOrDisagreeMapper;
import com.roc.mapper.ReplyMapper;
import com.roc.mapper.SysUserMapper;
import com.roc.pojo.Experience;
import com.roc.pojo.PraiseOrDisagree;
import com.roc.pojo.SysUser;
import com.roc.utils.MD5Utils;
import com.roc.utils.OSSClientUtil;
import com.roc.utils.ResultEnum;
import com.roc.vo.ExperienceVo;
import com.roc.vo.ReplyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    public Map<String,Object> findExperienceHomeData(int userId) {
        Map<String,Object> map=new HashMap<>(16);
        List<ExperienceVo> markList = experienceMapper.getAllList(userId);
        map.put("count",markList.size());
        map.put("result",markList);
        map.put("msg",ResultEnum.OPERATION_SUCCESS.getMsg());
        return map;
    }

    public void postExperience(int userId, String content, String title, MultipartFile file,String token){
        Experience experience=new Experience();
        if (file!=null && file.getSize()>0){
            long defaultFileSize = 3145728;
            String imageName= MD5Utils.MD5Encode(token+ UUID.randomUUID().toString(),"utf-8");
            OSSClientUtil ossClient=new OSSClientUtil();
            String name = ossClient.uploadImg2Oss(file,defaultFileSize,imageName);
            String imgUrl = ossClient.getImgUrl(name);
            String[] split = imgUrl.split("\\?");
            experience.setImage(split[0]);
        }
        experience.setContent(content);
        experience.setUserId(userId);
        experience.setTitle(title);
        int i = experienceMapper.insertPojo(experience);
        if(i!=1){
            throw new LbsServerException(ResultEnum.PUBLIC_FAILURE);
        }
    }

    public Map<String, Object> findExperienceDetail(int operateObject,int commId){
        Map<String, Object> map = new HashMap<>(16);
        Experience experience = experienceMapper.getPojo(operateObject, commId);
        if (experience == null) {
            throw new LbsServerException(ResultEnum.OPERATION_FAILURE);
        }
        List<ReplyVo> listByCommId = replyMapper.getByExperienceId(commId, operateObject);
        map.put("count", listByCommId.size());
        map.put("result", listByCommId);
        map.put("image",experience.getImage());
        map.put("title", experience.getTitle());
        map.put("content", experience.getContent());
        map.put("likeNum", experience.getLikeNum());
        map.put("unlikeNum", experience.getUnlikeNum());
        map.put("createTime", experience.getCreateTime());
        map.put("operateStatus", experience.getOperateStatus());
        map.put("msg", ResultEnum.OPERATION_SUCCESS.getMsg());
        return map;
    }

    public Map<String, Object> findExperienceAdminData(int userId){
        Map<String, Object> map = new HashMap<>(16);
        SysUser user = userMapper.getPojo(userId);
        if(user==null){
            throw new LbsServerException(ResultEnum.OPERATION_FAILURE);
        }
        List<ExperienceVo> result = experienceMapper.getListByUserId(userId);
        map.put("status", HttpStatus.OK.value());
        map.put("count", result.size());
        map.put("msg", ResultEnum.OPERATION_SUCCESS.getMsg());
        map.put("result", result);
        return map;
    }


    @Transactional(readOnly = false)
    public void likeOrHate(int userId,int targetId,int targetType,int operateType){
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
