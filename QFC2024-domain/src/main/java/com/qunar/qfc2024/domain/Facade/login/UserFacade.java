package com.qunar.qfc2024.domain.Facade.login;


import com.qunar.qfc2024.domain.bo.User;

import java.util.List;

/**
 * 登录逻辑
 *
 * @author zhangge
 * @date 2024/6/25
 */
public interface UserFacade {


    /**
     * 插入一条信息到数据库中
     * @param user 用户
     * @return
     *
     * @author zhangge
     */
    boolean insertUser(User user);


    /**
     * 通过用户名进行查询对应的用户
     * @param username 用户名
     * @return 用户
     *
     * @author zhangge
     * @date 2023/4/18
     */
    User findByUsername(String username);

    /**
     * 返回所有用户
     * @return 用户列表
     *
     * @author zhangge
     * @date 2023/4/18
     */
    List<User> findAll();
}
