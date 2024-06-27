package com.qunar.qfc2024.web.service.codelines;

import com.qunar.qfc2024.api.vo.CodeLineInfo;
import com.qunar.qfc2024.api.vo.base.Result;
import com.qunar.qfc2024.api.service.codelines.CodeLineService;
import com.qunar.qfc2024.domain.Facade.codelines.CodeLineFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 代码行数统计服务实现类
 *
 * @author zhangge
 * @date 2024/6/25
 */
@Slf4j
@Service
public class CodeLineServiceImpl implements CodeLineService {

    @Autowired
    private CodeLineFacade codeLineFacade;

    @Override
    public Result<CodeLineInfo> getCodeLineInfo(String filename) {
        CodeLineInfo codeLineInfo = new CodeLineInfo();

        Long count = codeLineFacade.getCodeLineCount(filename);
        if (count == -1) {
            return Result.error("统计失败！");
        }
        codeLineInfo.setCodeLine(count);

        return new Result<>(Result.SUCCESS_CODE, codeLineInfo, null);
    }
}
