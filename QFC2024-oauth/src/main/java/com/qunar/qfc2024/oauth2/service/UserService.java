package com.qunar.qfc2024.oauth2.service;


import com.qunar.qfc2024.oauth2.po.UserPO;

import java.util.List;

public interface UserService {


    /**
     * 插入一条信息到数据库中
     * @param user 用户
     * @return
     *
     * @author zhangge
     */
    Integer insertUser(UserPO user);


    /**
     * 通过用户名进行查询对应的用户
     * @param username String
     * @return 用户信息
     *
     * @author zhangg
     * @date 2023/4/18
     */
    UserPO findByUsername(String username);

    /**
     * 返回所有用户
     * @return 用户列表
     *
     * @author zhangg
     * @date 2023/4/18
     */
    List<UserPO> findAll();
}
