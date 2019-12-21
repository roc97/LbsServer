package com.roc.service;

import com.roc.exception.LbsServerException;
import com.roc.mapper.*;
import com.roc.pojo.SysUser;
import com.roc.utils.DateUtil;
import com.roc.utils.OSSClientUtil;
import com.roc.utils.ResultEnum;
import com.roc.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author p
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private SysUserMapper mapper;
    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;
    @Autowired
    private MapMarkMessageMapper mapMarkMessageMapper;
    @Autowired
    private ExperienceMapper experienceMapper;
    @Autowired
    private ReplyMapper replyMapper;

    private BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();



    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser user = mapper.getByUserName(s);
        if(user==null){
            throw new UsernameNotFoundException(ResultEnum.NO_EXIST_USER.getMsg());
        }
        return user;
    }


    @Transactional(rollbackFor = LbsServerException.class)
    public void registerUser(String name,String username,String password){
        SysUser user = new SysUser();
        user.setName(name);
        user.setUserName(username);
        user.setPassword(password);
        //默认为普通用户
        int basicRoleId=2;
        if(user!=null){
            if(mapper.getByUserName(user.getUsername())!=null){
                throw new LbsServerException(ResultEnum.USER_ALREDY_EXIST);
            }
            String encodePassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encodePassword);
        }
        int i = mapper.insertPojo(user);
        if(i==0){
            throw new LbsServerException(ResultEnum.REGISTER_FAILURE);
        }
        int userId=mapper.getByUserName(user.getUsername()).getUserId();
        int j=sysRoleUserMapper.insertPojo(userId,basicRoleId);
        if(j==0){
            throw new LbsServerException(ResultEnum.REGISTER_FAILURE);
        }
    }

    public void updateUser(SysUser user){
        int i=mapper.updatePojo(user);
        if(i==0){
            throw new LbsServerException(ResultEnum.OPERATION_FAILURE);
        }
    }

    @Transactional(readOnly = true)
    public  Map<String, Object> getUser(int id){
        SysUser user=mapper.getPojo(id);
        Map<String, Object> map = new HashMap<>(16);
        if(user==null){
            throw new LbsServerException(ResultEnum.NO_EXIST_USER);
        }
        map.put("userName", user.getName());
        map.put("headImage", user.getHeadImage());
        map.put("msg", ResultEnum.OPERATION_SUCCESS.getMsg());
        return map;
    }

    public Map<String, Object> findUserList(){
        Map<String, Object> map = new HashMap<>(16);
        List<UserVo> userList = mapper.getUserList();
        map.put("count", userList.size());
        map.put("result", userList);
        map.put("status", HttpStatus.OK.value());
        map.put("msg", ResultEnum.OPERATION_SUCCESS.getMsg());
        return map;
    }

    @Transactional(readOnly = false)
    public String headImageUpload(MultipartFile file,String token,int userId){
        long defaultFileSize = 3145728;
        if (file == null || file.getSize() <= 0) {
            throw new LbsServerException(ResultEnum.FILE_UPLOAD_FAILURE);
        }
        OSSClientUtil ossClient=new OSSClientUtil();
        String name = ossClient.uploadImg2Oss(file,defaultFileSize,token);
        String imgUrl = ossClient.getImgUrl(name);
        String[] split = imgUrl.split("\\?");
        //对数据库修改
        SysUser user = mapper.getPojo(userId);
        user.setHeadImage(split[0]);
        updateUser(user);
        return split[0];
    }

    public void deleteMarkOrExperience(int id,String type){
        String comm = "comm", mark = "mark";
        int i=0;
        if (type.equals(comm)) {
            i=experienceMapper.deletePojo(id);
            replyMapper.deleteByExperienceId(id);
        } else if (type.equals(mark)) {
            i=mapMarkMessageMapper.deletePojo(id);
        } else {
           throw new LbsServerException(ResultEnum.OPERATION_FAILURE);
        }
        if (i!=1){
            throw new LbsServerException(ResultEnum.OPERATION_FAILURE);
        }
    }

    public Map<String,Object> countData(int status, String startTime, String endTime){
        List<Map> result = new ArrayList<>();
        Map<String, Object> map = new HashMap<>(16);
        String time1 = DateUtil.stampToDate(startTime);
        String time2 = DateUtil.stampToDate(endTime);
        if (status != 0) {
            result = mapMarkMessageMapper.countTimeSlotRecords(status, time1, time2);
        } else {
            result = experienceMapper.countTimeSlotRecords(time1, time2);
        }
        map.put("count", result.size());
        map.put("result", result);
        map.put("status", HttpStatus.OK.value());
        map.put("msg", ResultEnum.OPERATION_SUCCESS.getMsg());
        return map;
    }
}
