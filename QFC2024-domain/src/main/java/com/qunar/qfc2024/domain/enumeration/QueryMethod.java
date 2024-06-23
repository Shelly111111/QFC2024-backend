package com.qunar.qfc2024.domain.enumeration;

import lombok.extern.slf4j.Slf4j;

/**
 * 请求方式
 *
 * @author zhangge
 * @date 2024/6/9
 */
@Slf4j
public enum QueryMethod {
    /**
     * GET请求
     */
    GET,
    /**
     * POST请求
     */
    POST,
    /**
     * PUT请求
     */
    PUT,
    /**
     * DELETE请求
     */
    DELETE;

    public static QueryMethod getMethod(String method) {
        try {
            return QueryMethod.valueOf(method);
        } catch (Exception e) {
            log.error(e.getMessage() + " method:" + method);
        }
        return null;
    }
}
