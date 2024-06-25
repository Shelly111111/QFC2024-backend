package com.qunar.qfc2024.infrastructure.facade.login;

import com.qunar.qfc2024.domain.Facade.login.UserFacade;
import com.qunar.qfc2024.domain.bo.User;
import com.qunar.qfc2024.infrastructure.convert.UserConvert;
import com.qunar.qfc2024.infrastructure.po.UserPO;
import com.qunar.qfc2024.infrastructure.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 登录逻辑实现
 *
 * @author zhangge
 * @date 2024/6/25
 */
@Component
@Slf4j
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConvert userConvert;

    /**
     * 插入信息到数据库中
     *
     * @param user com.qunar.qfc2024.domain.bo.User
     * @return 是否成功
     */
    @Override
    public boolean insertUser(User user) {
        return userRepository.save(userConvert.convert(user));
    }

    /**
     * 通过用户名进行相关用户的查询
     *
     * @param username String
     * @return com.qunar.qfc2024.domain.bo.User
     * @author zhangge
     * @date 2023/4/18
     */
    @Override
    public User findByUsername(String username) {
        UserPO user = userRepository.query()
                .eq(StringUtils.isNotBlank(username), UserPO.USERNAME, username)
                .one();
        return userConvert.convert(user);
    }

    /**
     * 返回所有用户
     *
     * @return 用户列表
     * @author zhangge
     * @date 2023/4/18
     */
    @Override
    public List<User> findAll() {
        List<UserPO> list = userRepository.query().list();
        return userConvert.convertListUser(list);
    }
}
