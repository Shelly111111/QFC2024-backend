package com.qunar.qfc2024.infrastructure.facade.loganalysis;

import com.google.common.base.Splitter;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.qunar.qfc2024.common.enumeration.QueryMethod;
import com.qunar.qfc2024.domain.Facade.loganalysis.AccessFacade;
import com.qunar.qfc2024.domain.bo.GroupedURL;
import com.qunar.qfc2024.domain.bo.InterfaceInfo;
import com.qunar.qfc2024.domain.bo.InterfaceStat;
import com.qunar.qfc2024.domain.bo.MethodStat;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 问题一：日志分析
 *
 * @author zhangge
 * @date 2024/6/10
 */
@Component
@Slf4j
public class AccessFacadeImpl implements AccessFacade {

    @Value("${attachments.basePath}")
    private String basePath;

    @Value("${attachments.questions.one.folder}")
    private String folder;

    @Value("${attachments.questions.one.file}")
    private String file;

    @Value("${attachments.save}")
    private String savePath;

    private final Object lock = new Object();

    private final LoadingCache<String, InterfaceInfo> cache = CacheBuilder.newBuilder()
            .maximumSize(1000000L)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .expireAfterAccess(10, TimeUnit.MINUTES)
            .build(new CacheLoader<String, InterfaceInfo>() {
                @Override
                public InterfaceInfo load(String s) {
                    return null;
                }
            });

    private String currentFile = "";

    /**
     * 读取接口文件
     *
     * @param filename 文件名
     * @author zhangge
     * @date 2024/6/11
     */
    private void readInterfaceFile(String filename) throws IOException {
        synchronized (lock) {
            Map<String, InterfaceInfo> map = new HashMap<>(cache.asMap());
            if (map.isEmpty() || (!StringUtils.isBlank(filename) && !currentFile.equals(filename)) || (StringUtils.isBlank(filename) && !currentFile.equals(file))) {
                if (!StringUtils.isBlank(filename) && !currentFile.equals(filename)) {
                    currentFile = filename;
                } else if (StringUtils.isBlank(filename) && !currentFile.equals(file)) {
                    currentFile = file;
                }
                cache.invalidateAll();
                cache.cleanUp();
                InputStream inputStream;
                //未指定文件名
                if (StringUtils.isBlank(filename)) {
                    String filePath = Paths.get(basePath, folder, file).toString();
                    //读取测试文件
                    Resource resource = new ClassPathResource(filePath);
                    //获取流
                    inputStream = resource.getInputStream();
                } else {
                    //指定了文件名
                    String rootPath = System.getProperty("user.dir");
                    File file = Paths.get(rootPath, savePath, filename).toFile();
                    inputStream = new FileInputStream(file);
                }

                //扫描每行
                Scanner scanner = new Scanner(inputStream);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (StringUtils.isBlank(line)) {
                        continue;
                    }
                    //转化为接口信息类并存储到Cache中
                    InterfaceInfo interfaceInfo = new InterfaceInfo(line);
                    cache.put(interfaceInfo.getUuid(), interfaceInfo);
                }

                //关闭Scanner
                scanner.close();
                //关闭流
                inputStream.close();
            }
        }
    }

    @Override
    public Integer getQueryCount(String filename) throws IOException {
        readInterfaceFile(filename);
        return new HashMap<>(cache.asMap()).size();

    }

    @Override
    public List<InterfaceStat> getFrequentInterface(String filename, Long limitCount) throws IOException {
        readInterfaceFile(filename);
        //统计各个接口的请求数量
        Map<String, Long> map = new HashMap<>(cache.asMap()).values().stream()
                .collect(Collectors.groupingBy(InterfaceInfo::getUrl, Collectors.counting()));
        List<InterfaceStat> list = map.entrySet()
                //按照请求数量进行从大到小排序
                .stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                //截取排序前count个
                .limit(limitCount)
                //转化为类
                .map(a -> {
                    InterfaceStat interfaceStat = new InterfaceStat();
                    interfaceStat.setUrl(a.getKey());
                    interfaceStat.setQueryCount(a.getValue());
                    return interfaceStat;
                }).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<MethodStat> getQueryMethodCount(String filename) throws IOException {
        readInterfaceFile(filename);
        //统计GET和POST的请求数量
        Map<QueryMethod, Long> map = new HashMap<>(cache.asMap()).values().stream()
                //过滤除GET和POST请求外的请求
                .filter(a -> (a.getMethod() == QueryMethod.GET || a.getMethod() == QueryMethod.POST))
                .collect(Collectors.groupingBy(InterfaceInfo::getMethod, Collectors.counting()));
        //格式转化
        List<MethodStat> list = map.entrySet().stream()
                .map(a -> {
                    MethodStat methodStat = new MethodStat();
                    methodStat.setMethod(a.getKey());
                    methodStat.setQueryCount(a.getValue());
                    return methodStat;
                }).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<GroupedURL> getGroupedURL(String filename) throws IOException {
        readInterfaceFile(filename);
        Map<String, List<InterfaceInfo>> map = new HashMap<>(cache.asMap())
                .values()
                .stream()
                //保证url是按照/AAA/BBB而不是其他开头
                .filter(a -> a.getUrl().matches("/[^/]+/[^/]+"))
                .collect(Collectors.groupingBy(
                        //根据/AAA进行分组
                        a -> Splitter.on('/')
                                .omitEmptyStrings()
                                .splitToList(a.getUrl())
                                .get(0),
                        Collectors.toList()));
        List<GroupedURL> list = map.entrySet().stream()
                //转化为GroupURL
                .map(a -> new GroupedURL('/' + a.getKey(),
                        a.getValue().stream()
                                .map(InterfaceInfo::getUrl)
                                //去重
                                .distinct()
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
        return list;
    }


}
