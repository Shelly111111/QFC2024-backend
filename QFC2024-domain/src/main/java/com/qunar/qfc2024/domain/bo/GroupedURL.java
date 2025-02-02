package com.qunar.qfc2024.domain.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * URI分组
 *
 * @author zhangge
 * @date 2024/6/10
 */
@Data
@AllArgsConstructor
@Validated
public class GroupedURL {

    @ApiModelProperty("类别")
    @NotNull(message = "类别不能为空")
    private String category;

    @ApiModelProperty("url列表")
    private List<String> urls;
}
