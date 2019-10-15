package com.roc.pojo;


import lombok.Data;

import java.sql.Timestamp;

/**
 * 用户实体
 */
@Data
public class SysUser {

    private int userId;

    private String userName;//用户昵称

    private String account;//账号

    private String password;//密码

    private String sex;//性别

    private String headImage;//头像url

    private Timestamp  createTime;
}
