package com.qunar.qfc2024.api.service.textdecryption;

import com.qunar.qfc2024.api.vo.FileLineVO;
import com.qunar.qfc2024.api.vo.base.Page;
import com.qunar.qfc2024.api.vo.base.Result;

/**
 * 文本解密服务
 *
 * @author zhangge
 * @date 2024/6/27
 */
public interface TextDecryptService {

    /**
     * 文本解码
     *
     * @param filename 文件名
     * @param pageNum  当前页
     * @param pageSize 每页大小
     * @return 当前页文本
     * @author zhangge
     * @date 2024/6/27
     */
    Result<Page<FileLineVO>> textDecrypt(String filename, Integer pageNum, Integer pageSize);
}
