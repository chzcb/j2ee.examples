package com.example.webdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.dhp.core.rpc.RpcException;
import org.dhp.core.spring.FrameworkException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice(basePackages = {"com.example.webdemo.controller"})
public class ExceptionController {
    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<String> errorHandler(Exception e){
        log.info(e.getMessage(), e);
        return new ResponseEntity<String>("500:"+e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler({MissingServletRequestParameterException.class, MethodArgumentTypeMismatchException.class})
    @ResponseBody
    public ResponseEntity<String> specialExceptionHandler(Exception e){
        return new ResponseEntity<String>("400:"+e.getMessage(), null, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(RpcException.class)
    @ResponseBody
    public Map<String, Object> exceptionHandler(RpcException e){
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", e.getCode().getCode());
        result.put("msg", e.getCode().getMessage());
        return result;
    }
    
    @ExceptionHandler(FrameworkException.class)
    @ResponseBody
    public Map<String, Object> frameworkErrorHandler(FrameworkException e){
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", "400");
        result.put("msg", e.getMessage());
        return result;
    }
    
    
    
    
}
