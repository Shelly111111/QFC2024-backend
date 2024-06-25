package com.qunar.qfc2024.api.service.loganalysis;

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
     * 获取请求总量
     *
     * @param filename 文件名
     * @return 请求总量
     * @author zhangge
     * @date 2024/6/23
     */
    Result<Integer> getQueryCount(String filename);

    /**
     * 获取请求最频繁的接口列表
     *
     * @param limitCount 接口数
     * @param filename   文件名
     * @return 最频繁的接口列表
     * @author zhangge
     * @date 2024/6/24
     */
    Result<List<InterfaceStatVO>> getFrequentInterface(String filename, Long limitCount);

    /**
     * 获取各个请求方式的请求量
     *
     * @param filename 文件名
     * @return 各请求方式的请求量
     * @author zhangge
     * @date 2024/6/24
     */
    Result<List<InterfaceStatVO>> getQueryMethodCount(String filename);

    /**
     * URL 格式均为 /AAA/BBB 或者 /AAA/BBB/CCC 格式，按 AAA 分类
     *
     * @param filename 文件名
     * @return 分类后的url
     * @author zhangge
     * @date 2024/6/24
     */
    Result<List<GroupedURLVO>> getGroupedURL(String filename);

}
