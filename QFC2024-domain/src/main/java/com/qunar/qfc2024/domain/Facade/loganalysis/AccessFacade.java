package com.qunar.qfc2024.domain.Facade.loganalysis;

import com.qunar.qfc2024.domain.dto.GroupedURL;
import com.qunar.qfc2024.domain.dto.InterfaceStat;

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
     * @return 请求总量
     * @author zhangge
     * @date 2024/6/9
     */
    Integer getQueryCount();

    /**
     * 获取请求最频繁的接口列表
     *
     * @param limitCount 接口数
     * @return 最频繁的接口列表
     * @author zhangge
     * @date 2024/6/10
     */
    List<InterfaceStat> getFrequentInterface(Long limitCount);

    /**
     * 获取各个请求方式的请求量
     *
     * @return 各请求方式的请求量
     * @author zhangge
     * @date 2024/6/10
     */
    List<InterfaceStat> getQueryMethodCount();

    /**
     * URL 格式均为 /AAA/BBB 或者 /AAA/BBB/CCC 格式，按 AAA 分类
     *
     * @return 分类后的url
     * @author zhangge
     * @date 2024/6/10
     */
    List<GroupedURL> getGroupedURL();
}
