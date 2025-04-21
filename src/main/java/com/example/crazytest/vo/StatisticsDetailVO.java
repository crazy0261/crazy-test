package com.example.crazytest.vo;

import com.example.crazytest.entity.DataCountEntity;
import com.example.crazytest.entity.AssetsNotListEntity;
import com.example.crazytest.entity.NotFailEntity;
import com.example.crazytest.entity.NotTaskEntity;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/4/21 11:58
 * @DESRIPTION
 */

@Data
public class StatisticsDetailVO {

  @ApiModelProperty(value = "没有断言用例人员分布")
  List<DataCountEntity> assetsCount;

  @ApiModelProperty(value = "没有断言用例明细")
  List<AssetsNotListEntity> assetsList;

  @ApiModelProperty(value = "未添加任务用例-应用")
  List<DataCountEntity> notTaskCount;

  @ApiModelProperty(value = "未添加任务用例明细")
  List<NotTaskEntity> notTaskList;

  @ApiModelProperty(value = "近三天用例失败人员分布")
  List<DataCountEntity> failCaseCount;

  @ApiModelProperty(value = "近三天用例失败明细")
  List<NotFailEntity> failCaseList;

}
