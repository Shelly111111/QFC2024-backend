package com.qunar.qfc2024.web.loganalysis.controller;

import com.qunar.qfc2024.api.response.Result;
import com.qunar.qfc2024.api.service.loganalysis.LogAnalysisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


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
     * 接收用户上传的文件
     *
     * @param file 用户上传的文件
     * @return 是否接收成功
     * @author zhangge
     * @date 2024/6/24
     */
    @PostMapping("/upload")
    public Result getFile(@RequestParam("file") MultipartFile file) {
        return logAnalysisService.saveFile(file);
    }
}
