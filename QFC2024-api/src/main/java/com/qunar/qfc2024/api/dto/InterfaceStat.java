package com.qunar.qfc2024.api.dto;

import com.qunar.qfc2024.common.enumeration.QueryMethod;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;

/**
 * 请求接口统计
 * @author zhangge
 * @date 2024/6/10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class InterfaceStat {

    @ApiModelProperty("请求方式")
    private QueryMethod method;

    @ApiModelProperty("请求地址")
    private String url;

    @ApiModelProperty("请求数量")
    @Min(value = 0, message = "请求数量应大于等于0")
    private Long queryCount;
}
