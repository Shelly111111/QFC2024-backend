package com.qunar.qfc2024.api.service.login;


import com.qunar.qfc2024.api.response.Result;
import com.qunar.qfc2024.api.vo.UserVO;

import java.util.List;

/**
 * 登录服务
 *
 * @author zhangge
 * @date 2024/6/25
 */
public interface LoginService {


    /**
     * 插入一条信息到数据库中
     *
     * @param user 用户
     * @return
     * @author zhangge
     */
    Result insertUser(UserVO user);


    /**
     * 通过用户名进行查询对应的用户
     *
     * @param username 用户名
     * @return 用户
     * @author zhangge
     * @date 2023/4/18
     */
    UserVO findByUsername(String username);

    /**
     * 返回所有用户
     *
     * @return 用户列表
     * @author zhangge
     * @date 2023/4/18
     */
    Result<List<UserVO>> findAll();
}
