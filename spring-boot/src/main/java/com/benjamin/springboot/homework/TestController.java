package com.benjamin.springboot.homework;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName:TestController
 * Package:com.benjamin.springboot.controller
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 19-12-11 上午9:10
 */
@Controller
public class TestController {

    @RequestMapping(value = "/loginController")
    public String login(@RequestParam(value = "username", required = true) String username,
                        @RequestParam(value = "password", required = true) String password,
                        HttpServletRequest request) {
        if ("aaa".equals(username) && "aaa".equals(password)) {
            request.setAttribute("username", "aaa");
            request.setAttribute("password", "aaa");
            return "index";
        } else if ("bbb".equals(username) && "bbb".equals(password)) {
            request.setAttribute("username", "bbb");
            request.setAttribute("password", "bbb");
            return "index";
        }else{
            return "login";
        }
    }

    @RequestMapping(value = "/controllerA")
    public String controllerA(HttpServletRequest request){
        return "";
    }

    @RequestMapping(value = "/controllerB")
    public String controllerB(HttpServletRequest request){
        return "";
    }
}
