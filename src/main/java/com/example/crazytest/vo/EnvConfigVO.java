package com.example.crazytest.vo;

import com.example.crazytest.entity.EnvConfig;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/3/20 20:50
 * @DESRIPTION
 */

@Data
public class EnvConfigVO extends EnvConfig {

  @ApiModelProperty(value = "应用名")
  private String appName;

  @ApiModelProperty(value = "域名url")
  private String domainUrl;

  @ApiModelProperty(value = "域名name")
  private String domainName;


}
