package com.qunar.qfc2024.common.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 命令返回结果
 *
 * @param <T> 数据格式
 * @author zhangge
 * @date 2024/6/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShellResult<T> {

    @ApiModelProperty("结果数据")
    private T data;

    @ApiModelProperty("是否最后输出")
    private boolean print;
}
