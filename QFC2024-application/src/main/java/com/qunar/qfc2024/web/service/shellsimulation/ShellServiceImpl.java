package com.qunar.qfc2024.web.service.shellsimulation;

import com.qunar.qfc2024.api.service.shellsimulation.ShellService;
import com.qunar.qfc2024.api.vo.base.Result;
import com.qunar.qfc2024.domain.Facade.shellsimulation.ShellFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Shell模拟服务
 *
 * @author zhangge
 * @date 2024/6/28
 */
@Service
public class ShellServiceImpl implements ShellService {

    @Autowired
    private ShellFacade shellFacade;

    @Override
    public Result<List<String>> run(String command) {
        try {
            List<String> list = shellFacade.run(command);
            return new Result<>(Result.SUCCESS_CODE, list, null);
        } catch (Exception e) {
            return Result.error("执行失败：" + e.getMessage());
        }
    }
}
