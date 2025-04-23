package com.example.crazytest.utils;

import groovy.lang.GroovyShell;
import java.util.Map;

/**
 * @author
 * @name Menghui
 * @date 2025/4/16 10:47
 * @DESRIPTION Groovy脚本执行工具类
 */

public class GroovyExecUtil {

  private GroovyExecUtil(){}

  public static Object executeGroovy(String script, Map<String, String> envParameter) {
    GroovyShell shell = new GroovyShell();
    String groovyCode = VariablesUtil.replacePlaceholders(script, envParameter);
    return shell.evaluate(groovyCode);
  }
}
