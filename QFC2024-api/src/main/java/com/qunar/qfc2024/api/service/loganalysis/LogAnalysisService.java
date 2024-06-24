package com.qunar.qfc2024.api.service.loganalysis;

import com.qunar.qfc2024.api.response.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * 日志分析服务
 *
 * @author zhangge
 * @date 2024/6/23
 */
public interface LogAnalysisService {

    /**
     * 获取请求总量
     *
     * @return 请求总量
     * @author zhangge
     * @date 2024/6/23
     */
    Result<Integer> getQueryCount();;

    /**
     * 保存文件
     *
     * @param file 文件
     * @return 是否成功
     * @author zhangge
     * @date 2024/6/24
     */
    Result saveFile(MultipartFile file);
}
