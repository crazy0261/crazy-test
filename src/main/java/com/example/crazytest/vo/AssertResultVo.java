package com.example.crazytest.vo;

import java.util.List;
import lombok.Data;

/**
 * @author
 * @name Menghui
 * @date 2025/3/25 21:25
 * @DESRIPTION
 */

@Data
public class AssertResultVo {
  private Boolean pass;
  private List<String> message;

  public static AssertResultVo fail(){
    AssertResultVo assertResultVo = new AssertResultVo();
    assertResultVo.setPass(false);
    return assertResultVo;
  }

}
