package com.qunar.qfc2024.web.service;

import com.qunar.qfc2024.api.response.Result;
import com.qunar.qfc2024.api.service.loganalysis.LogAnalysisService;
import com.qunar.qfc2024.domain.Facade.loganalysis.AccessFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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



    @Override
    public Result<Integer> getQueryCount(String filename) {
        Integer queryCount = accessFacade.getQueryCount(filename);

        if (Objects.isNull(queryCount)) {
            return Result.error("获取请求总量失败！");
        }

        return new Result<>(Result.SUCCESS_CODE, queryCount, null);
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
