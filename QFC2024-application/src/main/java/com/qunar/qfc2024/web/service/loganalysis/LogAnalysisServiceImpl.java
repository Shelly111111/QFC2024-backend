package com.qunar.qfc2024.web.service.loganalysis;

import com.qunar.qfc2024.api.dto.GroupedURLDTO;
import com.qunar.qfc2024.api.dto.InterfaceStatDTO;
import com.qunar.qfc2024.api.response.Result;
import com.qunar.qfc2024.api.service.loganalysis.LogAnalysisService;
import com.qunar.qfc2024.domain.Facade.loganalysis.AccessFacade;
import com.qunar.qfc2024.web.convert.loganalysis.LogAnalysisMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    public Result<Integer> getQueryCount(String filename) {
        Integer queryCount = accessFacade.getQueryCount(filename);

        if (Objects.isNull(queryCount)) {
            return Result.error("获取请求总量失败！");
        }

        return new Result<>(Result.SUCCESS_CODE, queryCount, null);
    }

    @Override
    public Result<List<InterfaceStatDTO>> getFrequentInterface(String filename, Long limitCount) {

        List<InterfaceStatDTO> interfaceStats = logAnalysisMapping.convertInterfaceStatList(accessFacade.getFrequentInterface(filename, limitCount));
        if (Objects.isNull(interfaceStats)) {
            return Result.error("获取频繁接口列表失败！");
        }
        return new Result<>(Result.SUCCESS_CODE, interfaceStats, null);
    }

    @Override
    public Result<List<InterfaceStatDTO>> getQueryMethodCount(String filename) {

        List<InterfaceStatDTO> interfaceStats = logAnalysisMapping.convertMethodStatList(accessFacade.getQueryMethodCount(filename));
        if (Objects.isNull(interfaceStats)) {
            return Result.error("获取请求方式请求列表失败！");
        }
        return new Result<>(Result.SUCCESS_CODE, interfaceStats, null);
    }

    @Override
    public Result<List<GroupedURLDTO>> getGroupedURL(String filename) {
        List<GroupedURLDTO> groupedURLs = logAnalysisMapping.convertGroupedURLList(accessFacade.getGroupedURL(filename));
        if(Objects.isNull(groupedURLs)){
            return Result.error("获取URL分类列表失败！");
        }
        return new Result<>(Result.SUCCESS_CODE, groupedURLs, null);
    }

    @Override
    public Result saveFile(MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error("文件上传失败，请重新上传！");
        }
        String filename = file.getOriginalFilename();
        //检测文件类型
        if (!(filename.endsWith(".txt") || filename.endsWith(".text") || filename.endsWith(".log"))) {
            return Result.error("只能上传文本文件或log日志文件！");
        }
        //存储文件到本地
        if (!accessFacade.saveFile(file)) {
            return Result.error("文件上传失败，请重新上传！");
        }

        return Result.success("文件上传成功！");
    }
}
