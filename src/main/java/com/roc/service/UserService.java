package com.roc.service;

import com.roc.exception.LbsServerException;
import com.roc.mapper.SysRoleUserMapper;
import com.roc.mapper.SysUserMapper;
import com.roc.pojo.SysUser;
import com.roc.utils.OSSClientUtil;
import com.roc.utils.ResultEnum;
import com.roc.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author p
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private SysUserMapper mapper;
    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

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
    public int registerUser(SysUser user)throws LbsServerException{
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
        return i+j;
    }

    public void updateUser(SysUser user){
        int i=mapper.updatePojo(user);
        if(i==0){
            throw new LbsServerException(ResultEnum.OPERATION_FAILURE);
        }
    }

    @Transactional(readOnly = true)
    public SysUser getUser(int id){
        SysUser user=mapper.getPojo(id);
        if(user==null){
            throw new LbsServerException(ResultEnum.NO_EXIST_USER);
        }
        return user;
    }

    public int deleteUser(int id){
        return mapper.deletePojo(id);
    }

    public List<UserVo> findUserList(){
        return mapper.getUserList();
    }

    @Transactional(readOnly = false)
    public String headImageUpload(MultipartFile file,long defaultSize,String token,int userId)throws LbsServerException{
        if (file == null || file.getSize() <= 0) {
            throw new LbsServerException(ResultEnum.FILE_UPLOAD_FAILURE);
        }
        OSSClientUtil ossClient=new OSSClientUtil();
        String name = ossClient.uploadImg2Oss(file,defaultSize,token);
        String imgUrl = ossClient.getImgUrl(name);
        String[] split = imgUrl.split("\\?");
        //对数据库修改
        SysUser user = getUser(userId);
        user.setHeadImage(split[0]);
        updateUser(user);
        return split[0];
    }

}
