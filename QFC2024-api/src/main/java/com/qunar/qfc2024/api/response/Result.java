package com.qunar.qfc2024.api.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 前端展示结果
 *
 * @param <T>
 * @author zhhangge
 * @date 2024/6/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    private Integer code;

    private T data;

    private String message;

    public static Result error(String message) {
        return new Result(500, null, message);
    }

    public static Result success(String message) {
        return new Result(200, null, message);
    }

}
