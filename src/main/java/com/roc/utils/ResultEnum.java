package com.roc.utils;

import lombok.Getter;

@Getter
public enum ResultEnum {
    LOGIN_SUCCESS(0,"登录成功"),
    NO_EXIST_USER(1,"不存在该用户"),
    PASSWORD_ERROR(2,"密码错误"),
    ;

    private Integer code;
    private String msg;
    ResultEnum(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }
}
