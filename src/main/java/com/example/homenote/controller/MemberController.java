package com.example.homenote.controller;

import com.example.homenote.req.MemberReq;
import com.example.homenote.res.CommonRes;
import com.example.homenote.res.MemberRes;
import com.example.homenote.service.MemberService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {
    // 引入 service
    @Resource
    private MemberService memberService;
    // 查询列表
    @GetMapping("/list")
    public CommonRes list(MemberReq req){
        CommonRes<Object> Res = new CommonRes<>();
        List<MemberRes> list = memberService.list(req);
        Res.setData(list);
        Res.setMsg("请求成功");
        return Res;
    }
    @PostMapping("/save")
    public CommonRes save(@RequestBody MemberReq req){
        CommonRes Res = new CommonRes<>();
        memberService.save(req);
        Res.setMsg("请求成功");
        return Res;
    }
    //    删除用户信息     @PathVariable 自动映射 path 上的参数
    @DeleteMapping("/delete/{id}")
    public CommonRes delete(@PathVariable String id){
        CommonRes Res = new CommonRes<>();
        memberService.delete(id);
        return Res;
    }
}