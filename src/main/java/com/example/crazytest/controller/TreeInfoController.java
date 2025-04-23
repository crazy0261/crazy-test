package com.example.crazytest.controller;


import com.example.crazytest.entity.TreeInfo;
import com.example.crazytest.entity.req.TreeReq;
import com.example.crazytest.services.TreeInfoService;
import com.example.crazytest.utils.Result;
import com.example.crazytest.vo.TreeInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 树结构管理 前端控制器
 * </p>
 *
 * @author Menghui
 * @since 2025-04-05
 */
@RestController
@RequestMapping("/tree")
@Tag(name = "树结构管理")
public class TreeInfoController {

  @Autowired
  TreeInfoService treeInfoService;

  @GetMapping("/project")
  @Operation(summary = "查询当前项目")
  public Result<TreeInfoVo> list() {
    return Result.success(treeInfoService.treeInfoByProjectId());
  }

  @PostMapping("/save")
  @Operation(summary = "更新tree")
  public Result<Boolean> treeSave(@RequestBody TreeInfo treeInfo) {
    return Result.success(treeInfoService.treeSave(treeInfo));
  }

  @PostMapping("/node/add")
  public Result<Void> add(@RequestBody TreeReq treeReq) {
    treeInfoService.saveNode(treeReq);
    return Result.success();
  }

  @PostMapping("/node/update")
  public Result<Void> update(@RequestBody TreeReq treeReq) {
    treeInfoService.updateNode(treeReq);
    return Result.success();
  }

  @PostMapping("/node/delete")
  public Result<Void> delete(@RequestBody TreeReq treeReq) {
    treeInfoService.deleteNode(treeReq);
    return Result.success();
  }

}
