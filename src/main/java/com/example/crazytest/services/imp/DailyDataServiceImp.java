package com.example.crazytest.services.imp;

import cn.hutool.core.util.NumberUtil;
import com.example.crazytest.entity.DailyData;
import com.example.crazytest.entity.req.DailyDataReq;
import com.example.crazytest.repository.ApiCaseRepositoryService;
import com.example.crazytest.repository.ApiManageRepositoryService;
import com.example.crazytest.repository.ApplicationManagementRepositoryService;
import com.example.crazytest.repository.DailyDataRepositoryService;
import com.example.crazytest.repository.ProcessCaseRepositoryService;
import com.example.crazytest.repository.UserRepositoryService;
import com.example.crazytest.services.ApiCaseService;
import com.example.crazytest.services.DailyDataService;
import com.example.crazytest.utils.BaseContext;
import com.example.crazytest.utils.ComputeNumUtil;
import com.example.crazytest.vo.CoreIndicatorsListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @name Menghui
 * @date 2025/4/17 22:20
 * @DESRIPTION
 */

@Service
public class DailyDataServiceImp implements DailyDataService {

  @Autowired
  DailyDataRepositoryService dailyDataRepository;

  @Autowired
  UserRepositoryService userRepositoryService;

  @Autowired
  ApplicationManagementRepositoryService applicationManagementRepositoryService;

  @Autowired
  ApiManageRepositoryService apiManageRepositoryService;

  @Autowired
  ApiCaseRepositoryService apiCaseRepositoryService;

  @Autowired
  ProcessCaseRepositoryService processCaseRepositoryService;

  @Autowired
  ApiCaseService apiCaseService;


  @Override
  public CoreIndicatorsListVO getCoreIndicatorsDetail() {
    CoreIndicatorsListVO coreIndicatorsListVO = new CoreIndicatorsListVO();
    Long userCount = userRepositoryService.getUserCount(BaseContext.getSelectProjectId());
    Long appCount = applicationManagementRepositoryService.getApplicationCount(BaseContext.getSelectProjectId());
    Long apiCount = apiManageRepositoryService.getApiCount(BaseContext.getSelectProjectId());
    Long apiCaseCount = apiCaseRepositoryService.getApiCaseCount(BaseContext.getSelectProjectId());
    Long processCaseCount = processCaseRepositoryService.getProcessCaseCount(BaseContext.getSelectProjectId());
    Long coverageIsApiCount = apiCaseService.getCoverageIsApiCount();

    coreIndicatorsListVO.setUserCount(userCount);
    coreIndicatorsListVO.setAppCount(appCount);
    coreIndicatorsListVO.setApiCount(apiCount);
    coreIndicatorsListVO.setBugCount(0L); // 没接Bud平台
    coreIndicatorsListVO.setCaseCount(apiCaseCount+processCaseCount);
    coreIndicatorsListVO.setApiCaseCount(apiCaseCount);
    coreIndicatorsListVO.setProcessCaseCount(processCaseCount);
    coreIndicatorsListVO.setCaseSuccessRate(1.0);
    // todo 后续接入 bug 和成功率
    coreIndicatorsListVO.setCoverageIsApiCount(coverageIsApiCount);
    coreIndicatorsListVO.setCoverageNotApiCount(apiCount-coverageIsApiCount);
    coreIndicatorsListVO.setCoverageApiRate(ComputeNumUtil.divideNum(coverageIsApiCount, apiCount,2));

    return coreIndicatorsListVO;
  }
}
