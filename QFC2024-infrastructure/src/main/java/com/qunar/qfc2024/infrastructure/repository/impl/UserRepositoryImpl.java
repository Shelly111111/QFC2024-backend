package com.qunar.qfc2024.infrastructure.repository.impl;

import com.qunar.qfc2024.infrastructure.po.UserPO;
import com.qunar.qfc2024.infrastructure.mapper.UserMapper;
import com.qunar.qfc2024.infrastructure.repository.UserRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mybatisplus generator
 * @since 2024-06-25
 */
@Service
public class UserRepositoryImpl extends ServiceImpl<UserMapper, UserPO> implements UserRepository {

}
