package com.qunar.qfc2024.common.utils;

import com.qunar.qfc2024.common.entity.ShellResult;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * Shell命令处理器
 *
 * @author zhangge
 * @date 2024/6/14
 */
public class Shell {

    /**
     * 按选项处理内容并返回
     *
     * @param context 内容
     * @param opts    选项
     * @param args    参数
     * @return 处理后的结果
     * @author zhangge
     * @date 2024/6/14
     */
    public ShellResult<String> cat(ShellResult<String> context, List<String> opts, List<String> args) {
        return context;
    }

    /**
     * 判断内容中是否包含 keyword
     *
     * @param context 内容
     * @param opts    选项
     * @param args    参数
     * @return 处理后的结果
     * @author zhangge
     * @date 2024/6/14
     */
    public ShellResult<String> grep(ShellResult<String> context, List<String> opts, List<String> args) {
        if (Objects.isNull(context) || StringUtils.isBlank(context.getData())) {
            return null;
        }
        if (context.getData().contains(args.get(0))) {
            return context;
        }
        return null;
    }

    /**
     * 用于wc统计
     */
    private Integer wcCount = 0;

    /**
     * 统计内容数
     *
     * @param context 内容
     * @param opts    选项
     * @param args    参数
     * @return 处理后的结果
     * @author zhangge
     * @date 2024/6/14
     */
    public ShellResult<Integer> wc(ShellResult<String> context, List<String> opts, List<String> args) {
        if (Objects.isNull(context)) {
            return new ShellResult<>(wcCount, true);
        }
        wcCount++;
        return new ShellResult<>(wcCount, true);
    }
}
