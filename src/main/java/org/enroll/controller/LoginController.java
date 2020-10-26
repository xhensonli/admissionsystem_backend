package org.enroll.controller;

import org.enroll.configuration.LoginProperties;
import org.enroll.utils.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginProperties properties;

    @Resource(name = "globalStorage")
    Map<String, Object> storage;

    @RequestMapping("/doLogin")
    public JsonResponse doLogin(String name, String pass, HttpSession session){
        if(properties.getAdminName().equals(name) && properties.getAdminPass().equals(pass)){
            storage.put("authSession", session);
            return new JsonResponse(JsonResponse.OK, null, null);
        } else {
            return new JsonResponse(JsonResponse.AUTH_ERR, null, "登陆失败");
        }
    }

    @RequestMapping("/checkLogin")
    public JsonResponse checkLogin(HttpSession session){
        if (session.equals(storage.get("authSession"))){
            return new JsonResponse(JsonResponse.OK, null, "已登录");
        } else {
            return new JsonResponse(JsonResponse.AUTH_ERR, null, "未登录");
        }
    }


    @RequestMapping("/logout")
    public JsonResponse logout(){
        storage.remove("authSession");
        return new JsonResponse(JsonResponse.OK, null, "注销成功");
    }
}
