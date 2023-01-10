package com.example.spring.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.spring.exception.AccountFailure;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if(request.getSession().getAttribute("user") != null){
            return true;
        } else {
            throw new AccountFailure("Please login first");
        }
    }

    
    
}
