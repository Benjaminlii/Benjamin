package com.thoughtCoding.theMall;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.thoughtCoding.theMall.model.User;

/**
 * ClassName:Filter
 * Package:com.thoughtCoding.theMall
 * <p>
 * Description:
 *
 * @author: Benjamin
 * @date: 20-3-12 下午6:27
 */
@WebFilter(filterName = "filter", urlPatterns = "/testFilter")
public class Filter implements javax.servlet.Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        User user = new User();
        user.setUsername("benjamin");
        request.setAttribute("user", user);
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
