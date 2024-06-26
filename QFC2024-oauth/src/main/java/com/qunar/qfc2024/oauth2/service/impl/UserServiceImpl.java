package com.qunar.qfc2024.oauth2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.qunar.qfc2024.oauth2.mapper.UserMapper;
import com.qunar.qfc2024.oauth2.po.UserPO;
import com.qunar.qfc2024.oauth2.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 插入信息到数据库中
     *
     * @param user 用户
     * @return
     */
    @Override
    public Integer insertUser(UserPO user) {
        return userMapper.insert(user);
    }

    /**
     * 通过用户名进行相关用户的查询
     *
     * @param username String
     * @return 用户信息
     *
     * @author zhangge
     * @date 2023/4/18
     */
    @Override
    public UserPO findByUsername(String username) {
        LambdaQueryWrapper<UserPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(username), UserPO::getUsername, username);
        return userMapper.selectOne(queryWrapper);
    }

    /**
     * 返回所有用户
     * @return 用户列表
     *
     * @author zhangge
     * @date 2023/4/18
     */
    @Override
    public List<UserPO> findAll() {
        Map<String, Object> map = new HashMap<>();
        List<UserPO> users = userMapper.selectByMap(map);
        return users;
    }
}