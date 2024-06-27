package com.qunar.qfc2024.web.controller.codelines;

import com.qunar.qfc2024.api.vo.CodeLineInfo;
import com.qunar.qfc2024.api.vo.base.Result;
import com.qunar.qfc2024.api.service.codelines.CodeLineService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 代码行数统计
 *
 * @author zhangge
 * @date 2024/6/25
 */
@Slf4j
@RestController
@RequestMapping("/code_lines")
public class CodeLinesController {

    @Autowired
    private CodeLineService codeLineService;


    /**
     * 统计行数
     *
     * @param filename 文件名
     * @return 统计结果
     * @author zhangge
     * @date 2024/6/25
     */
    @PostMapping("/statistics")
    public Result<CodeLineInfo> getCodeLineInfo(@RequestParam("file") String filename) {
        if (StringUtils.isBlank(filename)) {
            return Result.error("请传入要进行分析的日志文件名！");
        }
        if (!filename.endsWith(".java")) {
            return Result.error("只能分析java文件！");
        }
        return codeLineService.getCodeLineInfo(filename);
    }
}
