package com.roc.utils;

import com.roc.exception.LbsServerException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = LbsServerException.class)
    public JsonResult lbsExceptionHandler(HttpServletRequest req, LbsServerException e){
        e.printStackTrace();
        return JsonResult.error(HttpServletResponse.SC_FORBIDDEN,e.getMessage());
    }
}
