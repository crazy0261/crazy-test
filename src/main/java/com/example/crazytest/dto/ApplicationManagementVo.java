package com.example.crazytest.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/3/18 23:17
 * @DESRIPTION
 */

@Data
public class ApplicationManagementVo {

  @ApiModelProperty(value = "应用名")
  private String name;

  @ApiModelProperty(value = "应用描述")
  private String remark;

  @ApiModelProperty(value = "负责人id")
  private Long ownerId;

  @ApiModelProperty(value = "负责人")
  private String ownerName;

  @ApiModelProperty(value = "创建时间")
  private LocalDateTime createTime;


  @TableField(fill = FieldFill.INSERT)
  @ApiModelProperty(value = "创建者")
  private String createByName;


  @ApiModelProperty(value = "更新时间")
  private LocalDateTime updateTime;


  @ApiModelProperty(value = "更新者")
  private String updateByName;

}
