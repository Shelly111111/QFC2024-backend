package com.qunar.qfc2024.api.service.loganalysis;

import com.qunar.qfc2024.api.response.LogAnalysis;
import com.qunar.qfc2024.api.vo.GroupedURLVO;
import com.qunar.qfc2024.api.vo.InterfaceStatVO;
import com.qunar.qfc2024.api.response.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 日志分析服务
 *
 * @author zhangge
 * @date 2024/6/23
 */
public interface LogAnalysisService {

    /**
     * 日志分析
     *
     * @param filename 文件名
     * @return 分析结果
     */
    Result<LogAnalysis> analysis(String filename);
}
