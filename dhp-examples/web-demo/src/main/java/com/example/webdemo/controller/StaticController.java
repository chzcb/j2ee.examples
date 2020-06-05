package com.example.webdemo.controller;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@CacheConfig(cacheNames = "static")
@Controller
public class StaticController {
    
    @Resource
    HttpServletResponse response;
    
    @ResponseBody
    @RequestMapping(path = "/static/test.js")
    public String testJs(){
        return "var date = "+new Date().toString()+"\n console.log(date);";
    }
}
