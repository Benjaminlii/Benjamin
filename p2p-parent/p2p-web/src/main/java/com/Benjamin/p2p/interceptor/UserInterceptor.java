package com.Benjamin.p2p.interceptor;

import com.Benjamin.p2p.common.constant.Constants;
import com.Benjamin.p2p.model.user.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //获取session中的用户信息
        User sessionUser = (User) httpServletRequest.getSession().getAttribute(Constants.SESSION_USER);

        //判断用户是否登录
        if(sessionUser == null){
            //未登录的情况下跳转指登录页面
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.jsp");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
