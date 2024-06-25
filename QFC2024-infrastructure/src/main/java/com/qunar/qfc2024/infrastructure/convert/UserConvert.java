package com.qunar.qfc2024.infrastructure.convert;

import com.qunar.qfc2024.domain.bo.User;
import com.qunar.qfc2024.infrastructure.po.UserPO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author zhangge
 * @date 2024/6/25
 */
@Mapper(componentModel = "spring")
public interface UserConvert {

    User convert(UserPO source);

    UserPO convert(User source);

    List<User> convertListUser(List<UserPO> source);
}
