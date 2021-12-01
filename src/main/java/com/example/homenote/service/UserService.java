package com.example.homenote.service;

import com.example.homenote.domain.User;
import com.example.homenote.domain.UserExample;
import com.example.homenote.mapper.UserMapper;
import com.example.homenote.req.UserReq;
import com.example.homenote.res.UserRes;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
// 创建 EbookService 类
public class UserService {
    @Resource
    // 获取对应 mapper 类（dao类）
    private UserMapper userMapper;

    // 获取列表的方法
    public List<UserRes> list(UserReq req){
        // 创建 example 对象    (new UserExample(); ctrl+alt+v)
        UserExample UserExample = new UserExample();
        // criteria
        UserExample.Criteria criteria = UserExample.createCriteria();
        // 创建模糊查询
        if(!ObjectUtils.isEmpty(req.getUsername())){
            criteria.andUsernameEqualTo(req.getUsername());
        }

        // 分页
        // PageHelper.startPage(1, 2);
        // 查询 返回数据
        List<User> UserList = userMapper.selectByExample(UserExample);

        // 循环返回需要的数据
        ArrayList<UserRes> UserResList = new ArrayList<>();
        for (User User : UserList) {
            // User -> UserRes
            UserRes UserRes = new UserRes();
            BeanUtils.copyProperties(User, UserRes);
            UserResList.add(UserRes);
        }

        // 返回处理后的数据
        return UserResList;
    }

    // 新增用户信息
    public void save(UserReq req){
        User User = new User();
        BeanUtils.copyProperties(req, User);
        if(ObjectUtils.isEmpty(req.getId())){
        // add
            User.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
            userMapper.insert(User);
        }else{
        // update
            userMapper.updateByPrimaryKey(User);
        }

    }
    // 删除
    public void delete(String id){

        userMapper.deleteByPrimaryKey(id);
    }
}
