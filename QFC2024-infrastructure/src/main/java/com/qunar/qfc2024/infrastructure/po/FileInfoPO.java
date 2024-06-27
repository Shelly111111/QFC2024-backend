package com.qunar.qfc2024.infrastructure.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("t_file_info")
@ApiModel(value="FileInfoPO对象", description="")
public class FileInfoPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "文件名")
    @TableField("filename")
    private String filename;

    @ApiModelProperty(value = "版本")
    @TableField("version")
    private Integer version;

    @ApiModelProperty(value = "创建用户")
    @TableField("create_user")
    private String createUser;

    @ApiModelProperty(value = "更新用户")
    @TableField("update_user")
    private String updateUser;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "是否删除")
    @TableField("is_delete")
    private Integer isDelete;


    public static final String ID = "id";

    public static final String FILENAME = "filename";

    public static final String VERSION = "version";

    public static final String CREATE_USER = "create_user";

    public static final String UPDATE_USER = "update_user";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String IS_DELETE = "is_delete";

}
