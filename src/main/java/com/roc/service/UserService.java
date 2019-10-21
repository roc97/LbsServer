package com.roc.service;

import com.roc.exception.LbsServerException;
import com.roc.mapper.SysUserMapper;
import com.roc.pojo.SysUser;
import com.roc.utils.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author p
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private SysUserMapper mapper;
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

    @Transactional(readOnly = true)
    public boolean login(String userName,String password) throws LbsServerException{
        String encodePassword = bCryptPasswordEncoder.encode(password);
        SysUser user = (SysUser) loadUserByUsername(userName);
        if(user!=null) {
            if (user.getPassword().equals(encodePassword)) {
                return true;
            }else{
                throw new LbsServerException(ResultEnum.PASSWORD_ERROR);
            }
        }
        return false;
    }


    public int addUser(SysUser user){
        if(user!=null){
            String encodePassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encodePassword);
        }
        return mapper.insertPojo(user);
    }

    public int updateUser(SysUser user){
        return mapper.updatePojo(user);
    }

    @Transactional(readOnly = true)
    public SysUser getUser(int id){
        return mapper.getPojo(id);
    }

    public int deleteUser(int id){
        return mapper.deletePojo(id);
    }


}
