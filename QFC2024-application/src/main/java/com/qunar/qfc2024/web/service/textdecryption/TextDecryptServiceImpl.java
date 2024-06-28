package com.qunar.qfc2024.web.service.textdecryption;

import com.qunar.qfc2024.api.service.textdecryption.TextDecryptService;
import com.qunar.qfc2024.api.vo.FileLineVO;
import com.qunar.qfc2024.api.vo.base.Page;
import com.qunar.qfc2024.api.vo.base.Result;
import com.qunar.qfc2024.domain.Facade.textdecryption.TextDecryptFacade;
import com.qunar.qfc2024.web.convert.textdecryption.TextDecryptMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

/**
 * 文本解密服务实现类
 *
 * @author zhangge
 * @date 2024/6/27
 */
@Service
@Slf4j
public class TextDecryptServiceImpl implements TextDecryptService {

    @Autowired
    private TextDecryptFacade textDecryptFacade;

    @Autowired
    private TextDecryptMapping textDecryptMapping;

    @Override
    public Result<Page<FileLineVO>> textDecrypt(String filename, Integer pageNum, Integer pageSize) {
        try {
            Page page = textDecryptMapping.convert(textDecryptFacade.textDecrypt(filename, pageNum, pageSize));
            if (Objects.isNull(page)) {
                return Result.error("文件解密失败");
            }
            return new Result<>(Result.SUCCESS_CODE, page, null);
        } catch (IOException e) {
            return Result.error("文件解密失败：" + e.getMessage());
        }
    }
}
