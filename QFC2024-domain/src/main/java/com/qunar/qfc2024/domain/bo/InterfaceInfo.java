package com.qunar.qfc2024.domain.bo;

import com.google.common.base.Splitter;
import com.qunar.qfc2024.common.enumeration.QueryMethod;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.tags.Param;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 日志信息
 *
 * @author zhangge
 * @date 2024/6/9
 */
@Data
@AllArgsConstructor
@Validated
public class InterfaceInfo {

    @ApiModelProperty("主键")
    private String uuid;

    @ApiModelProperty("请求字符串")
    @NotBlank(message = "请求字符串不能为空")
    private String allQueryString;

    @ApiModelProperty("请求方式")
    @NotNull(message = "请求方式不能为空")
    private QueryMethod method;

    @ApiModelProperty("请求地址")
    @NotBlank(message = "请求地址不能为空")
    private String url;

    @ApiModelProperty("请求参数")
    private List<Param> args;

    @ApiModelProperty("请求参数数量")
    @Min(value = 0, message = "参数数量应大于等于0")
    private Integer argCount;

    /**
     * 日志信息
     *
     * @param queryString 请求字符串
     * @author zhangge
     * @date 2024/6/9
     */
    public InterfaceInfo(String queryString) {

        uuid = UUID.randomUUID().toString();

        assert StringUtils.isNotBlank(queryString) : "请求字符串不能为空";
        allQueryString = queryString;


        List<String> list = Splitter.on(' ').omitEmptyStrings().splitToList(queryString);
        assert list.size() == 2 : "请求字符串格式必须为:\"请求方式+' '+请求地址及参数组成\"";

        method = QueryMethod.getMethod(list.get(0));

        List<String> query = Splitter.on('?').omitEmptyStrings().trimResults().splitToList(list.get(1));
        assert query.size() <= 2 : "请求地址及参数使用'?'分割，且请求地址和参数中不可存在其他'?'字符";
        url = query.get(0);

        if (query.size() > 1) {
            List<String> queryArgs = Splitter.on('&').omitEmptyStrings().trimResults().splitToList(query.get(1));
            args = queryArgs.stream().map(a -> {
                List<String> arg = Splitter.on('=').omitEmptyStrings().trimResults().splitToList(a);
                assert arg.size() == 2 : "请求参数使用'='分割，且请求参数中不可存在其他'='字符";
                Param param = new Param();
                param.setName(arg.get(0));
                param.setValue(arg.get(1));
                return param;
            }).collect(Collectors.toList());
            argCount = args.size();
        } else {
            args = new ArrayList<>();
            argCount = 0;
        }
    }
}
