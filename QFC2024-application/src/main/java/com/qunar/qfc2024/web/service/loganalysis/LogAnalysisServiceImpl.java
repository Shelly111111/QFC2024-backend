package com.qunar.qfc2024.web.service.loganalysis;

import com.qunar.qfc2024.api.response.LogAnalysis;
import com.qunar.qfc2024.api.response.Result;
import com.qunar.qfc2024.api.service.loganalysis.LogAnalysisService;
import com.qunar.qfc2024.api.vo.GroupedURLVO;
import com.qunar.qfc2024.api.vo.InterfaceStatVO;
import com.qunar.qfc2024.common.enumeration.QueryMethod;
import com.qunar.qfc2024.domain.Facade.loganalysis.AccessFacade;
import com.qunar.qfc2024.web.convert.loganalysis.LogAnalysisMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 日志分析服务
 *
 * @author zhangge
 * @date 2024/6/23
 */
@Slf4j
@Service
public class LogAnalysisServiceImpl implements LogAnalysisService {

    @Autowired
    private AccessFacade accessFacade;

    @Autowired
    private LogAnalysisMapping logAnalysisMapping;

    @Override
    public Result<LogAnalysis> analysis(String filename) {
        LogAnalysis analysis = new LogAnalysis();

        //统计请求总量
        Integer queryCount = accessFacade.getQueryCount(filename);
        if (Objects.isNull(queryCount)) {
            return Result.error("获取请求总量失败！");
        }
        analysis.setQueryCount(queryCount);

        //获取GET、POST请求量
        List<InterfaceStatVO> interfaceStats = logAnalysisMapping.convertMethodStatList(accessFacade.getQueryMethodCount(filename));
        if (Objects.isNull(interfaceStats)) {
            return Result.error("获取请求方式请求列表失败！");
        }
        for (InterfaceStatVO stat : interfaceStats) {
            if (QueryMethod.POST.toString().equals(stat.getLabel())) {
                analysis.setPostCount(stat.getChildren());
            } else if (QueryMethod.GET.toString().equals(stat.getLabel())) {
                analysis.setGetCount(stat.getChildren());
            }
        }

        //获取频繁接口
        interfaceStats = logAnalysisMapping.convertInterfaceStatList(accessFacade.getFrequentInterface(filename, 10L));
        if (Objects.isNull(interfaceStats)) {
            return Result.error("获取频繁接口列表失败！");
        }
        analysis.setFrequentInterface(interfaceStats);

        //获取URL分组
        List<GroupedURLVO> groupedURLs = logAnalysisMapping.convertGroupedURLList(accessFacade.getGroupedURL(filename));
        if (Objects.isNull(groupedURLs)) {
            return Result.error("获取URL分类列表失败！");
        }
        analysis.setGroupedURL(groupedURLs);

        return new Result<>(Result.SUCCESS_CODE, analysis, null);
    }
}
