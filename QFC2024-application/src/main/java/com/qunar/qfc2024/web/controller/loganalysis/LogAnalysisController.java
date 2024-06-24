package com.qunar.qfc2024.web.controller.loganalysis;

import com.qunar.qfc2024.api.dto.GroupedURLDTO;
import com.qunar.qfc2024.api.dto.InterfaceStatDTO;
import com.qunar.qfc2024.api.response.LogAnalysis;
import com.qunar.qfc2024.api.response.Result;
import com.qunar.qfc2024.api.service.loganalysis.LogAnalysisService;
import com.qunar.qfc2024.common.enumeration.QueryMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


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
        LogAnalysis analysis = new LogAnalysis();

        //获取请求总量
        Result<Integer> queryCount = logAnalysisService.getQueryCount(filename);
        if (Result.SUCCESS_CODE.equals(queryCount.getCode())) {
            analysis.setQueryCount(queryCount.getData());
        } else {
            return Result.error(queryCount.getMessage());
        }

        //获取GET、POST请求量
        Result<List<InterfaceStatDTO>> methodCount = logAnalysisService.getQueryMethodCount(filename);
        if (Result.SUCCESS_CODE.equals(methodCount.getCode())) {
            for (InterfaceStatDTO stat : methodCount.getData()) {
                if (QueryMethod.POST.toString().equals(stat.getLabel())) {
                    analysis.setPostCount(stat.getChildren());
                } else if (QueryMethod.GET.toString().equals(stat.getLabel())) {
                    analysis.setGetCount(stat.getChildren());
                }
            }
        } else {
            return Result.error(methodCount.getMessage());
        }

        //获取频繁接口
        Result<List<InterfaceStatDTO>> frequentInterface = logAnalysisService.getFrequentInterface(filename, 10L);
        if (Result.SUCCESS_CODE.equals(frequentInterface.getCode())) {
            analysis.setFrequentInterface(frequentInterface.getData());
        } else {
            return Result.error(frequentInterface.getMessage());
        }

        //获取URL分组
        Result<List<GroupedURLDTO>> groupedURL = logAnalysisService.getGroupedURL(filename);
        if (Result.SUCCESS_CODE.equals(groupedURL.getCode())) {
            analysis.setGroupedURL(groupedURL.getData());
        } else {
            return Result.error(groupedURL.getMessage());
        }

//        System.out.println(analysis);
        return new Result<>(Result.SUCCESS_CODE, analysis, null);
    }
}
