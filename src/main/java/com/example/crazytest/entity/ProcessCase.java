package com.example.crazytest.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
 * 场景用例
 * </p>
 *
 * @author Menghui
 * @since 2025-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("process_case")
@ApiModel(value = "ProcessCase对象", description = "场景用例")
public class ProcessCase implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  @ApiModelProperty(value = "项目id")
  private Long projectId;

  @ApiModelProperty(value = "应用id")
  private Long appId;

  @ApiModelProperty(value = "树节点key")
  private String treeKey;

  @ApiModelProperty(value = "用例名")
  private String name;

  @ApiModelProperty(value = "优先级")
  private Integer priority;

  @ApiModelProperty(value = "是否是子流程")
  private Integer isSubProcess;

  @ApiModelProperty(value = "用例入参")
  private String inputParams;

  @ApiModelProperty(value = "节点")
  private String nodes;

  @ApiModelProperty(value = "边")
  private String edges;

  @ApiModelProperty(value = "负责人id")
  private Long ownerId;

  @ApiModelProperty(value = "状态")
  private Integer status;

  @ApiModelProperty(value = "备注")
  private String remark;

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

  @TableLogic
  @ApiModelProperty(value = "是否删除")
  private Integer isDelete;

}
