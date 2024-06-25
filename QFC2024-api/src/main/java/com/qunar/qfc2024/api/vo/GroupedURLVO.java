package com.qunar.qfc2024.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * URI分组
 * @author zhangge
 * @date 2024/6/10
 */
@Data
@AllArgsConstructor
@Validated
public class GroupedURLVO {

    @ApiModelProperty("类别")
    @NotNull(message = "类别不能为空")
    private String label;

    @ApiModelProperty("url列表")
    private String children;
}
