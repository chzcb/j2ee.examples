package com.example.webdemo.intercept;

import com.example.webdemo.pojo.Header;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class HeaderInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Header header = new Header();
        header.setIp(request.getRemoteHost());
        request.setAttribute(".HEADER_REQUEST_ATTR", header);
        return true;
    }
}
