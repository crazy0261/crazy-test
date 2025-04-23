package com.example.crazytest.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/4/23 22:04
 * @DESRIPTION
 */

@Data
public class CaseResultDetailVO {

  IPage<ApiCaseResultVO> apiResultDetail;

  IPage<ProcessCaseResultDetailVO> processCaseResultDetail;

  Long total;

}
