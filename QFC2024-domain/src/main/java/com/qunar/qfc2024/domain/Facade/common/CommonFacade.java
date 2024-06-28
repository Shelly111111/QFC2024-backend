package com.qunar.qfc2024.domain.Facade.common;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
     * @param username 用户名
     * @param file     文件
     * @return 是否成功
     * @author zhangge
     * @date 2024/6/24
     */
    boolean saveFile(String username, MultipartFile file) throws IOException;

    /**
     * 获取文件列表
     *
     * @return 文件列表
     * @author zhangge
     * @date 2024/6/28
     */
    List<String> getFileList();
}
