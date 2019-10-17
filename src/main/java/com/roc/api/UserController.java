package com.roc.api;

import com.roc.service.UserService;
import com.roc.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/user")
@ResponseBody
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public JsonResult login(Map<String,Object> map){
        String account=(String)map.get("account");
        String password=(String)map.get("password");
        boolean flag = userService.login(account, password);
        if(flag){
            map.put("msg","登录成功");
        }
        return JsonResult.error(403,"登录失败");
    }
}
