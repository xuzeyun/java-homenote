package com.example.homenote.service;

import com.example.homenote.domain.User;
import com.example.homenote.domain.UserExample;
import com.example.homenote.exception.BusinessException;
import com.example.homenote.exception.BusinessExceptionCode;
import com.example.homenote.mapper.UserMapper;
import com.example.homenote.req.UserLoginReq;
import com.example.homenote.req.UserReq;
import com.example.homenote.res.UserLoginRes;
import com.example.homenote.res.UserRes;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
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
            // add 新增
            // 查看用户名是否存在
            User userDB = selectByUsername(req.getUsername());
            if(ObjectUtils.isEmpty(userDB)){
                User.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
                userMapper.insert(User);
            } else {
                // 用户名已存在
                throw new BusinessException(BusinessExceptionCode.USER_USERNAME_EXIST);
            }
        }else{
            // update 修改
            userMapper.updateByPrimaryKey(User);
        }
    }
    // 删除
    public void delete(String id){
        userMapper.deleteByPrimaryKey(id);
    }

    /* 根据用户名查询 */
    public User selectByUsername(String username) {
        UserExample UserExample = new UserExample();
//        创建 Criteria 对象
        UserExample.Criteria criteria = UserExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> userList = userMapper.selectByExample(UserExample);
        if(CollectionUtils.isEmpty(userList)){
            return null;
        } else {
            return userList.get(0);
        }
    }

    /* 登录 */
    public UserLoginRes login(UserLoginReq req) {
        User userDb = selectByUsername(req.getUsername());
        if(ObjectUtils.isEmpty(userDb)) {
            // 用户名不存在
            // 打印日志 抛出异常
            throw new BusinessException(BusinessExceptionCode.LOGIN_USER_EXIST);
        } else {
            if(userDb.getPassword().equals(req.getPassword())){
                /* 登录成功 */
//                UserLoginRes userRes = CopyUtil.copy(userDb, UserLoginRes.class);
                UserLoginRes res = new UserLoginRes();
//               把 a 和 b 中相同属性进行赋值，a 赋值给 b
                BeanUtils.copyProperties(userDb, res);
                return res;
            } else {
                /* 密码不对 */
                // 打印日志 抛出异常
                throw new BusinessException(BusinessExceptionCode.LOGIN_USER_EXIST);
            }
        }
    }
}
