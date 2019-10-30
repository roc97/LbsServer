package com.roc.vo;

import lombok.Data;

/**
 * @author Roc
 * @version 1.0
 * @date 2019/10/30 21:05
 * @description 推广审核视图对象
 */
@Data
public class PublicCheckVo {
    private int markId;

    private int status;

    private String title;

    private String content;
}
