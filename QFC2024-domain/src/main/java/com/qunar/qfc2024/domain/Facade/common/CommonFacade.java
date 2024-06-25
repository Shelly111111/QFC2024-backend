package com.qunar.qfc2024.domain.Facade.common;

import org.springframework.web.multipart.MultipartFile;

/**
 * 通用Facade
 *
 * @author zhangge
 * @date 2024/6/25
 */
public interface CommonFacade {

    /**
     * 保存文件到本地
     *
     * @param file 文件
     * @return 是否成功
     * @author zhangge
     * @date 2024/6/24
     */
    boolean saveFile(MultipartFile file);
}
