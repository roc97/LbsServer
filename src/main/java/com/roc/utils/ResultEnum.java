package com.roc.utils;

import lombok.Getter;

/**
 * @author p
 */
@Getter
public enum ResultEnum {
    LOGIN_SUCCESS(0,"登录成功"),
    NO_EXIST_USER(1,"不存在该用户"),
    NO_LOGIN(2,"尚未登录,请登录!"),
    LACK_AUTHORITY(3,"权限不足，请联系管理员！"),
    USERNANE_OR_PASSWORD_ERROR(4,"账号或者密码输入错误！"),
    LOGIN_FAILURE(5,"登录失败"),
    LOGOUT_SUCCESS(6,"注销成功"),
    REGISTER_FAILURE(7,"注册失败"),
    USER_ALREDY_EXIST(8,"用户已存在"),
    REGISTER_SUCCESS(9,"注册成功"),
    ;

    private Integer code;
    private String msg;
    ResultEnum(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }
}
