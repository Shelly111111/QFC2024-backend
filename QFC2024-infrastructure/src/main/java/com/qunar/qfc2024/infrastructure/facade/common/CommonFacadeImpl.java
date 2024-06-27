package com.qunar.qfc2024.infrastructure.facade.common;

import com.qunar.qfc2024.domain.Facade.common.CommonFacade;
import com.qunar.qfc2024.infrastructure.po.FileInfoPO;
import com.qunar.qfc2024.infrastructure.repository.FileInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

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

    @Autowired
    private FileInfoRepository fileInfoRepository;

    /**
     * 保存文件到本地
     *
     * @param username 用户名
     * @param file     文件
     * @return 是否成功
     * @author zhangge
     * @date 2024/6/24
     */
    @Override
    public boolean saveFile(String username, MultipartFile file) {

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

            //查询数据库中是否存在该文件
            FileInfoPO fileInfo = fileInfoRepository.query()
                    .eq(FileInfoPO.FILENAME, filename)
                    .eq(FileInfoPO.IS_DELETE, 0)
                    .one();

            //如果不存在，则存储入数据库
            if (Objects.isNull(fileInfo)) {
                fileInfo = new FileInfoPO();
                fileInfo.setFilename(filename);
                //写入版本号
                fileInfo.setVersion(1);
                fileInfo.setCreateUser(username);
                fileInfo.setUpdateUser(username);

                if (!fileInfoRepository.save(fileInfo)) {
                    log.error(filename + "存储数据库失败！");
                    return false;
                }
            } else {
                boolean update = fileInfoRepository.update()
                        .set(FileInfoPO.UPDATE_USER, username)
                        //版本+1
                        .set(FileInfoPO.VERSION, fileInfo.getVersion() + 1)
                        .eq(FileInfoPO.FILENAME, filename)
                        .eq(FileInfoPO.VERSION, fileInfo.getVersion())
                        .update();
                if (!update) {
                    log.error(filename + "更新失败！");
                    return false;
                }
            }

        } catch (IOException e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }
}
