package com.qunar.qfc2024.domain.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author zhangge
 * @date 2024/6/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class Prop {

    @ApiModelProperty("自然顺序")
    @NotNull(message = "自然顺序不能为空")
    private Integer natureOrder;

    @ApiModelProperty("索引顺序")
    @NotNull(message = "索引顺序不能为空")
    private Integer indexOrder;

    @ApiModelProperty("文本排序")
    @NotNull(message = "文本排序不能为空")
    private Integer charOrder;

    @ApiModelProperty("文本倒叙")
    @NotNull(message = "文本倒叙不能为空")
    private Integer charOrderDESC;

    @ApiModelProperty("文本内容")
    @NotBlank(message = "文本内容不能为空")
    private String value;

}
