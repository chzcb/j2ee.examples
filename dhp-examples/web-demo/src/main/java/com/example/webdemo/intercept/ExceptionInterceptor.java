package com.example.webdemo.intercept;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ExceptionInterceptor implements HandlerInterceptor {
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if(response.getStatus() == 400){
            response.setStatus(200);
            response.getWriter().write("dddd");
            modelAndView.clear();
        }
    }
}
