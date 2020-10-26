package com.example.webdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.chzcb.user.pojo.req.UserLoginReq;
import org.chzcb.user.pojo.resp.UserLoginResp;
import org.chzcb.user.service.IUserService;
import org.dhp.common.utils.JacksonUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/user")
@RestController
@Slf4j
public class UserController {

    @Resource
    IUserService userService;

    @RequestMapping(path = "/login")
    public UserLoginResp userLogin(UserLoginReq request) {
        UserLoginResp resp = userService.userLogin(request);
        log.info("login resp: {}", JacksonUtil.bean2Json(resp));
        return resp;
    }
}
