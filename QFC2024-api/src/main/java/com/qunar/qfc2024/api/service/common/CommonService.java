package com.qunar.qfc2024.api.service.common;

import com.qunar.qfc2024.api.vo.base.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 通用服务
 *
 * @author zhangge
 * @date 2024/6/25
 */
public interface CommonService {

    /**
     * 保存文件
     *
     * @param username 用户名
     * @param file     文件
     * @return 是否成功
     * @author zhangge
     * @date 2024/6/24
     */
    Result saveFile(String username, MultipartFile file);

    /**
     * 获取文件列表
     *
     * @return 文件列表
     * @author zhangge
     * @date 2024/6/28
     */
    Result<List<String>> getFileList();
}
