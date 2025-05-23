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
 * 测试账号表
 * </p>
 *
 * @author Menghui
 * @since 2025-03-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("test_account")
@ApiModel(value = "TestAccount对象", description = "测试账号表")
public class TestAccount implements Serializable {

  private static final long serialVersionUID = 1L;

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  @ApiModelProperty(value = "项目id")
  private Long projectId;

  @ApiModelProperty(value = "名称")
  private String name;

  @ApiModelProperty(value = "账号/密码")
  private String inputParams;

  @ApiModelProperty(value = "接口用例id")
  private Long apiCaseId;

  @ApiModelProperty(value = "cron表达式")
  private String cron;

  @ApiModelProperty(value = "json path")
  private String jsonPath;

  @ApiModelProperty(value = "token")
  private String token;

  @ApiModelProperty(value = "生成token状态")
  private String genTokenStatus;

  @ApiModelProperty(value = "失败原因")
  private String failReason;

  @ApiModelProperty(value = "下次执行时间")
  private LocalDateTime nextExecTime;

  @TableField(fill = FieldFill.INSERT)
  @ApiModelProperty(value = "创建者ID")
  private Long createById;

  @TableField(fill = FieldFill.INSERT)
  @ApiModelProperty(value = "创建者")
  private String createByName;

  @TableField(fill = FieldFill.INSERT)
  @ApiModelProperty(value = "创建时间")
  private LocalDateTime createTime;

  @TableField(fill = FieldFill.INSERT_UPDATE)
  @ApiModelProperty(value = "修改者ID")
  private Long updateById;

  @TableField(fill = FieldFill.INSERT_UPDATE)
  @ApiModelProperty(value = "修改者")
  private String updateByName;

  @TableField(fill = FieldFill.INSERT_UPDATE)
  @ApiModelProperty(value = "修改时间")
  private LocalDateTime updateTime;

  @TableLogic
  @ApiModelProperty(value = "是否删除")
  private Integer isDelete;


}
