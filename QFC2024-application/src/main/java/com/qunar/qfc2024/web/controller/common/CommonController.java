package com.qunar.qfc2024.web.controller.common;

import com.qunar.qfc2024.api.vo.base.Result;
import com.qunar.qfc2024.api.service.common.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 通用controller
 *
 * @author zhangge
 * @date 2024/6/25
 */
@Slf4j
@RestController
@RequestMapping
public class CommonController {

    @Autowired
    private CommonService commonService;

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
        //获取用户身份信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return commonService.saveFile(authentication.getName(), file);
    }
}
