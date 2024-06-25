package com.qunar.qfc2024.infrastructure.facade.common;

import com.qunar.qfc2024.domain.Facade.common.CommonFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 通用门户
 *
 * @author zhangge
 * @date 2024/6/25
 */
@Component
@Slf4j
public class CommonFacadeImpl implements CommonFacade {

    @Value("${attachments.save}")
    private String savePath;

    /**
     * 保存文件到本地
     *
     * @param file 文件
     * @return 是否成功
     * @author zhangge
     * @date 2024/6/24
     */
    @Override
    public boolean saveFile(MultipartFile file) {

        String rootPath = System.getProperty("user.dir");
        //获取输出文件
        String filename = file.getOriginalFilename();

        try {
            Path outPath = Paths.get(rootPath, savePath);
            File saveFolder = outPath.toFile();
            //如果文件夹不存在，则创建
            if (!saveFolder.exists() || !saveFolder.isDirectory()) {
                Files.createDirectory(outPath);
            }

            File saveFile = outPath.resolve(filename).toFile();
            //如果文件不存在，则创建
            if (!saveFile.exists()) {
                saveFile.createNewFile();
            }

            FileOutputStream outputStream = new FileOutputStream(saveFile);
            outputStream.write(file.getBytes());
            //关闭文件流
            outputStream.flush();
            outputStream.close();

        } catch (IOException e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }
}
