package com.qunar.qfc2024.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 代码行数信息
 *
 * @author zhangge
 * @date 2024/6/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeLineInfo {

    @ApiModelProperty("文件名")
    private String filename;

    @ApiModelProperty("总行数")
    private Long totalCount;

    @ApiModelProperty("代码行数")
    private Long codeLine;

    @ApiModelProperty("空白行数")
    private Long whiteSpaceLine;

    @ApiModelProperty("注释行数")
    private Long annotationLine;
}
