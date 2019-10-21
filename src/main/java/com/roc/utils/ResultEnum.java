package com.roc.utils;

import lombok.Getter;

/**
 * @author p
 */
@Getter
public enum ResultEnum {
    LOGIN_SUCCESS(0,"登录成功"),
    NO_EXIST_USER(1,"不存在该用户"),
    PASSWORD_ERROR(2,"密码错误"),
    NO_LOGIN(3,"尚未登录,请登录!"),
    LACK_AUTHORITY(4,"权限不足，请联系管理员！"),
    USERNANE_OR_PASSWORD_ERROR(5,"账户名或者密码输入错误！"),
    LOGIN_FAILURE(6,"登录失败"),
    LOGOUT_SUCCESS(7,"注销成功"),
    ;

    private Integer code;
    private String msg;
    ResultEnum(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }
}
