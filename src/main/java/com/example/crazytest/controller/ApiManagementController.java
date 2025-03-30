package com.example.crazytest.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.crazytest.entity.ApiManagement;
import com.example.crazytest.vo.ApiManagementVO;
import com.example.crazytest.entity.req.ApiManagementReq;
import com.example.crazytest.services.ApiManagementService;
import com.example.crazytest.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 接口管理表 前端控制器
 * </p>
 *
 * @author Menghui
 * @since 2025-03-13
 */
@RestController
@Tag(name = "接口管理")
@RequestMapping("/api/management")
public class ApiManagementController {

  //
  @Autowired
  ApiManagementService apiManagementService;
//  @Autowired
//  ApiTestcaseService apiTestcaseService;
//  @Autowired
//  MulTestcaseNodeService mulTestcaseNodeService;
//
//  /**
//   * 不分页查询应用的所有接口
//   * */
//  @PostMapping("/listAllByAppId")
//  public Result listAllByAppId(String appId){
//    if(StringUtils.isBlank(appId)){
//      return null;
//    }
//    List<ApiManage> apiManageList = apiManageService.queryByAppId(Long.parseLong(appId));
//    return Result.suc(apiManageList, apiManageList.size());
//  }
//

  /**
   * 分页查询
   */
  @PostMapping("/list")
  @Operation(summary = "查询所有接口")
  public Result<List<ApiManagementVO>> listAll(@RequestBody ApiManagementReq apiManagementReq) {
    IPage<ApiManagementVO> apiManagementVoPage = apiManagementService.listAll(apiManagementReq);
    return Result.success(apiManagementVoPage.getRecords(), apiManagementVoPage.getTotal());
  }

  @GetMapping("/get/id")
  @Operation(summary = "根据id查询接口")
  public Result<ApiManagement> getById(@RequestParam(required = true) Long id) {
    return Result.success(apiManagementService.getById(id));
  }

  @PostMapping("/save")
  @Operation(summary = "保存接口")
  public Result<Boolean> save(@RequestBody ApiManagement apiManagement) {
    return Result.success(apiManagementService.save(apiManagement));
  }
//
//  // 新增
//  @PostMapping("/add")
//  public Result add(@RequestBody ApiManageAddReq apiManageAddReq){
//    return apiManageService.add(apiManageAddReq);
//  }
//
//  // 修改
//  @PostMapping("/modify")
//  public Result mod(@RequestBody ApiManageModifyReq apiManageModifyReq) {
//    return apiManageService.modifyById(apiManageModifyReq);
//  }
//
//  // 删除
//  @PostMapping("/delete")
//  public Result delete(@RequestParam(required = true) Long id,
//      @RequestParam(required = true)String remark) {
//    apiTestcaseService.validDelApi(id);
//    mulTestcaseNodeService.validDelApi(id);
//    apiManageService.delById(id, remark);
//    return Result.suc("删除成功");
//  }
//
//  /**
//   * 批量删除接口
//   * */
//  @PostMapping("/batchDelete")
//  public Result batchDelete(
//      @RequestParam(required = true) String apiIds,
//      @RequestParam(required = true)String remark) {
//    List<Long> apiIdList = Arrays.stream(apiIds.split(","))
//        .map(Long::parseLong)
//        .collect(Collectors.toList());
//    List<Long> failApiId = new ArrayList<>();
//    if(!apiIdList.isEmpty()){
//      for(Long apiId : apiIdList){
//        try{
//          apiTestcaseService.validDelApi(apiId);
//          mulTestcaseNodeService.validDelApi(apiId);
//          apiManageService.delById(apiId, remark);
//        }catch (Exception e){
//          failApiId.add(apiId);
//        }
//      }
//    }
//    if(!failApiId.isEmpty()){
//      return Result.suc("以下接口存在用例，删除失败，接口ID="+failApiId);
//    }
//    return Result.suc("删除成功");
//  }
//
//  /**
//   * 查询关联用例
//   * */
//  @PostMapping("/queryRelateCase")
//  public Result queryRelateCase(@RequestParam(required = true) Long id) {
//    List<JSONObject> res = new ArrayList<>();
//    List<ApiTestcase> apiCaseList = apiTestcaseService.queryCaseByApiId(id);
//    List<MulTestcaseVo> mulCaseList = mulTestcaseNodeService.queryCaseIdsByApiId(id);
//    if(apiCaseList != null && apiCaseList.size() > 0){
//      for(ApiTestcase apiTestcase : apiCaseList){
//        JSONObject json = new JSONObject();
//        json.put("caseType", "接口用例");
//        json.put("caseId", apiTestcase.getId());
//        json.put("caseName", apiTestcase.getName());
//        res.add(json);
//      }
//    }
//    if(mulCaseList != null && mulCaseList.size() > 0){
//      for(MulTestcaseVo mulTestcaseVo : mulCaseList){
//        JSONObject json = new JSONObject();
//        json.put("caseType", "场景用例");
//        json.put("caseId", mulTestcaseVo.getId());
//        json.put("caseName", mulTestcaseVo.getName());
//        res.add(json);
//      }
//    }
//    return Result.suc(res);
//  }
//
//
//  /**
//   * 通过swagger导入接口
//   * */
//  @PostMapping("/importApi")
//  public Result importApi(
//      @RequestParam(required = true)String url,
//      @RequestParam(required = true)Long applicationId,
//      String appPath) {
//    return Result.suc(apiManageService.importApiBySwagger(url, applicationId, appPath));
//  }
//
//  /**
//   * 通过JSON导入接口【暂不可用】
//   * */
//  @PostMapping("/importApiByJson")
//  public Result importApiByJson( String json, Long applicationId) {
//    apiManageService.importApiByJson(json, applicationId);
//    return Result.suc("导入成功");
//  }
//
//  /**
//   * 通过fetch导入接口
//   * */
//  @PostMapping("/importByFetch")
//  public Result importByFetch(String fetch, Long applicationId, String apiName){
//    apiManageService.importByFetch(fetch, applicationId, apiName);
//    return Result.suc("导入成功");
//  }
//
//  /**
//   * 认领接口
//   * */
//  @PostMapping("/claim")
//  public Result claim(@RequestParam(required = true)String apiIds) {
//    apiManageService.claim(apiIds);
//    return Result.suc("认领成功");
//  }
//
//  /**
//   * 取消认领
//   * */
//  @PostMapping("/cancelClaim")
//  public Result cancelClaim(@RequestParam(required = true)String apiIds) {
//    apiManageService.cancelClaim(apiIds);
//    return Result.suc("已取消认领");
//  }
//
//  /**
//   * 下架接口
//   * */
//  @PostMapping("/disable")
//  public Result disabled(
//      @RequestParam(required = true) String apiIds,
//      @RequestParam(required = true)String remark) {
//    apiManageService.disabled(apiIds, remark);
//    return Result.suc("下架成功");
//  }
//
//  /**
//   * 上架接口
//   * */
//  @PostMapping("/enable")
//  public Result enable(
//      @RequestParam(required = true) String apiIds,
//      @RequestParam(required = true)String remark) {
//    apiManageService.enable(apiIds, remark);
//    return Result.suc("上架成功");
//  }
//
//  /**
//   * 设置接口优先级
//   * */
//  @PostMapping("/setPriority")
//  public Result setPriority(
//      @RequestParam(required = true)String apiIds,
//      @RequestParam(required = true)Integer priority) {
//    apiManageService.setPriority(apiIds, priority);
//    return Result.suc("设置接口优先级成功");
//  }
//
//  /**
//   * 设置可在生产执行
//   * */
//  @PostMapping("/setProdExec")
//  public Result setProdExec(
//      @RequestParam(required = true)String apiIds,
//      @RequestParam(required = true)Integer canProdExec) {
//    apiManageService.setProdExec(apiIds, canProdExec);
//    return Result.suc("设置成功");
//  }
//
//  /**
//   * 移动
//   * */
//  @PostMapping("/move")
//  public Result move(
//      @RequestParam(required = true) String apiIds,
//      @RequestParam(required = true)Long appId){
//    List<Long> ignoreApiIdList = apiManageService.move(apiIds, appId);
//    if(ignoreApiIdList.isEmpty()){
//      return Result.suc("移动成功");
//    }else {
//      return Result.fail("由于目标应用下已存在相同的接口，以下接口移动失败："+ignoreApiIdList);
//    }
//  }
//
//  /**
//   * 复制
//   * */
//  @PostMapping("/copy")
//  public Result copy(
//      @RequestParam(required = true)String apiIds,
//      @RequestParam(required = true)Long appId){
//    List<Long> ignoreApiIdList = apiManageService.copy(apiIds, appId);
//    if(ignoreApiIdList.isEmpty()){
//      return Result.suc("复制成功");
//    }else {
//      return Result.fail("由于目标应用下已存在相同的接口，以下接口复制失败："+ignoreApiIdList);
//    }
//  }
//
//  /**
//   * 更新最近30天调用次数
//   * */
//  @PostMapping("/updateInvokeTimes")
//  public Result updateInvokeTimes() {
//    apiManageService.updateInvokeTimes();
//    return Result.suc("更新成功");
//  }
//
//  /**
//   * 批量修改接口类型
//   * */
//  @PostMapping("/batchModApiType")
//  public Result batchModApiType(
//      @RequestParam(required = true)String apiIds,
//      @RequestParam(required = true)String apiType) {
//    apiManageService.batchModApiType(apiIds, apiType);
//    return Result.suc("设置成功");
//  }

}
