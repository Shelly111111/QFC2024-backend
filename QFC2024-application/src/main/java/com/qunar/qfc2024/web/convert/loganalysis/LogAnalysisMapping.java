package com.qunar.qfc2024.web.convert.loganalysis;

import com.qunar.qfc2024.api.dto.GroupedURLDTO;
import com.qunar.qfc2024.api.dto.InterfaceStatDTO;
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
    InterfaceStatDTO convert(InterfaceStat source);

    List<InterfaceStatDTO> convertInterfaceStatList(List<InterfaceStat> source);

    /**
     * 转化
     *
     * @param source 源
     * @return 目标
     */
    @Mappings({@Mapping(source = "method", target = "label"), @Mapping(source = "queryCount", target = "children")})
    InterfaceStatDTO convert(MethodStat source);

    List<InterfaceStatDTO> convertMethodStatList(List<MethodStat> source);

    @Mappings({@Mapping(source = "category", target = "label"), @Mapping(target = "children", expression = "java(source.getUrls().stream().collect(Collectors.joining(\";\")))")})
    GroupedURLDTO convert(GroupedURL source);

    List<GroupedURLDTO> convertGroupedURLList(List<GroupedURL> source);
}
