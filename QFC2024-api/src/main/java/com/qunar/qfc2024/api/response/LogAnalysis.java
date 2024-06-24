package com.qunar.qfc2024.api.response;

import com.qunar.qfc2024.api.dto.GroupedURLDTO;
import com.qunar.qfc2024.api.dto.InterfaceStatDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private String getCount;

    @ApiModelProperty("POST请求量")
    private String postCount;

    @ApiModelProperty("最频繁请求接口")
    private List<InterfaceStatDTO> frequentInterface;

    @ApiModelProperty("URL分组")
    private List<GroupedURLDTO> groupedURL;
}
