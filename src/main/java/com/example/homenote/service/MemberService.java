package com.example.homenote.service;

import com.example.homenote.domain.Member;
import com.example.homenote.domain.MemberExample;
import com.example.homenote.mapper.MemberMapper;
import com.example.homenote.req.MemberReq;
import com.example.homenote.res.MemberRes;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
// 创建 EbookService 类
public class MemberService {
    @Resource
    // 获取对应 mapper 类（dao类）
    private MemberMapper memberMapper;

    // 获取列表的方法
    public List<MemberRes> list(MemberReq req){
        // 创建 example 对象    (new MemberExample(); ctrl+alt+v)
        MemberExample MemberExample = new MemberExample();
        // criteria
        MemberExample.Criteria criteria = MemberExample.createCriteria();
        // 创建模糊查询
        criteria.andNameLike("%" + req.getName() + "%");

        // 查询 返回数据
        List<Member> MemberList = memberMapper.selectByExample(MemberExample);

        // 循环返回需要的数据
        ArrayList<MemberRes> MemberResList = new ArrayList<>();
        for (Member Member : MemberList) {
            // Member -> MemberRes
            MemberRes MemberRes = new MemberRes();
            BeanUtils.copyProperties(Member, MemberRes);
            MemberResList.add(MemberRes);
        }

        // 返回处理后的数据
        return MemberResList;
    }

    // 新增用户信息
    public void save(MemberReq req){
        Member Member = new Member();
        BeanUtils.copyProperties(req, Member);
        if(ObjectUtils.isEmpty(req.getId())){
//            add
            Member.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
            memberMapper.insert(Member);
        }else{
//            update
            memberMapper.updateByPrimaryKey(Member);
        }

    }
    //    删除
    public void delete(String id){

        memberMapper.deleteByPrimaryKey(id);
    }
}
