package com.qunar.qfc2024.domain.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户信息
 *
 * @author zhangge
 * @date 2024/6/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;
}
