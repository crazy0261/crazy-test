package com.example.crazytest.services.imp;

import com.example.crazytest.entity.CaseResultCountEntity;
import com.example.crazytest.entity.CaseSuccessRateDataEntity;
import com.example.crazytest.entity.DailyData;
import com.example.crazytest.entity.TrendDataEntity;
import com.example.crazytest.entity.User;
import com.example.crazytest.entity.UserDistributionEntity;
import com.example.crazytest.repository.ApiCaseRepositoryService;
import com.example.crazytest.repository.ApiManageRepositoryService;
import com.example.crazytest.repository.ApplicationManagementRepositoryService;
import com.example.crazytest.repository.DailyDataRepositoryService;
import com.example.crazytest.repository.ProcessCaseRepositoryService;
import com.example.crazytest.repository.UserRepositoryService;
import com.example.crazytest.services.ApiCaseResultService;
import com.example.crazytest.services.ApiCaseService;
import com.example.crazytest.services.DailyDataService;
import com.example.crazytest.services.ProcessCaseResultService;
import com.example.crazytest.utils.BaseContext;
import com.example.crazytest.utils.ComputeNumUtil;
import com.example.crazytest.vo.CoreIndicatorsListVO;
import com.example.crazytest.vo.DailyDataCaseVO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;
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

  @Autowired
  ApiCaseResultService apiCaseResultService;

  @Autowired
  ProcessCaseResultService processResultService;


  @Override
  public CoreIndicatorsListVO getCoreIndicatorsDetail() {
    CoreIndicatorsListVO coreIndicatorsListVO = new CoreIndicatorsListVO();
    Long userCount = userRepositoryService.getUserCount(BaseContext.getSelectProjectId());
    Long appCount = applicationManagementRepositoryService
        .getApplicationCount(BaseContext.getSelectProjectId());
    Long apiCount = apiManageRepositoryService.getApiCount(BaseContext.getSelectProjectId());
    Long apiCaseCount = apiCaseRepositoryService.getApiCaseCount(BaseContext.getSelectProjectId());
    Long processCaseCount = processCaseRepositoryService
        .getProcessCaseCount(BaseContext.getSelectProjectId());
    Long coverageIsApiCount = apiCaseService.getCoverageIsApiCount();

    CaseResultCountEntity apiCaseResultCount = apiCaseResultService.getApiCaseSuccessCount();
    CaseResultCountEntity processCaseResultCount = processResultService.getCaseResultCount();

    Double caseSuccessRate = ComputeNumUtil.divideNum(
        LongStream.of(apiCaseResultCount.getApiCaseSuccessCount(), processCaseResultCount
            .getApiCaseProcessSuccessCount()).sum()
        , LongStream.of(apiCaseResultCount.getApiCaseCount(), processCaseResultCount
            .getApiProcessCaseCount()).sum(), 2);

    coreIndicatorsListVO.setUserCount(userCount);
    coreIndicatorsListVO.setAppCount(appCount);
    coreIndicatorsListVO.setApiCount(apiCount);
    coreIndicatorsListVO.setBugCount(0L); // 没接Bud平台
    coreIndicatorsListVO.setCaseCount(apiCaseCount + processCaseCount);
    coreIndicatorsListVO.setApiCaseCount(apiCaseCount);
    coreIndicatorsListVO.setProcessCaseCount(processCaseCount);
    coreIndicatorsListVO.setCaseSuccessRate(caseSuccessRate);
    coreIndicatorsListVO.setCoverageIsApiCount(coverageIsApiCount);
    coreIndicatorsListVO.setCoverageNotApiCount(apiCount - coverageIsApiCount);
    coreIndicatorsListVO
        .setCoverageApiRate(ComputeNumUtil.divideNum(coverageIsApiCount, apiCount, 2));
    coreIndicatorsListVO.setSumCaseSuccessCount(
        apiCaseResultCount.getApiCaseSuccessCount() + processCaseResultCount
            .getApiCaseProcessSuccessCount());
    coreIndicatorsListVO.setSumCaseFailureCount(
        apiCaseResultCount.getApiCaseFailCount() + processCaseResultCount
            .getApiCaseProcessFailCount());
    coreIndicatorsListVO.setUserDistributionEntities(this.getUserDistribution());

    return coreIndicatorsListVO;
  }

  /**
   * 获取趋势数据
   *
   * @param startTime
   * @param endTime
   * @return
   */
  @Override
  public DailyDataCaseVO getTrendData(LocalDate startTime, LocalDate endTime) {
    DailyDataCaseVO dataCaseVO = new DailyDataCaseVO();
    List<TrendDataEntity> trendData = new ArrayList<>();
    List<CaseSuccessRateDataEntity> caseSuccessRateData= new ArrayList<>();
    List<DailyData> dailyDataList = dailyDataRepository
        .getCoreIndicatorsList(BaseContext.getSelectProjectId(), startTime, endTime);

    dailyDataList.forEach(dailyData -> {
      TrendDataEntity trendDataEntity = new TrendDataEntity();
      trendDataEntity.setDate(dailyData.getDate());
      trendDataEntity.setApiCaseNum(dailyData.getApiCaseNum());
      trendDataEntity.setProcessCaseNum(dailyData.getProcessCaseNum());
      trendData.add(trendDataEntity);

      CaseSuccessRateDataEntity successRateDataEntity = new CaseSuccessRateDataEntity();
      successRateDataEntity.setDate(dailyData.getDate());
      successRateDataEntity.setScales(dailyData.getCaseSuccessRate());
      caseSuccessRateData.add(successRateDataEntity);
    });

    dataCaseVO.setTrendData(trendData);
    dataCaseVO.setCaseSuccessRateData(caseSuccessRateData);
    return dataCaseVO;
  }

  /**
   * 用户分布
   *
   * @return
   */
  @Override
  public List<UserDistributionEntity> getUserDistribution() {
    List<UserDistributionEntity> userDistributionEntities = new ArrayList<>();
    List<User> userList = userRepositoryService
        .getProcessUserList(BaseContext.getSelectProjectId());

    userList.forEach(user -> {
      UserDistributionEntity userDistributionEntity = new UserDistributionEntity();
      userDistributionEntity.setUserName(user.getName());
      userDistributionEntity.setApiCaseNum(apiCaseRepositoryService.userApiCaseCount(user.getId()));
      userDistributionEntity.setProcessCaseNum(
          processCaseRepositoryService.getProcessCaseCreateByCount(user.getId()));
      userDistributionEntities.add(userDistributionEntity);
    });
    return userDistributionEntities;
  }

  @Override
  public void createDataDaily(Long projectId) {
    Long apiCaseNum = apiCaseRepositoryService.getApiCaseCount(projectId);
    Long processCaseNum = processCaseRepositoryService
        .getProcessCaseCount(projectId);

    CaseResultCountEntity apiCaseResultCount = apiCaseResultService.getApiCaseSuccessCount();
    CaseResultCountEntity processCaseResultCount = processResultService.getCaseResultCount();

    Double caseSuccessRate = ComputeNumUtil.divideNum(
        LongStream.of(apiCaseResultCount.getApiCaseSuccessCount(), processCaseResultCount
            .getApiCaseProcessSuccessCount()).sum()
        , LongStream.of(apiCaseResultCount.getApiCaseCount(), processCaseResultCount
            .getApiProcessCaseCount()).sum(), 2);

    DailyData dailyData = new DailyData();
    dailyData.setProjectId(projectId);
    dailyData.setDate(LocalDate.now());
    dailyData.setApiCaseNum(apiCaseNum);
    dailyData.setProcessCaseNum(processCaseNum);
    dailyData.setCaseSuccessRate(caseSuccessRate);
    dailyDataRepository.saveOrUpdate(dailyData);
  }
}
