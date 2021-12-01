package com.example.homenote.controller;

import com.example.homenote.req.UserReq;
import com.example.homenote.res.CommonRes;
import com.example.homenote.res.UserRes;
import com.example.homenote.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    // 引入 service
    @Resource
    private UserService userService;
    // 查询
    @GetMapping("/list")
    public CommonRes list(UserReq req){
        CommonRes<Object> Res = new CommonRes<>();
        List<UserRes> list = userService.list(req);
        Res.setData(list);
        Res.setMsg("请求成功");
        return Res;
    }
    // 保存 & 修改
    @PostMapping("/save")
    public CommonRes save(@RequestBody UserReq req){
        CommonRes Res = new CommonRes<>();
        userService.save(req);
        Res.setMsg("请求成功");
        return Res;
    }
    // 删除     @PathVariable 自动映射 path 上的参数
    @DeleteMapping("/delete/{id}")
    public CommonRes delete(@PathVariable String id){
        CommonRes Res = new CommonRes<>();
        userService.delete(id);
        Res.setMsg("删除成功");
        return Res;
    }
}