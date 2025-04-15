package com.example.crazytest.entity.req;

import com.alibaba.fastjson.JSONObject;
import com.example.crazytest.vo.ParamsListVO;
import java.util.List;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/3/23 14:31
 * @DESRIPTION
 */

@Data
public class ApiDebugReq {

  private Long id;
  private Long envId;
  private Long testAccount;
  private JSONObject testAccountInParam;
  private List<ParamsListVO> inputParams;
  private String mode;
  private String remark;
  private Long scheduleId;
  private Long scheduleBatchId;
}
