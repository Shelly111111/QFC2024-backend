package com.qunar.qfc2024.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 请求接口统计
 *
 * @author zhangge
 * @date 2024/6/10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterfaceStatVO {

    @ApiModelProperty("请求地址/方式")
    private String label;

    @ApiModelProperty("请求数量")
    private String children;
}
