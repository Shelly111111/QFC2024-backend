package com.qunar.qfc2024.infrastructure.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author mybatisplus generator
 * @since 2024-06-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_file_line")
@ApiModel(value="FileLinePO对象", description="")
public class FileLinePO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("uuid")
    private String uuid;

    @ApiModelProperty(value = "文件id")
    @TableField("file_id")
    private Integer fileId;

    @ApiModelProperty(value = "文件版本")
    @TableField("version")
    private Integer version;

    @ApiModelProperty(value = "行内容")
    @TableField("line")
    private String line;

    @ApiModelProperty(value = "解密后的行内容")
    @TableField("decrypt_line")
    private String decryptLine;

    @ApiModelProperty(value = "行下标")
    @TableField("line_index")
    private Long lineIndex;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "是否删除")
    @TableField("is_delete")
    private Integer isDelete;


    public static final String UUID = "uuid";

    public static final String FILE_ID = "file_id";

    public static final String VERSION = "version";

    public static final String LINE = "line";

    public static final String DECRYPT_LINE = "decrypt_line";

    public static final String LINE_INDEX = "line_index";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String IS_DELETE = "is_delete";

}
