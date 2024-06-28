package com.qunar.qfc2024.web.service.loganalysis;

import com.qunar.qfc2024.api.vo.InterfaceStatVO;
import com.qunar.qfc2024.api.vo.LogAnalysis;
import com.qunar.qfc2024.api.vo.base.Result;
import com.qunar.qfc2024.api.service.loganalysis.LogAnalysisService;
import com.qunar.qfc2024.api.vo.GroupedURLVO;
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
        analysis.setFilename(filename);
        try {
            //统计请求总量
            Integer queryCount = accessFacade.getQueryCount(filename);
            analysis.setQueryCount(queryCount);

            //获取GET、POST请求量
            List<InterfaceStatVO> interfaceStats = logAnalysisMapping.convertMethodStatList(accessFacade.getQueryMethodCount(filename));
            for (InterfaceStatVO stat : interfaceStats) {
                if (QueryMethod.POST.toString().equals(stat.getLabel())) {
                    analysis.setPostCount(stat.getChildren());
                } else if (QueryMethod.GET.toString().equals(stat.getLabel())) {
                    analysis.setGetCount(stat.getChildren());
                }
            }

            //获取频繁接口
            interfaceStats = logAnalysisMapping.convertInterfaceStatList(accessFacade.getFrequentInterface(filename, 10L));
            analysis.setFrequentInterface(interfaceStats);

            //获取URL分组
            List<GroupedURLVO> groupedURLs = logAnalysisMapping.convertGroupedURLList(accessFacade.getGroupedURL(filename));
            analysis.setGroupedURL(groupedURLs);

        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.error("文件分析失败：" + e.getMessage());
        }
        return new Result<>(Result.SUCCESS_CODE, analysis, null);
    }
}
