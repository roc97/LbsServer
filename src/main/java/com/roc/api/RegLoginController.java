package com.roc.api;

import com.roc.utils.JsonResult;
import com.roc.utils.ResultEnum;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author p
 */
@RestController
public class RegLoginController {

    @RequestMapping("/login_p")
    public JsonResult login() {
        return JsonResult.error(ResultEnum.NO_LOGIN.getMsg());
    }
}
