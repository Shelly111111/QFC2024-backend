package com.qunar.qfc2024.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文件行信息
 *
 * @author zhangge
 * @date 2024/6/27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileLineVO {

    @ApiModelProperty("文件名")
    private String filename;

    @ApiModelProperty(value = "行内容")
    private String line;

    @ApiModelProperty(value = "解密后的行内容")
    private String decryptLine;

    @ApiModelProperty(value = "行下标")
    private Long lineIndex;
}
