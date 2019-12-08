package com.roc.vo;

import lombok.Data;

/**
 * @author Roc
 * @version 1.0
 * @date 2019/12/8 10:43
 * @description user视图层对象
 */
@Data
public class UserVo {

    private int userId;
    /**
     * 账号
     */
    private String userName;

    private String headImage;
}
