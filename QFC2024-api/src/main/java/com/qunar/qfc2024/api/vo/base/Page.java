package com.qunar.qfc2024.api.vo.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zhangge
 * @date 2024/6/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page<T> {

    @ApiModelProperty("总大小")
    private Integer totalSize;

    @ApiModelProperty("当前页")
    private Integer currentPage;

    @ApiModelProperty("页大小")
    private Integer pageSize;

    @ApiModelProperty("数据")
    private List<T> data;

}