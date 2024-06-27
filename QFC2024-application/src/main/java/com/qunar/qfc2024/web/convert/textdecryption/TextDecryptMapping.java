package com.qunar.qfc2024.web.convert.textdecryption;

import com.qunar.qfc2024.api.vo.FileLineVO;
import com.qunar.qfc2024.api.vo.base.Page;
import com.qunar.qfc2024.domain.bo.FileLine;
import com.qunar.qfc2024.domain.bo.base.BasePage;
import org.mapstruct.Mapper;

/**
 * @author zhangge
 * @date 2024/6/27
 **/
@Mapper(componentModel = "spring")
public interface TextDecryptMapping {

    FileLineVO convert(FileLine source);

    Page convert(BasePage source);
}
