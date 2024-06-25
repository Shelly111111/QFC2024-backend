package com.shanke.oauth2.res;

import lombok.Data;

/**
 *
 */
@Data
public class Result {

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 消息
     */
    private String message;
    /**
     * 数据
     */
    private Object data;

    /**
     * 未找到
     */
    public static final int NOT_FOUND = 461;
    /**
     * 匹配错误
     */
    public static final int ERROR = 462;
    /**
     * 成功
     */
    public static final int SUCCESS = 200;

    /***
     * 警告
     */
    public static final int WARN = 300;


    /***
     *
     *
     * @param code
     * @param msg
     * @author: 漫舞枪神
     * @date: 2023/4/13
     */
    public Result(Integer code, String msg) {
        this.code = code;
        this.message = msg;
    }
}
