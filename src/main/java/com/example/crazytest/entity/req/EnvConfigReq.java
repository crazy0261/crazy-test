package com.example.crazytest.entity.req;

import com.example.crazytest.vo.ParamsListVO;
import java.util.List;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/3/23 21:54
 * @DESRIPTION
 */

@Data
public class EnvConfigReq {

  Long id;
  Long appId;
  Long domainId;
  String envName;
  Integer envSort;
  List<ParamsListVO> requestHeaders;
  List<ParamsListVO> envVariables;


}
