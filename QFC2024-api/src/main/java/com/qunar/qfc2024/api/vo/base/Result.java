package com.qunar.qfc2024.api.vo.base;

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

    public static final Integer SUCCESS_CODE = 200;

    public static final Integer ERROR_CODE = 404;

    public static Result error(String message) {
        return new Result(ERROR_CODE, null, message);
    }

    public static Result success(String message) {
        return new Result(SUCCESS_CODE, null, message);
    }
}
