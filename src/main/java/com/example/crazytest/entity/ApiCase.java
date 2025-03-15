package com.example.crazytest.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 测试用例
 * </p>
 *
 * @author Menghui
 * @since 2025-03-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("api_case")
@ApiModel(value="ApiCase对象", description="测试用例")
public class ApiCase implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "租户")
    private String tenantId;

    @ApiModelProperty(value = "项目id")
    private Long projectId;

    @ApiModelProperty(value = "应用id")
    private Long appId;

    @ApiModelProperty(value = "用例名")
    private String name;

    @ApiModelProperty(value = "关联的接口id")
    private Long apiId;

    @ApiModelProperty(value = "请求头")
    private String requestHeaders;

    @ApiModelProperty(value = "请求参数")
    private String requestParams;

    @ApiModelProperty(value = "断言")
    private String asserts;

    @ApiModelProperty(value = "用例变量，优先级高于环境变量")
    private String envVariables;

    @ApiModelProperty(value = "优先级")
    private Integer priority;

    @ApiModelProperty(value = "负责人id")
    private Long ownerId;

    @ApiModelProperty(value = "创建者id")
    private Long creator;

    @ApiModelProperty(value = "修改者ID")
    private Long menderId;

    @ApiModelProperty(value = "是否删除")
    private Long isDelete;

    @ApiModelProperty(value = "加密参数id")
    private Integer secretId;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "最近一次执行结果")
    private String recentExecResult;

    @ApiModelProperty(value = "最近一次执行时间")
    private LocalDateTime recentExecTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime modifyTime;

}
