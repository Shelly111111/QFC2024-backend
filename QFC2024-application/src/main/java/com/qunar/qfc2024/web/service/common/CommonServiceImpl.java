package com.qunar.qfc2024.web.service.common;

import com.qunar.qfc2024.api.response.Result;
import com.qunar.qfc2024.api.service.common.CommonService;
import com.qunar.qfc2024.domain.Facade.common.CommonFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 通用服务实现类
 *
 * @author zhangge
 * @date 2024/6/25
 */
@Slf4j
@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonFacade commonFacade;

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
        if (!commonFacade.saveFile(file)) {
            return Result.error("文件上传失败，请重新上传！");
        }

        return Result.success("文件上传成功！");
    }
}
