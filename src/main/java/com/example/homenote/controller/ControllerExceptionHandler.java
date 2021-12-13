package com.example.homenote.controller;

import com.example.homenote.exception.BusinessException;
import com.example.homenote.res.CommonRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


import java.net.BindException;

/* 统一异常处理、数据预处理 */
@ControllerAdvice
public class ControllerExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /* 校验异常统一处理*/
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public CommonRes validExceptionHandler(BusinessException e) {
        CommonRes res = new CommonRes();
//        拿到异常描述
        LOG.warn("业务异常：{}", e.getCode().getDesc());
        res.setSuccess(false);
        res.setMsg(e.getCode().getDesc());
        System.out.print(res);
        return res;
    }
}
