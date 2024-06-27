package com.qunar.qfc2024.domain.Facade.textdecryption;


import com.qunar.qfc2024.domain.bo.FileLine;
import com.qunar.qfc2024.domain.bo.base.BasePage;

import java.io.IOException;

/**
 * 文本解密
 *
 * @author zhangge
 * @date 2024/6/12
 */
public interface TextDecryptFacade {

    /**
     * 文本解码
     *
     * @param filename 文件名
     * @param pageNum  当前页
     * @param pageSize 每页大小
     * @return 当前页文本
     * @author zhangge
     * @date 2024/6/12
     */
    BasePage<FileLine> textDecrypt(String filename, Integer pageNum, Integer pageSize) throws IOException;
}
