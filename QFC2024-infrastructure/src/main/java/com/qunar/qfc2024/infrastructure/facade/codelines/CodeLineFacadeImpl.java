package com.qunar.qfc2024.infrastructure.facade.codelines;

import com.qunar.qfc2024.domain.Facade.codelines.CodeLineFacade;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * 有效代码行数统计
 *
 * @author zhangge
 * @date 2024/6/11
 */
@Component
@Slf4j
public class CodeLineFacadeImpl implements CodeLineFacade {

    @Value("${attachments.basePath}")
    private String basePath;

    @Value("${attachments.questions.two.folder}")
    private String folder;

    @Value("${attachments.questions.two.file}")
    private String file;

    @Value("${attachments.questions.two.output}")
    private String output;

    /**
     * 计算有效代码行
     *
     * @param inputStream 输入流
     * @return 有效代码行数
     */
    private Long calCodeLine(InputStream inputStream) {
        Long count = 0L;
        boolean canAdd = true;
        //扫描每行
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String s = line.trim();
            //如果是空白行
            if (StringUtils.isBlank(s)) {
                continue;
            }

            //处理"/* xxxxx */"类注释
            if (!canAdd) {
                if (s.endsWith("*/")) {
                    canAdd = true;
                }
                continue;
            }
            if (s.startsWith("/*")) {
                if (!s.endsWith("*/")) {
                    canAdd = false;
                }
                continue;
            }

            //处理"//"类注释
            if (s.startsWith("//")) {
                continue;
            }

            count++;
        }
        //关闭scanner
        scanner.close();
        return count;
    }

    @Override
    public Long getCodeLineCount() {

        //读取测试文件
        Resource resource = new ClassPathResource(Paths.get(basePath, folder, file).toString());
        String rootPath = System.getProperty("user.dir");
        try {
            //获取输入流
            InputStream inputStream = resource.getInputStream();

            //计算有效代码行
            Long count = calCodeLine(inputStream);

            //关闭流
            inputStream.close();

            //获取输出文件
            Path outPath = Paths.get(rootPath, "out");
            File folder = outPath.toFile();
            //如果文件夹不存在，则创建
            if(!folder.exists() || !folder.isDirectory()){
                Files.createDirectory(outPath);
            }
            File file = outPath.resolve(output).toFile();
            //如果文件不存在，则创建
            if (!file.exists()) {
                file.createNewFile();
            }
            //获取输出流
            OutputStream outputStream = new FileOutputStream(file);

            //写入文件
            outputStream.write(count.toString().getBytes());
            outputStream.flush();

            //关闭输出流
            outputStream.close();

            return count;
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return 0L;
    }
}
