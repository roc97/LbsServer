package com.roc.utils;

import lombok.Getter;

/**
 * @author p
 */
@Getter
public enum ResultEnum {
    LOGIN_SUCCESS(0,"登录成功"),
    NO_EXIST_USER(403,"不存在该用户"),
    NO_LOGIN(403,"尚未登录,请登录!"),
    LACK_AUTHORITY(3,"权限不足，请联系管理员！"),
    USERNANE_OR_PASSWORD_ERROR(403,"账号或者密码输入错误！"),
    LOGIN_FAILURE(403,"登录失败"),
    LOGOUT_SUCCESS(6,"注销成功"),
    REGISTER_FAILURE(403,"注册失败"),
    USER_ALREDY_EXIST(403,"用户已存在"),
    REGISTER_SUCCESS(9,"注册成功"),
    FILE_UPLOAD_FAILURE(403,"文件上传失败,请重新上传！"),
    JSON_PARSE_EXCEPTION(403,"数据解析异常!"),
    FILE_SIZE_TOO_BIG(403,"图片大小不能超过3M！"),
    OPERATION_SUCCESS(13,"操作成功!"),
    OPERATION_FAILURE(403,"操作失败"),
    ATTENTION_FAILURE(403,"关注失败"),
    ATTENTION_EXIST(403,"该用户已关注"),
    ATTENTION_SUCCESS(16,"关注成功"),
    CANCEL_SUCCESS(17,"取关成功"),
    PUBLIC_FAILURE(403,"发布失败"),
    PUBLIC_SUCCESS(19,"发布成功"),
    REPLY_SUCCESS(20,"回复成功"),
    REPLY_FAILURE(403,"回复失败"),
    LOGIN_EXPIRE(403,"登录过期，请重新登录!"),
    LOGIN_NO_AUTHORIZATION(403,"未授权，请重新登录!"),
    INTERNAL_ERROR(500,"内部错误，请联系管理员！"),
    ;

    private Integer code;
    private String msg;
    ResultEnum(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }
}
