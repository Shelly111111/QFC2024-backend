package com.qunar.qfc2024.oauth.convert;

import com.qunar.qfc2024.api.vo.UserVO;
import com.qunar.qfc2024.domain.bo.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LoginMapping {

    UserVO convert(User source);

    User convert(UserVO source);

    List<UserVO> convertListUserVO(List<User> source);
}
