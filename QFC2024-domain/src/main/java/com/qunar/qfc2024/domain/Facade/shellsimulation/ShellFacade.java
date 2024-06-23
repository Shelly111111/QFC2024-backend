package com.qunar.qfc2024.domain.Facade.shellsimulation;

/**
 * Shell模拟器
 *
 * @author zhangge
 * @date 2024/6/14
 */
public interface ShellFacade {

    /**
     * 执行command命令
     *
     * @param command shell命令
     * @author zhangge
     * @date 2024/6/14
     */
    void run(String command);
}
