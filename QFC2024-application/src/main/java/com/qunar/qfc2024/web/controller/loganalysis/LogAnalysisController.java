package com.qunar.qfc2024.web.controller.loganalysis;

import com.qunar.qfc2024.api.vo.LogAnalysis;
import com.qunar.qfc2024.api.vo.base.Result;
import com.qunar.qfc2024.api.service.loganalysis.LogAnalysisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 日志分析服务
 *
 * @author zhangge
 * @date 2024/6/23
 */
@Slf4j
@RestController
@RequestMapping("/log_analysis")
public class LogAnalysisController {

    @Autowired
    private LogAnalysisService logAnalysisService;

    /**
     * 日志分析
     *
     * @param filename 日志文件名
     * @return 处理结果
     * @author zhangge
     * @date 2024/6/24
     */
    @PostMapping("/analysis")
    public Result<LogAnalysis> logAnalysis(@RequestParam("file") String filename) {
        if (StringUtils.isBlank(filename)) {
            return Result.error("请传入要进行分析的日志文件名！");
        }
        if (!(filename.endsWith(".txt") || filename.endsWith(".text") || filename.endsWith(".log"))) {
            return Result.error("只能分析文本文件或log日志文件！");
        }

        return logAnalysisService.analysis(filename);
    }
}
