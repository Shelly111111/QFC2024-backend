package com.qunar.qfc2024.api.service.codelines;

import com.qunar.qfc2024.api.response.CodeLineInfo;
import com.qunar.qfc2024.api.response.Result;

/**
 * 代码行数统计服务
 *
 * @author zhangge
 * @date 2024/6/25
 */
public interface CodeLineService {

    /**
     * 获取代码行数信息
     *
     * @param filename 文件名
     * @return 有效代码行数
     * @author zhangge
     * @date 2024/6/25
     */
    Result<CodeLineInfo> getCodeLineInfo(String filename);
}
