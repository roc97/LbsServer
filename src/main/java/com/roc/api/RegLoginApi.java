package com.roc.api;

import com.roc.utils.JsonResult;
import com.roc.utils.ResultEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author p
 */
@Api(description = "未登录接口")
@RestController
public class RegLoginApi {
    @ApiOperation(value = "未登录功能", notes = "未登录时，返回状态码500", response = JsonResult.class)
    @RequestMapping(value = "/login_p",method = RequestMethod.GET)
    public JsonResult login() {
        return JsonResult.error(ResultEnum.NO_LOGIN);
    }
}
