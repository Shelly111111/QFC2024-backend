package com.qunar.qfc2024.api.service.shellsimulation;

import com.qunar.qfc2024.api.vo.base.Result;

import java.util.List;

/**
 * Shell模拟服务
 *
 * @author zhangge
 * @date 2024/6/28
 */
public interface ShellService {

    /**
     * 执行command命令
     *
     * @param command shell命令
     * @return 返回数据
     * @author zhangge
     * @date 2024/6/28
     */
    Result<List<String>> run(String command);
}
