package com.qunar.qfc2024.domain.Facade.loganalysis;

import com.qunar.qfc2024.domain.bo.GroupedURL;
import com.qunar.qfc2024.domain.bo.InterfaceStat;
import com.qunar.qfc2024.domain.bo.MethodStat;

import java.util.List;

/**
 * 问题一：日志分析
 *
 * @author zhangge
 * @date 2024/6/9
 */
public interface AccessFacade {

    /**
     * 获取请求总量
     *
     * @param filename 文件名
     * @return 请求总量
     * @author zhangge
     * @date 2024/6/9
     */
    Integer getQueryCount(String filename);

    /**
     * 获取请求最频繁的接口列表
     *
     * @param limitCount 接口数
     * @param filename   文件名
     * @return 最频繁的接口列表
     * @author zhangge
     * @date 2024/6/10
     */
    List<InterfaceStat> getFrequentInterface(String filename, Long limitCount);

    /**
     * 获取各个请求方式的请求量
     *
     * @param filename 文件名
     * @return 各请求方式的请求量
     * @author zhangge
     * @date 2024/6/10
     */
    List<MethodStat> getQueryMethodCount(String filename);

    /**
     * URL 格式均为 /AAA/BBB 或者 /AAA/BBB/CCC 格式，按 AAA 分类
     *
     * @param filename 文件名
     * @return 分类后的url
     * @author zhangge
     * @date 2024/6/10
     */
    List<GroupedURL> getGroupedURL(String filename);

}
