package com.qunar.qfc2024.web.controller.textdecryption;

import com.qunar.qfc2024.api.service.textdecryption.TextDecryptService;
import com.qunar.qfc2024.api.vo.FileLineVO;
import com.qunar.qfc2024.api.vo.LogAnalysis;
import com.qunar.qfc2024.api.vo.base.Page;
import com.qunar.qfc2024.api.vo.base.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * 文件解密服务
 *
 * @author zhangge
 * @date 2024/6/27
 */
@Slf4j
@RestController
@RequestMapping("/text_decrypt")
public class TextDecryptController {

    @Autowired
    private TextDecryptService textDecryptService;

    /**
     * 文件解密
     *
     * @param filename 日志文件名
     * @return 处理结果
     * @author zhangge
     * @date 2024/6/24
     */
    @PostMapping("/decrypt")
    public Result<Page<FileLineVO>> decrypt(@RequestParam("file") String filename,
                                            @RequestParam("pageNumber") Integer pageNumber,
                                            @RequestParam("pageSize") Integer pageSize) {
        if (StringUtils.isBlank(filename)) {
            return Result.error("请传入要进行解密的文件名！");
        }
        if (!(filename.endsWith(".txt") || filename.endsWith(".text"))) {
            return Result.error("只能分析文本文件！");
        }

        pageNumber = Objects.isNull(pageNumber) ? 0 : pageNumber;
        pageSize = Objects.isNull(pageSize) ? 10 : pageSize;
        return textDecryptService.textDecrypt(filename, pageNumber, pageSize);
    }
}
