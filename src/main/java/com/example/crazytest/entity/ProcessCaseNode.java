package com.example.crazytest.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 场景测试用例节点详情
 * </p>
 *
 * @author Menghui
 * @since 2025-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("process_case_node")
@ApiModel(value = "ProcessCaseNode对象", description = "场景测试用例节点详情")
public class ProcessCaseNode implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(value = "id")
  private Long id;

  @ApiModelProperty(value = "项目id")
  private Long projectId;

  @ApiModelProperty(value = "用例id")
  private Long caseId;

  @ApiModelProperty(value = "应用id")
  private Long appId;

  @ApiModelProperty(value = "接口id")
  private Long apiId;

  @ApiModelProperty(value = "请求头")
  private String requestHeaders;

  @ApiModelProperty(value = "请求参数")
  private String requestParams;

  @ApiModelProperty(value = "断言")
  private String assertList;

  @ApiModelProperty(value = "环境关联")
  private String isSubEnv;

  @ApiModelProperty(value = "入参（目前只有子流程用到）")
  private String inputParams;

  @ApiModelProperty(value = "节点出参")
  private String outputParams;

  @ApiModelProperty(value = "子流程用例id")
  private Long subCaseId;

  @ApiModelProperty(value = "条件节点和前置步骤输出参数的key")
  private String groovyKey;

  @ApiModelProperty(value = "groovy脚本(仅条件节点用到)")
  private String groovyScript;

  @ApiModelProperty(value = "加密参数id")
  private Long secretId;

  @ApiModelProperty(value = "数据源ID(仅SQL节点用到)")
  private Long dataSourceId;

  @ApiModelProperty(value = "SQL脚本(仅SQL节点用到)")
  private String sqlScript;

  @ApiModelProperty(value = "创建者ID")
  @TableField(fill = FieldFill.INSERT)
  private Long createById;

  @ApiModelProperty(value = "创建者姓名")
  @TableField(fill = FieldFill.INSERT)
  private String createByName;

  @ApiModelProperty(value = "创建时间")
  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime createTime;

  @ApiModelProperty(value = "修改者ID")
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private Long updateById;

  @ApiModelProperty(value = "修改者姓名")
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private String updateByName;

  @ApiModelProperty(value = "更新时间")
  @TableField(fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;

  @ApiModelProperty(value = "是否删除")
  private Integer isDelete;

}
