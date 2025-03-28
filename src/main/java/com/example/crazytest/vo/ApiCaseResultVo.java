package com.example.crazytest.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/3/28 13:37
 * @DESRIPTION
 */

@Data
public class ApiCaseResultVo {

  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  @ApiModelProperty(value = "定时任务批次id")
  private Long scheduleBatchId;

  @ApiModelProperty(value = "执行状态,INIT,RUNNING,SUCCESS,FAIL")
  private String status;

  @ApiModelProperty(value = "执行结果")
  private String debugResult;

  @ApiModelProperty(value = "执行时间")
  private LocalDateTime createTime;

}
