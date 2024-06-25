package com.qunar.qfc2024.api.service.common;

import com.qunar.qfc2024.api.response.Result;
import org.springframework.web.multipart.MultipartFile;

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
     * @param file 文件
     * @return 是否成功
     * @author zhangge
     * @date 2024/6/24
     */
    Result saveFile(MultipartFile file);
}
