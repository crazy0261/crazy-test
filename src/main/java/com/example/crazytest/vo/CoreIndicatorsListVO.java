package com.example.crazytest.vo;

import com.example.crazytest.entity.UserDistributionEntity;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/4/17 22:09
 * @DESRIPTION
 */

@Data
public class CoreIndicatorsListVO {

  @ApiModelProperty(value = "总数用户")
  private Long userCount;

  @ApiModelProperty(value = "应用总数")
  private Long appCount;

  @ApiModelProperty(value = "接口总数")
  private Long apiCount;

  @ApiModelProperty(value = "缺陷总数")
  private Long bugCount;

  @ApiModelProperty(value = "用例总数")
  private Long caseCount;

  @ApiModelProperty(value = "接口用例总数")
  private Long apiCaseCount;

  @ApiModelProperty(value = "场景用例总数")
  private Long processCaseCount;

  @ApiModelProperty(value = "用例成功率")
  private Double caseSuccessRate;

  @ApiModelProperty(value = "接口覆盖率")
  private Double coverageApiRate;

  @ApiModelProperty(value = "接口覆盖数")
  private Long coverageIsApiCount;

  @ApiModelProperty(value = "接口未覆盖总数")
  private Long coverageNotApiCount;

  @ApiModelProperty(value = "用例成功数(接口+场景)")
  private int sumCaseSuccessCount;

  @ApiModelProperty(value = "用例失败数(接口+场景)")
  private int sumCaseFailureCount;

  @ApiModelProperty(value = "人员分布")
  List<UserDistributionEntity> userDistributionEntities;

}
