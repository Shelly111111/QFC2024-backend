package com.qunar.qfc2024.web.service.common;

import com.qunar.qfc2024.api.vo.base.Result;
import com.qunar.qfc2024.api.service.common.CommonService;
import com.qunar.qfc2024.domain.Facade.common.CommonFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    public Result saveFile(String username, MultipartFile file) {
        try {
            //存储文件到本地
            if (!commonFacade.saveFile(username, file)) {
                return Result.error("文件上传失败，请重新上传！");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.error("文件上传失败：" + e.getMessage());
        }

        return Result.success("文件上传成功！");
    }

    @Override
    public Result<List<String>> getFileList() {
        List<String> fileList = commonFacade.getFileList();
        return new Result<>(Result.SUCCESS_CODE, fileList, null);
    }
}
