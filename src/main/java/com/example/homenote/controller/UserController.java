package com.example.homenote.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.homenote.req.UserLoginReq;
import com.example.homenote.req.UserReq;
import com.example.homenote.res.CommonRes;
import com.example.homenote.res.UserLoginRes;
import com.example.homenote.res.UserRes;
import com.example.homenote.service.UserService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@RestController
@RequestMapping("/user")
public class UserController {
//    private static final Logger LOG = (Logger) LoggerFactory.getLogger(UserController.class);

    // 引入 service
    @Resource
    private UserService userService;

//    将登录信息保存
    @Resource
    private RedisTemplate redisTemplate;
    // 查询
    @GetMapping("/list")
    public CommonRes list(UserReq req){
        CommonRes<Object> res = new CommonRes<>();
        List<UserRes> list = userService.list(req);
        res.setData(list);
        res.setMsg("请求成功");
        return res;
    }
    // 保存 & 修改
    @PostMapping("/save")
    public CommonRes save(@RequestBody UserReq req){
        /* 把密码转化为 md5 */
//        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonRes res = new CommonRes<>();
        userService.save(req);
        res.setMsg("注册成功");
        return res;
    }
    // 删除     @PathVariable 自动映射 path 上的参数
    @DeleteMapping("/delete/{id}")
    public CommonRes delete(@PathVariable String id){
        CommonRes res = new CommonRes<>();
        userService.delete(id);
        res.setMsg("删除成功");
        return res;
    }

    // 登录
    @PostMapping("/login")
    public CommonRes login(@RequestBody UserLoginReq req){
        CommonRes<Object> res = new CommonRes<>();
        UserLoginRes user = userService.login(req);

        res.setMsg("登录成功");


//        登录成功，生成 token, 保存如 redis，token为 key user对象 为值，存放事件 24 小时
        String token = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        // 把 token 返回前端，后端可以用过 token 来验证是否有效
//        LOG.info("生成单点登录 token：" + token + "，并放入 redis 中");
        user.setToken(token);
        System.out.print(token + "tooken");
        redisTemplate.opsForValue().set(token, JSONObject.toJSONString(user), 3600 * 24, TimeUnit.SECONDS);

        res.setData(user);
        return res;
    }
}