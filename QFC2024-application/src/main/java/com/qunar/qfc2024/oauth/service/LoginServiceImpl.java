package com.qunar.qfc2024.oauth.service;

import com.qunar.qfc2024.api.response.Result;
import com.qunar.qfc2024.api.service.login.LoginService;
import com.qunar.qfc2024.api.vo.UserVO;
import com.qunar.qfc2024.domain.Facade.login.UserFacade;
import com.qunar.qfc2024.oauth.convert.LoginMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 登录服务实现
 *
 * @author zhangge
 * @date 2024/6/25
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserFacade userFacade;
    @Autowired
    private LoginMapping loginMapping;

    @Override
    public Result insertUser(UserVO user) {
        boolean success = userFacade.insertUser(loginMapping.convert(user));
        if (success) {
            return Result.success("插入用户成功！");
        }
        return Result.error("插入用户失败！");
    }

    @Override
    public UserVO findByUsername(String username) {
        return loginMapping.convert(userFacade.findByUsername(username));
    }

    @Override
    public Result<List<UserVO>> findAll() {
        List<UserVO> users = loginMapping.convertListUserVO(userFacade.findAll());
        if (Objects.isNull(users)) {
            return Result.error("获取所有用户失败！");
        }
        return new Result<>(Result.SUCCESS_CODE, users, null);
    }
}