package com.qunar.qfc2024.web.controller.shellsimulation;


import com.qunar.qfc2024.api.service.shellsimulation.ShellService;
import com.qunar.qfc2024.api.vo.base.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Shell模拟服务
 *
 * @author zhangge
 * @date 2024/6/28
 */
@Slf4j
@RestController
@RequestMapping("/shell")
public class ShellController {

    @Autowired
    private ShellService shellService;

    /**
     * 执行command命令
     *
     * @param command shell命令
     * @return 返回数据
     * @author zhangge
     * @date 2024/6/28
     */
    @PostMapping("/execute")
    public Result<List<String>> execute(@RequestParam("cmd") String command) {
        if (StringUtils.isBlank(command)) {
            return Result.error("shell命令不能为空");
        }
        return shellService.run(command);
    }
}
