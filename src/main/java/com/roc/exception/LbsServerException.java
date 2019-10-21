package com.roc.exception;

import com.roc.utils.ResultEnum;
import lombok.Getter;

/**
 * @author p
 */
@Getter
public class LbsServerException extends RuntimeException {

    private Integer code;

    public LbsServerException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code=resultEnum.getCode();
    }

    public LbsServerException(Integer code,String message){
        super(message);
        this.code=code;
    }
}
