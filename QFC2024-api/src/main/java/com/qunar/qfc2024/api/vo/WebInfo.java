package com.qunar.qfc2024.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangge
 * @date 2021/6/21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebInfo {

    @ApiModelProperty("网页url")
    private String url;

    @ApiModelProperty("总字符数(包括标点符号)")
    private Long characterCount;

    @ApiModelProperty("总汉字数")
    private Long chineseCharacterCount;

    @ApiModelProperty("英文字符数")
    private Long englishCharacterCount;

    @ApiModelProperty("标点符号数")
    private Long punctuationMarksCount;
}
