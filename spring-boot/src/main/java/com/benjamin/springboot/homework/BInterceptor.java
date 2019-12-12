package com.benjamin.springboot.homework;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName:BInterceptor
 * Package:com.benjamin.springboot.controller
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 19-12-11 上午9:37
 */
public class BInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String username = (String) request.getAttribute("username");
        String password = (String) request.getAttribute("password");
        return "bbb".equals(username) && "bbb".equals(password);
    }
}