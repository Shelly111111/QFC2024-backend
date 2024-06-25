package com.qunar.qfc2024.web.convert.loganalysis;

import com.qunar.qfc2024.api.vo.GroupedURLVO;
import com.qunar.qfc2024.api.vo.InterfaceStatVO;
import com.qunar.qfc2024.common.enumeration.QueryMethod;
import com.qunar.qfc2024.domain.bo.GroupedURL;
import com.qunar.qfc2024.domain.bo.InterfaceStat;
import com.qunar.qfc2024.domain.bo.MethodStat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @author zhangge
 * @date 2024/6/24
 **/
@Mapper(componentModel = "spring", imports = {QueryMethod.class, java.util.stream.Collectors.class})
public interface LogAnalysisMapping {

    /**
     * 转化
     *
     * @param source 源
     * @return 目标
     */
    @Mappings({@Mapping(source = "url", target = "label"), @Mapping(source = "queryCount", target = "children")})
    InterfaceStatVO convert(InterfaceStat source);

    List<InterfaceStatVO> convertInterfaceStatList(List<InterfaceStat> source);

    /**
     * 转化
     *
     * @param source 源
     * @return 目标
     */
    @Mappings({@Mapping(source = "method", target = "label"), @Mapping(source = "queryCount", target = "children")})
    InterfaceStatVO convert(MethodStat source);

    List<InterfaceStatVO> convertMethodStatList(List<MethodStat> source);

    @Mappings({@Mapping(source = "category", target = "label"), @Mapping(target = "children", expression = "java(source.getUrls().stream().collect(Collectors.joining(\";\")))")})
    GroupedURLVO convert(GroupedURL source);

    List<GroupedURLVO> convertGroupedURLList(List<GroupedURL> source);
}
