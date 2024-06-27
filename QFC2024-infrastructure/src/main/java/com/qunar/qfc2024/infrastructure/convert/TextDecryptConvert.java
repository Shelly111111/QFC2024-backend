package com.qunar.qfc2024.infrastructure.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qunar.qfc2024.domain.bo.FileLine;
import com.qunar.qfc2024.domain.bo.base.BasePage;
import com.qunar.qfc2024.infrastructure.po.FileLinePO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author zhangge
 * @date 2024/6/27
 **/
@Mapper(componentModel = "spring")
public interface TextDecryptConvert {

    FileLine convert(FileLinePO source);

    @Mappings({
            @Mapping(source = "total", target = "totalSize"),
            @Mapping(source = "current", target = "currentPage"),
            @Mapping(source = "size", target = "pageSize"),
            @Mapping(source = "records", target = "data")
    })
    BasePage<FileLine> convert(Page<FileLinePO> source);
}
