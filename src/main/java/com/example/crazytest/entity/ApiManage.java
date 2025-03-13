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
 * 接口管理表
 * </p>
 *
 * @author Menghui
 * @since 2025-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("api_manage")
@ApiModel(value = "ApiManage对象", description = "接口管理表")
public class ApiManage implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  @ApiModelProperty(value = "租户")
  private String tenantId;

  @ApiModelProperty(value = "项目Id")
  private Long projectId;

  @ApiModelProperty(value = "应用id")
  private Long applicationId;

  @ApiModelProperty(value = "接口名")
  private String name;

  @ApiModelProperty(value = "请求方法")
  private String method;

  @ApiModelProperty(value = "接口协议")
  private String protocal;

  @ApiModelProperty(value = "接口路径")
  private String path;

  @ApiModelProperty(value = "参数类型")
  private String contentType;

  @ApiModelProperty(value = "请求头")
  private String requestHeaders;

  @ApiModelProperty(value = "请求参数")
  private String requestParams;

  @ApiModelProperty(value = "响应结果")
  private String response;

  @ApiModelProperty(value = "超时时间（秒）")
  private Integer timeOut;

  @ApiModelProperty(value = "优先级")
  private Integer priority;

  @ApiModelProperty(value = "是否可生产执行：0否 1是")
  private Integer canProdExec;

  @ApiModelProperty(value = "接口类型")
  private String apiType;

  @ApiModelProperty(value = "负责人")
  private Long owner;

  @ApiModelProperty(value = "用例数")
  private Integer caseCount;

  @ApiModelProperty(value = "创建者id")
  private Long creatorId;

  @ApiModelProperty(value = "修改者id")
  private Long menderId;

  @ApiModelProperty(value = "最近30天调用次数")
  private Integer invokeTimes;

  @ApiModelProperty(value = "创建时间")
  private LocalDateTime createTime;

  @ApiModelProperty(value = "修改时间")
  private LocalDateTime modifyTime;

  @ApiModelProperty(value = "是否删除")
  private Integer isDelete;

  @ApiModelProperty(value = "备注")
  private String remark;

}
