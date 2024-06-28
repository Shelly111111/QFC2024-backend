package com.qunar.qfc2024.domain.Facade.shellsimulation;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

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
     * @return 返回数据
     * @author zhangge
     * @date 2024/6/14
     */
    List<String> run(String command) throws FileNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException;
}
