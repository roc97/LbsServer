package com.roc.utils;

import com.roc.exception.LbsServerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author p
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = LbsServerException.class)
    public JsonResult lbsExceptionHandler(LbsServerException e){
        log.error(e.getMessage());
        return JsonResult.error(HttpServletResponse.SC_FORBIDDEN,e.getMessage());
    }
    @ExceptionHandler(value = Exception.class)
    public JsonResult lbsExceptionHandler(Exception e){
        log.error(ResultEnum.INTERNAL_ERROR.getMsg());
        log.error(e.getMessage());
        return JsonResult.error(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,ResultEnum.INTERNAL_ERROR.getMsg());
    }
}
