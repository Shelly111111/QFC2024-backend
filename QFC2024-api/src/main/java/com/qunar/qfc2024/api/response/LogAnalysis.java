package com.qunar.qfc2024.api.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 日志分析返回结果
 *
 * @author zhangge
 * @date 2024/6/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogAnalysis {

    @ApiModelProperty("请求总量")
    private Integer queryCount;

    @ApiModelProperty("GET请求量")
    private Integer getCount;

    @ApiModelProperty("POST请求量")
    private Integer postCount;
}
