package com.qunar.qfc2024.domain.Facade.codelines;

import java.io.IOException;

/**
 * 有效代码行数统计
 *
 * @author zhangge
 * @date 2024/6/11
 */
public interface CodeLineFacade {

    /**
     * 获取有效代码行数
     *
     * @param filename 文件名
     * @return 有效代码行数
     * @author zhangge
     * @date 2024/6/11
     */
    Long getCodeLineCount(String filename) throws IOException;
}
